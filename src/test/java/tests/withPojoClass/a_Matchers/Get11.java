package tests.withPojoClass.a_Matchers;

import baseUrl.TestBaseUrls;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

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
    public void get11 () {
        Response response = given().spec(dummySpec).when().get("employees");
        int listSize = response.as(List.class).size();
        // System.out.println("listSize = " + listSize);

        for (int i=0; i < response.as(List.class).size(); i++) {


        }





    }
    /*
    "status": "success",
    "data": [
        {
            "id": 1,
            "employee_name": "Tiger Nixon",
            "employee_salary": 320800,
            "employee_age": 61,
            "profile_image": ""
        },
     */


}


    /*              *****
        GSON is a converter
        GSON is used to convert Json Format Data to Java Objects --> De-Serialization
        GSON is used to convert Java object to Json Data Format ==> Serialization
    */

