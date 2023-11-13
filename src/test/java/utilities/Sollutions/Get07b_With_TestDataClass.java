package utilities.Sollutions;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import testData.JsonplaceholderTestdata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07b_With_TestDataClass extends JsonplaceholderBaseUrl {
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
    // MATCHERS WITH EXPECTED TEST DATA
    @Test
    public void get07Matchers_With_ExpData(){
        specJson.pathParam("1", "todos");
        Response response = given().spec(specJson).when().get("{1}");
        // System.out.println(response.asString());
        JsonplaceholderTestdata jsonplaceholderTestdata = new JsonplaceholderTestdata();

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", hasSize((Integer) jsonplaceholderTestdata.todosData().get("totalTitles")),
                        "title", hasItem(jsonplaceholderTestdata.todosData().get("expTitle")),
                        "id", hasItem(jsonplaceholderTestdata.todosData().get("expId111")),
                        "id", hasItem(jsonplaceholderTestdata.todosData().get("expId121")),
                        "id", hasItem(jsonplaceholderTestdata.todosData().get("expId131")),
                        // or test all 3 at once with "hasItems"
                        // "id", hasItems(jsonplaceholderTestdata.todosData().get("expId111"), jsonplaceholderTestdata.todosData().get("expId121"), jsonplaceholderTestdata.todosData().get("expId131")),"id", hasItems(jsonplaceholderTestdata.todosData().get("expId111"), jsonplaceholderTestdata.todosData().get("expId121"), jsonplaceholderTestdata.todosData().get("expId131")),"id", hasItems(jsonplaceholderTestdata.todosData().get("expId111"), jsonplaceholderTestdata.todosData().get("expId121"), jsonplaceholderTestdata.todosData().get("expId131")),
                        "title[3]", equalTo(jsonplaceholderTestdata.todosData().get("4th title")),
                        "title[199]", equalTo(jsonplaceholderTestdata.todosData().get("last title")));
    }

    // JSONPATH
    @Test
    public void get07JsonPath(){
        specJson.pathParam("1", "todos");
        Response response = given().spec(specJson).when().get("{1}");
        response.then().
                statusCode(200).
                contentType(ContentType.JSON);

        JsonplaceholderTestdata expData = new JsonplaceholderTestdata();
        JsonPath json = response.jsonPath();

        assertEquals(200, json.getList("title").size());
        assertTrue(json.getList("title").contains(expData.todosData().get("title")));

        List<Integer> expIds = Arrays.asList(111, 121, 131);
        assertTrue(json.getList("id").containsAll(expIds));

        assertEquals(expData.todosData().get("4th title"), json.getString("title[3]"));
        //   OR
        List<String> titleList = json.getList("title");
        assertEquals(expData.todosData().get("4th title"), json.getList("title").get(3));

        assertEquals(expData.todosData().get("last title"), json.getList("title").get(titleList.size()-1));

        // Body Test With "SoftAssert" (ALWAYS USE ASSERTALL AT THE END)
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(titleList.size(), 200);
        softAssert.assertTrue(titleList.contains(expData.todosData().get("expTitle")));
        softAssert.assertTrue(response.jsonPath().getList("id").containsAll(expIds));
        softAssert.assertEquals(response.jsonPath().getString("title[3]"), expData.todosData().get("4th title"));
        softAssert.assertEquals(response.jsonPath().getList("title").get(titleList.size()-1), expData.todosData().get("last title"));

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
        response.then().
                statusCode(200).
                contentType(ContentType.JSON);

        JsonplaceholderTestdata expData = new JsonplaceholderTestdata();
        List actData = response.as(List.class);

        // System.out.println("actData = " + actData);
        List titleList = null;
        System.out.println("actData.size() = " + actData.size());

        for (int i = 0; i < actData.size(); i++) {
            titleList.add(  ((List) ((HashMap<?, ?>) actData.get(i)).get("title"))  );
        }

        System.out.println("titleList = " + titleList);

    }


}
