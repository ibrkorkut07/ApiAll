package tests.withPojoClass.b_JsonPath.solved;

import baseUrl.TestBaseUrls;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get09 extends TestBaseUrls {
   /*
	 When I send GET Request to
	 	  http://dummy.restapiexample.com/api/v1/employees
	 Then
		 Status code is 200
		 1) Print all ids greater than 10 on the console
		    Assert that there are 14 ids greater than 10
		 2) Print all ages less than 30 on the console
		    Assert that maximum age is 23
		 3) Print all employee names whose salaries are greater than 350000
		    Assert that Charde Marshall is one of the employees whose salary is greater than 350,000
		 4) Print all employee salaries whose ids are less than 11
		    Assert that maximum salary is 433060
	*/

    @Test
    public void get09JsonPath () {
        dummySpec.pathParam("first", "employees");
        Response response = given().spec(dummySpec).when().get("{first}");
        JsonPath json = response.jsonPath();

        // 1) Print all ids greater than 10 on the console
        //	  Assert that there are 14 ids greater than 10
        System.out.println("all ids greater than 10: " + json.getList("data.findAll{it.id>10}.id"));
        assertTrue(json.getList("data.findAll{it.id>10}.id").size() == 14);

        // 2) Print all ages less than 30 on the console
        //    Assert that maximum age is 23
        List<Integer> agesLessThan30 = json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println("all ages less than 30: " + agesLessThan30);
        Collections.sort(agesLessThan30);
        assertTrue(agesLessThan30.get(agesLessThan30.size()-1) == 23);

        // 3) Print all employee names whose salaries are greater than 350000
        //    Assert that Charde Marshall is one of the employees whose salary is greater than 350,000
        List<String> whoseSalaryGreaterThan350000 = json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println("employee names whose salary greater than 350000: " + whoseSalaryGreaterThan350000);
        assertTrue(whoseSalaryGreaterThan350000.contains("Charde Marshall"));

        // 4) Print all employee salaries whose ids are less than 11
        //    Assert that maximum salary is 433060
        List<Integer> salariesWithIdLessThan11 = json.getList("data.findAll{it.id<11}.employee_salary");
        System.out.println("all salaries with id less than 11: " + salariesWithIdLessThan11);
        Collections.sort(salariesWithIdLessThan11);
        assertTrue(salariesWithIdLessThan11.get(salariesWithIdLessThan11.size()-1) == 433060);


    }

}