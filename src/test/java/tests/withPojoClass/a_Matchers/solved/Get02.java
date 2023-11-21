package tests.withPojoClass.a_Matchers.solved;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Get02 extends TestBaseUrls {

    /*
        When I send a GET Request to
             https://restful-booker.herokuapp.com/booking/5
		Then HTTP Status code should be "200"
		And  Content type should be in "JSON" format
		And  Status Line should be "HTTP/1.1 200 OK"
		And  Response body does not contain "Not Found"
	    And  Response body contains "bookingdates"
	*/

    // No need for Matchers
    @Test
    public void get02WithPojo() {
        restfulSpec.pathParam("first", "5");

        Response response = given().spec(restfulSpec).when().get("/{first}");   // OR  ...get("/5");
        HashMap<String, Object> bodyMap = response.as(HashMap.class);

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

        Assert.assertTrue(bodyMap.containsKey("bookingdates"));
        Assert.assertFalse(bodyMap.containsKey("Not Found"));
    }

}