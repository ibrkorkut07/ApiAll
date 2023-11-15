package tests.withPojoClass.a_Matchers.solved;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import pojos.Bookingdates;
import pojos.SingleBooking;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Post02 extends TestBaseUrls {

    /*
    	 When I send POST Request to
    	      https://restful-booker.herokuapp.com/booking ==> spec02
		      with the following request body
			  {
			    "firstname": "Ali",
				"lastname": "Can",
				"totalprice": 111,
				"depositpaid": true,
				"bookingdates": {
						        "checkin": "2020-09-16",
						        "checkout": "2020-09-18"
				        	    },
				"additionalneeds": "Wifi"
					    }
		 Then
			   Status code is 200
			   Content Type is "application/json"
			   Assert all response body details

		       Note: Create Request Body by using Map
	*/
    @Test
    public void post02 () {

        Bookingdates bookingdates = new Bookingdates("2020-09-16", "2020-09-18");
        SingleBooking testBody = new SingleBooking("Ali", "Can", 111, true, bookingdates, "Wifi");
        Response postRequest = given().contentType(ContentType.JSON).spec(restfulSpec).body(testBody).when().post();
        postRequest.prettyPrint();

        postRequest.then().statusCode(200).contentType(ContentType.JSON);

        restfulSpec.queryParam("firstname", "Ali");
        Response response = given().contentType(ContentType.JSON).spec(restfulSpec).when().get();
        response.prettyPrint();
        SingleBooking expData = new SingleBooking("Ali", "Can", 111, true, bookingdates, "Wifi");

        response.then().statusCode(200).contentType(ContentType.JSON).
                body("firstname", equalTo(expData.getFirstname()),
                    "lastname", equalTo(expData.getLastname()),
                        "totalprice", equalTo(expData.getTotalprice()),
                        "depositpaid", equalTo(expData.isDepositpaid()),
                        "bookingdates.checkin", equalTo(expData.getBookingdates().getCheckin()),
                        "bookingdates.checkout", equalTo(expData.getBookingdates().getCheckout()),
                        "additionalneeds", equalTo(expData.getAdditionalneeds())  );
    }

}
