package completedTestsWithPojo.tests;


public class Get1 {


    /*
	    Positive Scenario
	    When I send a GET Request to https://restful-booker.herokuapp.com/booking
	    And I accept type "application/json"  ==> Means API is accepting data just in Json Format
A	    And content type should be "application/json" ==> Response body must be in Json format
	*/



    /*
        Positive Scenario
        When I send a GET Request to https://restful-booker.herokuapp.com/booking/5
        Then status code should be 200
        And content type should be "application/json"
        And Status Line should be HTTP/1.1 200 OK
    */



    /*
	    Negative Scenario
	    When I send a GET Request to https://restful-booker.herokuapp.com/booking/1001
	    Then status code should be 404
	    And Response Body contains "Not Found"
	    And Response Body does not contain "Suleyman"
	*/


}