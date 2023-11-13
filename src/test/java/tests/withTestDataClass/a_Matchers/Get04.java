package tests.withTestDataClass.a_Matchers;

import baseUrl.TestBaseUrls;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get04 extends TestBaseUrls {

    /*
        When I send a GET Request to
             https://jsonplaceholder.typicode.com/todos/2
        Then status code should be 200
        And "completed" value should be false
        And "title” should be “quis ut nam facilis et officia qui”
        Then "userId"  should be 1
        Then Among headers "Via"  should be "1.1 vegur"
                           "Server"  should be “cloudflare”
    */

}