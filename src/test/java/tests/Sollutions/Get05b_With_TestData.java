package tests.Sollutions;

import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import testData.RestfulTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get05b_With_TestData extends RestfulBaseUrl {
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
	    And "checkout" date should be "2023-05-10"
	    And "additionalneeds" should be "Breakfast"   // not included always
	    NOTE: Keep in mind that values keep changing
   */

    // MATCHERS WITH EXPECTED TEST DATA
    @Test
    public void get05Matchers_WithExpData(){
        specRest.pathParams("1", "booking", "2", "1");
        Response response = given().spec(specRest).contentType(ContentType.JSON).when().get("{1}/{2}");
        RestfulTestData restfulTestData = new RestfulTestData();

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo(restfulTestData.booking2Data().get("firstname")),
                        "lastname", Matchers.equalTo(restfulTestData.booking2Data().get("lastname")),  // Avoid typing "Matchers" by importing "equalTo"
                        "bookingdates.checkin", equalTo(restfulTestData.bookingSingleUserBookingdatesData().get("checkin")),
                        "bookingdates.checkout", equalTo(restfulTestData.bookingSingleUserBookingdatesData().get("checkout")),
                        "additionalneeds", equalTo(restfulTestData.booking2Data().get("additionalneeds")));
    }

    // JSONPATH
    @Test
    public void get05JsonPath() {
        specRest.pathParams("1","booking", "2", "1");
        Response response = given().spec(specRest).when().get("{1}/{2}");

        response.then().statusCode(200).contentType(ContentType.JSON);

        JsonPath json = response.jsonPath();
        Assert.assertEquals("Mary", json.getString("firstname"));  // Avoid typing "Assert" by importing "assertEquals"
        assertEquals("Ericsson", json.getString("lastname"));
        assertEquals("2022-02-19", json.getString("bookingdates.checkin"));
        assertEquals("2023-05-10", json.getString("bookingdates.checkout"));
        assertEquals("Breakfast", json.getString("additionalneeds"));

        // Body Test With "SoftAssert" (ALWAYS USE ASSERTALL AT THE END)
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.jsonPath().getInt("firstname"), "Mary");
        softAssert.assertEquals(response.jsonPath().getString("lastname"), "Ericsson");
        softAssert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), "2022-02-19");
        softAssert.assertEquals(response.jsonPath().getString("bookingdates.checkout"), "023-05-10");
        softAssert.assertEquals(response.jsonPath().getString("additionalneeds"), "Breakfast");
        softAssert.assertAll();   // Tests will always show as passed if you do NOT use assertAll at the end. So, Soft Assert
                                  // tests are meaningless without "assertAll" at the end.
    }

    // Use De-Serialization to convert Json response body to a Map.
    // Then by using the map and soft-assertion make assertions.

    // DE-SERIALIZATION
    @Test
    public void test05Deserialization() {
        specRest.pathParams("1","booking", "2", "1");
        Response response = given().spec(specRest).when().get("{1}/{2}");
        /*
        Then HTTP Status Code should be 200
        And Response format should be "application/JSON"
        And "firstname" should be "Mary"
        And "lastname" should be "Ericsson"
        And "totalprice" should be 111
        And "depositpaid" should be false
        And "checkin" date should be "2022-02-19"
        And "checkout" date should be "2023-05-10"
        And "additionalneeds" should be "Breakfast"
        */
        HashMap<String, Object> expRestfulBookingIdObject = new HashMap<>();
        expRestfulBookingIdObject.put("statusCode", 200);
        expRestfulBookingIdObject.put("firstname", "Mary");
        expRestfulBookingIdObject.put("lastname", "Brown");
        expRestfulBookingIdObject.put("totalprice", 319);
        expRestfulBookingIdObject.put("depositpaid", false);
        expRestfulBookingIdObject.put("checkin", "2021-12-18");
        expRestfulBookingIdObject.put("checkout", "2022-04-23");
        expRestfulBookingIdObject.put("additionalneeds", "Breakfast");

        HashMap<String, Object> actRestfulBookingIdObject = response.as(HashMap.class);
        System.out.println("actPlaceHolderTodosObjec = " + actRestfulBookingIdObject);

        assertEquals(expRestfulBookingIdObject.get("statusCode"), response.statusCode());
        assertEquals(expRestfulBookingIdObject.get("firstname"), actRestfulBookingIdObject.get("firstname"));
        assertEquals(expRestfulBookingIdObject.get("lastname"), actRestfulBookingIdObject.get("lastname"));
        assertEquals(expRestfulBookingIdObject.get("totalprice"), actRestfulBookingIdObject.get("totalprice"));
        assertEquals(expRestfulBookingIdObject.get("depositpaid"), actRestfulBookingIdObject.get("depositpaid"));
        assertEquals(expRestfulBookingIdObject.get("checkin"), ((Map)actRestfulBookingIdObject.get("bookingdates")).get("checkin"));
        assertEquals(expRestfulBookingIdObject.get("checkout"), ((Map)actRestfulBookingIdObject.get("bookingdates")).get("checkout"));
    }

}