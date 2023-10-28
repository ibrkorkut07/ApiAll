package tests.get;

import baseUrl.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.*;

public class Get01a_With_BaseUrl extends RestfulBaseUrl {

    /*
	    Positive Scenario
	    When I send a GET Request to
	         https://restful-booker.herokuapp.com/booking
	    And I accept type "application/json"  ==> Means API is accepting data just in Json Format
	    And content type should be "application/json" ==> Response body must be in Json format
        And statusLine should be "HTTP/1.1 200"
	*/

    @Test
    public void get01_WithBaseUrl(){
        specRest.pathParams("first", "booking");
        Response response = given().spec(specRest).when().get("/{first}");
        response.then().
                statusCode(200).
                contentType("application/json").
                statusLine("HTTP/1.1 200 OK");
        // OR
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8", response.contentType());
        Assert.assertEquals("HTTP/1.1 200 OK", response.statusLine());
    }

    /*
	    Negative Scenario
	    When I send a GET Request to
	         https://restful-booker.herokuapp.com/booking/1001
	    Then status code should be 404
	    And Response Body contains "Not Found"
	    And Response Body does not contain "Suleyman"
	*/

    /*  Extra task
	    Positive Scenario
	    When I send a GET Request to
	         https://restful-booker.herokuapp.com/booking
	    And I accept type "application/json"  ==> Means API is accepting data just in Json Format
	    And content type should be "application/json" ==> Response body must be in Json format
	*/

}
