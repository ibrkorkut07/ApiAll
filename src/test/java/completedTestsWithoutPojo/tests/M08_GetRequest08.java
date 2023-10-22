package completedTestsWithoutPojo.tests;

import completedTestsWithoutPojo.baseUrl.JsonplaceholderBaseUrl;
import completedTestsWithoutPojo.utilities.JsonUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import completedTestsWithoutPojo.pojos.JsonTodos2Pojo;
import completedTestsWithoutPojo.testData.JsonplaceholderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class M08_GetRequest08 extends JsonplaceholderBaseUrl {

    /*
    https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
    Dönen response un
    Status kodunun 200, dönen body de,
    "completed": değerinin false
    "title”: değerinin “quis ut nam facilis et officia qui”
    "userId" sinin 1 ve
    header değerlerinden
    "Via" değerinin “1.1 vegur” ve
    "Server" değerinin “cloudflare” olduğunu test edin…
    */

    @Test
    public void test00 () {
        spec03.pathParams("p1", "todos", "p2", "2");
        Response response = given().spec(spec03).when().get("{p1}/{p2}");

        String jsonData = "{\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 2,\n" +
                "    \"title\": \"quis ut nam facilis et officia qui\",\n" +
                "    \"completed\": false\n" +
                "}";

        HashMap expData = JsonUtil.convertJsonToJava(jsonData, HashMap.class);
        HashMap actData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);

        assertEquals(expData.get("completed"), actData.get("completed"));
        assertEquals(expData.get("title"), actData.get("title"));
        assertEquals(expData.get("userId"), actData.get("userId"));

        assertEquals("cloudflare", actData.get("Server"));
    }

    // Matchers without Testdata class
    @Test
    public void test01(){
        spec03.pathParams("p1", "todos", "p2", "2");
        Response response = given().spec(spec03).when().get("{p1}/{p2}");

        // without expData from JsonplaceholderTestData
        response.then().assertThat().statusCode(200).body("completed", equalTo(false),
                "title", equalTo("quis ut nam facilis et officia qui"),
                "userId", equalTo(1)).
                headers("Via", equalTo("1.1 vegur"),
                "Server", equalTo("cloudflare"));

        // wit expData from JsonplaceholderTestData
        JsonplaceholderTestData jsonplaceholderTestData = new JsonplaceholderTestData();
        HashMap<String, Object> expData = jsonplaceholderTestData.todos2Data();
        response.then().assertThat().statusCode(200).headers("Via", equalTo(expData.get("Via")),
                        "Server", equalTo(expData.get("Server"))) .
                body("completed", equalTo(expData.get("completed")),
                        "title", equalTo(expData.get("title")),
                        "userId", equalTo(expData.get("userId")));
    }

    // Matchers with Testdata class
    @Test
    public void test02(){
        spec03.pathParams("p1", "todos", "p2", "2");
        Response response = given().spec(spec03).when().get("{p1}/{p2}");

        JsonplaceholderTestData todos2Object = new JsonplaceholderTestData();
        HashMap<String, Object> expData = todos2Object.todos2Data();

        response.then().assertThat().statusCode(equalTo(expData.get("statusCode"))).
                        body("completed", equalTo(expData.get("completed")),
                        "title", equalTo(expData.get("title")),
                        "userId", equalTo(expData.get("userId"))).
                headers("Via", equalTo(expData.get("Via")),
                        "Server", equalTo(expData.get("Server")));
    }

    // Jsonpath
    @Test
    public void test03(){
        spec03.pathParams("p1", "todos", "p2", "2");
        Response response = given().spec(spec03).when().get("{p1}/{p2}");

        JsonPath json = response.jsonPath();
        assertEquals(false, json.getBoolean("completed"));
        assertEquals("quis ut nam facilis et officia qui", json.getString("title"));
        assertEquals(1, json.getInt("userId"));
    }

    // Deserialization
    @Test
    public void test04(){
        spec03.pathParams("p1", "todos", "p2", "2");
        Response response = given().spec(spec03).when().get("{p1}/{p2}");
        JsonplaceholderTestData jsonplaceholderTestData = new JsonplaceholderTestData();
        HashMap<String, Object> expDataMap = jsonplaceholderTestData.todos2Data();
        HashMap<String, Object> actData = response.as(HashMap.class);

        // System.out.println("actData = " + actData);

        Assert.assertEquals(expDataMap.get("statusCode"), response.statusCode());
        Assert.assertEquals(expDataMap.get("completed"), actData.get("completed"));
        Assert.assertEquals(expDataMap.get("title"), actData.get("title"));
        Assert.assertEquals(expDataMap.get("userId"), actData.get("userId"));
        Assert.assertEquals(expDataMap.get("Via"), response.headers().getValue("Via"));
        Assert.assertEquals(expDataMap.get("Server"), response.headers().getValue("Server"));

        /* HashMap<String, Object> todos2ExpData = new HashMap<>();
        todos2ExpData.put("statusCode", 200);
        todos2ExpData.put("completed", false);
        todos2ExpData.put("title", "quis ut nam facilis et officia qui");
        todos2ExpData.put("userId", 1);
        todos2ExpData.put("Via", "1.1 vegur");
        todos2ExpData.put("Server", "cloudflare");  */

    /*  Status kodunun 200, dönen body de,
            "completed": değerinin false
            "title”: değerinin “quis ut nam facilis et officia qui”
            "userId" sinin 1 ve
        header değerlerinden
            "Via" değerinin “1.1 vegur” ve
            "Server" değerinin “cloudflare” olduğunu test edin…   */
    }

    // Pojo
    @Test
    public void test05(){

        //1) URL
        spec03.pathParams("p1", "todos", "p2", "2");

        //2) EXPECTED DATA
        JsonTodos2Pojo expData = new JsonTodos2Pojo(1, 2,
                "quis ut nam facilis et officia qui", false, "1.1 vegur", "cloudflare");

        //3) REQUEST & RESPONSE
        Response response = given().spec(spec03).when().get("{p1}/{p2}");
        HashMap<String, Object> actHeaderData = response.as(HashMap.class);
        JsonTodos2Pojo actBodyData = response.as(JsonTodos2Pojo.class);

        //4) ASSERTION
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(expData.getVia(), response.headers().getValue("Via"));
        Assert.assertEquals(expData.getServer(), response.headers().getValue("Server"));

        Assert.assertEquals(expData.getId(), actBodyData.getId());
        Assert.assertEquals(expData.getUserId(), actBodyData.getUserId());
        Assert.assertEquals(expData.getTitle(), actBodyData.getTitle());
        Assert.assertEquals(expData.isCompleted(), actBodyData.isCompleted());

    /*  Status kodunun 200, dönen body de,
            "id" degerinin 1,
             "userId" sinin 1
             "title”: değerinin “quis ut nam facilis et officia qui”
             "completed": değerinin false
             ve
        header değerlerinden
            "Via" değerinin “1.1 vegur” ve
            "Server" değerinin “cloudflare” olduğunu test edin…   */
    }

    // ObjectMapper
    @Test
    public void test06(){

        //1) URL
        spec03.pathParams("p1", "todos", "p2", "2");

        //2) EXPECTED DATA
        String jsonData = "{\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 2,\n" +
                "    \"title\": \"quis ut nam facilis et officia qui\",\n" +
                "    \"completed\": false\n" +
                "}";

        HashMap expData = JsonUtil.convertJsonToJava(jsonData, HashMap.class);

        //3) REQUEST & RESPONSE
        Response response = given().spec(spec03).when().get("{p1}/{p2}");

        //4) ASSERTION
        HashMap actData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);

        assertEquals(expData.get("userId"), actData.get("userId"));
        assertEquals(expData.get("id"), actData.get("id"));
        assertEquals(expData.get("title"), actData.get("title"));
        assertEquals(expData.get("completed"), actData.get("completed"));

    /*  Status kodunun 200, dönen body de,
            "completed": değerinin false
            "title”: değerinin “quis ut nam facilis et officia qui”
            "userId" sinin 1 ve
        header değerlerinden
            "Via" değerinin “1.1 vegur” ve
            "Server" değerinin “cloudflare” olduğunu test edin…   */
    }
}
