package tests.Sollutions;

import baseUrl.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get04d_MoreDynamic extends JsonplaceholderBaseUrl {

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

    // MATCHERS
    @Test
    public void get04Matchers() {
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

    // JSONPATH
    @Test
    public void get04JsonPath() {
        specJson.pathParams("first", "todos", "second", "2");
        Response response = given().spec(specJson).when().get("{first}/{second}");
        response.then().
                statusCode(200).
                headers("Via", equalTo("1.1 vegur"),
                        "Server", equalTo("cloudflare"));

        JsonPath json = response.jsonPath();
        assertEquals(false, json.getBoolean("completed"));
        // OR --> Assert.assertFalse(json.getBoolean("completed"));
        assertEquals("quis ut nam facilis et officia qui", json.getString("title"));
        assertEquals(1, json.getInt("userId"));

        // Body Test With SoftAssert (ALWAYS USE ASSERTALL AT THE END)
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.jsonPath().getBoolean("completed"), false);
        softAssert.assertEquals(response.jsonPath().getString("title"), "quis ut nam facilis et officia qui");
        softAssert.assertEquals(response.jsonPath().getString("userId"), 1);
        softAssert.assertAll();   // Tests will always show as passed if you do NOT use assertAll at the end. So, Soft Assert
        // tests are meaningless without "assertAll" at the end.
    }


    // DE-SERIALIZATION
    @Test
    public void test04Deserialization() {
        specJson.pathParams("first", "todos", "second", "2");
        Response response = given().spec(specJson).when().get("{first}/{second}");

        HashMap<String, Object> expPlaceHolderTodos_2_Object = new HashMap<>();
        expPlaceHolderTodos_2_Object.put("statusCode", 200);
        expPlaceHolderTodos_2_Object.put("Via", "1.1 vegur");
        expPlaceHolderTodos_2_Object.put("Server", "cloudflare");
        expPlaceHolderTodos_2_Object.put("userId", 1);
        expPlaceHolderTodos_2_Object.put("id", 2);
        expPlaceHolderTodos_2_Object.put("title", "quis ut nam facilis et officia qui");
        expPlaceHolderTodos_2_Object.put("completed", false);

        HashMap<String, Object> actPlaceHolderTodosObjec = response.as(HashMap.class);
        System.out.println("actPlaceHolderTodosObjec = " + actPlaceHolderTodosObjec);

        Assert.assertEquals(expPlaceHolderTodos_2_Object.get("statusCode"), response.statusCode());
        assertEquals(expPlaceHolderTodos_2_Object.get("Via"), response.getHeader("Via"));
        assertEquals(expPlaceHolderTodos_2_Object.get("Server"), response.getHeader("Server"));
        assertEquals(expPlaceHolderTodos_2_Object.get("userId"), actPlaceHolderTodosObjec.get("userId"));
        assertEquals(expPlaceHolderTodos_2_Object.get("title"), actPlaceHolderTodosObjec.get("title"));
        assertEquals(expPlaceHolderTodos_2_Object.get("completed"), actPlaceHolderTodosObjec.get("completed"));
    }


}