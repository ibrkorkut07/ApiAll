package tests.A_Matchers;

import baseUrl.DummyBaseUrl;

import static io.restassured.RestAssured.given;


public class Patch extends DummyBaseUrl {
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
		    "completed": true
		}
	 */

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
