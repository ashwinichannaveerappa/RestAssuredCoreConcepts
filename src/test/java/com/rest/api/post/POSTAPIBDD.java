package com.rest.api.post;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POSTAPIBDD {
	
	
	@Test
	public void tokenPoseBDDAPI_test() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		given().log().all()
			.contentType(ContentType.JSON)
			.body("{\r\n" + 
					"    \"username\" : \"admin\",\r\n" + 
					"    \"password\" : \"password123\"\r\n" + 
					"}")
		.when().log().all()
			.post("/auth")
		.then().log().all()
			.assertThat()
				.statusCode(200);
			
	}
	
	@Test
	public void tokenPOSTBDDAPI_FILE_TEST() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		String tokenID=given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("C:\\Users\\ashua\\Desktop\\Automation\\RestAssured_NaveenAutomation\\September2020APIBatch\\src\\test\\java\\DataFiles\\credentials.json"))
		.when().log().all()
			.post("/auth")
		.then().log().all()
			.extract().path("token"); //extract method to extract anything from the body
		System.out.println(tokenID);
		Assert.assertNotNull(tokenID); //tokenId should not be null
	}
	
	@Test
	public void createUser_Post_JSONStringTest() {
		RestAssured.baseURI="https://gorest.co.in";
		
		given()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer 45db930d2442f97e86c68ecc3f222ee788cbd7c2b685aaa0348c59bc69cc9f85")
			.body("{\"name\":\"Tenalitesy Ramakrishna\", \"gender\":\"Male\", \"email\":\"tenali12344.ramakrishna@15ce.com\", \"status\":\"Active\"}")
		.when().log().all()
			.post("/public-api/users")
		.then()
			.assertThat()
				.body("_meta.success", equalTo(true));
	}
	
	@Test
	public void createUser_Post_API_FileTest() {
		RestAssured.baseURI="https://gorest.co.in";
		
		given()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer 45db930d2442f97e86c68ecc3f222ee788cbd7c2b685aaa0348c59bc69cc9f85")
			.body(new File("C:\\Users\\ashua\\Desktop\\Automation\\RestAssured_NaveenAutomation\\September2020APIBatch\\src\\test\\java\\DataFiles\\user.json"))
		.when().log().all()
			.post("/public-api/users")
		.then()
			.assertThat()
				.body("_meta.success", equalTo(true));
	}
}
