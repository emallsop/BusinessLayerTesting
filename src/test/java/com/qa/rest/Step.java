package com.qa.rest;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.junit.Assert.*;

import org.json.JSONObject;


public class Step {
	
	JSONObject j = null;
	RequestSpecification request = null;
	Response response = null;
	
	@Given("^a film exists with the Title Guardians of the Galaxy Two$")
	public void a_film_exists_with_the_Title_Guardians_of_the_Galaxy_Two() throws Throwable {
		
		request = RestAssured.given().contentType(ContentType.JSON);
		response = request.get("http://www.omdbapi.com/?apikey=e35fad3&t=Guardians of the Galaxy vol.2"); 
		j = new JSONObject(response.asString());
		
		assertEquals("True", j.getString("Response"));
	
	}

	@When("^a user retrieves the film by the title Guardians of the Galaxy Two$")
	public void a_user_retrieves_the_film_by_the_title_Guardians_of_the_Galaxy_Two() throws Throwable {

		response.prettyPrint(); 
		j = new JSONObject(response.asString());
		
		assertEquals("Guardians of the Galaxy Vol. 2", j.getString("Title"));
		
		//old method using string
		/*boolean moviePresent = response.asString().contains("Guardians of the Galaxy Vol. 2");
		assertEquals("Movie present", true, moviePresent); */
		
	}

	@Then("^the status code reads (\\d+)$")
	public void the_status_code_reads(int arg1) throws Throwable {
	  
		System.out.println("Status code = " + response.getStatusCode()); 
		assertEquals("Status code correct", arg1, response.getStatusCode());

	}

	@Given("^a film exists with the Title IT$")
	public void a_film_exists_with_the_Title_IT() throws Throwable {
		
		request = RestAssured.given().contentType(ContentType.JSON);
		response = request.get("http://www.omdbapi.com/?apikey=e35fad3&t=IT"); 
		j = new JSONObject(response.asString());
		
		assertEquals("True", j.getString("Response"));
	    
	}

	@When("^a user retrieves the film by the title IT$")
	public void a_user_retrieves_the_film_by_the_title_IT() throws Throwable {
		
		response.prettyPrint(); 		
		j = new JSONObject(response.asString());
		
		assertEquals("It", j.getString("Title"));
		
		//old method using string
		/*boolean moviePresent = response.asString().contains("It");	
		assertEquals("Movie present", true, moviePresent);*/

	}

	@Then("^the Rated Field is equal to R$")
	public void the_Rated_Field_is_equal_to_R() throws Throwable {
		
		j = new JSONObject(response.asString());
		
		assertEquals("R", j.getString("Rated"));

	}

	@Given("^a film exists with the Title \"([^\"]*)\"$")
	public void a_film_exists_with_the_Title(String arg1) throws Throwable {
		request = RestAssured.given().contentType(ContentType.JSON);
		response = request.get("http://www.omdbapi.com/?apikey=e35fad3&t=" + arg1); 
		j = new JSONObject(response.asString());
		
		assertEquals("True", j.getString("Response"));
	}

	@When("^a user retrieves the film by the title \"([^\"]*)\"$")
	public void a_user_retrieves_the_film_by_the_title(String arg1) throws Throwable {
		response.prettyPrint(); 
		j = new JSONObject(response.asString());
		
		assertTrue(j.getString("Title").equalsIgnoreCase(arg1));
	}

	@Then("^the Rated Field is equal to \"([^\"]*)\"$")
	public void the_Rated_Field_is_equal_to(String arg1) throws Throwable {
		j = new JSONObject(response.asString());
		
		assertEquals(arg1, j.getString("Rated"));
	}

}
