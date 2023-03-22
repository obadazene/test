package com.heros.app.response;

import com.heros.app.entity.SuperHero;

public class SuperHeroResponse {

	private long id;
	private String name;

	public long getId() {
		return id;
	}
	
	
	public SuperHeroResponse(SuperHero superHero) {
		super();
		this.id = superHero.getId();
		this.name = superHero.getName();
	}


	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
