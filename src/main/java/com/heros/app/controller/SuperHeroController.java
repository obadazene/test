package com.heros.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heros.app.entity.SuperHero;
import com.heros.app.request.SuperHeroRequets;
import com.heros.app.response.SuperHeroResponse;
import com.heros.app.service.SuperHeroService;

@RestController
@RequestMapping("/heros")
public class SuperHeroController {
	
	
	@Autowired
	SuperHeroService heroService;

	@GetMapping("/getAll")
	public List<SuperHero> getAllSuperHeros() {

		return heroService.getAllSuperHeros();
	}

	@GetMapping("/getById")
	public SuperHero getSuperHeroById(@RequestParam long id) {

		return heroService.getSuperHeroById(id);
	}

	@GetMapping("/getBySubString")
	public List<SuperHero> searchByNameLike(@RequestParam String subString) {

		return heroService.searchByNameLike(subString);
	}

	@PostMapping("/create")
	public SuperHeroResponse createSuperHero(@RequestBody SuperHeroRequets superHeroRequets) {
		return heroService.createSuperHero(superHeroRequets);
	}

	@PutMapping("/update")
	public SuperHeroResponse updateSuperHero(@RequestParam long id, @RequestParam String modifiedName) {
		SuperHero superHero = heroService.getSuperHeroById(id);
		if (superHero != null) {
			return heroService.updateSuperHero(id, modifiedName);
		} else {
			return null;
		}

	}

	@DeleteMapping("/delete")
	public void DeleteSuperHero(@RequestParam long id) {
		SuperHero superHero = heroService.getSuperHeroById(id);
		if (superHero != null) {
			heroService.DeleteSuperHero(superHero);
		} else {
			System.out.print("El superHero no existe");
			
		}

	}
}
