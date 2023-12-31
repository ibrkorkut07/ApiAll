package tests.withPojoClass.b_JsonPath.solved;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

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
       restfulSpec.pathParam("first", "10000");

       // There is a contentType issue here
       Response response = given().contentType(ContentType.ANY).spec(restfulSpec).when().get("/{first}");   // ???!!!
       HashMap<String, Object> actData = response.as(HashMap.class);
       System.out.println("actData = " + actData);

       response.then().statusCode(404);

       JsonPath json = response.jsonPath();
//       json.prettyPrint();
//       Assert.assertTrue(json.get().toString().contains("Not Found"));


//       Assert.assertTrue(response.getBody().asString().contains("Not Found"));
//       Assert.assertFalse(response.getBody().toString().contains("Suleyman"));
   }

}

