package tests.withPojoClass.a_Matchers.solved;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends TestBaseUrls {
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

    // It is useless to use Pojo here
    @Test
    public void get07Matchers () {
        Response response = given().spec(placeSpec).when().get();
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", hasSize(200),
                        "title", hasItem("dignissimos quo nobis earum saepe"),
                        "id", hasItems(111, 121, 131),
                        "title[3]", equalTo("et porro tempora"),
                        "title[-1]", equalTo("ipsam aperiam voluptates qui")    );
    }

}
