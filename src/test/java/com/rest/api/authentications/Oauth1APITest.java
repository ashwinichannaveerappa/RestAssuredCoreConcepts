package com.rest.api.authentications;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Oauth1APITest {
//			oauth1==(consumerKey, consumerSecret, accessToken, secretToken)

	@Test
	public void TwitterStatusAPI_OAuth1_test() {
		
		RequestSpecification request = 
				RestAssured.given()
			.auth()
			.oauth("0IN6EsjDerVouMVyn4whTQbxx", "etNvnq9xfIU2UAD9PCIzWv50UMZqGyd3MKPTql1ghjaYiubHGt", "260652715-29herWXQdcOBDimGlw933GiZfi555g6rH91VHBSQ", "zdJJ4FGwal3pOhw34RAa1SjLb8BSyMm7aB20cREe0OsWc");
		
		Response response = request.get("https://api.twitter.com/1.1/trends/available.json");
		System.out.println(response.prettyPrint());
	}
}
