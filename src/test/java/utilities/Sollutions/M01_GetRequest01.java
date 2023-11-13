package utilities.Sollutions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

@JsonIgnoreProperties(ignoreUnknown = true)
public class M01_GetRequest01 extends RestfulBaseUrl {

   /*
    https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde
    donecek cevap(response) icin
        HTTP status kodunun 200
        Content Type’in Json
        Ve Status Line’in HTTP/1.1 200 OK
    Oldugunu test edin
   */

    @Test
    public void test02(){
        specRest.pathParams("p1", "booking", "p2", "3");
        Response response = given().spec(specRest).when().get("{p1}/{p2}");

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");
        response.prettyPrint();

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8", response.contentType());
        Assert.assertEquals("HTTP/1.1 200 OK", response.statusLine());
    }
}
