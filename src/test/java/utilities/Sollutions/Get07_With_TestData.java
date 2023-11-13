package utilities.Sollutions;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07_With_TestData extends JsonplaceholderBaseUrl {
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
    public void get07Matchers(){
        specJson.pathParam("1", "todos");
        Response response = given().spec(specJson).when().get("{1}");
        // System.out.println(response.asString());

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", hasSize(200),
                        "title", hasItem("dignissimos quo nobis earum saepe"),
                        "id", hasItems(111, 121, 131),
                        "title[3]", equalTo("et porro tempora"),
                        "title[199]", equalTo("ipsam aperiam voluptates qui"));
    }

    // JSONPATH
    @Test
    public void get07JsonPath(){
        specJson.pathParam("1", "todos");
        Response response = given().spec(specJson).when().get("{1}");
        response.then().
                statusCode(200).
                contentType(ContentType.JSON);

        JsonPath json = response.jsonPath();
        assertEquals(200, json.getList("title").size());
        assertTrue(json.getList("title").contains("dignissimos quo nobis earum saepe"));

        List<Integer> expIds = Arrays.asList(111, 121, 131);
        assertTrue(json.getList("id").containsAll(expIds));

        assertEquals("et porro tempora", json.getString("title[3]"));
        List<String> titleList = json.getList("title");
        assertEquals("et porro tempora", json.getList("title").get(3));
        assertEquals("ipsam aperiam voluptates qui", json.getList("title").get(titleList.size()-1));

        // Body Test With "SoftAssert" (ALWAYS USE ASSERTALL AT THE END)
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(titleList.size(), 200);
        softAssert.assertTrue(titleList.contains("dignissimos quo nobis earum saepe"));
        softAssert.assertTrue(response.jsonPath().getList("id").containsAll(expIds));
        softAssert.assertEquals(response.jsonPath().getString("title[3]"), "et porro tempora");
        softAssert.assertEquals(response.jsonPath().getList("title").get(titleList.size()-1), "ipsam aperiam voluptates qui");

        softAssert.assertAll();   // Tests will always show as passed if you do NOT use assertAll at the end. So, Soft Assert
        // tests are meaningless without "assertAll" at the end
    }

    // DE-SERIALIZATION
    @Test
    public void test07Deserialization() {
        specJson.pathParam("1", "todos");
        Response response = given().spec(specJson).when().get("{1}");
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

        HashMap<String, Object> expPlaceHolder_AllTodos_Object = new HashMap<>();
        expPlaceHolder_AllTodos_Object.put("statusCode", 200);
        expPlaceHolder_AllTodos_Object.put("contentType", "application/json; charset=utf-8");
        expPlaceHolder_AllTodos_Object.put("Server", "cloudflare");
        expPlaceHolder_AllTodos_Object.put("userId", 7);
        expPlaceHolder_AllTodos_Object.put("id", 123);
        expPlaceHolder_AllTodos_Object.put("title", "esse et quis iste est earum aut impedit");
        expPlaceHolder_AllTodos_Object.put("completed", false);

        HashMap<String, Object> actPlaceHolderTodosObjec = response.as(HashMap.class);
        System.out.println("actPlaceHolderTodosObjec = " + actPlaceHolderTodosObjec);

        Assert.assertEquals(expPlaceHolder_AllTodos_Object.get("statusCode"), response.statusCode());
        assertEquals(expPlaceHolder_AllTodos_Object.get("contentType"), response.getHeader("Content-Type"));
        assertEquals(expPlaceHolder_AllTodos_Object.get("Server"), response.getHeader("Server"));
        assertEquals(expPlaceHolder_AllTodos_Object.get("userId"), actPlaceHolderTodosObjec.get("userId"));
        assertEquals(expPlaceHolder_AllTodos_Object.get("title"), actPlaceHolderTodosObjec.get("title"));
        assertEquals(expPlaceHolder_AllTodos_Object.get("completed"), actPlaceHolderTodosObjec.get("completed"));
    }


}
