package utilities.tasks;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import pojos.BookingHeaders;

import static io.restassured.RestAssured.given;

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

    @Test
    public void get02Matchers() {
        Response response = given().contentType(ContentType.JSON).spec(restfulSpec).when().get();

        // Way 1: Partly with Matchers but without Pojo as it is a very simple task
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK").
                headers("Server", Matchers.equalTo("Cowboy"),
                        "Connection", Matchers.equalTo("keep-alive"),
                        "Via", Matchers.equalTo("1.1 vegur"));

        // Way 2: With Matchers and Pojo (although not worth doing with Pojo)
        BookingHeaders expdata = new BookingHeaders(200, "application/json; charset=utf-8", "HTTP/1.1 200 OK", "Cowboy", "keep-alive", "1.1 vegur");

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK").
                headers("Server", Matchers.equalTo(expdata.getServer()),
                        "Connection", Matchers.equalTo(expdata.getConnection()),
                        "Via", Matchers.equalTo(expdata.getVia()));
    }


}
