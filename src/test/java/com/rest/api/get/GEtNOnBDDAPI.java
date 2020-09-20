package com.rest.api.get;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GEtNOnBDDAPI {

	//prepare the Request
	//hit the aPI
	//get the response
	//fetch the values from response
	
	@Test
	public void getUSer_Non_Bdd_Test() {
		RestAssured.baseURI="https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization","Bearer e9e19bcf76aad3f3d5f0c477930ac6da35b242926f6b50b0d805f1b97d14bdd4");
		
		//hit the api
		Response response = request.get("public-api/users");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));
		
	}
	
	@Test
	public void getUSer_Non_Bdd_WithQueryParam_Test() {
		RestAssured.baseURI="https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization","Bearer e9e19bcf76aad3f3d5f0c477930ac6da35b242926f6b50b0d805f1b97d14bdd4");
		request.queryParam("name", "Johnss");
		request.queryParam("gender", "Male");
		request.queryParam("email", "john@gmail.com");
		request.queryParam("status", "Active");

		//hit the api
		Response response = request.get("public-api/users");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));
	}
	
	//Map is the class which is implementing map interface
	//stores null values and null key
	//non Synchronized : multiple threads can access
	//whenever we are using multi threading environment we can use Hashmap
	
	@Test
	public void getUSer_Non_Bdd_HashMapQueryParam_Test() {
		RestAssured.baseURI="https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization","Bearer e9e19bcf76aad3f3d5f0c477930ac6da35b242926f6b50b0d805f1b97d14bdd4");
	
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", "TestTest");
		params.put("gender", "Male");
		params.put("email", "Male123@gmail.com");
		params.put("status", "Active");
	
		request.queryParams(params);
		//hit the api
		Response response = request.get("public-api/users");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));
		System.out.println(response.getStatusLine());
		System.out.println(response.getContentType());
		System.out.println(response.getCookie("PHPSSSIED"));
		
		//JsonPAth RestAssured provides own JsonPArser
		JsonPath js = response.jsonPath();
		System.out.println(js.getString("_meta.success"));
		
		//Assetions
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Server"), "nginx");
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	
}
