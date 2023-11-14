package tests.withPojoClass.a_Matchers;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Bookingdates;
import pojos.SingleTodos;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06 extends TestBaseUrls {

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

    @Test
    public void get06MatchersWithPojo () {
        placeSpec.pathParam("first", "123");
        Response response = given().spec(placeSpec).when().get("/{first}");

        SingleTodos expData = new SingleTodos(7, 123, "esse et quis iste est earum aut impedit", false);

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                headers("Server", equalTo("cloudflare")).
                body("userId", equalTo(expData.getUserId()),
                        "id", equalTo(expData.getId()),
                        "title", equalTo(expData.getTitle()),
                        "completed", equalTo(expData.isCompleted())  );
    }

}