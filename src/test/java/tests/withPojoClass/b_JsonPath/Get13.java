package tests.withPojoClass.b_JsonPath;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Bookingdates;
import pojos.SingleBooking;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
    public void get13MatchersWithPojo () {
        restfulSpec.pathParam("first", "5");
        Response response = given().spec(restfulSpec).when().get("{first}");
        Bookingdates expBookingDates = new Bookingdates("2017-08-01", "2022-05-23");
        SingleBooking expData = new SingleBooking("Mark", "Ericsson", 380, false, expBookingDates, "Breakfast");

        response.then().statusCode(200).contentType(ContentType.JSON).
                body("firstname", equalTo(expData.getFirstname()),
                        "lastname", equalTo(expData.getLastname()),
                                "totalprice", equalTo(expData.getTotalprice()),
                                "depositpaid", equalTo(expData.isDepositpaid()),
                                "bookingdates.checkin", equalTo(expData.getBookingdates().getCheckin()),
                                "bookingdates.checkout", equalTo(expData.getBookingdates().getCheckout()) );
    }

}