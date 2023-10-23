package tests.getSollutions;

import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class M02_GetRequest02 extends RestfulBaseUrl {

    /*
    https://restful-booker.herokuapp.com/booking url'ine
        accept type'i "application/json" olan GET request'i yolladigimda gelen response'un
        status kodunun 200
        content type'inin "application/json" oldugunu test edin
    */

    @Test
    public void test02(){
        specRest.pathParam("p1", "booking");
        Response response = given().contentType(ContentType.JSON).spec(specRest).when().get("{p1}");
        response.then().assertThat().statusCode(200).contentType("application/json");  // accepts "application/json"

        Assert.assertEquals(200, response.statusCode());
        // Assert.assertEquals("application/json", response.contentType());  // does not accept "application/json"
        Assert.assertEquals("application/json; charset=utf-8", response.contentType());  // accepts only full type text
    }
}
