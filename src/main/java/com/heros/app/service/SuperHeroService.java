package com.heros.app.service;

import java.util.List;

import com.heros.app.entity.SuperHero;
import com.heros.app.request.SuperHeroRequets;
import com.heros.app.response.SuperHeroResponse;

public interface SuperHeroService {
	
	public List<SuperHero> getAllSuperHeros();

	public SuperHero getSuperHeroById(long id);

	public List<SuperHero> searchByNameLike(String subString);

	public SuperHeroResponse createSuperHero(SuperHeroRequets superHeroRequets);

	public SuperHeroResponse updateSuperHero(long id, String modifiedName);

	public void DeleteSuperHero(SuperHero superHero);

}
