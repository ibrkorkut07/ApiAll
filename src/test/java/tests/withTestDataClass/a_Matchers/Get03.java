package tests.withTestDataClass.a_Matchers;

import baseUrl.TestBaseUrls;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasToString;

public class Get03 extends TestBaseUrls {

   /*
	 Negative Scenario
	 When I send a GET Request to
	      https://restful-booker.herokuapp.com/booking/10000
	 Then status code should be 404
	 And Response Body contains "Not Found"
	 And Response Body does not contain "Suleyman"
	*/

    }

