package com.hockeyhero.nearest.domain;

import java.math.BigInteger;
import lombok.Data;

@Data
public class HeroKeysSearchResult {

    private Long id;
    private Float distance; 
	
	public HeroKeysSearchResult (BigInteger id, Double distance) {
		this.id = id.longValue(); 
		this.distance = distance.floatValue(); 
	}

	@Override
	public String toString() {
		return "HeroKeysSearchResult [distance=" + distance + ", id=" + id + "]";
	}	
}
