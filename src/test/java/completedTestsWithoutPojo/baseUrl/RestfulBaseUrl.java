package completedTestsWithoutPojo.baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RestfulBaseUrl {

    protected RequestSpecification spec01;

    @Before
    public void setUp() {
        spec01 = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com/").build();
    }
}
