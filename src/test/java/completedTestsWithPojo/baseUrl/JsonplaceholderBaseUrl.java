package completedTestsWithPojo.baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonplaceholderBaseUrl {

    protected RequestSpecification spec03;

    @Before
    public void setUp() {
        spec03 = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com/").build();
    }
}
