package com.hockeyhero.nearest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hockeyhero.nearest.domain.HeroKeys;
import com.hockeyhero.nearest.domain.HeroKeysSearchCriteria;
import com.hockeyhero.nearest.domain.HeroKeysSearchResult;
import com.hockeyhero.nearest.exception.HeroKeysException;
import com.hockeyhero.nearest.repository.HeroKeysRepository;
import com.hockeyhero.nearest.repository.HeroKeysRepositorySPImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HeroKeysService {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(HeroKeysService.class);
    
    @Autowired
    private HeroKeysRepository		heroKeysRepository;
    
	public HeroKeysService() {
	}
	
	@Transactional
	public HeroKeys createHeroKeys(HeroKeys heroKeys) throws HeroKeysException {
		if (heroKeysRepository.findById(heroKeys.getid()).isPresent()) {
			String errorMessage = "Entity already exists: " + heroKeys.getid().toString();
			throw new HeroKeysException(errorMessage); 
		}
		return heroKeysRepository.save(heroKeys);
	}
	
	@Transactional
	public Iterable<HeroKeys> findAll() {
		return heroKeysRepository.findAll();
	}
	
	@Transactional
	public HeroKeys updateHeroKeys(HeroKeys heroKeys) throws HeroKeysException {
		if (heroKeysRepository.findById(heroKeys.getid()).isPresent()) {
			return heroKeysRepository.save(heroKeys);
		}
		String errorMessage = "Entity does not exist: " + heroKeys.getid().toString();
		throw new HeroKeysException(errorMessage); 		
	}

	@Transactional
	public Long deleteById(Long id) throws HeroKeysException {
		if (heroKeysRepository.findById(id).isPresent()) {
			heroKeysRepository.deleteById(id);
			return id; 
		}
		String errorMessage = "Entity does not exist: " + id.toString();
		throw new HeroKeysException(errorMessage); 
	}	
	
	@Transactional
	public List<HeroKeysSearchResult> findNearestHeroes(HeroKeysSearchCriteria heroKeysSearchCriteria) {
		return heroKeysRepository.findNearest(heroKeysSearchCriteria);
	}
	
	@Transactional 
	public HeroKeys findById(Long heroId) throws HeroKeysException {
		Optional<HeroKeys> heroKeys = heroKeysRepository.findById(heroId);
		if (heroKeys.isPresent()) {
			return heroKeys.get(); 
		}
		String errorMessage = "Entity does not exist: " + heroId.toString();
		throw new HeroKeysException(errorMessage); 		
	}
}
