package com.hockeyhero.nearest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hockeyhero.nearest.domain.HeroKeys;
import com.hockeyhero.nearest.domain.HeroKeysSearchCriteria;
import com.hockeyhero.nearest.domain.HeroKeysSearchResult;
import com.hockeyhero.nearest.repository.HeroKeysRepository;
import com.hockeyhero.nearest.repository.HeroKeysRepositorySPImpl;

@Service
public class HeroKeysService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeroKeysService.class);

    @Autowired
    private HeroKeysRepository		heroKeysRepository;
    
	public HeroKeysService() {
	}
	
	@Transactional
	public HeroKeys save(HeroKeys heroKeys) {
		return heroKeysRepository.save(heroKeys);
	}
	
	@Transactional
	public Iterable<HeroKeys> findAll() {
		return heroKeysRepository.findAll();
	}
	
	@Transactional
	public HeroKeys update(HeroKeys heroKeys) {
		return heroKeysRepository.save(heroKeys);
	}

	@Transactional
	public void deleteById(Long id) {
		heroKeysRepository.deleteById(id);
	}	
	
	@Transactional
	public List<HeroKeysSearchResult> findNearestHeroes(HeroKeysSearchCriteria heroKeysSearchCriteria) {
		return heroKeysRepository.findNearest(heroKeysSearchCriteria);
	}
}
