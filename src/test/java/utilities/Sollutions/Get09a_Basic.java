package utilities.Sollutions;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get09a_Basic extends DummyBaseUrl {
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

    // Matchers
    @Test
    public void get09_Matchers(){
        // Matchers Class is to match exp and act data. It is not related to printing
    }

    // JSONPATH
    @Test
    public void get09_JsonPath(){
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

    // DE-SERIALIZATION
    @Test
    public void test09_Deserialization() {
        specDummy.pathParams("1", "api", "2", "v1", "3", "employees");
        Response response = given().contentType(ContentType.JSON).spec(specDummy).when().get("{1}/{2}/{3}");
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

        HashMap<String, Object> actDummy_AllEmployees_Map = response.as(HashMap.class);
        System.out.println("actDummy_AllEmployees_Object = " + actDummy_AllEmployees_Map);


        List<Integer> idListGreaterThan10 = new ArrayList<>();
        for (int i = 0; i<((List<?>)actDummy_AllEmployees_Map.get("data")).size(); i++) {
            if ( ((int) ((Map<?, ?>) ((List<?>) actDummy_AllEmployees_Map.get("data")).get(i)).get("id")) > 10  )
                idListGreaterThan10.add(((int) ((Map<?, ?>) ((List<?>) actDummy_AllEmployees_Map.get("data")).get(i)).get("id")));
        }
        System.out.println("idListGreaterThan10 = " + idListGreaterThan10);
        assertEquals(14, idListGreaterThan10.size());

        List<Integer> ageListLowerThan30 = new ArrayList<>();
        for (int j = 0; j < ((List<?>) actDummy_AllEmployees_Map.get("data")).size(); j++) {
            if (((int)  ((Map<?, ?>) ( (List<?>) actDummy_AllEmployees_Map.get("data")).get(j)).get("employee_age"))<30 )
                ageListLowerThan30.add(((int) ((Map<?, ?>) ((List<?>) actDummy_AllEmployees_Map.get("data")).get(j)).get("employee_age")));
        }
        System.out.println("ageListLowerThan30 = " + ageListLowerThan30);
        Collections.sort(ageListLowerThan30);
        assertEquals(23, (int) ageListLowerThan30.get(ageListLowerThan30.size()-1));

        List<String> employeeNamesMoreThan350KSalary = new ArrayList<>();
        for (int m = 0; m < ((List<?>) actDummy_AllEmployees_Map.get("data")).size(); m++) {
            if ( ((Integer)((Map<?, ?>) ((List<?>) actDummy_AllEmployees_Map.get("data")).get(m)).get("employee_salary")) > 350000 )
                employeeNamesMoreThan350KSalary.add(((String)((Map<?, ?>) ((List<?>) actDummy_AllEmployees_Map.get("data")).get(m)).get("employee_name")));
        }
        System.out.println("Employee Names With More Than 350K Salary = " + employeeNamesMoreThan350KSalary);
        assertTrue(employeeNamesMoreThan350KSalary.contains("Charde Marshall"));

        List<Integer> employeeSalariesWithIdsLessThan11 = new ArrayList<>();
        for (int z = 0; z < ((List<?>) actDummy_AllEmployees_Map.get("data")).size(); z++ ) {
            if ( ((Integer) ((Map<?, ?>)((List<?>) actDummy_AllEmployees_Map.get("data")).get(z)).get("id")) < 11   )
                employeeSalariesWithIdsLessThan11.add( ((Integer) ((Map<?, ?>)((List<?>) actDummy_AllEmployees_Map.get("data")).get(z)).get("employee_salary"))  );
        }
        System.out.println("Employee Salaries With Ids Less Than 11 = " + employeeSalariesWithIdsLessThan11);
        Collections.sort(employeeSalariesWithIdsLessThan11);
        assertEquals(433060, ((int) employeeSalariesWithIdsLessThan11.get(employeeSalariesWithIdsLessThan11.size()-1)) );
    }

    // OBJECT MAPPER (???!!!) Not a suitable task for Object Mapper
    @Test
    public void test06_ObjectMapper() {
        specDummy.pathParams("1", "api", "2", "v1", "3", "employees");
        Response response = given().contentType(ContentType.JSON).spec(specDummy).when().get("{1}/{2}/{3}");

        response.then().statusCode(200).contentType("application/json");
    }



}