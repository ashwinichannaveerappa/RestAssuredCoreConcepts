package com.rest.api.put;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.tools.ant.types.Mapper;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.post.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PUTApiTest {

	//Create a user with a post call
	//user info
	//update the info put call
	//1.Other attributes should remain same
	//2.The Attributes which has been changed, need to check
	@Test
	public void update_User_PUT_API_Test() {
		//1.  Crate a POST Request with Payload
	User user = new User("UmanghrestTsest","Male","umsh2r332sang@gmail.com","Active");

	//Convert this POJO to JSON -- using JACKSON API -ObjectMapper
	ObjectMapper mapper = new ObjectMapper();
	String userJson=null;
	try {
		userJson=mapper.writeValueAsString(user);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	System.out.println("POST Call JSON is :"+ userJson);
	
	//write Post Call;
	RestAssured.baseURI="https://gorest.co.in";
	
	int userid= given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer e9e19bcf76aad3f3d5f0c477930ac6da35b242926f6b50b0d805f1b97d14bdd4")
		.body(userJson)
	.when().log().all()
		.post("/public-api/users")
	.then().log().all()
		.assertThat()
			.contentType(ContentType.JSON)
			.statusCode(200)
			.extract().path("data.id");
	System.out.println("user ud is ::>"+userid);
	//Call the PUT API:
	//Using Setters update the data
	//inside body we need to pass json only soo using ObjectMapper 

	//Convert this POJO to JSON -- using JACK;
	user.setEmail("umangysd.sharma@gmail.com");
	user.setName("Umaddnssg Sharmz");
	String updatedUserJson=null;
	try {
		updatedUserJson=mapper.writeValueAsString(user);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	System.out.println("POST Call JSON is :"+ userJson);
	given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer e9e19bcf76aad3f3d5f0c477930ac6da35b242926f6b50b0d805f1b97d14bdd4")
		.body(updatedUserJson).
	when()
		.put("public-api/users/"+userid).
	then()
		.assertThat()
			.contentType(ContentType.JSON)
			.and()
				.body("data.email", equalTo(user.getEmail()))
			.and()
				.body("data.id", equalTo(userid))
			.and()
				.body("data.name", equalTo(user.getName()));
	
	//Get: Call to valdiate the updated info 
	//sequence
	//POST --get
	//POST -->Put--Get
	
	given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer e9e19bcf76aad3f3d5f0c477930ac6da35b242926f6b50b0d805f1b97d14bdd4")
	.when()
	.get("public-api/users/"+userid)
		.then()
			.assertThat()
				.contentType(ContentType.JSON)
				.statusCode(200)
				.body("data.name",equalTo(user.getName()))
				.body("data.id",equalTo(userid));

	}	
}
