package tests.withPojoClass.c_Deserialization;

import baseUrl.TestBaseUrls;

public class Get02 extends TestBaseUrls {

    /*
        When I send a GET Request to
             https://restful-booker.herokuapp.com/booking/5
		Then HTTP Status code should be "200"
		And  Content type should be in "JSON" format
		And  Status Line should be "HTTP/1.1 200 OK"
		And  Response body does not contain "Not Found"
	    And  Response body contains "bookingdates"
	*/

}