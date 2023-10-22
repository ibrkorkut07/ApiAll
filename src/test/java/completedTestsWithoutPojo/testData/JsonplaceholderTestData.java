package completedTestsWithoutPojo.testData;

import org.json.JSONObject;

import java.util.HashMap;

public class JsonplaceholderTestData {

    public HashMap<String, Object> todos123Data () {
        HashMap<String, Object> todos123ExpData = new HashMap<>();
        todos123ExpData.put("statusCode", 200);
        todos123ExpData.put("contentType", "application/json; charset=utf-8");
        todos123ExpData.put("Server", "cloudflare");
        todos123ExpData.put("userId", 7);
        todos123ExpData.put("id", 123);
        todos123ExpData.put("title", "esse et quis iste est earum aut impedit");
        todos123ExpData.put("completed", false);
        return todos123ExpData;

    /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda gelen response’un
        status kodunun 200
        content type'inin "application/json"
        Headers'daki "Server" in "cloudflare"
        response body'deki "userId"'nin 7
        "title" in "esse et quis iste est earum aut impedit"
        "completed" bolumunun false oldugunu test edin
    */
    }

    public HashMap<String, Object> todos2Data () {
        HashMap<String, Object> todos2ExpData = new HashMap<>();
        todos2ExpData.put("statusCode", 200);
        todos2ExpData.put("completed", false);
        todos2ExpData.put("title", "quis ut nam facilis et officia qui");
        todos2ExpData.put("userId", 1);
        todos2ExpData.put("Via", "1.1 vegur");
        todos2ExpData.put("Server", "cloudflare");
        return todos2ExpData;
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
    }

    public HashMap<String, Object> todos198DataMap () {
        HashMap<String, Object> expDataMap = new HashMap<>();
        expDataMap.put("statusCode", 200);
        expDataMap.put("userId", 10);
        expDataMap.put("id", 198);
        expDataMap.put("title", "quis eius est sint explicabo");
        expDataMap.put("completed", true);
        return expDataMap;
    /*
    statusCode = 200 ve body kısmının
    {   "userId": 10,
        "id": 198,
        "title": "quis eius est sint explicabo",
        "completed": true   }   olduğunu test edin
     */
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

    public HashMap<String, Object> tidyHashMapPostRequest () {
        HashMap<String, Object> expDataMap = new HashMap<>();
        expDataMap.put("userId", 55);
        expDataMap.put("title", "Tidy your room");
        expDataMap.put("completed", false);
        return expDataMap;
        /* { "userId": 55,
             "title": "Tidy your room",
             "completed": false } */
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
