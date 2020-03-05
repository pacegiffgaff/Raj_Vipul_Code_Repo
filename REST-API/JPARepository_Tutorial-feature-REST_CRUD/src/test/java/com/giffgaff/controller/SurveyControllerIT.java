package com.giffgaff.controller;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import com.giffgaff.entity.Question;
import com.giffgaff.restapi.RestapiApplication;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

//Part 1: Initialize and launch spring-boot application
	//@RunWith(SpringRunner.class)
	//@SpringBootTest(classes = Application.class, WebEnvironment =SpringBootTest.WebEnvironment.RANDOM_Port)
	//@LocalServerPort

/*
 * Part 2: invoke the url
 * 
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestapiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {
	
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	
	@Before
	public void before() {

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

	}


	/*
	 * ParameterizedTypeReference is used in case of List instead of String.class
	 * */
	@Test
	public void testRetrieveAllQuestions() {

		String url = "http://localhost:" + port + "/surveys/Survey1/questions";
		
		Question sampleQuestion = new Question("Question1",
				"Largest Country in the World", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));

		ResponseEntity<List<Question>> response = restTemplate.exchange(url,
				HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER",
						headers),
				new ParameterizedTypeReference<List<Question>>() {
				});

		
		assertTrue(response.getBody().contains(sampleQuestion));
	}

	/**
	 * test case for RetrieveQuestion
	 * @throws JSONException
	 */
	@Test
	public void testRetrieveQuestion() throws JSONException {
	
		String url = "http://localhost:"+port+"/surveys/Survey1/questions/Question1";
		
		  String output = restTemplate.getForObject(url, String.class);
		  System.out.println("Response from GET : "+output); //XML response only
		 
		/*To get the response in JSON
		 * we will use exchange() instead of getForObject*/
		
		String expected = "{'id':'Question1','description':'Largest Country in the World','correctAnswer':'Russia','options':['India','Russia','United States','China']}";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(url,
				HttpMethod.GET, entity, String.class);
		
		assertTrue(response.getBody().contains("\"id\":\"Question1\""));
		JSONAssert.assertEquals(expected, output, false);

	}

	/**
	 * test case for Add Question
	 */
	@Test
	public void testAddQuestion() {
		String url = "http://localhost:" + port + "/surveys/Survey1/questions";

		Question question = new Question("DOESNTMATTER", "Question1", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));

		HttpEntity entity = new HttpEntity<Question>(question, headers);

		ResponseEntity<String> response = restTemplate.exchange(url,
				HttpMethod.POST, entity, String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/surveys/Survey1/questions/"));
	}

}
