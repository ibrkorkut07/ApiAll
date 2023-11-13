package utilities.Sollutions;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01b_Without_BaseUrl {

    /*
        Positive Scenario
        When I send a GET Request to
             https://restful-booker.herokuapp.com/booking/5
        Then status code should be 200
        And content type should be "application/json"
        And Status Line should be HTTP/1.1 200 OK
    */

    @Test
    public void get01_WithoutBaseUrl(){
        String url = "https://restful-booker.herokuapp.com/booking/5";
        Response response = given().when().get(url);

        response.then().assertThat().
                statusCode(200).
                contentType("application/json").
                statusLine("HTTP/1.1 200 OK");
        // OR
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8", response.contentType());
        Assert.assertEquals("HTTP/1.1 200 OK", response.statusLine());
    }

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
