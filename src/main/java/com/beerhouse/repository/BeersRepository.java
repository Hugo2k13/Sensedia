package com.beerhouse.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.beerhouse.model.Beers;

public interface BeersRepository extends CrudRepository<Beers, Long> {

	List<Beers> findByNome(String nome);
	
}
