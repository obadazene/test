package com.heros.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.heros.app.entity.SuperHero;

@Repository
public interface SuperHeroDao extends JpaRepository<SuperHero, Long> {
	
	
	@Query("SELECT sp FROM SuperHero sp WHERE sp.name LIKE %:subString%")
	List<SuperHero> searchByNameLike(@Param("subString") String subString);

}
