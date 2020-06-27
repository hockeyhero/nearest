package com.hockeyhero.nearest.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
public class HeroKeysSearchCriteria {
	
	private Double latitude; 
	
	private Double longitude; 
	
	private Integer radius; 
	
	private Integer position; 
	
	private Integer lowAge; 
	
	private Integer highAge;
	
	private Integer lowSkill; 
	
	private Integer highSkill; 

	public HeroKeysSearchCriteria() {
		// TODO Auto-generated constructor stub
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getLowAge() {
		return lowAge;
	}

	public void setLowAge(Integer lowAge) {
		this.lowAge = lowAge;
	}

	public Integer getHighAge() {
		return highAge;
	}

	public void setHighAge(Integer highAge) {
		this.highAge = highAge;
	}

	public Integer getLowSkill() {
		return lowSkill;
	}

	public void setLowSkill(Integer lowSkill) {
		this.lowSkill = lowSkill;
	}

	public Integer getHighSkill() {
		return highSkill;
	}

	public void setHighSkill(Integer highSkill) {
		this.highSkill = highSkill;
	}

	public HeroKeysSearchCriteria(Double latitude, Double longitude, Integer radius, Integer position, Integer lowAge,
			Integer highAge, Integer lowSkill, Integer highSkill) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		this.position = position;
		this.lowAge = lowAge;
		this.highAge = highAge;
		this.lowSkill = lowSkill;
		this.highSkill = highSkill;
	}
	
	public HeroKeysSearchCriteria(String latitude, String longitude, int radius, int position, int lowAge,
			int highAge, int lowSkill, int highSkill) {
		super();
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);
		this.radius = radius;
		this.position = position;
		this.lowAge = lowAge;
		this.highAge = highAge;
		this.lowSkill = lowSkill;
		this.highSkill = highSkill;
	}

	@Override
	public String toString() {
		return "HeroKeysSearchCriteria [latitude=" + latitude + ", longitude=" + longitude + ", radius=" + radius
				+ ", position=" + position + ", lowAge=" + lowAge + ", highAge=" + highAge + ", lowSkill=" + lowSkill
				+ ", highSkill=" + highSkill + "]";
	}
	
	
}
