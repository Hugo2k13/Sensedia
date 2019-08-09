package com.beerhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.beerhouse.model.Beers;
import com.beerhouse.repository.BeersRepository;

@RestController
public class BeersController {

	@Autowired
	BeersRepository repository;
	
	//retrieve all beers
	@RequestMapping(value = "/beer/", method = RequestMethod.GET)
	public ResponseEntity<List<Beers>> findAllBeers() {
		List<Beers> beers = (List<Beers>) this.repository.findAll();
		if(beers.isEmpty()){
			return new ResponseEntity<List<Beers>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Beers>>(beers, HttpStatus.OK);
	}
	
	//retrieve a single beer
	@RequestMapping(value = "/beer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Beers> getBeerById(@PathVariable Long id) {
		System.out.println("Fetching beer id " + id);
		Beers beer = null;
		try{
			beer = this.repository.findById(id).orElse(null);
	    }
	    catch(Exception ex){
			System.out.println("Something went wrong" + ex);
	    }
		if (beer == null) {
			System.out.println("beer id " + id + " was not found");
			return new ResponseEntity<Beers>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Beers>(beer, HttpStatus.OK);
	}

	//Create a beer
	@RequestMapping(value = "/beer/", method = RequestMethod.POST)
	public ResponseEntity<Void> createBeer(@RequestBody Beers newBeer, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating beer name: " + newBeer.getNome());
		List<Beers> beer = this.repository.findByNome(newBeer.getNome());
		System.out.println("size " + beer.size());
		if (!beer.isEmpty()) {
			System.out.println("já existe " + newBeer.getNome() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		repository.save(newBeer);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/beer/{id}").buildAndExpand(newBeer.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	//Update a beer
	@RequestMapping(value = "/beer/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Beers> updateBeer1(@PathVariable("id") Long id, @RequestBody Beers newBeer) {
		System.out.println("Updating beer id: " + id);
		
		Beers beer = this.repository.findById(id).orElse(null);
		
		if (beer == null) {
			System.out.println("Beer with id " + id + " was not found");
			return new ResponseEntity<Beers>(HttpStatus.NOT_FOUND);
		}
		beer.setNome(newBeer.getNome());
		beer.setPreco(newBeer.getPreco());
		beer.setAlcoolica(newBeer.getNome());
		beer.setCategoria(newBeer.getCategoria());
		beer.setIngredientes(newBeer.getIngredientes());

		repository.save(beer);
		return new ResponseEntity<Beers>(beer, HttpStatus.OK);
	}
	
	//Partial update 
	@RequestMapping(value = "/beer/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Beers> updateBeer2(@PathVariable("id") Long id, @RequestBody Beers newBeer) {
		System.out.println("Updating beer id: " + id);
		
		Beers beer = this.repository.findById(id).orElse(null);
		
		if (beer == null) {
			System.out.println("Beer with id " + id + " was not found");
			return new ResponseEntity<Beers>(HttpStatus.NOT_FOUND);
		}
		beer.setNome(newBeer.getNome());

		repository.save(beer);
		return new ResponseEntity<Beers>(beer, HttpStatus.OK);
	}
	
	//Delete a beer
	@RequestMapping(value = "/beer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Beers> deleteBeer(@PathVariable("id") long id) {
		System.out.println("Fetching and deleting beer with id " + id);
		Beers beer = new Beers();;
		try{
			beer = this.repository.findById(id).orElse(null);
	    }
	    catch(Exception ex){
			System.out.println("Something went wrong" + ex);
	    }
		if (beer == null) {
			System.out.println("Unable to delete this beer! Id:  " + id + " was not found");
			return new ResponseEntity<Beers>(HttpStatus.NOT_FOUND);
		}

		repository.delete(beer);
		return new ResponseEntity<Beers>(HttpStatus.NO_CONTENT);
	}
	
	//Delete all beers
	@RequestMapping(value = "/beer/", method = RequestMethod.DELETE)
	public ResponseEntity<Beers> deleteAllUsers() {
		System.out.println("Deleting Every Single Beer");

		repository.deleteAll();
		return new ResponseEntity<Beers>(HttpStatus.NO_CONTENT);
	}
	
}
