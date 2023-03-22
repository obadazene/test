package com.heros.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.heros.app.entity.SuperHero;
import com.heros.app.repository.SuperHeroDao;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class SuperHeroServiceImplTest {

	@Autowired
	SuperHeroDao dao;
	
	@Test
	void getAllSuperHeros() {
		System.out.print("");
		SuperHero[] superHeros = 
		{ new SuperHero(1, "Hulk"), new SuperHero(2, "Superman"),
		  new SuperHero(3, "Batman"),new SuperHero(4, "Ironman") 
		};
		List<SuperHero> superHerosList = new ArrayList<>(Arrays.asList(superHeros));
		List<SuperHero> jpaList = dao.findAll();
		assertNotNull(jpaList);
		assertThat(jpaList.toArray()).usingRecursiveComparison()
		.isEqualTo(superHerosList.toArray());
	}
	
	@Test
	void getSuperHeroById() {
		SuperHero superHero = new SuperHero(1, "Hulk");
		Optional<SuperHero> optional = dao.findById(1L);
		if (!optional.isPresent()) {
			System.out.println("El super hero no existe");
		} else {
			assertThat(optional.get().getId()).isGreaterThan(0);
			assertThat(optional.get()).usingRecursiveComparison()
			.isEqualTo(superHero);
		}

	}

	
	 
	    
		@Test
		void searchByNameLike() {
		SuperHero[] superHeros = { 
		new SuperHero(2, "Superman"), new SuperHero(3, "Batman"),
		new SuperHero(4, "Ironman") 
		};
		List<SuperHero> superHerosList = new ArrayList<>(Arrays.asList(superHeros));
		List<SuperHero> jpaList = dao.searchByNameLike("man");
		assertNotNull(jpaList);
		assertThat(jpaList.toArray()).usingRecursiveComparison()
		.isEqualTo(superHerosList.toArray());

		}
	 
	
	@Test
	void createSuperHero() {
		//Compare the size of the list before the insert
		List<SuperHero> jpaListBefore = dao.findAll();
		assertThat(jpaListBefore.size()).isEqualTo(4);
		SuperHero superHero = new SuperHero(5, "CatWomen");
		SuperHero superHero2 = new SuperHero(6, "GreenLantern");
		dao.save(superHero);
		dao.save(superHero2);
		//Compare the size of the list after the insert 
		List<SuperHero> jpaList = dao.findAll();
		assertThat(jpaList.size()).isEqualTo(6);
		
	}
	
	@Test
	void updateSuperHero() {
		//Before the update
		assertEquals(dao.findById(1L).get().getName(),"Hulk");
		assertEquals(dao.findById(2L).get().getName(),"Superman");
		SuperHero superHero = new SuperHero();
		superHero.setName("GreenLantern");
		superHero.setId(1);
		
		SuperHero superHero2 = new SuperHero();
		superHero2.setName("CatWomen");
		superHero2.setId(2);
		dao.save(superHero);
		dao.save(superHero2);
		//After the update
		assertEquals(dao.findById(1L).get().getName(),"GreenLantern");
		assertEquals(dao.findById(2L).get().getName(),"CatWomen");
	}

	
	@Test
	public void DeleteSuperHero() {
		//Compare the size of the list before the delete
		List<SuperHero> jpaListBefore = dao.findAll();
		assertThat(jpaListBefore.size()).isEqualTo(4);
		
		Optional<SuperHero> superHero =dao.findById(1L);
		Optional<SuperHero> superHero2 =dao.findById(2L);
		if(superHero.isPresent()) {
			dao.delete(superHero.get());
		}
		
		if(superHero2.isPresent()) {
			dao.delete(superHero2.get());
		}
		//Compare the content of the optional is empty for the deleted superheros
		assertSame(Optional.empty(), dao.findById(1L));
		assertSame(Optional.empty(), dao.findById(2L));
		//Compare the size of the list after the delete
		List<SuperHero> jpaListAfter = dao.findAll();
		assertThat(jpaListAfter.size()).isEqualTo(2);
	}

}
