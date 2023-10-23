package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class RestfulBaseUrl {

    protected RequestSpecification specRest;
    @Before
    public void setUp() {
        specRest = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com/").build();
    }
}
