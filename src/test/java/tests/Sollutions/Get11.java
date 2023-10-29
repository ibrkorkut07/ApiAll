package tests.Sollutions;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get11 extends DummyBaseUrl {

    /*
        When Send a GET Request to
             http://dummy.restapiexample.com/api/v1/employees
        Then HTTP Status Code should be 200
        And  Highest salary should be 725000
        And  Lowest age should be 19
        Then Second-highest salary should be 675000
    */

    // MATCHERS
    @Test
    public void get11Matchers(){
        specDummy.pathParams("1", "api", "2", "v1", "3", "employees");
        Response response = given().spec(specDummy).when().get("{1}/{2}/{3}");

        response.then().statusCode(200);
//        String bodyMap = response.getBody().asString();
//        System.out.println("bodyMap = " + bodyMap);
    }

    // JSONPATH
    @Test
    public void get11JsonPath(){
        specDummy.pathParams("1", "api", "2", "v1", "3", "employees");
        Response response = given().spec(specDummy).when().get("{1}/{2}/{3}");

        response.then().statusCode(200);
        JsonPath json = response.jsonPath();
        List<Integer> salaryList = json.getList("data.employee_salary");
        Collections.sort(salaryList);   // Collections() reorders lists in ascending order
        // System.out.println("salaryList = " + salaryList);   // salaryList in ascending order
        Assert.assertEquals((Integer) 725000, salaryList.get(salaryList.size()-1));
        Assert.assertEquals((Integer) 675000, salaryList.get(salaryList.size()-2));

        List<Integer> ageList =  json.getList("data.employee_age");
        Collections.sort(ageList);
        // System.out.println("ageList = " + ageList);   // agelist in ascending order
        Assert.assertEquals((Integer) 19, ageList.get(0));
    }

    // DE-SERIALIZATION
    @Test
    public void test11Deserialization() {
        specDummy.pathParams("1", "api", "2", "v1", "3", "employees");
        Response response = given().contentType(ContentType.JSON).spec(specDummy).when().get("{1}/{2}/{3}");
        response.then().statusCode(200);

        HashMap<String, Object> actdataMap = response.as(HashMap.class);

        List<Integer> employeeSalaryList = new ArrayList<>();
        for (int i = 0; i < ((List<?>) actdataMap.get("data")).size(); i++) {
            employeeSalaryList.add( ((int) ((Map<?, ?>)((List<?>) actdataMap.get("data")).get(i)).get("employee_salary")) );
        }
        Collections.sort(employeeSalaryList);
        assertEquals(725000, ((int) employeeSalaryList.get(employeeSalaryList.size()-1)));
        assertEquals(675000, ((int) employeeSalaryList.get(employeeSalaryList.size()-2)));

        List<Integer> employeeAgeList = new ArrayList<>();
        for (int i = 0; i < ((List<?>) actdataMap.get("data")).size(); i++) {
            employeeAgeList.add( ((int) ((Map<?, ?>)((List<?>) actdataMap.get("data")).get(i)).get("employee_age")) );
        }
        Collections.sort(employeeAgeList);
        assertEquals(19, ((int) employeeAgeList.get(0)));

    /*
        And  Highest salary should be 725000
        And  Lowest age should be 19
        Then Second-highest salary should be 675000
    */
    }

}

    /*
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


    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    En yüksek maaşın 725000 olduğunu,
    En küçük yaşın 19 olduğunu,
    İkinci en yüksek maaşın 675000  olduğunu test edin.

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

    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    En yüksek maaşın 725000 olduğunu,
    En küçük yaşın 19 olduğunu,
    İkinci en yüksek maaşın 675000  olduğunu test edin.

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


    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    En yüksek maaşın 725000 olduğunu,
    En küçük yaşın 19 olduğunu,
    İkinci en yüksek maaşın 675000  olduğunu test edin.

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


    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    En yüksek maaşın 725000 olduğunu,
    En küçük yaşın 19 olduğunu,
    İkinci en yüksek maaşın 675000  olduğunu test edin.

        }



        GSON is a converter
        GSON is used to convert Json Format Data to Java Objects --> De-Serialization
        GSON is used to convert Java object to Json Data Format ==> Serialization
    */