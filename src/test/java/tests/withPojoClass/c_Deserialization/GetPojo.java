package tests.withPojoClass.c_Deserialization;

import baseUrl.TestBaseUrls;

public class GetPojo extends TestBaseUrls {
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

}
