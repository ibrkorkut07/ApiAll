package tests.withPojoClass.a_Matchers.solved;

import baseUrl.TestBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import pojos.SingleEmployee;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Get12 extends TestBaseUrls {

    /*
        When Send a GET Request to
             http://dummy.restapiexample.com/api/v1/employees
        Then Write all employee names on console
        And  Write 3rd employee name on console
        And  Write first 5 employee names on console
        Then Write last employee name on console
    */


    // There are almost always too many requests on this url. So, almost impossible to catch the actData
    @Test
    public void get10MatchersWithPojo () {
        dummySpec.pathParam("first", "employees");
        Response response = given().contentType("text/html").spec(dummySpec).when().get("{first}");

        JsonPath json = response.jsonPath();
        List<String> allEmployeeNames = json.getList("data.employee_name");

        System.out.println("All employee_names: " + allEmployeeNames);
        System.out.println("3rd employee name " + allEmployeeNames.get(2));
        System.out.println("First 5 employee names: " + allEmployeeNames.subList(0, 5));    // index 5 is excluded
        System.out.println("Last employee name: " + allEmployeeNames.get(allEmployeeNames.size() - 1));
    }

}