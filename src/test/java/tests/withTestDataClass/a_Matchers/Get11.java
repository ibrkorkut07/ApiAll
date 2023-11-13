package tests.withTestDataClass.a_Matchers;

import baseUrl.TestBaseUrls;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get11 extends TestBaseUrls {

    /*
        When Send a GET Request to
             http://dummy.restapiexample.com/api/v1/employees
        Then HTTP Status Code should be 200
        And  Highest salary should be 725000
        And  Lowest age should be 19
        Then Second-highest salary should be 675000
    */
}
    /*    GSON is a converter
        GSON is used to convert Json Format Data to Java Objects --> De-Serialization
        GSON is used to convert Java object to Json Data Format ==> Serialization
    */