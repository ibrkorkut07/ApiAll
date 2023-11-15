package tests.withPojoClass.a_Matchers.solved;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.DummyErrorDelete;
import pojos.DummySuccesfulDelete;
import pojos.SingleTodos;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class Delete02 extends TestBaseUrls {
    /*  Sample:
        // Get the data before deleting
        Response responseGet = given().spec(spec01).when().get("/198");
        responseGet.prettyPrint();

        // The data after deleting
        Response responseDel = given().spec(spec01).when().delete("/198");
        responseDel.prettyPrint();
     */

    @Test
    public void delete02Matchers () {

    /*
        When I send GET Request to https://jsonplaceholder.typicode.com/todos/198
        And  Accept contentType(ContentType.JSON)
        Then Status code should be 200
        And  I should get the following response body:
             {
                "userId": 10,
                "id": 198,
                "title": "quis eius est sint explicabo",
                "completed": true
               }
        When I send DELETE Request to the same url
        Then Status code should be 200
        And  Response body should be empty
	*/
        // placeSpec.pathParams("first", 198);
        Response response = given().contentType(ContentType.JSON).spec(placeSpec).when().get("/198");
        SingleTodos expData = new SingleTodos(10, 198, "quis eius est sint explicabo", true);

        response.then().statusCode(200).
                body("userId", equalTo(expData.getUserId()),
                        "id", equalTo(expData.getId()),
                        "title", equalTo(expData.getTitle()),
                        "completed", equalTo(expData.isCompleted()));


        Response deleteResponse = given().contentType(ContentType.JSON).spec(placeSpec).when().delete("/198");
        // deleteResponse.prettyPrint();
        // System.out.println("deleteResponse.getBody().toString() = " + deleteResponse.getBody().asString());

        HashMap<String, Object> bodyMap = deleteResponse.as(HashMap.class);

        response.then().statusCode(200);
        assertTrue(bodyMap.isEmpty());

        // OR
        assertTrue(deleteResponse.getBody().asString().contains(""));
     }

}
