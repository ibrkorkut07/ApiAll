package tests.Sollutions;

import baseUrl.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import testData.JsonplaceholderTestdata;
import utilities.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get04b_With_TestDataClass extends JsonplaceholderBaseUrl {

    /*
        When I send a GET Request to
             https://jsonplaceholder.typicode.com/todos/2
        Then status code should be 200
        Then Among headers "Via"  should be "1.1 vegur"
                           "Server"  should be “cloudflare”
        And "completed" value should be false
        And "title” should be “quis ut nam facilis et officia qui”
        Then "userId"  should be 1
    */

    // MATCHERS WITH EXPECTED TEST DATA
    @Test
    public void get04Matchers_With_ExpData() {
        specJson.pathParams("first", "todos", "second", "2");
        Response response = given().spec(specJson).when().get("{first}/{second}");
        JsonplaceholderTestdata jsonplaceholderTestdata = new JsonplaceholderTestdata();
        response.then().statusCode(200).
                body("completed", equalTo(jsonplaceholderTestdata.todos2Data().get("completed")),
                        "title", equalTo(jsonplaceholderTestdata.todos2Data().get("title")),
                        "userId", equalTo(jsonplaceholderTestdata.todos2Data().get("userId"))).
                headers("Via", equalTo(jsonplaceholderTestdata.todos2Data().get("Via")),
                "Server", equalTo(jsonplaceholderTestdata.todos2Data().get("Server")));
    }

    // JSONPATH
    @Test
    public void get04_JsonPath_With_TestData() {

        specJson.pathParams("first", "todos", "second", "2");
        Response response = given().spec(specJson).when().get("{first}/{second}");
            /*
        When I send a GET Request to
             https://jsonplaceholder.typicode.com/todos/2
        Then status code should be 200
        Then Among headers "Via"  should be "1.1 vegur"
                           "Server"  should be “cloudflare”
        And "completed" value should be false
        And "title” should be “quis ut nam facilis et officia qui”
        Then "userId"  should be 1
    */
        JsonplaceholderTestdata expData = new JsonplaceholderTestdata();

        response.then().
                statusCode(200).
                headers("Via", equalTo(expData.todos2Data().get("Via")),
                        "Server", equalTo(expData.todos2Data().get("Server")));
        JsonPath json = response.jsonPath();

        assertEquals(expData.todos2Data().get("completed"), json.getBoolean("completed"));
        assertEquals(expData.todos2Data().get("title"), json.getString("title"));
        assertEquals(expData.todos2Data().get("userId"), json.getInt("userId"));

        // Body Test With "SoftAssert" (ALWAYS USE ASSERTALL AT THE END)
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(expData.todos2Data().get("completed"), json.getBoolean("completed"));
        softAssert.assertEquals(expData.todos2Data().get("title"), json.getString("title"));
        softAssert.assertEquals(expData.todos2Data().get("userId"), json.getInt("userId"));

        softAssert.assertAll();   // Tests will always show as passed if you do NOT use assertAll at the end. So, Soft Assert
        // SoftAssert tests are meaningless without "assertAll" at the end.
    }

    // DE-SERIALIZATION
    @Test
    public void test04Deserialization() {
        specJson.pathParams("first", "todos", "second", "2");
        Response response = given().spec(specJson).when().get("{first}/{second}");

        JsonplaceholderTestdata expData = new JsonplaceholderTestdata();
        HashMap<String, Object> actdata = response.as(HashMap.class);

        Assert.assertEquals(expData.todos2Data().get("statusCode"), response.statusCode());
        assertEquals(expData.todos2Data().get("Via"), response.getHeader("Via"));
        assertEquals(expData.todos2Data().get("Server"), response.getHeader("Server"));
        assertEquals(expData.todos2Data().get("userId"), actdata.get("userId"));
        assertEquals(expData.todos2Data().get("title"), actdata.get("title"));
        assertEquals(expData.todos2Data().get("completed"), actdata.get("completed"));
    }

    // OBJECT MAPPER
    @Test
    public void test04_ObjectMapper_With_TestData() {
        specJson.pathParams("first", "todos", "second", "2");
        Response response = given().spec(specJson).when().get("{first}/{second}");
            /*
        When I send a GET Request to
             https://jsonplaceholder.typicode.com/todos/2
        Then status code should be 200
        Then Among headers "Via"  should be "1.1 vegur"
                           "Server"  should be “cloudflare”
        And "completed" value should be false
        And "title” should be “quis ut nam facilis et officia qui”
        Then "userId"  should be 1
    */
        JsonplaceholderTestdata expData = new JsonplaceholderTestdata();
        response.then().
                statusCode(200).
                headers("Via", equalTo(expData.todos2Data().get("Via")),
                        "Server", equalTo(expData.todos2Data().get("Server")));

        String expPojoData = "{\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 2,\n" +
                "    \"title\": \"quis ut nam facilis et officia qui\",\n" +
                "    \"completed\": false\n" +
                "}";

        HashMap expJavaData = JsonUtil.convertJsonToJava(expPojoData, HashMap.class);
        HashMap actJavaData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);

        assertEquals(expJavaData.get("completed"), actJavaData.get("completed"));
        assertEquals(expJavaData.get("title"), actJavaData.get("title"));
        assertEquals(expJavaData.get("userId"), actJavaData.get("userId"));
    }


}