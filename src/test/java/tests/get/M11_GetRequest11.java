package tests.get;

import baseUrl.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class M11_GetRequest11 extends DummyBaseUrl {

    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    En yüksek maaşın 725000 olduğunu,
    En küçük yaşın 19 olduğunu,
    İkinci en yüksek maaşın 675000  olduğunu test edin.
     */

    @Test
    public void test00 () {
        specDummy.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().contentType("application/json").spec(specDummy).when().get("{p1}/{p2}/{p3}");
    }

    // Matchers
    @Test
    public void test01(){
        specDummy.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(specDummy).when().get("{p1}/{p2}/{p3}");

    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    En yüksek maaşın 725000 olduğunu,
    En küçük yaşın 19 olduğunu,
    İkinci en yüksek maaşın 675000  olduğunu test edin.
    */
    }

    // Jsonpath
    @Test
    public void test02(){
        specDummy.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(specDummy).when().get("{p1}/{p2}/{p3}");

        response.then().assertThat().statusCode(200);
        JsonPath json = response.jsonPath();

        List<Integer> salaryList = json.getList("data.employee_salary");
        Collections.sort(salaryList);
        assertEquals((Integer) 725000, salaryList.get(salaryList.size()-1));
        List<Integer> ageList = json.getList("data.employee_age");
        Collections.sort(ageList);
        assertEquals((Integer) 19, ageList.get(0));
        assertEquals((Integer) 675000, salaryList.get(salaryList.size()-2));
    }

    // Deserialization
    @Test
    public void test03(){
        specDummy.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(specDummy).when().get("{p1}/{p2}/{p3}");

        response.then().assertThat().statusCode(200);

        HashMap<String, Object> actDataMap = response.as(HashMap.class);
        int dataSize = ((List) actDataMap.get("data")).size();

        System.out.println("dataSize = " + dataSize);

        List<Integer> salaryList = new ArrayList<> ();
        for (int i = 0; i<dataSize; i++) {
            salaryList.add((Integer) ( (HashMap<?,?>) ((List<?>) actDataMap.get("data")).get(i) ).get("employee_salary"));
        }

        System.out.println("salaryList = " + salaryList);
        Collections.sort(salaryList);
        // System.out.println("salaryList.get(dataSize-1) = " + salaryList.get(dataSize - 1));
        assertEquals( (Integer)  725000, salaryList.get(dataSize-1) );
        assertEquals( (Integer)  675000, salaryList.get(dataSize-2) );

        List<Integer> ageList = new ArrayList<> ();
        for (int i = 0; i<dataSize; i++) {
            ageList.add((Integer) ( (HashMap<?,?>) ((List<?>) actDataMap.get("data")).get(i) ).get("employee_age"));
        }

        System.out.println("ageList = " + ageList);

        Collections.sort(ageList);
        assertEquals((Integer) 19, ageList.get(0));
    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    En yüksek maaşın 725000 olduğunu,
    En küçük yaşın 19 olduğunu,
    İkinci en yüksek maaşın 675000  olduğunu test edin.
    */
    }

    // Pojo
    @Test
    public void test04(){
        specDummy.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(specDummy).when().get("{p1}/{p2}/{p3}");

        //1) URL
        //2) EXPECTED DATA
        //3) REQUEST & RESPONSE
        //4) ASSERTION

    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    En yüksek maaşın 725000 olduğunu,
    En küçük yaşın 19 olduğunu,
    İkinci en yüksek maaşın 675000  olduğunu test edin.
    */
    }

    // ObjectMapper
    @Test
    public void test05(){

        //1) URL
        //2) EXPECTED DATA
        //3) REQUEST & RESPONSE
        //4) ASSERTION
        specDummy.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(specDummy).when().get("{p1}/{p2}/{p3}");

    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    En yüksek maaşın 725000 olduğunu,
    En küçük yaşın 19 olduğunu,
    İkinci en yüksek maaşın 675000  olduğunu test edin.
    */
    }
}
