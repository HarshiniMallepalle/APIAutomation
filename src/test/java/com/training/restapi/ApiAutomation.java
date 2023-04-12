package com.training.restapi;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiAutomation {
	String sHostUrl = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
	String sEPLogin = "/login";
	String sEPGetData = "/getdata";
	String sEPUpdateData = "/updateData";
	String sEPAddData = "/addData";
	String sEPDeleteData = "/deleteData";
	String sURI = "";
	Response response;
	String token;

	@Test
	public void login() {
		// Login
		sURI = sHostUrl + sEPLogin;
		System.out.println(sURI);
		RestAssured.baseURI = sURI;
		response = RestAssured.given()
				.body("{\"username\": \"harshinim0802@gmail.com\", \r\n" + "\"password\": \"harshinim123\"}").when()
				.contentType("application/json").post();

		int status = response.getStatusCode();
		// System.out.println(status);
		// System.out.println(response.asString());
		// System.out.println(response.asPrettyString());
		response.prettyPrint();

		token = response.jsonPath().get("token[0]");
		System.out.println(token);
		if (status == 201) {
			System.out.println("Test case is passed");
		} else {
			System.out.println("Test case fail");
		}

		// GetData

		sURI = sHostUrl + sEPGetData;
		RestAssured.baseURI = sURI;
		HashMap<String, String> headerData = new HashMap<String, String>();
		headerData.put("Content-Type", "application/json");
		headerData.put("token", token);

		// output is as excepted but some problem in API application

//		response = RestAssured.given().headers(headerData).get();
//		System.out.println(response.asPrettyString());
//		List<String> accountnumbers = response.jsonPath().getList("accountno");
//		for (String accountnumber : accountnumbers) {
//			System.out.println(accountnumber);
//		}

		// addData
		sURI = sHostUrl + sEPAddData;
		RestAssured.baseURI = sURI;
		response = RestAssured.given()
				.body("{\"accountno\":\"TA-1210082\",\r\n" + "\"departmentno\":12,\r\n"
						+ "\"id\":\"fqVpXkBQUukBCe2lqEfQ\",\r\n" + "\"pincode\":583103,\r\n" + "\"salary\":6005,\r\n"
						+ "\"userid\":\"SXM8JTStXnqKndvVlkpC\"}")
				.headers(headerData).post();
		// System.out.println(response.asPrettyString());
		response.prettyPrint();

		// UpdateData
		sURI = sHostUrl + sEPUpdateData;
		RestAssured.baseURI = sURI;
		response = RestAssured.given()
				.body("{\"accountno\":\"TA-1210082\",\r\n" + "\"departmentno\":83,\r\n"
						+ "\"id\":\"fqVpXkBQUukBCe2lqEfQ\",\r\n" + "\"pincode\":583103,\r\n" + "\"salary\":6005,\r\n"
						+ "\"userid\":\"SXM8JTStXnqKndvVlkpC\"}")
				.headers(headerData).put();
		// System.out.println(response.asPrettyString());
		response.prettyPrint();

		// deleteData
		sURI = sHostUrl + sEPDeleteData;
		RestAssured.baseURI = sURI;
		response = RestAssured.given()
				.body("{\"accountno\":\"TA-1210082\",\r\n" + "\"departmentno\":83,\r\n"
						+ "\"id\":\"fqVpXkBQUukBCe2lqEfQ\",\r\n" + "\"pincode\":583103,\r\n" + "\"salary\":6005,\r\n"
						+ "\"userid\":\"SXM8JTStXnqKndvVlkpC\"}")
				.headers(headerData).delete();
		// System.out.println(response.asPrettyString());
		response.prettyPrint();

	}

}
