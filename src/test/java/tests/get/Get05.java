package tests.get;


import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get05 extends RestfulBaseUrl {
    /*
		When I send a GET request to REST API URL
		     https://restful-booker.herokuapp.com/booking/1
	    And Accepted content type is “application/JSON”
	    Then HTTP Status Code should be 200
	    And Response format should be "application/JSON"
	    And "firstname" should be "Mary"
	    And "lastname" should be "Ericsson"
	    And "totalprice" should be 111
	    And "depositpaid" should be false
	    And "checkin" date should be "2022-02-19"
	    And "checkout" date should be "023-05-10"
	    And "additionalneeds" should be "Breakfast"   // not included always
   */

    @Test
    public void get05(){
        specRest.pathParams("1", "booking", "2", "1");
        Response response = given().spec(specRest).contentType(ContentType.JSON).when().get("{1}/{2}");
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Mary"),
                        "lastname", equalTo("Ericsson"),
                        "bookingdates.checkin", equalTo("2022-02-19"),
                        "bookingdates.checkout", equalTo("023-05-10"),
                        "additionalneeds", equalTo("Breakfast"));
    }

    // Assert the followings by using Soft Assertion
    @Test
    public void get05SoftAssertTest(){
        specRest.pathParams("1", "booking", "2", "1");
        Response response = given().spec(specRest).contentType(ContentType.JSON).when().get("{1}/{2}");
        response.then().
                statusCode(200).
                contentType(ContentType.JSON);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.jsonPath().getInt("firstname"), "Mary");
        softAssert.assertEquals(response.jsonPath().getString("lastname"), "Ericsson");
        softAssert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), "2022-02-19");
        softAssert.assertEquals(response.jsonPath().getString("bookingdates.checkout"), "023-05-10");
        softAssert.assertEquals(response.jsonPath().getString("additionalneeds"), "Breakfast");
        softAssert.assertAll();
    }

    // Use De-Serialization to convert Json response body to a Map.
    // Then by using the map and soft-assertion make assertions.

}