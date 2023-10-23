package tests.get;

import baseUrl.DummyBaseUrl;
import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.*;

import static io.restassured.RestAssured.given;

public class Get09 extends DummyBaseUrl {
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
    public void get09(){
        specDummy.pathParams("1", "api", "2", "v1", "3", "employees");
        Response response = given().spec(specDummy).when().get("{1}/{2}/{3}");
        JsonPath jsonPath = response.jsonPath();

        List<Integer> idsGreaterthan10List = jsonPath.getList("data.findAll{it.id>10}.id");
        System.out.println("idsGreaterthan10List = " + idsGreaterthan10List);
        Assert.assertEquals(14, idsGreaterthan10List.size());

        List<Integer> agesLessThan30List = jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println("agesLessThan30List = " + agesLessThan30List);
        Collections.sort(agesLessThan30List);
        System.out.println(agesLessThan30List.get(agesLessThan30List.size()-1));

        List<String> employeeNamesGreaterThan350000Salary = jsonPath.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println("employeeNamesGreaterThan350000Salary = " + employeeNamesGreaterThan350000Salary);
        Assert.assertTrue(employeeNamesGreaterThan350000Salary.contains("Charde Marshall"));

        List<Integer> employeeSalariesWithIdLessThan11 = jsonPath.getList("data.findAll{it.id<11}.employee_salary");
        System.out.println("employeeSalariesWithIdLessThan11 = " + employeeSalariesWithIdLessThan11);
        Collections.sort(employeeSalariesWithIdLessThan11);
        Assert.assertTrue((employeeSalariesWithIdLessThan11.get(employeeSalariesWithIdLessThan11.size()-1)).equals(433060));


    }

}
