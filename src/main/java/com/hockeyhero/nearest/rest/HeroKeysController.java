package com.hockeyhero.nearest.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hockeyhero.nearest.domain.HeroKeys;
import com.hockeyhero.nearest.domain.HeroKeysSearchCriteria;
import com.hockeyhero.nearest.domain.HeroKeysSearchResult;
import com.hockeyhero.nearest.repository.HeroKeysRepositorySPImpl;
import com.hockeyhero.nearest.service.HeroKeysService;

@RestController
public class HeroKeysController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeroKeysRepositorySPImpl.class);

	
	@Autowired
	HeroKeysService heroKeysService;

	public HeroKeysController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping("/herokeys")
	HeroKeys create(@RequestBody HeroKeys heroKeys) {
		return heroKeysService.save(heroKeys);
	}
	
	@GetMapping("/herokeys")
	Iterable<HeroKeys> read() {
		return heroKeysService.findAll();
	}
	
	@PutMapping("/herokeys")
	HeroKeys update(@RequestBody HeroKeys heroKeys) {
		return heroKeysService.save(heroKeys);
	}
	
	@DeleteMapping("/herokeys/{id}")
	void delete(@PathVariable Long id) {
		heroKeysService.deleteById(id);
	}
	
	@GetMapping("/herokeys/search")
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
