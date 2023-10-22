package completedTestsWithoutPojo.tests;

import completedTestsWithoutPojo.baseUrl.JsonplaceholderBaseUrl;
import completedTestsWithoutPojo.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import completedTestsWithoutPojo.pojos.JsonTodos123Pojo;
import completedTestsWithoutPojo.testData.JsonplaceholderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

public class M05_GetRequest05 extends JsonplaceholderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda gelen response’un
        status kodunun 200,
        content type'inin "application/json"
    Headers'daki
        "Server" in "cloudflare"
    response body'deki
        "userId"'nin 7,
        "id" nin 123,
        "title" in "esse et quis iste est earum aut impedit" ve
        "completed" bolumunun false oldugunu test edin
    */

    @Test
    public void test00 () {
        spec03.pathParams("p1", "todos", "p2", "123");
        Response response = given().spec(spec03).when().get("{p1}/{p2}");
    }

    // Matchers without Testdata class
    @Test
    public void test01(){
        spec03.pathParams("p1", "todos", "p2", "123");
        Response response = given().spec(spec03).when().get("{p1}/{p2}");

        response.then().assertThat().statusCode(200).contentType("application/json").
                headers("Server", equalTo("cloudflare")).
                body("userId", equalTo(7),
                        "id", equalTo(123),
                        "title", equalTo("esse et quis iste est earum aut impedit"),
                        "completed", equalTo(false));
    }

    // Matchers with Testdata class
    @Test
    public void test02(){
        JsonplaceholderTestData testDataObject = new JsonplaceholderTestData();
        HashMap<String, Object> expData = testDataObject.todos123Data();

        spec03.pathParams("p1", "todos", "p2", "123");
        Response response = given().spec(spec03).when().get("{p1}/{p2}");

        response.then().assertThat().statusCode((Integer) expData.get("statusCode")).
                contentType(ContentType.JSON).
                headers("Server", equalTo(expData.get("Server"))).
                body("userId", equalTo(expData.get("userId")),
                        "title", equalTo(expData.get("title")),
                        "completed", equalTo(expData.get("completed")));

    /*
        todos123ExpData.put("statusCode", 200);
        todos123ExpData.put("contentType", "application/json; charset=utf-8");
        todos123ExpData.put("Server", "cloudflare");
        todos123ExpData.put("userId", 7);
        todos123ExpData.put("id", 123);
        todos123ExpData.put("title", "esse et quis iste est earum aut impedit");
        todos123ExpData.put("completed", false);
        return todos123ExpData;
     */
    }

    // Jsonpath
    @Test
    public void test03(){
        JsonplaceholderTestData testDataObject = new JsonplaceholderTestData();
        HashMap<String, Object> expData = testDataObject.todos123Data();

        spec03.pathParams("p1", "todos", "p2", "123");
        Response response = given().spec(spec03).when().get("{p1}/{p2}");

        response.then().assertThat().statusCode(200).contentType("application/json").
                headers("Server",expData.get("Server"));

        JsonPath json = response.jsonPath();

        assertEquals(expData.get("userId"), json.getInt("userId"));
        assertEquals(expData.get("id"), json.getInt("id"));
        assertEquals(expData.get("title"), json.getString("title"));
    }

    // Deserialization
    @Test
    public void test04(){
        spec03.pathParams("p1", "todos", "p2", "123");
        Response response = given().spec(spec03).when().get("{p1}/{p2}");

        JsonplaceholderTestData jsonplaceholderTestData = new JsonplaceholderTestData();
        HashMap<String, Object> expData = jsonplaceholderTestData.todos123Data();
        HashMap<String, Object> actDataMap = response.as(HashMap.class);

        assertEquals(expData.get("statusCode"), response.statusCode());
        assertEquals(expData.get("contentType"), response.contentType());
        assertEquals(expData.get("Server"), response.headers().getValue("Server"));
        assertEquals(expData.get("id"), actDataMap.get("id"));
        assertEquals(expData.get("title"), actDataMap.get("title"));
        assertEquals(expData.get("completed"), actDataMap.get("completed"));

        /*  todos123ExpData.put("Server", "cloudflare");
          todos123ExpData.put("userId", 7);
          todos123ExpData.put("id", 123);
          todos123ExpData.put("title", "esse et quis iste est earum aut impedit");
          todos123ExpData.put("completed", false);

            accept type'i "application/json" olan GET request'i yolladigimda gelen response’un
            status kodunun 200,
            content type'inin "application/json"
            Headers'daki
                "Server" in "cloudflare"
            response body'deki
                "userId"'nin 7,
                "id" nin 123,
                "title" in "esse et quis iste est earum aut impedit" ve
                "completed" bolumunun false oldugunu test edin  */
    }

    // Pojo
    @Test
    public void test05(){
        //1) URL
        spec03.pathParams("p1", "todos", "p2", "123");

        //2) EXPECTED DATA
        JsonTodos123Pojo expTodos123Data = new JsonTodos123Pojo(200, "application/json; charset=utf-8", "cloudflare",
                7, 123, "esse et quis iste est earum aut impedit", false);

        //3) REQUEST & RESPONSE
        Response response = given().contentType(ContentType.JSON).spec(spec03).when().get("{p1}/{p2}");

        //4) ASSERTION
        JsonTodos123Pojo actdata = response.as(JsonTodos123Pojo.class);

        assertEquals(expTodos123Data.getStatusCode(), response.statusCode());
        assertEquals(expTodos123Data.getContentType(), response.contentType());
        assertEquals(expTodos123Data.getServer(), response.headers().getValue("Server"));
        assertEquals(expTodos123Data.getUserId(), actdata.getUserId());
        assertEquals(expTodos123Data.getId(), actdata.getId());
        assertEquals(expTodos123Data.getTitle(), actdata.getTitle());
        assertEquals(expTodos123Data.isCompleted(), actdata.isCompleted());
    }

    // ObjectMapper
    @Test
    public void test06(){

        //1) URL
        spec03.pathParams("p1", "todos", "p2", "123");

        //2) EXPECTED DATA
        String jsonData = "{\n" +
                "            \"userId\": 7,\n" +
                "                \"id\": 123,\n" +
                "                \"title\": \"esse et quis iste est earum aut impedit\",\n" +
                "                \"completed\": false\n" +
                "        }";

        HashMap expData = JsonUtil.convertJsonToJava(jsonData, HashMap.class);

        //3) REQUEST & RESPONSE
        Response response = given().spec(spec03).when().get("{p1}/{p2}");

        //4) ASSERTION
        HashMap actData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);

        assertEquals(expData.get("userId"), actData.get("userId"));
        assertEquals(expData.get("id"), actData.get("id"));
        assertEquals(expData.get("title"), actData.get("title"));
        assertEquals(expData.get("completed"), actData.get("completed"));
    }
}
