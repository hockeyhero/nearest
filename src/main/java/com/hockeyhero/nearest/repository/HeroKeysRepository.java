package com.hockeyhero.nearest.repository;

import com.hockeyhero.nearest.domain.HeroKeys;
import com.hockeyhero.nearest.domain.HeroKeysSearchCriteria;
import com.hockeyhero.nearest.domain.HeroKeysSearchResult;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Spring Data  repository for the HeroKeys entity.
 */
@SuppressWarnings("unused")
@Repository
@Table
public interface HeroKeysRepository extends JpaRepository<HeroKeys, Long>, HeroKeysRepositorySP {
}

