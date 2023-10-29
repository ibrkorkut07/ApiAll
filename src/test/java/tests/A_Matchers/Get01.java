package tests.A_Matchers;

import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get01 extends RestfulBaseUrl {

    // NO NEED FOR MATCHERS HERE
    @Test
    public void get01_Matchers_Positive () {
    /*  Positive Scenario
	    When  I send a GET Request to
	          https://restful-booker.herokuapp.com/booking
	    And   I accept type "application/json"  ==> Means API is accepting data just in Json Format
	    And   content type should be "application/json; charset=utf-8" ==> Response body must be in Json format
        And   statusLine should be "HTTP/1.1 200 OK"
	*/
        specRest.pathParam("1", "booking");
        Response response = given().spec(specRest).when().get("{1}");
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");
    }


    @Test
    public void get01_Matchers_Negative () {
    /*  Negative Scenario
	    When  I send a GET Request to
	          https://restful-booker.herokuapp.com/booking/1001
	    Then  status code should be 404
	    And   Response Body contains "Not Found"
	    And   Response Body does not contain "Suleyman"
	*/
    }


    @Test
    public void get01_Matchers_Extra () {
    /*  Extra task
	    Positive Scenario
	    When  I send a GET Request to
	          https://restful-booker.herokuapp.com/booking
	    And   I accept type "application/json"  ==> Means API is accepting data just in Json Format
	    And   content type should be "application/json" ==> Response body must be in Json format
	*/
    }

}
