package com.heros.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heros.app.entity.SuperHero;
import com.heros.app.repository.SuperHeroDao;
import com.heros.app.request.SuperHeroRequets;
import com.heros.app.response.SuperHeroResponse;

@Service
public class SuperHeroServiceImpl implements SuperHeroService {
	@Autowired
	SuperHeroDao dao;

	public List<SuperHero> getAllSuperHeros() {
		
		return dao.findAll();
	}

	public SuperHero getSuperHeroById(long id) {
	
		Optional<SuperHero> optional = dao.findById(id);
		if (!optional.isPresent()) {
			return null;
		} else {
			return optional.get();
		}

	}

	public List<SuperHero> searchByNameLike(String subString) {
	
		return dao.searchByNameLike(subString);
	}

	public SuperHeroResponse createSuperHero(SuperHeroRequets superHeroRequets) {
		SuperHero superHero = new SuperHero();
		superHero.setName(superHeroRequets.getName());
		dao.save(superHero);
		return new SuperHeroResponse(superHero);
	}

	public SuperHeroResponse updateSuperHero(long id, String modifiedName) {
	
		SuperHero superHero = new SuperHero();
		superHero.setName(modifiedName);
		superHero.setId(id);
		dao.save(superHero);
		return new SuperHeroResponse(superHero);
	}

	public void DeleteSuperHero(SuperHero superHero) {
		dao.delete(superHero);
		
	}

}
