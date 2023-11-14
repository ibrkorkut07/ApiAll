package tests.withPojoClass.a_Matchers.solved;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

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
   // No need for Matchers & Pojo
   @Test
   public void get03() {
       restfulSpec.pathParam("first", "1000");

       Response response = given().spec(restfulSpec).when().get("{first}");

       response.then().statusCode(404);

       Assert.assertTrue(response.getBody().asString().contains("Not Found"));
       Assert.assertFalse(response.getBody().toString().contains("Suleyman"));
   }

}

