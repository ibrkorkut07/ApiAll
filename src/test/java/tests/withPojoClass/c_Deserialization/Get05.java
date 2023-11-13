package tests.withPojoClass.c_Deserialization;

import baseUrl.TestBaseUrls;

public class Get05 extends TestBaseUrls {
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

}