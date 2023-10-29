package tests.tasks;

import baseUrl.RestfulBaseUrl;

public class Get02 extends RestfulBaseUrl {

    /*
        When I send a GET Request to
             https://restful-booker.herokuapp.com/booking/5
		Then HTTP Status code should be "200"
		And  Content type should be in "JSON" format
		And  Status Line should be "HTTP/1.1 200 OK"
		And  response body does not contain "Not Found"
	    And  response body contains "bookingdates"
	*/

}