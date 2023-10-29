package tests.a_Matchers;

public class Post02 {

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

}
