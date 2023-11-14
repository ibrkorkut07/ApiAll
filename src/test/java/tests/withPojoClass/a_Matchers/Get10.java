package tests.withPojoClass.a_Matchers;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import pojos.SingleEmployee;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get10 extends TestBaseUrls {

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
                        “id”: 11,
                        "employee_name": "Jena Gaines",
                        "employee_salary": 90560,
                        "employee_age": 30,
                        "profile_image": ""
                      }
    */

    // There are almost always too many requests on this url. So, almost impossible to catch the actData
    @Test
    public void get10MatchersWithPojo () {
        dummySpec.pathParam("first", "employees");
        Response response = given().contentType("text/html").spec(dummySpec).when().get("{first}");

        SingleEmployee expData = new SingleEmployee(11, "Jena Gaines", 90560, 30, "");
        SingleEmployee actBodyData = response.as(SingleEmployee.class);
        JsonPath json = response.jsonPath();

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body(json.getList("data.employee_name"), Matchers.hasItem("Ashton Cox"));



            /*
		And there should be 24 employees
		And "Ashton Cox" should be one of the employees
		And Name of the 5th employee should be "Airi Satou"
        And Salary of 6th employee 372000
		And 21, 23, and 61 should be among the employee ages
		Then Salary of the second to last employee should be 106450
		And 11th Employee's information should be as follows;
		             {
                        “id”: 11,
                        "employee_name": "Jena Gaines",
                        "employee_salary": 90560,
                        "employee_age": 30,
                        "profile_image": ""
                      }
    */












    }

}
