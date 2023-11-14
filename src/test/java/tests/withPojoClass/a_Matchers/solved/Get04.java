package tests.withPojoClass.a_Matchers.solved;

import baseUrl.TestBaseUrls;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import pojos.SingleTodos;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get04 extends TestBaseUrls {

    /*
        When I send a GET Request to
             https://jsonplaceholder.typicode.com/todos/2
        Then status code should be 200
        Then Among headers "Via"  should be "1.1 vegur"
                           "Server"  should be “cloudflare”
        And  "userId"  should be 1
        And "title” should be “quis ut nam facilis et officia qui”
        Then "completed" value should be false
    */

    // Matchers & Pojo
    @Test
    public void get04MatchersWithPojo () {
        placeSpec.pathParam("first", "2");
        Response response = given().spec(placeSpec).when().get("/{first}");
        JsonPath json = response.jsonPath();

        SingleTodos expData = new SingleTodos(1, 2, "quis ut nam facilis et officia qui", false);

        response.then().statusCode(200).
                headers("Via", equalTo("1.1 vegur"),
                        "Server", equalTo("cloudflare")).
                body("userId", equalTo(expData.getUserId()),
                        "id", equalTo(expData.getId()),
                        "title", equalTo(expData.getTitle()),
                        "completed", equalTo(expData.isCompleted()) );
    }

}