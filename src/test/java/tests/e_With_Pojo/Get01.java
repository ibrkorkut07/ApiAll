package tests.e_With_Pojo;

import baseUrl.RestfulBaseUrl;

public class Get01 extends RestfulBaseUrl {

    /*
	    Positive Scenario
	    When I send a GET Request to
	         https://restful-booker.herokuapp.com/booking
	    And I accept type "application/json"  ==> Means API is accepting data just in Json Format
	    And content type should be "application/json" ==> Response body must be in Json format
        And statusLine should be "HTTP/1.1 200"
	*/


    /*
	    Negative Scenario
	    When I send a GET Request to
	         https://restful-booker.herokuapp.com/booking/1001
	    Then status code should be 404
	    And Response Body contains "Not Found"
	    And Response Body does not contain "Suleyman"
	*/

    /*  Extra task
	    Positive Scenario
	    When I send a GET Request to
	         https://restful-booker.herokuapp.com/booking
	    And I accept type "application/json"  ==> Means API is accepting data just in Json Format
	    And content type should be "application/json" ==> Response body must be in Json format
	*/

}
