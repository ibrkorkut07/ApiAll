package completedTestsWithoutPojo.tests;

import completedTestsWithoutPojo.baseUrl.RestfulBaseUrl;
import completedTestsWithoutPojo.utilities.JsonUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import completedTestsWithoutPojo.pojos.BookingdatesPojo;
import completedTestsWithoutPojo.pojos.RestfulBooking7Pojo;
import completedTestsWithoutPojo.testData.RestfulTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class M09_GetRequest09 extends RestfulBaseUrl {

    /*
    https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
    dönen response body nin
        {   "firstname": "Eric",
            "lastname": "Jackson",
            "totalprice": 952,
            "depositpaid": false,
            "bookingdates": {
                    "checkin": "2022-10-09",
                    "checkout": "2022-11-11"
    }   olduğunu test edin
     */

    @Test
    public void test00 () {
        spec01.pathParams("p1", "booking", "p2", "1");
        Response response = given().spec(spec01).when().get("{p1}/{p2}");
    }

    // Matchers without Testdata class
    @Test
    public void test01(){
        spec01.pathParams("p1", "booking", "p2", "1");
        Response response = given().spec(spec01).when().get("{p1}/{p2}");

        response.then().assertThat().statusCode(200).
                body("firstname", equalTo("Susan"),
                "lastname", equalTo("Jackson"),
                "totalprice", equalTo(255),
                "depositpaid", equalTo(false),
                "bookingdates.checkin", equalTo("2015-12-26"),
                "bookingdates.checkout", equalTo("2020-10-31"));
    }

    // Matchers with Testdata class
    @Test
    public void test02(){
        spec01.pathParams("p1", "booking", "p2", "1");
        Response response = given().spec(spec01).when().get("{p1}/{p2}");

        RestfulTestData restfulObject = new RestfulTestData();
        HashMap<String, Object> expData = restfulObject.booking1SetUp();

        response.then().assertThat().statusCode(equalTo(expData.get("statusCode"))).
                body("firstname", equalTo(expData.get("firstname")),
                        "lastname", equalTo(expData.get("lastname")),
                        "totalprice", equalTo(expData.get("totalprice")),
                        "depositpaid", equalTo(expData.get("depositpaid")),
                        "bookingdates.checkin", equalTo(expData.get("bookingdates.checkin")),
                        "bookingdates.checkout", equalTo(expData.get("bookingdates.checkout")));
    }

    // Jsonpath
    @Test
    public void test03(){
        spec01.pathParams("p1", "booking", "p2", "1");
        Response response = given().spec(spec01).when().get("{p1}/{p2}");

        RestfulTestData restfulObject = new RestfulTestData();
        HashMap<String, Object> expData = restfulObject.booking1SetUp();

        JsonPath json = response.jsonPath();

        assertEquals(expData.get("firstname"), json.getString("firstname"));
        assertEquals(expData.get("lastname"), json.getString("lastname"));
        assertEquals(expData.get("totalprice"), json.getInt("totalprice"));
        assertEquals(expData.get("depositpaid"), json.getBoolean("depositpaid"));
        assertEquals(expData.get("bookingdates.checkin"), json.getString("bookingdates.checkin"));
        assertEquals(expData.get("bookingdates.checkout"), json.getString("bookingdates.checkout"));
    }

    // Deserialization
    @Test
    public void test04(){
        spec01.pathParams("p1", "booking", "p2", "1");
        Response response = given().spec(spec01).when().get("{p1}/{p2}");

        RestfulTestData restfulTestData = new RestfulTestData();
        HashMap<String, Object> expData = restfulTestData.booking1SetUp();

        HashMap<String, Object> actData = response.as(HashMap.class);

        assertEquals(expData.get("firstname"), actData.get("firstname"));
        assertEquals(expData.get("lastname"), actData.get("lastname"));
        assertEquals(expData.get("totalprice"), actData.get("totalprice"));
        assertEquals(expData.get("depositpaid"), actData.get("depositpaid"));
        assertEquals(expData.get("bookingdates.checkin"), actData.get("bookingdates.checkin"));
        assertEquals(expData.get("bookingdates.checkout"), actData.get("bookingdates.checkout"));

        /*  "firstname": "Eric",
            "lastname": "Jackson",
            "totalprice": 952,
            "depositpaid": false,
            "bookingdates": {
                    "checkin": "2022-10-09",
                    "checkout": "2022-11-11"    */
    }

    // Pojo
    @Test
    public void test05(){

        //1) URL
        spec01.pathParams("p1", "booking", "p2", "1");

        //2) EXPECTED DATA
        BookingdatesPojo expBookingDates = new BookingdatesPojo("2016-04-21", "2017-10-17");
        RestfulBooking7Pojo expData = new RestfulBooking7Pojo("Susan", "Ericsson", 173, true, expBookingDates);

        //3) REQUEST & RESPONSE
        Response response = given().spec(spec01).when().get("{p1}/{p2}");

        //4) ASSERTION
        RestfulBooking7Pojo actData = response.as(RestfulBooking7Pojo.class);

        assertEquals(expData.getFirstname(), actData.getFirstname());
        assertEquals(expData.getLastname(), actData.getLastname());
        assertEquals(expData.getTotalprice(), actData.getTotalprice());
        assertEquals(expData.isDepositpaid(), actData.isDepositpaid());
        assertEquals(expData.getBookingdates().getCheckin(), actData.getBookingdates().getCheckin());
        assertEquals(expData.getBookingdates().getCheckout(), actData.getBookingdates().getCheckout());
    }

    // ObjectMapper
    @Test
    public void test06(){

        // DATA NOT FOUND ERROR

        //1) URL
        spec01.pathParams("p1", "booking", "p2", "1");

        //2) EXPECTED DATA
        String jsonData = "{\n" +
                "    \"firstname\": \"Eric\",\n" +
                "    \"lastname\": \"Jackson\",\n" +
                "    \"totalprice\": 952,\n" +
                "    \"depositpaid\": false,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-10-09\",\n" +
                "        \"checkout\": \"2022-11-11\"\n" +
                "}";

        HashMap<String, Object> expData = JsonUtil.convertJsonToJava(jsonData, HashMap.class);

        //3) REQUEST & RESPONSE
        Response response = given().spec(spec01).when().get("{p1}/{p2}");

        //4) ASSERTION
        HashMap actData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);

        assertEquals(expData.get("firstname"), actData.get("firstname"));
        assertEquals(expData.get("lastname"), actData.get("lastname"));
        assertEquals(expData.get("totalprice"), actData.get("totalprice"));
        assertEquals(expData.get("depositpaid"), actData.get("depositpaid"));
        assertEquals(expData.get("bookingdates.checkin"), actData.get("bookingdates.checkin"));
        assertEquals(expData.get("bookingdates.checkout"), actData.get("bookingdates.checkout"));

        /*  {
            "firstname": "Eric",
            "lastname": "Jackson",
            "totalprice": 952,
            "depositpaid": false,
            "bookingdates": {
                    "checkin": "2022-10-09",
                    "checkout": "2022-11-11"
                    }   */
    }
}
