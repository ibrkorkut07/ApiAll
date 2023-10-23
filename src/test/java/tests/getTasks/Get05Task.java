package tests.getTasks;


public class Get05Task {
    /*
		When I send a GET request to REST API URL
		     https://restful-booker.herokuapp.com/booking/1
	    And Accepted content is “application/JSON”
	    Then HTTP Status Code should be 200
	    And Response format should be "application/JSON"
	    And "firstname" should be "Mary"
	    And "lastname" should be "Ericsson"
	    And "totalprice" should be 111
	    And "depositpaid" should be false
	    And "checkin" date should be "2022-02-19"
	    And "checkout" date should be "023-05-10"
	    And "additionalneeds" should be "Breakfast"   // not included always
   */


    // Use De-Serialization to convert Json response body to a Map.
    // Then by using the map and soft-assertion make assertions.


}
