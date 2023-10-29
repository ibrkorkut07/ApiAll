package tests.Sollutions;

import baseUrl.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Get08Solution extends RestfulBaseUrl {
/*
	 Among the data there should be someone whose first name is Jim
	 URL: https://restful-booker.herokuapp.com/booking
	*/

	/*
	 Query param is used to filter the result
	 Syntax ==> ?key=value&key=value
	 1.Way:You can type query param inside the get() method parenthesis (Not Recommended)
	 2.Way:You can use spec02.queryParam("firstname", "Jim"); (Use it for single query param)
	 3.Way:You can use spec02.queryParams("firstname", "Jim", "totalprice", 2); (Use it for multiple query params)
	*/
    @Test
    public void api1(){
        specRest.queryParam("firstname", "Jim");
        Response response = given().spec(specRest).when().get();
        response.prettyPrint();


        Assert.assertTrue(response.asString().contains("bookingid"));
    }

}
