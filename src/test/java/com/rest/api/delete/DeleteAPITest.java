package com.rest.api.delete;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.post.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteAPITest {

	@Test
	public void deleteAPITest() {
		
		//Post--delete --get --sequence for validation
		User user = new User("deletdeddTdssredr","Male","dddetesddtd@gmail.com","Active");


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
		
		//inside body we need to pass json only soo using ObjectMapper 
		
		given()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer e9e19bcf76aad3f3d5f0c477930ac6da35b242926f6b50b0d805f1b97d14bdd4").
		when()
			.delete("public-api/users/"+userid)
		.then()	
			.assertThat()
				.statusCode(204)
				.contentType(ContentType.JSON)
				.body("result", equalTo(null));

		//try to retrieve the deleted record
		
		given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer e9e19bcf76aad3f3d5f0c477930ac6da35b242926f6b50b0d805f1b97d14bdd4")
	.when()
	.get("public-api/users/"+userid)
		.then()
			.assertThat()
				.contentType(ContentType.JSON)
				.statusCode(404);
	
		
	}
}
