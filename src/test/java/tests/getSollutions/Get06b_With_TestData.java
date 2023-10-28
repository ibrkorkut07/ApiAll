package tests.getSollutions;

import baseUrl.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06b_With_TestData extends JsonplaceholderBaseUrl {

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

    // MATCHERS
    @Test
    public void get06Matchers(){
        specJson.pathParams("1", "todos", "2", "123");
        Response response = given().spec(specJson).contentType(ContentType.JSON).when().get("{1}/{2}");
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                header("Server", equalTo("cloudflare")).
                body("userId", equalTo(7),
                        "id", equalTo(123),
                        "title", equalTo("esse et quis iste est earum aut impedit"),
                        "completed", equalTo(false));

    }

    // JSONPATH
    @Test
    public void get06JsonPath(){
        specJson.pathParams("1", "todos", "2", "123");
        Response response = given().spec(specJson).contentType(ContentType.JSON).when().get("{1}/{2}");
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                header("Server", equalTo("cloudflare"));

        JsonPath json = response.jsonPath();
        assertEquals(7, json.getInt("userId"));   // value of "id" is Integer --> getInt
        assertEquals(123, json.getInt("id"));
        assertEquals("esse et quis iste est earum aut impedit", json.getString("title"));
        assertEquals(false, json.getBoolean("completed"));
        // OR --> assertFalse(json.getBoolean("completed"));

        // Body Test With "SoftAssert" (ALWAYS USE ASSERTALL AT THE END)
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.jsonPath().getInt("userId"), 7);
        softAssert.assertEquals(response.jsonPath().getInt("id"), 123);
        softAssert.assertEquals(response.jsonPath().getString("title"), "esse et quis iste est earum aut impedit");
        softAssert.assertEquals(response.jsonPath().getBoolean("completed"), false);

        softAssert.assertAll();   // Tests will always show as passed if you do NOT use assertAll at the end. So, Soft Assert
        // tests are meaningless without "assertAll" at the end
    }

    // DE-SERIALIZATION
    @Test
    public void test06Deserialization() {
        specJson.pathParams("1", "todos", "2", "123");
        Response response = given().spec(specJson).contentType(ContentType.JSON).when().get("{1}/{2}");
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

        HashMap<String, Object> expPlaceHolderTodos_123_Object = new HashMap<>();
        expPlaceHolderTodos_123_Object.put("statusCode", 200);
        expPlaceHolderTodos_123_Object.put("contentType", "application/json; charset=utf-8");
        expPlaceHolderTodos_123_Object.put("Server", "cloudflare");
        expPlaceHolderTodos_123_Object.put("userId", 7);
        expPlaceHolderTodos_123_Object.put("id", 123);
        expPlaceHolderTodos_123_Object.put("title", "esse et quis iste est earum aut impedit");
        expPlaceHolderTodos_123_Object.put("completed", false);

        HashMap<String, Object> actPlaceHolderTodosObjec = response.as(HashMap.class);
        System.out.println("actPlaceHolderTodosObjec = " + actPlaceHolderTodosObjec);

        Assert.assertEquals(expPlaceHolderTodos_123_Object.get("statusCode"), response.statusCode());
        assertEquals(expPlaceHolderTodos_123_Object.get("contentType"), response.getHeader("Content-Type"));
        assertEquals(expPlaceHolderTodos_123_Object.get("Server"), response.getHeader("Server"));
        assertEquals(expPlaceHolderTodos_123_Object.get("userId"), actPlaceHolderTodosObjec.get("userId"));
        assertEquals(expPlaceHolderTodos_123_Object.get("title"), actPlaceHolderTodosObjec.get("title"));
        assertEquals(expPlaceHolderTodos_123_Object.get("completed"), actPlaceHolderTodosObjec.get("completed"));
    }



}