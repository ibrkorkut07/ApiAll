package tests.get;

import baseUrl.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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
    // MATCHERS
    @Test
    public void matcherTest(){
        specJson.pathParam("1", "todos");
        Response response = given().spec(specJson).when().get("{1}");
        // System.out.println(response.asString());

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", hasItem("dignissimos quo nobis earum saepe"),
                        "id", hasItems(111, 121, 131),
                        "title[3]", equalTo("et porro tempora"),
                        "title[199]", equalTo("ipsam aperiam voluptates qui"));

    }

}
