package tests.E_With_Pojo;

import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDates_Pojo;
import pojos.Booking_Pojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetPojo extends RestfulBaseUrl {
    /*
    https://www.jsonschema2pojo.org/
    Copy-paste the json data
    Type your pojo package and class name
    Source type: json
    Include getters and setters
    Include constructors
    Include toString (to see on the console)

    and then click on preview
    Delete super() in constructors and comments
     */

        /*
		When I send a GET request to REST API URL
		     https://restful-booker.herokuapp.com/booking/5
	    And Accepted content type is “application/JSON”
	    Then HTTP Status Code should be 200
	    And Response format should be "application/JSON"
	    And {
                "firstname": "Eric",
                "lastname": "Jones",
                "totalprice": 915,
                "depositpaid": false,
                "bookingdates": {
                                "checkin": "2020-03-10",
                                "checkout": "2022-11-23"
                                 },
                "additionalneeds": "Breakfast"   // not included always
                }
	    NOTE: Keep in mind that values keep changing
   */

    // POJO
    @Test
    public void getPojo () {

        // Step 1: Set the url
        specRest.pathParams("first", "booking", "second", 5);

        // Step 2: Set the expected data - start with inner pojo (bookingDates)
        // By doing so, we convert Pojo objects to Java objects
        BookingDates_Pojo bookingDatesPojo = new BookingDates_Pojo("2018-04-23", "2019-10-11");
        Booking_Pojo expBookingPojoData = new Booking_Pojo("Jim", "Jones", 596, false, bookingDatesPojo, "Breakfast");
        // System.out.println("bookingDatesPojo = " + bookingDatesPojo);
        // System.out.println("bookingPojo = " + bookingPojo);

        // Step 3: Send Request & Get Response
        Response response = given().contentType(ContentType.JSON).spec(specRest).when().get("{first}/{second}");
        response.prettyPrint();

        // Step 4: Assert
        // Use Gson convert response body to Booking_Pojo class (As I have everything in Booking_Pojo)
        Booking_Pojo actData = response.as(Booking_Pojo.class);
        System.out.println("actData = " + actData);

        response.then().statusCode(200).contentType(ContentType.JSON);

        assertEquals(200, response.statusCode());
        assertEquals(expBookingPojoData.getFirstname(), actData.getFirstname());
        assertEquals(expBookingPojoData.getLastname(), actData.getLastname());
        assertEquals(expBookingPojoData.getTotalprice(), actData.getTotalprice());
        assertEquals(expBookingPojoData.getDepositpaid(), actData.getDepositpaid());
        assertEquals(expBookingPojoData.getBookingdates().getCheckin(), actData.getBookingdates().getCheckin());
        assertEquals(expBookingPojoData.getBookingdates().getCheckout(), actData.getBookingdates().getCheckout());
        assertEquals(expBookingPojoData.getAdditionalneeds(), actData.getAdditionalneeds());



    }
}
