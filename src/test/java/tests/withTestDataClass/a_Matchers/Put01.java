package tests.withTestDataClass.a_Matchers;

public class Put01 {
    /*
	    For PUT Request(Full Update) we need;
		1) Endpoint ==> Mandatory
		2) Request Body ==> Mandatory
		3) Authorization ==> It depends on the API
		4) Headers ==> It depends on the API
	*/

    /*
		 When I send PUT Request to
		      http://dummy.restapiexample.com/api/v1/update/2
		 Then
		      Status code is 200
		      Content Type is "application/json"
		      "status" key has value "success"
		      "message" key has value "Successfully! Record has been updated."

		 Note: Create Request Body in 3 different ways
	*/

        //softassertion with de-serialization

}
