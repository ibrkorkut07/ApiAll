package tests.a_Matchers;

import baseUrl.JsonplaceholderBaseUrl;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends JsonplaceholderBaseUrl {
    /*
        When I send a GET Request to
             https://jsonplaceholder.typicode.com/todos
		Then
			 HTTP Status code should be "200"
			 And Content type should be in "JSON" format
			 And there should be 200 "title"
			 And "dignissimos quo nobis earum saepe" should be one of the "title"s
			 And 111, 121, and 131 should be among the "id"s
			 And 4th title is "et porro tempora"
			 And last title is "ipsam aperiam voluptates qui"
    */

}
