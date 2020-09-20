package com.rest.api.post;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POSTAPIWithPOJO {

	
	//create a user
	//POST -url
	
	@Test
	public void createUser_With_Pojo_Test() {
		
		//create payload using User.java
		User user = new User("nishasee", "Female", "nsishasharssma@gmail.com", "Active");
		
		//convert pojo to json -- we need to use third api rest assured does not have any inbuilt 
		//This concept called serializaton --using JACKSON API
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = null;
		try {
			userJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println(userJson);
		
		
		RestAssured.baseURI="https://gorest.co.in";
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer e9e19bcf76aad3f3d5f0c477930ac6da35b242926f6b50b0d805f1b97d14bdd4")
			.body(userJson)
		.when().log().all()
			.post("/public-api/users")
		.then().log().all()
			.assertThat()
				.contentType(ContentType.JSON)
				.statusCode(200)
			.and()
				.body("data.name",equalTo(user.getName()))
				.body("data.email",equalTo(user.getEmail()))
				.body("data.status",equalTo(user.getStatus()));
				
	}
	
	//POST --get
	//POST -->Put--Get
}
