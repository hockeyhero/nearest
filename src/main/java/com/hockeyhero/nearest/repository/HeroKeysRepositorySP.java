package com.hockeyhero.nearest.repository;

import java.util.List;

import com.hockeyhero.nearest.domain.HeroKeysSearchCriteria;
import com.hockeyhero.nearest.domain.HeroKeysSearchResult;

public interface HeroKeysRepositorySP {
		public List<HeroKeysSearchResult> findNearest(HeroKeysSearchCriteria heroKeysSearchCriteria);
}
