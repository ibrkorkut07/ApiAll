package tests.withPojoClass.a_Matchers.solved;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import pojos.SingleTodos;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Patch extends TestBaseUrls {
    /*
		 For PATCH Request(Partial Update) you need;
		 1)Endpoint ==> Mandatory
		 2)Request Body ==> Mandatory
		 3)Authorization ==> It depends on the API
		 4)Header ==> It depends on the API
	 */

	/*
		{
		   "userId": 10,
		   "id": 198,
		   "title": "quis eius est sint explicabo",
		    "completed": false
		}
	 */
    @Test
    public void patch01MatchersWithPojo () {

        placeSpec.pathParam("first", "10");
        SingleTodos data = new SingleTodos(10, 198, "quis eius est sint explicabo", false);
        int id = 44;
        Response response = given().spec(placeSpec).body(id).patch("/{first}");

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("userId", equalTo(data.getUserId()),
                        "id", equalTo(id),
                        "title", equalTo(data.getTitle()),
                        "completed", equalTo(data.isCompleted())    );
        // Postman patches but not automation ???!!!
    }

}

/*
    @Test
    public void api(){
        specDummy.pathParam("id", 8);



        Map<String ,Object> reqBody = new HashMap<>();
        reqBody.put("firstname", "Team13");

        Response response = given().spec(specDummy).when().patch("/{id}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

    }
 */
