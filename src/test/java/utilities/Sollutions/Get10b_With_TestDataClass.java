package utilities.Sollutions;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get10b_With_TestDataClass extends DummyBaseUrl {

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

    // MATCHERS
    @Test
    public void get10Matchers(){
        specDummy.pathParams("1", "api", "2", "v1", "3", "employees");
        Response response = given().spec(specDummy).when().get("{1}/{2}/{3}");
        response.then().statusCode(200).contentType(ContentType.JSON).
                body("data.id", Matchers.hasSize(24),
                        "data.employee_name", hasItem("Ashton Cox"), // You can avoid typing "Matchers" by importing all Matchers methods
                        "data.employee_name[4]", Matchers.equalTo("Airi Satou"),
                        "data.employee_salary[5]", equalTo(372000),
                        "data.employee_age", hasItems(21, 23, 61)).
                body("data.id[10]", equalTo(11),
                        "data.employee_name[10]", equalTo("Jena Gaines"),
                        "data.employee_salary[10]", equalTo(90560),
                        "data.employee_age[10]", equalTo(30),
                        "data.profile_image[10]", equalTo(""));
    }

    // JSONPATH
    @Test
    public void get10JsonPath(){
        specDummy.pathParams("1", "api", "2", "v1", "3", "employees");
        Response response = given().spec(specDummy).when().get("{1}/{2}/{3}");
        response.then().statusCode(200).contentType(ContentType.JSON);

        JsonPath json = response.jsonPath();
        // json.getList()
        assertEquals(24, json.getList("data.id").size());
        assertTrue(json.getList("data.employee_name").contains("Ashton Cox"));
        assertEquals(json.getString("data.employee_name[4]"), "Airi Satou");
        assertEquals(json.getInt("data.employee_salary[5]"), 372000);
        assertTrue(json.getList("data.employee_age").contains(21));
        assertTrue(json.getList("data.employee_age").contains(23));
        assertTrue(json.getList("data.employee_age").contains(61));
        int listSize = json.getList("data.id").size();
        assertEquals(106450, json.getList("data.employee_salary").get(listSize-2));
        // assertEquals(106450, json.getInt("data.employee_salary[22]"));
        // assertEquals(106450, json.getList("data.employee_salary").get(22));
        assertEquals("Jena Gaines", json.getList("data.employee_name").get(10));
        assertEquals(90560, json.getList("data.employee_salary").get(10));
        assertEquals(30, json.getList("data.employee_age").get(10));
        assertEquals("", json.getList("data.profile_image").get(10));
    }

    // DE-SERIALIZATION
    @Test
    public void test10Deserialization() {
        specDummy.pathParams("1", "api", "2", "v1", "3", "employees");
        Response response = given().contentType(ContentType.JSON).spec(specDummy).when().get("{1}/{2}/{3}");
        response.then().statusCode(200).contentType(ContentType.JSON);

        HashMap<String, Object> actdataMap = response.as(HashMap.class);

        List<String> employeeNameList = new ArrayList<>();
        for (int i = 0; i < ((List<?>) actdataMap.get("data")).size(); i++) {
            employeeNameList.add( ((String) ((Map<?, ?>)((List<?>) actdataMap.get("data")).get(i)).get("employee_name")) );
        }

        assertEquals(24, employeeNameList.size());
        assertTrue(employeeNameList.contains("Ashton Cox"));
        assertEquals("Airi Satou", employeeNameList.get(4));
        assertEquals(372000, ((int) ((Map<?, ?>) ((List<?>) actdataMap.get("data")).get(5)).get("employee_salary")) );

        List<Integer> employeAgeList = new ArrayList<>();
        for (int i = 0; i < ((List<?>) actdataMap.get("data")).size(); i++) {
            employeAgeList.add( ((int) ((Map<?, ?>)((List<?>) actdataMap.get("data")).get(i)).get("employee_age")) );
        }
        List<Integer> expAgeList = Arrays.asList(21, 23, 61);
        assertTrue(employeAgeList.containsAll(expAgeList));

        assertEquals(106450, ((int) ((Map<?, ?>) ((List<?>) actdataMap.get("data")).get(((List<?>) actdataMap.get("data")).size()-2)).get("employee_salary")) );
        assertEquals(11, ((int) ((Map<?, ?>) ((List<?>) actdataMap.get("data")).get(10)).get("id")) );
        assertEquals("Jena Gaines", ((String) ((Map<?, ?>) ((List<?>) actdataMap.get("data")).get(10)).get("employee_name")) );
        assertEquals(90560, ((int) ((Map<?, ?>) ((List<?>) actdataMap.get("data")).get(10)).get("employee_salary")) );
        assertEquals(30, ((int) ((Map<?, ?>) ((List<?>) actdataMap.get("data")).get(10)).get("employee_age")) );
        assertEquals("", ((String) ((Map<?, ?>) ((List<?>) actdataMap.get("data")).get(10)).get("profile_image")) );
    }

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
