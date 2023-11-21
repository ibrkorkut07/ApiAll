package tests.withPojoClass.b_JsonPath.solved;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void get02JsonWithoutPojo() {
        restfulSpec.pathParam("first", "5");

        Response response = given().spec(restfulSpec).when().get("/{first}");   // OR  ...get("/5");


        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

        // With JsonPath
        JsonPath json = response.jsonPath();
        assertFalse(json.get().toString().contains("Not Found"));
        assertTrue(json.get().toString().contains("bookingdates"));
        json.prettyPrint();


        // Without Jsonpath
        HashMap<String, Object> bodyMap = response.as(HashMap.class);
        Assert.assertTrue(bodyMap.containsKey("bookingdates"));
        assertFalse(response.getBody().toString().contains("Not Found"));

    }

}