//package completedTestsWithoutPojo.tests;
//
//import completedTestsWithoutPojo.baseUrl.RestfulBaseUrl;
//import completedTestsWithoutPojo.utilities.JsonUtil;
//import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import org.junit.Assert;
//import org.junit.Test;
//import completedTestsWithoutPojo.pojos.BookingdatesPojo;
//import completedTestsWithoutPojo.pojos.RestfulBooking7Pojo;
//import completedTestsWithoutPojo.testData.RestfulTestData;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.core.IsEqual.equalTo;
//
//public class M03_GetRequest03 extends RestfulBaseUrl {
//
//    /*
//    https://restful-booker.herokuapp.com/booking/7 url'ine
//        accept type'i "application/json" olan GET request'i yolladigimda gelen response'un
//        status kodunun 200 ve
//        content type'inin "application/json"
//
//{
//    "firstname": "Mary",
//    "lastname": "Smith",
//    "totalprice": 102,
//    "depositpaid": true,
//    "bookingdates": {
//        "checkin": "2015-12-23",
//        "checkout": "2020-02-11"
//    },
//    "additionalneeds": "Breakfast"
//}
//        oldugunu test edin
//    */
//
//    @Test
//    public void test01 () {
//        spec01.pathParams("p1", "booking", "p2", "7");
//        Response response = given().contentType(ContentType.JSON).spec(spec01).when().get("{p1}/{p2}");
//    }
//
//    // Matchers
//    @Test
//    public void test02(){
//
//        spec01.pathParams("p1", "booking", "p2", "1");
//        Response response2 = given().contentType(ContentType.JSON).spec(spec01).when().get("{p1}/{p2}");
//
//        RestfulTestData bookingObject = new RestfulTestData();
//        HashMap<String, Object> expData = bookingObject.booking7DataSetUp();
//
//        response2.then().statusCode((Integer) expData.get("statusCode")).
//                headers("Content-Type", equalTo(expData.get("contentType")),
//                        "Via", equalTo(expData.get("Via"))).
//                body("firstname", equalTo(expData.get("firstname")),
//                "lastname", equalTo(expData.get("lastname")),
//                "totalprice", equalTo(expData.get("totalprice")),
//                "depositpaid", equalTo(expData.get("depositpaid")),
//                "bookingDates.checkin", equalTo(expData.get("bookingDates.checkin")),
//                "bookingDates.checkout", equalTo(expData.get("bookingDates.checkout")),
//                "additionalneeds", equalTo(expData.get("additionalneeds")));
//    }
//
//    // Jsonpath
//    @Test
//    public void test03(){
//        spec01.pathParams("p1", "booking", "p2", "1");
//        Response response = given().contentType(ContentType.JSON).spec(spec01).when().get("{p1}/{p2}");
//
//        HashMap<String, Object> actData = response.as(HashMap.class);
//
//        RestfulTestData bookingObject = new RestfulTestData();
//        HashMap<String, Object> expData = bookingObject.booking7DataSetUp();
//
//        response.then().assertThat().statusCode(200).contentType("application/json; charset=utf-8");
//        JsonPath json = response.jsonPath();
//        assertEquals(expData.get("firstname"),  json.getString("firstname"));
//        assertEquals(expData.get("lastname"), json.getString("lastname"));
//        assertEquals(expData.get("totalprice"), json.getInt("totalprice"));
//        assertEquals(expData.get("depositpaid"), json.getBoolean("depositpaid"));
//        assertEquals(expData.get("bookingdates.checkin"), json.getString("bookingdates.checkin"));
//        assertEquals(expData.get("bookingdates.checkin"), json.getString("bookingdates.checkin"));
//    }
//
//    // Deserialization
//    @Test
//    public void test04(){
//        spec01.pathParams("p1", "booking", "p2", "1");
//        Response response = given().contentType(ContentType.JSON).spec(spec01).when().get("{p1}/{p2}");
//
//        RestfulTestData restfulTestData = new RestfulTestData();
//        HashMap<String, Object> expData = restfulTestData.booking7DataSetUp();
//        assertEquals(expData.get("statusCode"), response.statusCode());
//        assertEquals(expData.get("contentType"), response.contentType());
//
//        HashMap<String, Object> actData = response.as(HashMap.class);
//
//        assertEquals(expData.get("firstname"), actData.get("firstname"));
//        assertEquals(expData.get("lastname"), actData.get("lastname"));
//        assertEquals(expData.get("totalprice"), actData.get("totalprice"));
//        assertEquals(expData.get("depositpaid"), actData.get("depositpaid"));
//        assertEquals(  ((Map) expData.get("bookingdates")).get("checkin"), ((Map) actData.get("bookingdates")).get("checkin")  );
//        assertEquals(  ( (Map) expData.get("bookingdates")).get("checkout"), ( (Map) actData.get("bookingdates")).get("checkout")  );
//        assertEquals( (Map) expData.get("additionalneeds"), (Map) actData.get("additionalneeds"));
//
//        /*
//        status kodunun 200 ve
//        content type'inin "application/json"
//
//            "firstname": "Susan",
//            "lastname": "Brown",
//            "totalprice": 189,
//            "depositpaid": false,
//            "bookingdates": {
//                    "checkin": "2017-08-15",
//                    "checkout": "2018-12-01"
//            "additionalneeds": "Breakfast"
//        */
//    }
//
//    // Pojo
//    @Test
//    public void test05(){
//        //1) URL
//        spec01.pathParams("p1", "booking", "p2", "1");
//
//        //2) EXPECTED DATA
//        BookingdatesPojo dates = new BookingdatesPojo("2017-09-19", "2018-04-19");
//        RestfulBooking7Pojo expData = new RestfulBooking7Pojo("Mary", "Brown", 631, false, dates);
//
//        //3) REQUEST & RESPONSE
//        Response response = given().contentType(ContentType.JSON).spec(spec01).when().get("{p1}/{p2}");
//
//        //4) ASSERTION
//        RestfulBooking7Pojo actdata = response.as(RestfulBooking7Pojo.class);
//        response.then().assertThat().statusCode(200).contentType("application/json; charset=utf-8");
//        Assert.assertEquals(expData.getFirstname(), actdata.getFirstname());
//        Assert.assertEquals(expData.getLastname(), actdata.getLastname());
//        Assert.assertEquals(expData.getTotalprice(), actdata.getTotalprice());
//        Assert.assertEquals(expData.isDepositpaid(), actdata.isDepositpaid());
//        Assert.assertEquals(expData.getBookingdates(), actdata.getBookingdates());
//        // Assert.assertEquals(expData.getAdditionalneeds(), actdata.getAdditionalneeds());
//
//        /*
//        status kodunun 200 ve
//        content type'inin "application/json"
//
//            "firstname": "Susan",
//            "lastname": "Brown",
//            "totalprice": 189,
//            "depositpaid": false,
//            "bookingdates": {
//                    "checkin": "2017-08-15",
//                    "checkout": "2018-12-01"
//            "additionalneeds": "Breakfast"
//        */
//    }
//
//    // ObjectMapper
//    @Test
//    public void test06(){
//
//        //1) URL
//        spec01.pathParams("p1", "booking", "p2", "1");
//
//        //2) EXPECTED DATA
//        String jsonData = "{\n" +
//                "    \"firstname\": \"Susan\",\n" +
//                "    \"lastname\": \"Jackson\",\n" +
//                "    \"totalprice\": 550,\n" +
//                "    \"depositpaid\": false,\n" +
//                "    \"bookingdates\": {\n" +
//                "        \"checkin\": \"2016-10-17\",\n" +
//                "        \"checkout\": \"2019-04-10\"\n" +
//                "    },\n" +
//                "    \"additionalneeds\": \"Breakfast\"\n" +
//                "}";
//
//        HashMap<String, Object> expData = JsonUtil.convertJsonToJava(jsonData, HashMap.class);
//
//        //3) REQUEST & RESPONSE
//        Response response = given().contentType(ContentType.JSON).spec(spec01).when().get("{p1}/{p2}");
//        HashMap actData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);
//
//        //4) ASSERTION
//        assertEquals(expData.get("firstname"), actData.get("firstname"));
//        assertEquals(expData.get("lastname"), actData.get("lastname"));
//        assertEquals(expData.get("totalprice"), actData.get("totalprice"));
//        assertEquals(expData.get("depositpaid"), actData.get("depositpaid"));
//        assertEquals(expData.get("bookingdates.checkin"), actData.get("bookingdates.checkin"));
//        assertEquals(expData.get("bookingdates.checkout"), actData.get("bookingdates.checkout"));
//        assertEquals(expData.get("additionalneeds"), actData.get("additionalneeds"));
//    }
//}
