package com.beerhouse;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.beerhouse.model.Beers;
import com.fasterxml.jackson.core.JsonProcessingException;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ApplicationTests {
	
	RestTemplate restTemplate = new RestTemplate();
	
	private Beers beer;
	
	@LocalServerPort
	int randomServerPort;
	
 
	@Test
	public void getAllBeersTest() throws URISyntaxException{
		final String baseUrl = "http://localhost:" + randomServerPort + "/beer/";

		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	    
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
	
}
