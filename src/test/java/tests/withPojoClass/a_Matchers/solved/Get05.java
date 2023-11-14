package tests.withPojoClass.a_Matchers.solved;

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
    public void get05MatcherWithPojo () {
        restfulSpec.pathParam("first", "11");
        Response response = given().contentType(ContentType.JSON).spec(restfulSpec).when().get("/{first}");

        Bookingdates expDataBookingdates = new Bookingdates("2018-01-01", "2019-01-01");
        SingleBooking expDataSingleBooking = new SingleBooking("San", "Sui", 111, true, expDataBookingdates, "Breakfast");

        response.then().statusCode(200).contentType(ContentType.JSON).
                body("firstname", equalTo(expDataSingleBooking.getFirstname()),
                        "lastname", equalTo(expDataSingleBooking.getLastname()),
                        "totalprice", equalTo(expDataSingleBooking.getTotalprice()),
                        "depositpaid", equalTo(expDataSingleBooking.isDepositpaid()),
                        "bookingdates.checkin", equalTo(expDataSingleBooking.getBookingdates().getCheckin()),
                        "bookingdates.checkout", equalTo(expDataSingleBooking.getBookingdates().getCheckout()),
                        "additionalneeds", equalTo(expDataSingleBooking.getAdditionalneeds())
                        );
//
//        HashMap<String, Object> actMap = response.as(HashMap.class);
//        System.out.println("actMap.get(\"firstname\") = " + actMap.get("firstname"));


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

    // Matchers & Pojo
    @Test
    public void get05MatcherWithActHashMapAndPojo () {
        restfulSpec.pathParam("first", "11");
        Response response = given().contentType(ContentType.JSON).spec(restfulSpec).when().get("{first}");
        HashMap<String, Object> actData = response.as(HashMap.class);

        Bookingdates expDataBookingdates = new Bookingdates("2018-01-01", "2019-01-01");
        SingleBooking expDataSingleBooking = new SingleBooking("San", "Sui", 111, true, expDataBookingdates, "Breakfast");

        response.then().statusCode(200).contentType(ContentType.JSON).
                body((String) actData.get("firstname"), equalTo(expDataSingleBooking.getFirstname()),
                        actData.get("lastname"), equalTo(expDataSingleBooking.getLastname()),
//                        actData.get("totalprice"), equalTo(expDataSingleBooking.getTotalprice()),
//                        actData.get("depositpaid"), equalTo(expDataSingleBooking.isDepositpaid()),
                        actData.get("bookingdates.checkin"), equalTo(expDataSingleBooking.getBookingdates().getCheckin()),
                        actData.get("bookingdates.checkout"), equalTo(expDataSingleBooking.getBookingdates().getCheckout()),
                        actData.get("additionalneeds"), equalTo(expDataSingleBooking.getAdditionalneeds()));
    }

    @Test
    public void get05MatcherWithActPojo () {
        restfulSpec.pathParam("first", "11");
        Response response = given().contentType(ContentType.JSON).spec(restfulSpec).when().get("{first}");
        SingleBooking actData = response.as(SingleBooking.class);

        Bookingdates expDataBookingdates = new Bookingdates("2018-01-01", "2019-01-01");
        SingleBooking expDataSingleBooking = new SingleBooking("San", "Sui", 111, true, expDataBookingdates, "Breakfast");

        response.then().statusCode(200).contentType(ContentType.JSON).
                body(actData.getFirstname(), equalTo(expDataSingleBooking.getFirstname()),
                        actData.getLastname(), equalTo(expDataSingleBooking.getLastname()),
//                        actData.getTotalprice(), equalTo(expDataSingleBooking.getTotalprice()),
//                        actData.isDepositpaid(), equalTo(expDataSingleBooking.isDepositpaid()),
                        actData.getBookingdates().getCheckin(), equalTo(expDataSingleBooking.getBookingdates().getCheckin()),
                        actData.getBookingdates().getCheckout(), equalTo(expDataSingleBooking.getBookingdates().getCheckout()),
                        actData.getAdditionalneeds(), equalTo(expDataSingleBooking.getAdditionalneeds()));
    }

}