package tests.get;

import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get03 extends RestfulBaseUrl {

Response response;
   /*
	 Negative Scenario
	 When I send a GET Request to
	      https://restful-booker.herokuapp.com/booking/10000
	 Then status code should be 404
	 And Response Body contains "Not Found"
	 And Response Body does not contain "Suleyman"
	*/

    // MATCHERS
    @Test
    public  void get03_Matchers(){
        specRest.pathParams("1", "booking", "2", "10000");
        Response response = given().spec(specRest).contentType(ContentType.JSON).when().get("{1}/{2}");
        response.then().
                statusCode(404).   // That also proves "Not Found"
                body(hasToString("Not Found"));
        // or
        // Assert.assertTrue(response.asString().contains("Not Found"));
        Assert.assertFalse(response.asString().contains("Suleyman"));
        System.out.println(response.asString());
    }

    // JSONPATH
    @Test
    public  void get03_JsonPath(){
        specRest.pathParams("1", "booking", "2", "10000");
        Response response = given().spec(specRest).when().get("{1}/{2}");
        response.then().statusCode(404);   // Status code 404 also proves "Not Found"

        JsonPath json = response.jsonPath();
        // System.out.println("json.toString() = " + json.);
        Assert.assertTrue(json.toString().contains("Not Found"));  // does not work
        Assert.assertFalse(json.toString().contains("Suleyman"));
    }

   /*
	 Negative Scenario
	 When I send a GET Request to
	      https://restful-booker.herokuapp.com/booking/10000
	 Then status code should be 404
	 And Response Body contains "Not Found"
	 And Response Body does not contain "Suleyman"
	*/

    }

