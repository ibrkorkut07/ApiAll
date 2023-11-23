package tests.withPojoClass.b_JsonPath.solved;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Bookingdates;
import pojos.SingleBooking;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get14 extends TestBaseUrls {

    /*
        When I send GET Request to
             https://restful-booker.herokuapp.com/booking/3
		Then Status code is 200
		And Content type is "application/json"
		And Status line is "HTTP/1.1 200 OK"
		And First name is Jim
		And Total price is 623
		And Deposit paid is true
		And Checkin date is "2020-03-18"

		Use De-Serialization to convert Json response body to a Map.
		Then by using the map and soft-assertion make assertions.
    */

    @Test
    public void get14MatchersWithPojo () {
        restfulSpec.pathParam("first", "3");
        Response response = given().spec(restfulSpec).when().get("{first}");
        JsonPath jsonActData = response.jsonPath();

        Bookingdates expBookingDates = new Bookingdates("2017-10-14", "");
        SingleBooking expPojoData = new SingleBooking("Jim", "", 791, true, expBookingDates, "");

        response.then().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");
        assertEquals(expPojoData.getFirstname(), jsonActData.getString("firstname"));
        assertEquals(expPojoData.getTotalprice(), jsonActData.getInt("totalprice"));
        assertEquals(expPojoData.isDepositpaid(), jsonActData.getBoolean("depositpaid"));
        assertEquals(expPojoData.getBookingdates().getCheckin(), jsonActData.getString("bookingdates.checkin"));

    }

}