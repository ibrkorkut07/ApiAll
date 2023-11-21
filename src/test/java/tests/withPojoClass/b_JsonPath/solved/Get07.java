package tests.withPojoClass.b_JsonPath.solved;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    public void get07JsonPath () {
        Response response = given().spec(placeSpec).when().get();
        response.then().
                statusCode(200).
                contentType(ContentType.JSON);

        JsonPath json = response.jsonPath();

        assertEquals(200, json.getList("title").size());
        assertTrue(json.getList("title").contains("dignissimos quo nobis earum saepe"));
        List<Integer> expIdList = new ArrayList<>();
        expIdList.add(111);
        expIdList.add(121);
        expIdList.add(131);
        assertTrue(json.getList("id").containsAll(expIdList));
        assertEquals(json.getString("title[3]"), "et porro tempora");
        assertEquals(json.getString("title[-1]"), "ipsam aperiam voluptates qui");
    }

}