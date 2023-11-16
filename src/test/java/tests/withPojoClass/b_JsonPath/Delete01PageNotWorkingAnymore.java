package tests.withPojoClass.b_JsonPath;

import baseUrl.TestBaseUrls;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyErrorDelete;
import pojos.DummySuccesfulDelete;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Delete01PageNotWorkingAnymore extends TestBaseUrls {
    /*  Sample:
        // Get the data before deleting
        Response responseGet = given().spec(spec01).when().get("/198");
        responseGet.prettyPrint();

        // The data after deleting
        Response responseDel = given().spec(spec01).when().delete("/198");
        responseDel.prettyPrint();
     */

    @Test
    public void delete01PositiveMatchersWithPojo () {
        // THIS PAGE DOES NOT WORK ANYMORE
             /* Positive Scenario
        When I send DELETE Request to http://dummy.restapiexample.com/api/v1/delete/719
        Then Status code should be 200
        And  The value of "status" key in response body should be "success"
		And  The value of "message" key in response body should be "Successfully! Record has been deleted"

		     Note 1: Use hard assertion
		     Note 2: After given(), please use contentType(ContentType.JSON)
	*/
        dummySpec.pathParams("first", "delete", "second", 2);
        Response response = given().spec(dummySpec).when().delete("{first}/{second}");
        response.prettyPrint();
        Response responsedelete = given().spec(dummySpec).when().delete("{first}/{second}");
        responsedelete.prettyPrint();
        DummySuccesfulDelete expData = new DummySuccesfulDelete("success", "Successfully! Record has been deleted");

        response.then().statusCode(200).
                body("status", equalTo(expData.getStatus()),
                        "message", equalTo(expData.getMessage()));

     }

    @Test
    public void delete01NegativeMatchersWithPojo () {
        // THIS PAGE DOES NOT WORK ANYMORE
    /* Negative Scenario
        When I send DELETE Request to http://dummy.restapiexample.com/api/v1/delete/719
		Then Status code should be 404
		And  "message": "Error Occured! Page Not found, contact rstapi2example@gmail.com"

		     Note 1: Use hard assertion
		     Note 2: After given(), please use contentType(ContentType.JSON)
	*/
        dummySpec.pathParams("first", "delete", "second", 719);
        Response response = given().spec(dummySpec).when().get("{first}/{second}");
        DummyErrorDelete expData = new DummyErrorDelete("Error Occured! Page Not found, contact rstapi2example@gmail.com");

        response.then().statusCode(404).
                body("message", equalTo(expData.getMessage()));
    }

}
