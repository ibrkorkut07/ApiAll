package tests.d_ObjectMapper;

import baseUrl.DummyBaseUrl;

public class Get10 extends DummyBaseUrl {

    /*
		When I send a GET request to REST API URL
			 http://dummy.restapiexample.com/api/v1/employees
	    When Accepted content type is “application/JSON”
	    Then HTTP Status Code should be 200
		And Response format should be "application/JSON"
		And there should be 24 employees
		And "Ashton Cox" should be one of the employees
		And Name of the 5th employee should be "Airi Satou"
        And Salary of 6th employee 372000
		And 21, 23, and 61 should be among the employee ages
		Then Salary of the second to last employee should be 106450
		And 11th Employee's information should be as follows;
		             {
                        “id”: ”11”,
                        "employee_name": "Jena Gaines",
                        "employee_salary": 90560,
                        "employee_age": 30,
                        "profile_image": ""
                      }
    */

}
