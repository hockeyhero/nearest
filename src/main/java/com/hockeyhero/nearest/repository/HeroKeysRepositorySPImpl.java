package com.hockeyhero.nearest.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hockeyhero.nearest.domain.HeroKeysSearchCriteria;
import com.hockeyhero.nearest.domain.HeroKeysSearchResult;

public class HeroKeysRepositorySPImpl implements HeroKeysRepositorySP {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(HeroKeysRepositorySPImpl.class);

	
    @PersistenceContext
    private EntityManager em;
    
	public HeroKeysRepositorySPImpl() {
		super();
	}    

    @Override
    public List<HeroKeysSearchResult> findNearest(HeroKeysSearchCriteria heroKeysSearchCriteria) {
    	LOGGER.info(heroKeysSearchCriteria.toString());
        StoredProcedureQuery findNearestHero = em.createNamedStoredProcedureQuery("sp_NearestHero");
        
        findNearestHero.setParameter("FVLATPOINT", heroKeysSearchCriteria.getLatitude().floatValue());
        findNearestHero.setParameter("FVLONGPOINT", heroKeysSearchCriteria.getLongitude().floatValue());
        findNearestHero.setParameter("FVRADIUS", heroKeysSearchCriteria.getRadius());
        findNearestHero.setParameter("FVPOSITION", heroKeysSearchCriteria.getPosition());            	
        findNearestHero.setParameter("FVLOWAGE", heroKeysSearchCriteria.getLowAge());
        findNearestHero.setParameter("FVHIGHAGE", heroKeysSearchCriteria.getHighAge());
        findNearestHero.setParameter("FVLOWSKILL", heroKeysSearchCriteria.getLowSkill());
        findNearestHero.setParameter("FVHIGHSKILL", heroKeysSearchCriteria.getHighSkill());

        List<HeroKeysSearchResult> results = null; 
        if (findNearestHero.execute())
        {
        	results = (List<HeroKeysSearchResult>)findNearestHero.getResultList();
        }
        
        return results;
    }
}
