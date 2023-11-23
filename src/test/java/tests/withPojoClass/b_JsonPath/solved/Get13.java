package tests.withPojoClass.b_JsonPath.solved;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Bookingdates;
import pojos.SingleBooking;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get13 extends TestBaseUrls {
    /*
	    When I send a GET request to REST API URL
		     https://restful-booker.herokuapp.com/booking/5
	    Then HTTP Status Code should be 200
		And response content type is “application/JSON”
		And response body should be as follows;
		    {
		     "firstname": "Sally",
		     "lastname": "Ericsson",
		     "totalprice": 111,
		     "depositpaid": false,
		     "bookingdates": {
		                      "checkin": "2017-05-23",
		                      "checkout":"2019-07-02" }
	                          }
	         "additionalneeds": "Breakfast"
	*/

    @Test
    public void get13JsonWithPojo () {
        restfulSpec.pathParam("first", "5");
        Response response = given().spec(restfulSpec).when().get("{first}");
        JsonPath actJsonData = response.jsonPath();
        Bookingdates expBookingDates = new Bookingdates("2015-02-16", "2019-03-31");
        SingleBooking expPojoData = new SingleBooking("Mark", "Wilson", 861, true, expBookingDates, "Breakfast");

        response.then().statusCode(200).contentType(ContentType.JSON);
        assertEquals(expPojoData.getFirstname(), actJsonData.getString("firstname"));
        assertEquals(expPojoData.getLastname(), actJsonData.getString("lastname"));
        assertEquals(expPojoData.getTotalprice(), actJsonData.getInt("totalprice"));
        assertEquals(expPojoData.isDepositpaid(), actJsonData.getBoolean("depositpaid"));
        assertEquals(expPojoData.getBookingdates().getCheckin(), actJsonData.getString("bookingdates.checkin"));
        assertEquals(expPojoData.getBookingdates().getCheckout(), actJsonData.getString("bookingdates.checkout"));
    }

}