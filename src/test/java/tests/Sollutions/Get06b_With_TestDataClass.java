package tests.Sollutions;

import baseUrl.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import testData.JsonplaceholderTestdata;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06b_With_TestDataClass extends JsonplaceholderBaseUrl {

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

    // MATCHERS WITH EXPECTED TEST DATA
    @Test
    public void get06Matchers_With_TestData(){
        specJson.pathParams("1", "todos", "2", "123");
        Response response = given().spec(specJson).contentType(ContentType.JSON).when().get("{1}/{2}");
        JsonplaceholderTestdata jsonplaceholderTestdata = new JsonplaceholderTestdata();

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                header("Server", equalTo(jsonplaceholderTestdata.todos123Data().get("Server"))).
                body("userId", equalTo(jsonplaceholderTestdata.todos123Data().get("userId")),
                        "id", equalTo(jsonplaceholderTestdata.todos123Data().get("id")),
                        "title", equalTo(jsonplaceholderTestdata.todos123Data().get("title")),
                        "completed", equalTo(jsonplaceholderTestdata.todos123Data().get("completed")));
    }

    // JSONPATH
    @Test
    public void get06_JsonPath_With_TestData(){
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

        JsonplaceholderTestdata expData = new JsonplaceholderTestdata();

        response.then().
                statusCode((int) expData.todos123Data().get("statusCode")).
                contentType((String) expData.todos123Data().get("contentType")).
                header("Server", equalTo(expData.todos123Data().get("Server")));

        JsonPath json = response.jsonPath();
        assertEquals(expData.todos123Data().get("userId"), json.getInt("userId"));
        assertEquals(expData.todos123Data().get("id"), json.getInt("id"));
        assertEquals(expData.todos123Data().get("title"), json.getString("title"));
        assertEquals(expData.todos123Data().get("completed"), json.getBoolean("completed"));
        // OR --> assertFalse(json.getBoolean("completed"));

        // Body Test With "SoftAssert" (ALWAYS USE ASSERTALL AT THE END)
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expData.todos123Data().get("userId"), json.getInt("userId"));
        softAssert.assertEquals(expData.todos123Data().get("id"), json.getInt("id"));
        softAssert.assertEquals(expData.todos123Data().get("title"), json.getString("title"));
        softAssert.assertEquals(expData.todos123Data().get("completed"), json.getBoolean("completed"));
        softAssert.assertAll();  // Tests will always show as passed if you do NOT use assertAll at the end. So, Soft Assert
        // tests are meaningless without "assertAll" at the end
    }

    // DE-SERIALIZATION
    @Test
    public void test06_Deserialization_With_TestData() {
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
        JsonplaceholderTestdata expData = new JsonplaceholderTestdata();
        HashMap<String, Object> actdata = response.as(HashMap.class);

        response.then().
                statusCode((int) expData.todos123Data().get("statusCode")).
                contentType((String) expData.todos123Data().get("contentType")).
                header("Server", equalTo(expData.todos123Data().get("Server")));

        Assert.assertEquals(expData.todos123Data().get("statusCode"), response.statusCode());   // alternative to Matchers
        assertEquals(expData.todos123Data().get("contentType"), response.getHeader("Content-Type"));   // alternative to Matchers
        assertEquals(expData.todos123Data().get("Server"), response.getHeader("Server"));   // alternative to Matchers
        assertEquals(expData.todos123Data().get("userId"), actdata.get("userId"));
        assertEquals(expData.todos123Data().get("id"), actdata.get("id"));
        assertEquals(expData.todos123Data().get("title"), actdata.get("title"));
        assertEquals(expData.todos123Data().get("completed"), actdata.get("completed"));
    }

    // OBJECT MAPPER
    @Test
    public void test06_ObjectMapper_With_TestData() {
        specJson.pathParams("first", "todos", "second", "123");
        Response response = given().spec(specJson).when().get("{first}/{second}");
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
        JsonplaceholderTestdata expdata = new JsonplaceholderTestdata();
        HashMap<String, Object> actData = response.as(HashMap.class);

        assertEquals(expdata.todos123Data().get("statusCode"), response.statusCode());
        assertEquals(expdata.todos123Data().get("contentType"), response.contentType());

        assertEquals(expdata.todos123Data().get("Server"), response.getHeader("Server"));

        assertEquals(expdata.todos123Data().get("userId"), actData.get("userId"));
        assertEquals(expdata.todos123Data().get("id"), actData.get("id"));
        assertEquals(expdata.todos123Data().get("title"), actData.get("title"));
        assertEquals(expdata.todos123Data().get("completed"), actData.get("completed"));
    }



}