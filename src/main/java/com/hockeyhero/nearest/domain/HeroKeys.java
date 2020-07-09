package com.hockeyhero.nearest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hero_keys", 
	indexes = {
			@Index(name="myposition_idx",columnList = "myposition"),
			@Index(name="age_idx",columnList = "age"),
			@Index(name="skill_idx",columnList = "skill")
	}
)
@SqlResultSetMapping(name = "HeroKeysSearchResult", 
classes = @ConstructorResult(
		targetClass = HeroKeysSearchResult.class, 
		columns = {
				@ColumnResult(name = "id"),
				@ColumnResult(name = "distance")
		}
	)
)
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name = "sp_NearestHero", 
		procedureName = "sp_NearestHero", 
		resultSetMappings  = "HeroKeysSearchResult", 
		parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FVLATPOINT",  type = Float.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FVLONGPOINT", type = Float.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FVRADIUS",    type = Integer.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FVPOSITION",  type = Integer.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FVLOWAGE",    type = Integer.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FVHIGHAGE",   type = Integer.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FVLOWSKILL",  type = Integer.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FVHIGHSKILL", type = Integer.class), 
		}
	)
})
public class HeroKeys implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Positive
    @Column(name="id", columnDefinition="BIGINT", unique=true)
    private Long id; 

    @Column(name = "hideme", columnDefinition="TINYINT")
    private Boolean hideMe;

    @Min(-90) 
    @Max(90)
    @Column(name = "latitude", columnDefinition="DOUBLE", nullable = false)
    private Double latitude;

    @Min(-180) @Max(180)
    @Column(name = "longitude", columnDefinition="DOUBLE", nullable = false)
    private Double longitude;

    @Min(0) @Max(15)
    @Column(name = "myposition", columnDefinition="TINYINT", nullable = false)
    private Integer myposition;

    @Positive
    @Column(name = "age", columnDefinition="TINYINT", nullable = false)
    private Integer age;

    @Min(0) @Max(10)
    @Column(name = "skill", columnDefinition="TINYINT",nullable = false)
    private Integer skill;

	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public HeroKeys(@Positive Long id, Boolean hideMe, @Min(-90) @Max(90) Double latitude,
			@Min(-180) @Max(180) Double longitude, @Min(0) @Max(15) Integer myposition, @Positive Integer age,
			@Min(0) @Max(10) Integer skill) {
		super();
		this.id = id;
		this.hideMe = hideMe;
		this.latitude = latitude;
		this.longitude = longitude;
		this.myposition = myposition;
		this.age = age;
		this.skill = skill;
	} 
	
}