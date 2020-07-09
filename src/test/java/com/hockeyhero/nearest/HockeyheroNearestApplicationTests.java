package com.hockeyhero.nearest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hockeyhero.nearest.rest.HeroKeysController;

import org.junit.Assert;

@SpringBootTest
class HockeyheroNearestApplicationTests {
	
	@Autowired
	HeroKeysController heroKeysController;

	@Test
	void contextLoads() {
		Assert.assertNotNull(heroKeysController);
	}

}
