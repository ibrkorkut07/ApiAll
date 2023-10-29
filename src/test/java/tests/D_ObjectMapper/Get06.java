package tests.D_ObjectMapper;

import baseUrl.JsonplaceholderBaseUrl;

public class Get06 extends JsonplaceholderBaseUrl {

    /*
		When I send a GET request to REST API URL
			 https://jsonplaceholder.typicode.com/todos/123
	    Then
		     HTTP Status Code should be 200
		     And "Server" in Headers should be "cloudflare"
		     And response content type is “application/JSON”
		     And "userId" should be 7
		     And "id"  should be 123
		     And "title" should be "esse et quis iste est earum aut impedit"
			 And "completed" should be false
    */

    /* INFO:
	    After the Base URL if you type something together with "/" like "/123",
	    it is called "path param". Path param makes the source small.
	*/

}