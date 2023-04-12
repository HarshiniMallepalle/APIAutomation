package com.training.restapi;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class ApiAutomationUser {

	String sHost = "https://jsonplaceholder.typicode.com";
	String sEPUser = "/users";
	String sURI = "";

	@Test
	public void validateUser() throws IOException {
		sURI = sHost + sEPUser;
		RestAssured.baseURI = sURI;
		Response response = RestAssured.given().get();
		// response.prettyPrint();
		System.out.println(response.getStatusCode());
		// String Actualvalue = response.jsonPath().get("name[0]");
		String filepath = "C:\\Users\\harsh\\eclipse-workspace\\ApiAutomation\\TestData\\ExpectedResponseUser.json";
		String fileReadytoRead = readFileReturnString(filepath);
		String ExpectedValue = JsonPath.read(fileReadytoRead, "$.[0].name");
		String ExpectedCity = JsonPath.read(fileReadytoRead, "$.[4].address.city");

		System.out.println(ExpectedValue);
		// assertEquals(Actualvalue, ExpectedValue);
		response.then().body("name[0]", equalTo(ExpectedValue));
		response.then().body("[0].email", startsWith("S"));
		 response.then().body("address.city",hasItem(ExpectedCity));//to find particular city from list
		 response.then().body("address.city",hasItems("Gwenborough","Wisokyburgh"));                                                            // address.city gives all cities
		
	// This 8 steps replaced by above hamcrest matchers 1 line	
// List<String> cities = response.jsonPath().getList("address.city");
//	for(String city: cities) {
//		System.out.println(city);
//		if(city.equalsIgnoreCase("Aliyaview")) {
//			System.out.println("Test Passed");
//			break;
//		}
//	}
	}

//converting json file to String file
	private String readFileReturnString(String filepath) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(filepath));
		return new String(encoded, StandardCharsets.UTF_8);
	}

}
