package tests.get;


import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get03 extends RestfulBaseUrl {

Response response;
   /*
	 Negative Scenario
	 when I send a GET Request to
	      https://restful-booker.herokuapp.com/booking/10000
	 then status code should be 404
	 and Response Body contains "Not Found"
	 and Response Body does not contain "Suleyman"
	*/

    @Test
    public  void get03(){
        specRest.pathParams("1", "booking", "2", "10000");
        Response response = given().spec(specRest).when().get("{1}/{2}");
        response.then().assertThat().statusCode(404);
        Assert.assertTrue(response.asString().contains("Not Found"));
        Assert.assertFalse(response.asString().contains("Suleyman"));
    }

}