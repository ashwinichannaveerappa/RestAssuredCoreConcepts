package com.rest.api.schema;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CheckSchemaTest {

	@Test
	public void bookings_Schema_Test() {
		given()
			.when()
				.get("http://ergast.com/api/f1/2017/circuits.json")
		.then()
			.assertThat()
				.statusCode(200)
				.and()
					.body(matchesJsonSchemaInClasspath("BookingsSchema.json"));
	}
}
