package tests.withPojoClass.a_Matchers.solved;

import baseUrl.TestBaseUrls;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

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

    // *** MATCHERS HAS NOTHING TO DO WITH PRINTING

    @Test
    public void get09 () {
        dummySpec.pathParam("first", "employees");
        Response response = given().spec(dummySpec).when().get("{first}");
        JsonPath json = response.jsonPath();

        // 1) Print all ids greater than 10 on the console
        //	  Assert that there are 14 ids greater than 10
        List<Integer> idsGreaterThan10List = json.getList("data.findAll{it.id>10}.id");
        System.out.println("idsGreaterThan10List = " + idsGreaterThan10List);
        assertTrue(idsGreaterThan10List.size()==14);

        // 2) Print all ages less than 30 on the console
        //    Assert that maximum age is 23
        List<Integer> agesLessThan30List = json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println("agesLessThan30List = " + agesLessThan30List);
        Collections.sort(agesLessThan30List);
        assertTrue(agesLessThan30List.get(agesLessThan30List.size()-1) == 23);

        // 3) Print all employee names whose salaries are greater than 350000
        //    Assert that Charde Marshall is one of the employees whose salary is greater than 350,000
        List<String> employeesHigherThan350000Salary = json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println("employeesHigherThan350000Salary = " + employeesHigherThan350000Salary);
        assertTrue(employeesHigherThan350000Salary.contains("Charde Marshall"));

        // 4) Print all employee salaries whose ids are less than 11
        //    Assert that maximum salary is 433060
        List<Integer> employeeSalariesLessThanId11 = json.getList("data.findAll{it.id<11}.employee_salary");
        System.out.println("employeeSalariesLessThanId11 = " + employeeSalariesLessThanId11);
        Collections.sort(employeeSalariesLessThanId11);
        Assert.assertTrue( employeeSalariesLessThanId11.get(employeeSalariesLessThanId11.size()-1) == 433060 );
    }

}