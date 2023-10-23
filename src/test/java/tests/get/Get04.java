package tests.get;

import baseUrl.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get04 extends JsonplaceholderBaseUrl {

    /*
        When I send a GET Request to
             https://jsonplaceholder.typicode.com/todos/2
        Then status code should be 200
        And "completed" value should be false
        And "title” should be “quis ut nam facilis et officia qui”
        Then "userId"  should be 1
        Then Among headers "Via"  should be "1.1 vegur"
                           "Server"  should be “cloudflare”
    */

    @Test
    public void get04() {
        specJson.pathParams("first", "todos", "second", "2");
        Response response = given().spec(specJson).when().get("{first}/{second}");
        response.then().
                statusCode(200).
                body("completed", equalTo(false),
                        "title", equalTo("quis ut nam facilis et officia qui"),
                        "userId", equalTo(1)).
                headers("Via", equalTo("1.1 vegur"),
                        "Server", equalTo("cloudflare"));
    }

}