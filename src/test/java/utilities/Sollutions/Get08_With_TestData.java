package utilities.Sollutions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Get08_With_TestData extends RestfulBaseUrl {
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

    @Test
    public void get08(){
        specRest.pathParam("1","booking").queryParam("firstname", "Jim");
        Response response = given().spec(specRest).contentType(ContentType.JSON).when().get("{1}");
        List actDataList = response.as(List.class);
        System.out.println("actDataList = " + actDataList);
        Assert.assertFalse(actDataList.isEmpty());
    }

}
