package com.rest.api.get;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import XMLUtil.XmlParser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GETBDDAPI {

	//REST ASSURED BDD:
	
	/*given()
	 * when()
	 * then()
	 * and()*/
		
	@Test
	public void getAPICircuitTest_1() {
		
		given().log().all()
		.when().log().all()
		.get("http://ergast.com/api/f1/2017/circuits.json")
		.then().log().all()
			.assertThat()
			.body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
	}
	
	@Test
	public void getAPICircuitTest_2() {
		
		//get method returns response object 
		Response response =
		given().log().all()
		.when().log().all()
		.get("http://ergast.com/api/f1/2017/circuits.json");
		int statusCode = response.getStatusCode();
		System.out.println("api response status code :"+statusCode);
		Assert.assertEquals(statusCode, 200);
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void getAPICircuitTest_contentLength() {
		
		//best way to write
		RestAssured.baseURI="http://ergast.com";
		given().log().all()
		.when().log().all()
		.get("/api/f1/2017/circuits.json")
		.then()
		.assertThat()
			.statusCode(200)
		.and()
			.contentType(ContentType.JSON)
		.and()
			.header("Content-Length",equalTo("4551"));		
	}
	
	@Test
	public void getJsonAPI_VerifyMd5Value() {
		String paramValue="test";
		String expecedMd5Value="098f6bcd4621d373cade4e832627b4f6";
		given().log().all()
			.param("text",paramValue)
		.when().log().all()
		.get("http://md5.jsontest,com?text=test")
		.then().log().all()
			.assertThat()
				.body("md5", equalTo(expecedMd5Value));
	}
	//2017--20
	//2016 --21
	//1966--9
	
	@DataProvider(name="getCircuitYearData")
	public Object[][] getCircuitYearInfo() {
		return new Object[][] {
			{"2017",20},
			{"2016",21},
			{"1966",9}
		};
	}
	
	
	@Test(dataProvider="getCircuitYearData")
	public void numberOfCircuitsYearTest(String seasonYear, int circuitNumber) {
		given().log().all()
			.pathParam("raceSeason", seasonYear)
			.when().log().all()
				.get("http://ergast.com/api/f1/{raceSeason}/circuits.json")
			.then().log().all()
				.assertThat()
					.body("MRData.CircuitTable.Circuits.circuitId",hasSize(circuitNumber));
	}
	
	@Test
	public void getUsersResponseXml_Test() {
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response = 
			given().log().all()
			.contentType("application/json")
			.header("Authorization","Bearer e9e19bcf76aad3f3d5f0c477930ac6da35b242926f6b50b0d805f1b97d14bdd4")
			.header("Accept","application/xml")
		.when().log().all()
			.get("/public-api/users.xml");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());

		//Using XMlPath of Restassured - get xml values
//		XmlPath xmlPath =response.xmlPath();
//		int total = xmlPath.get("hash.meta.pagination.total");
//		System.out.println("succrss value is:"+total);
		
		//using External xml parser
		String responseXmlString= response.prettyPrint();
		XmlParser xp = new XmlParser(responseXmlString);
		String value =xp.getTextContent("//hash/meta/pagination/total");
				System.out.println(value);
		
	}

}