package testData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class JsonplaceholderTestdata {

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

    // Build an expData method
    // Build an expData object in the expData method
    // Put expData as "key" and "value"
    public HashMap<String, Object> todosSingleUserData() {
        HashMap<String, Object> todosSingleUserExpData = new HashMap<>();
        todosSingleUserExpData.put("statusCode", 200);
        todosSingleUserExpData.put("completed", false);
        todosSingleUserExpData.put("title", "quis ut nam facilis et officia qui");
        todosSingleUserExpData.put("userId", 1);
        todosSingleUserExpData.put("Via", "1.1 vegur");
        todosSingleUserExpData.put("Server", "cloudflare");
        return todosSingleUserExpData;
    }

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
    public HashMap<String, Object> todos2Data () {
        HashMap<String, Object> todos2ExpData = new HashMap<>();
        todos2ExpData.put("statusCode", 200);
        todos2ExpData.put("completed", false);
        todos2ExpData.put("title", "quis ut nam facilis et officia qui");
        todos2ExpData.put("userId", 1);
        todos2ExpData.put("id", 2);
        todos2ExpData.put("Via", "1.1 vegur");
        todos2ExpData.put("Server", "cloudflare");
        return todos2ExpData;
    }

    /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda gelen response’un
    status kodunun 200
    content type'inin "application/json"
    Headers'daki "Server" in "cloudflare"
    response body'deki "userId"'nin 7
    And "id"  should be 123
    "title" in "esse et quis iste est earum aut impedit"
    "completed" bolumunun false oldugunu test edin
*/
    public HashMap<String, Object> todos123Data() {
        HashMap<String, Object> todos123ExpData = new HashMap<>();
        todos123ExpData.put("statusCode", 200);
        todos123ExpData.put("contentType", "application/json; charset=utf-8");
        todos123ExpData.put("Server", "cloudflare");
        todos123ExpData.put("userId", 7);
        todos123ExpData.put("id", 123);
        todos123ExpData.put("title", "esse et quis iste est earum aut impedit");
        todos123ExpData.put("completed", false);
        return todos123ExpData;
    }

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
    public HashMap<String, Object> todosData() {
        HashMap<String, Object> todosExpData = new HashMap<>();
        List<Integer> expIdList = Arrays.asList(111, 121, 131);
//        List<Integer> expIdList = new ArrayList<>();
//        expIdList.add(111);
//        expIdList.add(121);
//        expIdList.add(131);
        todosExpData.put("statusCode", 200);
        todosExpData.put("contantType", "application/json; charset=utf-8");
        todosExpData.put("totalTitles", 200);
        todosExpData.put("expTitle", "dignissimos quo nobis earum saepe");
        todosExpData.put("expId111", 111);
        todosExpData.put("expId121", 121);
        todosExpData.put("expId131", 131);
        todosExpData.put("expIdList", expIdList);
        todosExpData.put("4th title", "et porro tempora");
        todosExpData.put("last title", "ipsam aperiam voluptates qui");
        return todosExpData;
    }

    /*
        statusCode = 200 ve body kısmının
        {   "userId": 10,
        "id": 198,
        "title": "quis eius est sint explicabo",
        "completed": true   }   olduğunu test edin
    */
    public HashMap<String, Object> todos198DataMap () {
        HashMap<String, Object> expDataMap = new HashMap<>();
        expDataMap.put("statusCode", 200);
        expDataMap.put("userId", 10);
        expDataMap.put("id", 198);
        expDataMap.put("title", "quis eius est sint explicabo");
        expDataMap.put("completed", true);
        return expDataMap;
    }

    public JSONObject todos198PutData () {
        JSONObject putData = new JSONObject();
        putData.put("userId", 21);
        putData.put("title", "Wash the dishes");
        putData.put("completed", false);
        putData.put("id", 198);
        return putData;
    }

    public JSONObject expDataMap () {
        JSONObject expData = new JSONObject();
        expData.put("userId", 21);
        expData.put("id", 201);
        expData.put("title", "Tidy your room");
        expData.put("completed", false);
        return expData;
    }

    public JSONObject setUpTidyPostData(){
        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("userId", 55);
        expectedRequest.put("title", "Tidy your room");
        expectedRequest.put("completed", false);
        expectedRequest.put("statusCode", 201);
        expectedRequest.put("id", 201);
        return expectedRequest;
    }

    /* { "userId": 55,
     "title": "Tidy your room",
     "completed": false } */
    public HashMap<String, Object> tidyHashMapPostRequest () {
        HashMap<String, Object> expDataMap = new HashMap<>();
        expDataMap.put("userId", 55);
        expDataMap.put("title", "Tidy your room");
        expDataMap.put("completed", false);
        return expDataMap;
    }

    public JSONObject expStudyPatchData() {
        JSONObject patchData = new JSONObject();
        patchData.put("userId", 10);
        patchData.put("title", "API calismaliyim");
        patchData.put("completed", true);
        patchData.put("id", 198);
        return patchData;
    }
}
