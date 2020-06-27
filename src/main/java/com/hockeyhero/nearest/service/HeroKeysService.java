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
import com.hockeyhero.nearest.exception.HeroExistsException;
import com.hockeyhero.nearest.repository.HeroKeysRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HeroKeysService {
    @Autowired
    private HeroKeysRepository		heroKeysRepository;
    
	public HeroKeysService() {
	}
	
	@Transactional
	public HeroKeys createHeroKeys(HeroKeys heroKeys) throws HeroExistsException {
		if (heroKeysRepository.findById(heroKeys.getid()).isPresent()) {
			String errorMessage = "Entity already exists: " + heroKeys.getid().toString();
			throw new HeroExistsException(errorMessage); 
		}
		return heroKeysRepository.save(heroKeys);
	}
	
	@Transactional
	public Iterable<HeroKeys> findAll() {
		return heroKeysRepository.findAll();
	}
	
	@Transactional
	public HeroKeys updateHeroKeys(HeroKeys heroKeys) throws HeroExistsException {
		if (heroKeysRepository.findById(heroKeys.getid()).isPresent()) {
			return heroKeysRepository.save(heroKeys);
		}
		String errorMessage = "Entity does not exist: " + heroKeys.getid().toString();
		throw new HeroExistsException(errorMessage); 		
	}

	@Transactional
	public void deleteById(Long id) throws HeroExistsException {
		if (heroKeysRepository.findById(id).isPresent()) {
			heroKeysRepository.deleteById(id);
		}
		String errorMessage = "Entity does not exist: " + id.toString();
		throw new HeroExistsException(errorMessage); 
	}	
	
	@Transactional
	public List<HeroKeysSearchResult> findNearestHeroes(HeroKeysSearchCriteria heroKeysSearchCriteria) {
		return heroKeysRepository.findNearest(heroKeysSearchCriteria);
	}
	
	@Transactional 
	public Optional<HeroKeys> findById(Long heroId) {
		return heroKeysRepository.findById(heroId); 
	}
}
