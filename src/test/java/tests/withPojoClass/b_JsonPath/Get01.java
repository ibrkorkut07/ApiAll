package tests.withPojoClass.b_JsonPath;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingHeaders;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get01 extends TestBaseUrls {
    /*  Positive Scenario
	    When  I send a GET Request to
	          https://restful-booker.herokuapp.com/booking/
	    And   I accept Content-Type "application/json"  ==> Means API is accepting data just in Json Format
	    Then  statusCode should be 200
	    And   Content-Type should be "application/json; charset=utf-8" ==> Response body must be in Json format
        And   statusLine should be "HTTP/1.1 200 OK"
        And   Server should be "Cowboy"
        And   Connection should be "keep-alive"
        And   Via should be "1.1 vegur"
	*/

    // No need for Json as it is fully related to Headers
    @Test
    public void get02WithoutPojo() {
        Response response = given().contentType(ContentType.JSON).spec(restfulSpec).when().get();
        // JsonPath json = response.jsonPath();

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

        assertEquals(response.header("Server"), "Cowboy");
        assertEquals(response.header("Connection"), "keep-alive");
        assertEquals(response.header("Via"), "1.1 vegur");
    }

    // No need for Json as it is fully related to Headers
    @Test
    public void get02WithPojo() {

        Response response = given().contentType(ContentType.JSON).spec(restfulSpec).when().get();

        BookingHeaders expPojoData = new BookingHeaders(200, "application/json; charset=utf-8", "HTTP/1.1 200 OK", "Cowboy", "keep-alive", "1.1 vegur");

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("expdata.statusCode() = " + expPojoData.getStatusCode());
        System.out.println("expdata.getContentType() = " + expPojoData.getContentType());
        System.out.println("response.contentType() = " + response.contentType());

        assertTrue(expPojoData.getStatusCode() == response.statusCode());  // PAY ATTENTION TO == SIGN HERE AS THEY ARE INTEGERS
        assertEquals(expPojoData.getContentType(), response.contentType());
        assertEquals(expPojoData.getStatusLine(), response.statusLine());
        assertEquals(expPojoData.getServer(), response.header("Server"));
        assertEquals(expPojoData.getConnection(), response.header("Connection"));
        assertEquals(expPojoData.getVia(), response.header("Via"));
    }

}
