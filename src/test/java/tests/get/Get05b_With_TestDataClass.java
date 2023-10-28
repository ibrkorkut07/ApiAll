package tests.get;

import baseUrl.RestfulBaseUrl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import testData.JsonplaceholderTestdata;
import testData.RestfulTestData;
import utilities.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get05b_With_TestDataClass extends RestfulBaseUrl {
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
    public void get05_Matchers_With_TestData(){
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
    public void get05_JsonPath_With_TestData() {
        specRest.pathParams("1","booking", "2", "1");
        Response response = given().spec(specRest).when().get("{1}/{2}");

        response.then().statusCode(200).contentType(ContentType.JSON);

        RestfulTestData expData = new RestfulTestData();

        JsonPath json = response.jsonPath();
        Assert.assertEquals(expData.booking2Data().get("firstname"), json.getString("firstname"));  // Avoid typing "Assert" by importing "assertEquals"
        assertEquals(expData.booking2Data().get("lastname"), json.getString("lastname"));
        assertEquals(expData.booking2Data().get("bookingdates.checkin"), json.getString("bookingdates.checkin"));
        assertEquals(expData.booking2Data().get("bookingdates.checkout"), json.getString("bookingdates.checkout"));
        assertEquals(expData.booking2Data().get("additionalneeds"), json.getString("additionalneeds"));

        // Body Test With "SoftAssert" (ALWAYS USE ASSERTALL AT THE END)
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.jsonPath().getInt("firstname"), expData.booking2Data().get("firstname"));
        softAssert.assertEquals(response.jsonPath().getString("lastname"), expData.booking2Data().get("lastname"));
        softAssert.assertEquals(response.jsonPath().getString("bookingdates.checkin"), expData.booking2Data().get("bookingdates.checkin"));
        softAssert.assertEquals(response.jsonPath().getString("bookingdates.checkout"), expData.booking2Data().get("bookingdates.checkout"));
        softAssert.assertEquals(response.jsonPath().getString("additionalneeds"), json.getString("additionalneeds"));
        softAssert.assertAll();   // Tests will always show as passed if you do NOT use assertAll at the end. So, Soft Assert
                                  // tests are meaningless without "assertAll" at the end.
    }

    // Use De-Serialization to convert Json response body to a Map.
    // Then by using the map and soft-assertion make assertions.

    // DE-SERIALIZATION
    @Test
    public void test05_Deserialization_With_TestData() {
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

        RestfulTestData expData = new RestfulTestData();
        HashMap<String, Object> actData = response.as(HashMap.class);

        response.then().assertThat().statusCode((int) expData.booking2Data().get("statusCode")).
                contentType((String) expData.booking2Data().get("contentType"));

        assertEquals(expData.booking2Data().get("firstname"), actData.get("firstname"));
        assertEquals(expData.booking2Data().get("lastname"), actData.get("lastname"));
        assertEquals(expData.booking2Data().get("totalprice"), actData.get("totalprice"));
        assertEquals(expData.booking2Data().get("depositpaid"), actData.get("depositpaid"));
        assertEquals(expData.booking2Data().get("checkin"), actData.get("checkin"));
        assertEquals(expData.booking2Data().get("checkout"), actData.get("checkout"));
        assertEquals(expData.booking2Data().get("additionalneeds"), actData.get("additionalneeds"));
    }

    // OBJECT MAPPER
    @Test
    public void test05_ObjectMapper_With_TestData() {
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

        RestfulTestData expData = new RestfulTestData();
        HashMap<String, Object> actData = response.as(HashMap.class);

        response.then().assertThat().statusCode((int) expData.booking2Data().get("statusCode")).
                contentType((String) expData.booking2Data().get("contentType"));

        String expJsonData = "{\n" +
                "    \"firstname\": \"Jim\",\n" +
                "    \"lastname\": \"Jones\",\n" +
                "    \"totalprice\": 272,\n" +
                "    \"depositpaid\": false,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2019-08-03\",\n" +
                "        \"checkout\": \"2021-11-09\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";

        HashMap expJavaData = JsonUtil.convertJsonToJava(expJsonData, HashMap.class);
        HashMap actJavaData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);

        assertEquals(expJavaData.get("firstname"), actJavaData.get("firstname"));
        assertEquals(expJavaData.get("lastname"), actJavaData.get("lastname"));
        assertEquals(expJavaData.get("totalprice"), actJavaData.get("totalprice"));
        assertEquals(expJavaData.get("depositpaid"), actJavaData.get("depositpaid"));
        assertEquals(expJavaData.get("checkin"), actJavaData.get("checkin"));
        assertEquals(expJavaData.get("checkout"), actJavaData.get("checkout"));
        assertEquals(expJavaData.get("additionalneeds"), actJavaData.get("additionalneeds"));
    }

}