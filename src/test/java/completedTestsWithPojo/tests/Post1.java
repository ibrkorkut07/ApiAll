package completedTestsWithPojo.tests;

public class Post1 {
   /*
        For Post Request, you need;
		1) Endpoint ==> Mandatory
		2) Request Body ==> Mandatory
		3) Authorization ==> It depends on the API
		4) Headers ==> It depends on the API
   */

   /*
	    When I send POST Request to http://dummy.restapiexample.com/api/v1/create
		Then Status code is 200
			 Content Type is "application/json"
			 "status" key has value "success"
			 "message" key has value "Successfully! Record has been added."

			 Note: Create Request Body in 3 different ways
   */

    // 1st way
    // String reqBody = "{\"name\":\"mali\",\"salary\":\"444\",\"age\":\"35\"}";

    // 2nd way  this way is better
//        JSONObject reqBody = new JSONObject();
//        reqBody.put("name","ayyildiz");
//        reqBody.put("salary","1000");
//        reqBody.put("age","42");
    // 3rd way
//         Map<String, String> reqBody = new HashMap<>();

        //softassertion

}
