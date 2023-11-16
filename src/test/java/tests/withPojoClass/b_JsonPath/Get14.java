package tests.withPojoClass.b_JsonPath;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Bookingdates;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
        Bookingdates expBookingDates = new Bookingdates("2020-03-18", "");

        response.then().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK").
                body("firstname", equalTo("Jim"),
                        "totalprice", equalTo(623),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo(expBookingDates.getCheckin()) );
    }

}