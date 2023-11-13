package utilities.Sollutions;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Get12a_Basic extends DummyBaseUrl {

    /*
        When Send a GET Request to
             http://dummy.restapiexample.com/api/v1/employees
        Then Write all employee names on console
        And  Write 3rd employee name on console
        And  Write first 5 employee names on console
        Then Write last employee name on console
    */

    // Matchers
    @Test
    public void get09Matchers(){
        // Matchers Class is to match exp and act data. It is not related to printing.
    }

    // JSONPATH
    @Test
    public void get09Jsonpath(){
        specDummy.pathParams("1", "api", "2", "v1", "3", "employees");
        Response response = given().spec(specDummy).when().get("{1}/{2}/{3}");

        JsonPath json = response.jsonPath();
        System.out.println("All employee names: " + json.getList("data.employee_name"));
        System.out.println("3rd employee name: " + json.getString("data.employee_name[2]"));
        System.out.println("First 5 employee names: " + json.getList("data.findAll{it.id<6}.employee_name"));
        // System.out.println(json.getList("data.employee_name[0-4]"));

        int lastIndexNumber = json.getList("data.id").size()-1;
        // System.out.println("lastIndexNumber = " + lastIndexNumber);
        System.out.println("Last employee name: " + json.getList("data.employee_name").get(lastIndexNumber));
    }

    // DE-SERIALIZATION
    @Test
    public void test11Deserialization() {
        specDummy.pathParams("1", "api", "2", "v1", "3", "employees");
        Response response = given().contentType(ContentType.JSON).spec(specDummy).when().get("{1}/{2}/{3}");
        response.then().statusCode(200);

        HashMap<String, Object> actdataMap = response.as(HashMap.class);

        List<String> employeeNameList = new ArrayList<>();
        for (int i = 0; i < ((List<?>) actdataMap.get("data")).size(); i++) {
            employeeNameList.add( ((String) ((Map<?, ?>)((List<?>) actdataMap.get("data")).get(i)).get("employee_name")) );
        }
        System.out.println("employeeNameList = " + employeeNameList);
        System.out.println("3rd Employee Name = " + employeeNameList.get(2));

        List<String> first5EmployeeNameList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            first5EmployeeNameList.add( ((String) ((Map<?, ?>)((List<?>) actdataMap.get("data")).get(i)).get("employee_name")) );
        }
        System.out.println("first5employeeNameList = " + first5EmployeeNameList);

        System.out.println("Last Employee Name = " + employeeNameList.get(employeeNameList.size() - 1));
    }


        /*
        When Send a GET Request to
             http://dummy.restapiexample.com/api/v1/employees
        Then Write all employee names on console
        And  Write 3rd employee name on console
        And  Write first 5 employee names on console
        Then Write last employee name on console
    */


}