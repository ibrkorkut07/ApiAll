package tests.withPojoClass.b_JsonPath.solved;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Get08 extends TestBaseUrls {
    /*
	 Among the data there should be someone whose firstname is Jim
	 URL: https://restful-booker.herokuapp.com/booking
	*/

	/*
	 Query param is used to filter the result
	 Syntax ==> ?key=value&key=value
	 1.Way:You can type query param inside the get() method parenthesis (Not Recommended)
	 2.Way:You can use spec02.queryParam("firstname", "Jim"); (Use it for single query param)
	 3.Way:You can use spec02.queryParams("firstname", "Jim", "totalprice", 2); (Use it for multiple query params)
	*/

    // No need to use Matchers & Pojo
    @Test
    public void get08JsonPath () {
        restfulSpec.queryParam("firstname", "Jim");
        Response response = given().contentType(ContentType.JSON).spec(restfulSpec).when().get();
        JsonPath json = response.jsonPath();
        json.prettyPrint();
        assertFalse(json.getList("bookingid").isEmpty());
        assertTrue(json.getList("bookingid").size()>0);
    }

}
