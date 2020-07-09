package com.hockeyhero.nearest.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hockeyhero.nearest.domain.HeroKeys;
import com.hockeyhero.nearest.domain.HeroKeysSearchCriteria;
import com.hockeyhero.nearest.domain.HeroKeysSearchResult;
import com.hockeyhero.nearest.exception.HeroKeysException;
import com.hockeyhero.nearest.service.HeroKeysService;

@RestController
@RequestMapping("/herokeys")
public class HeroKeysController {

	
	@Autowired
	HeroKeysService heroKeysService;

	public HeroKeysController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping
	HeroKeys createHeroKeys(@Valid @RequestBody HeroKeys heroKeys) throws HeroKeysException {
		return heroKeysService.createHeroKeys(heroKeys);
	}
	
	@GetMapping
	Iterable<HeroKeys> findAllHeroKeys() {
		return heroKeysService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	HeroKeys findHeroKeys(@PathVariable Long id) throws HeroKeysException {
		return heroKeysService.findById(id);		
	}
	
	@PutMapping
	HeroKeys updateHeroKeys(@Valid @RequestBody HeroKeys heroKeys) throws HeroKeysException {
			return heroKeysService.updateHeroKeys(heroKeys); 
	}
	
	@DeleteMapping(value = "/{id}")
	void deleteHeroKeys(@PathVariable Long id) throws HeroKeysException {
		heroKeysService.deleteById(id);
	}
	
	@GetMapping("/search")
	Iterable<HeroKeysSearchResult> search(
			@RequestParam(required=true) String latitude,
			@RequestParam(required=true) String longitude,
			@RequestParam(required=true) int radius,
			@RequestParam(required=false, defaultValue = "15")  int position,
			@RequestParam(required=false, defaultValue = "0")   int lowAge,
			@RequestParam(required=false, defaultValue = "100") int highAge,
			@RequestParam(required=false, defaultValue = "0")   int lowSkill,
			@RequestParam(required=false, defaultValue = "10")  int highSkill)
	{
		HeroKeysSearchCriteria heroKeysSearchCriteria = new HeroKeysSearchCriteria(latitude, longitude, radius, position, lowAge, highAge, lowSkill, highSkill); 
		return heroKeysService.findNearestHeroes(heroKeysSearchCriteria);
	}
}
