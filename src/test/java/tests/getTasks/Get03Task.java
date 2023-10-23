package tests.getTasks;


import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Get03Task {

Response response;
   /*
	 Negative Scenario
	 When I send a GET Request to
	      https://restful-booker.herokuapp.com/booking/10000
	 Then status code should be 404
	 And Response Body contains "Not Found"
	 And Response Body does not contain "Suleyman"
	*/


}