package tests.get;


import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Get02 extends RestfulBaseUrl {

    /*
        When I send a GET Request to
             https://restful-booker.herokuapp.com/booking/5
		Then HTTP Status code should be "200"
		And  Content type should be in "JSON" format
		And  Status Line should be "HTTP/1.1 200 OK"
		And  response body does not contain "Not Found"
	    And  response body contains "bookingdates"
	*/

    @Test
    public void test() {
        specRest.pathParams("1", "booking", "2", "5");
        Response response = given().spec(specRest).when().get("{1}/{2}");
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");
        Assert.assertFalse("Body has Not Found text", response.getBody().toString().contains("Not Found"));
        Assert.assertTrue(response.getBody().asString().contains("bookingdates"));
    }


}