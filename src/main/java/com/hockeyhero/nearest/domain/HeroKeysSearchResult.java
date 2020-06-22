package com.hockeyhero.nearest.domain;

import java.math.BigInteger;
import lombok.Data;

@Data
public class HeroKeysSearchResult {

    private Long hero_id;
    private Float distance; 
	
	public HeroKeysSearchResult (BigInteger heroId, Double distance) {
		this.hero_id = heroId.longValue(); 
		this.distance = distance.floatValue(); 
	}

	@Override
	public String toString() {
		return "HeroKeysSearchResult [distance=" + distance + ", hero_id=" + hero_id + "]";
	}	
}
