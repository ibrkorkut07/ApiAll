package tests.withPojoClass.b_JsonPath.solved;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Bookingdates;
import pojos.SingleBooking;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get05 extends TestBaseUrls {
    /*
		When I send a GET request to REST API URL
		     https://restful-booker.herokuapp.com/booking/11
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

    // Matchers & Pojo
    @Test
    public void get05JsonWithPojo () {
        restfulSpec.pathParam("first", "11");
        Response response = given().contentType(ContentType.JSON).spec(restfulSpec).when().get("/{first}");
        HashMap<String, Object> actData = response.as(HashMap.class);

        Bookingdates bookingdates = new Bookingdates("2018-01-01", "2019-01-01");
        SingleBooking expPojo = new SingleBooking("John", "Smith", 111, true, bookingdates, "Breakfast");

        response.then().statusCode(200).contentType(ContentType.JSON);

        JsonPath json = response.jsonPath();
        json.prettyPrint();

        assertEquals(actData.get("firstname"), json.getString("firstname"));
        assertEquals(actData.get("lastname"), json.getString("lastname"));
        assertEquals(actData.get("totalprice"), json.getInt("totalprice"));
        assertEquals(actData.get("depositpaid"), json.getBoolean("depositpaid"));
        assertEquals(actData.get("bookingdates.checkin"), json.getString(bookingdates.getCheckin()));
        assertEquals(actData.get("bookingdates.checkout"), json.getString(bookingdates.getCheckout()));
        assertEquals(actData.get("additionalneeds"), json.getString("additionalneeds"));
    }
    /*
    {
    "firstname": "John",
    "lastname": "Smith",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
     */

}