package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class TestBaseUrls {

    protected RequestSpecification placeSpec;
    protected RequestSpecification restfulSpec;
    protected RequestSpecification dummySpec;
    // Or all in one assignment;
    // protected RequestSpecification placeSpec, resfulSpec, dummySpec;

    @Before
    public void setUp01() {
        placeSpec = new
                RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com/todos").   // ...todos or ...todos/1
                build();
    }

    @Before
    public void setUp02() {
        restfulSpec = new
                RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com/booking/").   // ...booking or ...booking/1
                build();
    }

    @Before
    public void setUp04() {
        dummySpec = new
                RequestSpecBuilder().
                setBaseUri("http://dummy.restapiexample.com/api/v1/").   // ...employees or ...employee/1
                build();
    }

}
