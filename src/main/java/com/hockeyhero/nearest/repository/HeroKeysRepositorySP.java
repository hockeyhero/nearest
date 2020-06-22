package com.hockeyhero.nearest.repository;

import java.util.List;

import com.hockeyhero.nearest.domain.HeroKeysSearchCriteria;
import com.hockeyhero.nearest.domain.HeroKeysSearchResult;

public interface HeroKeysRepositorySP {
		public List<HeroKeysSearchResult> findNearest(HeroKeysSearchCriteria heroKeysSearchCriteria);
}


/* https://stackoverflow.com/questions/11880924/how-to-add-custom-method-to-spring-data-jpa */