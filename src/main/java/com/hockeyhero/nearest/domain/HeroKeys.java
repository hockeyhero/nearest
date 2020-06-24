package com.hockeyhero.nearest.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
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
    @Column(name="id", columnDefinition="BIGINT", unique=true)
    private Long id; 

    @Column(name = "hideme", columnDefinition="TINYINT")
    private Boolean hideMe;

    @Column(name = "latitude", columnDefinition="DOUBLE", nullable = false)
    private Double latitude;

    @Column(name = "longitude", columnDefinition="DOUBLE", nullable = false)
    private Double longitude;

    @Column(name = "myposition", columnDefinition="TINYINT", nullable = false)
    private Integer myposition;

    @Column(name = "age", columnDefinition="TINYINT", nullable = false)
    private Integer age;

    @Column(name = "skill", columnDefinition="TINYINT",nullable = false)
    private Integer skill;

	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	} 
}