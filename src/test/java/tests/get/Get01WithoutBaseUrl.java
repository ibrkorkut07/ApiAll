package tests.get;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get01WithoutBaseUrl {

    /*
        Positive Scenario
        When I send a GET Request to
             https://restful-booker.herokuapp.com/booking/5
        Then status code should be 200
        And content type should be "application/json"
        And Status Line should be HTTP/1.1 200 OK
    */

    @Test
    public void TestWithoutBaseUrl(){
        String url = "https://restful-booker.herokuapp.com/booking/5";
        Response response = given().when().get(url);

        response.then().assertThat().
                statusCode(200).
                contentType("application/json").
                statusLine("HTTP/1.1 200 OK");
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
