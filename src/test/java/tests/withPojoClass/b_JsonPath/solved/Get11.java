package tests.withPojoClass.b_JsonPath.solved;

import baseUrl.TestBaseUrls;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.HighestLowestEmployeeValues;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get11 extends TestBaseUrls {

    // There are almost always too many requests on this url. So, almost impossible to catch the actData
    /*
        When Send a GET Request to
             http://dummy.restapiexample.com/api/v1/employees
        Then HTTP Status Code should be 200
        And  Highest salary should be 725000
        And  Lowest age should be 19
        Then Second-highest salary should be 675000
    */

    @Test
    public void get11JsonWithoutPojo () {
        Response response = given().spec(dummySpec).when().get("employees");
        response.then().statusCode(200);
        JsonPath json = response.jsonPath();
        List<Integer> salaryList = json.getList("data.employee_salary");
        Integer listSize = salaryList.size();
        Collections.sort(salaryList);
        System.out.println("salaryList = " + salaryList);

        List<Integer> ageList = json.getList("data.employee_age");
        Collections.sort(ageList);
        System.out.println("ageList = " + ageList);
        assertTrue(salaryList.get(salaryList.size()-1) == 725000);
        assertTrue(ageList.get(0) == 19);
        assertTrue(salaryList.get(salaryList.size()-2) == 675000);
    }

    @Test
    public void get11JsonWithPojo () {
        HighestLowestEmployeeValues expPojoData = new HighestLowestEmployeeValues(725000, 19, 675000);
        Response response = given().spec(dummySpec).when().get("employees");
        response.then().statusCode(200);
        JsonPath json = response.jsonPath();
        List<Integer> salaryList = json.getList("data.employee_salary");
        Integer listSize = salaryList.size();
        Collections.sort(salaryList);
        System.out.println("salaryList = " + salaryList);

        List<Integer> ageList = json.getList("data.employee_age");
        Collections.sort(ageList);
        System.out.println("ageList = " + ageList);
        assertEquals(expPojoData.getHighestSalary(), salaryList.get(salaryList.size()-1));
        assertEquals(expPojoData.getLowestAge(), ageList.get(0));
        assertEquals(expPojoData.getSecondHighestSalary(), salaryList.get(salaryList.size()-2));
    }

}
    /*              *****
        GSON is a converter
        GSON is used to convert Json Format Data to Java Objects --> De-Serialization
        GSON is used to convert Java object to Json Data Format ==> Serialization
    */

