package tests.Sollutions;

import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02 extends RestfulBaseUrl {

    /*
        When I send a GET Request to
             https://restful-booker.herokuapp.com/booking/5
		Then HTTP Status code should be "200"
		And  Content type should be in "JSON" format
		And  Status Line should be "HTTP/1.1 200 OK"
		And  response body does not contain "Not Found"
	    And  response body contains "bookingdates"
	*/

    // MATCHERS
    @Test
    public void get02_Matchers() {
        specRest.pathParams("1", "booking", "2", "5");
        Response response = given().spec(specRest).when().get("{1}/{2}");
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK").
                body("bookingdates", Matchers.hasKey("checkin"));
        // Assert.assertTrue(response.getBody().asString().contains("bookingdates"));
        Assert.assertFalse("Body contains \"Not Found\" text", response.getBody().asString().contains("Not Found"));
    }

    // JSONPATH
    @Test
    public void get02_Jsonpath() {
        specRest.pathParams("1", "booking", "2", "5");
        Response response = given().spec(specRest).when().get("{1}/{2}");
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

        JsonPath json = response.jsonPath();
        System.out.println("jsonMap Data = " + json.getMap(""));
        Assert.assertFalse(json.getMap("").toString().contains("Not Found"));
        assertTrue(json.getMap("").toString().contains("bookingdates"));

    }

    // DE-SERIALIZATION
    @Test
    public void test05_Deserialization() {
        specRest.pathParams("1", "booking", "2", "5");
        Response response = given().spec(specRest).when().get("{1}/{2}");
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

    /*
        When I send a GET Request to
             https://restful-booker.herokuapp.com/booking/5
		Then HTTP Status code should be "200"
		And  Content type should be in "JSON" format
		And  Status Line should be "HTTP/1.1 200 OK"
		And  response body does not contain "Not Found"
	    And  response body contains "bookingdates"
	*/
        HashMap<String, Object> actData = response.as(HashMap.class);
        System.out.println("actData = " + actData);
        assertFalse(actData.containsValue("Not Found"));
        assertTrue(actData.containsKey("bookingdates"));
    }

    // OBJECT MAPPER
    @Test
    public void test05_ObjectMapper() {
        specRest.pathParams("1", "booking", "2", "5");
        Response response = given().spec(specRest).when().get("{1}/{2}");
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

        String expJsonNotFoundData = "Not Found";

        String expJsonBookingdatesData = "{\n" +
                "    \"firstname\": \"Sally\",\n" +
                "    \"lastname\": \"Smith\",\n" +
                "    \"totalprice\": 508,\n" +
                "    \"depositpaid\": false,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2015-06-18\",\n" +
                "        \"checkout\": \"2020-08-18\"\n" +
                "    }\n" +
                "}";

        HashMap expNotFoundJavaData = JsonUtil.convertJsonToJava(expJsonNotFoundData, HashMap.class);
        HashMap expBookingdatesJavaData = JsonUtil.convertJsonToJava(expJsonBookingdatesData, HashMap.class);

        HashMap<String, Object> actData = response.as(HashMap.class);

        System.out.println("actData = " + actData);
        System.out.println("expNotFoundJavaData = " + expNotFoundJavaData);
        System.out.println("expBookingdatesJavaData = " + expBookingdatesJavaData);

        assertFalse(actData.containsValue("Not Found"));
        assertFalse(actData.containsValue(expNotFoundJavaData));

        assertTrue(actData.containsKey("bookingdates"));
        assertEquals(expBookingdatesJavaData.get("bookingdates"), actData.get("bookingdates"));

        // assertFalse(actJavaData.toString().contains("Not Found"));
    /*
        When I send a GET Request to
             https://restful-booker.herokuapp.com/booking/5
		Then HTTP Status code should be "200"
		And  Content type should be in "JSON" format
		And  Status Line should be "HTTP/1.1 200 OK"
		And  response body does not contain "Not Found"
	    And  response body contains "bookingdates"
	*/

    }


}