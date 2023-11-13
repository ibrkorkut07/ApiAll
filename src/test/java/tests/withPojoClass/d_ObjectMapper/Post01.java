package tests.withPojoClass.d_ObjectMapper;

public class Post01 {
   /*
        For Post Request, you need;
		1) Endpoint ==> Mandatory
		2) Request Body ==> Mandatory
		3) Authorization ==> It depends on the API
		4) Headers ==> It depends on the API
   */

   /*
	    When I send POST Request to
	         http://dummy.restapiexample.com/api/v1/create
		Then Status code is 200
			 Content Type is "application/json"
			 "status" key has value "success"
			 "message" key has value "Successfully! Record has been added."

			 Note: Create Request Body in 3 different ways
   */

   // Soft Assertion

}

//package tests.Matchers;
//
//import baseUrl.DummyBaseUrl;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.hamcrest.Matchers;
//import org.junit.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static io.restassured.RestAssured.given;
//
//public class Post1Solution extends DummyBaseUrl {
//   /*                For Post Request, you need;
//					 1)Endpoint ==> Mandatory
//					 2)Request Body ==> Mandatory
//					 3)Authorization ==> It depends on the API
//					 4)Headers ==> It depends on the API
//					 */
//    @Test
//    public void api(){
//         /*
//			 When
//			   I send POST Request to
//			   http://dummy.restapiexample.com/api/v1/create
//			 Then
//			   Status code is 200
//			   Content Type is "application/json"
//			   "status" key has value "success"
//			   "message" key has value "Successfully! Record has been added."
//
//			 Note: Create Request Body in 3 different ways
//			*/
//        specDummy.pathParam("create","create");
//        //1st way
//       // String reqBody = "{\"name\":\"mali\",\"salary\":\"444\",\"age\":\"35\"}";
//
//        //2nd way  this way is better
////        JSONObject reqBody = new JSONObject();
////        reqBody.put("name","ayyildiz");
////        reqBody.put("salary","1000");
////        reqBody.put("age","42");
//        //3rd way
//        Map<String, String> reqBody = new HashMap<>();
//        reqBody.put("name","ayyildiz");
//        reqBody.put("salary","1000");
//        reqBody.put("age","42");
//
//        Response response = given().spec(specDummy).when().post("/{create}");
//        response.prettyPrint();
//
//        response.then().assertThat().statusCode(200).contentType(ContentType.JSON)
//                .body("status", Matchers.equalTo("success"),
//                        "message", Matchers.equalTo("Successfully! Record has been added."));
//
//        //softassertion
//
//
//    }
//}
