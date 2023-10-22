package completedTestsWithoutPojo.tests;

import completedTestsWithoutPojo.baseUrl.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import completedTestsWithoutPojo.testData.RestfulTestData;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;

public class M10_GetRequest10 extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    5. Çalışan isminin "Airi Satou" olduğunu ,
    çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
    40,21 ve 19 yaslarında çalışanlar olup olmadığını
    11. Çalışan bilgilerinin
    {
        “id”:”11”
        "employee_name": "Jena Gaines",
        "employee_salary": 90560,
        "employee_age": 30,
        "profile_image": "" }
    }   olduğunu test edin.
*/
    @Test
    public void test00 () {
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(spec02).when().get("{p1}/{p2}/{p3}");
    }

    // Matchers without Testdata class
    @Test
    public void test01(){
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(spec02).when().get("{p1}/{p2}/{p3}");

        response.then().assertThat().statusCode(200).
                body("data[4].employee_name", equalTo("Airi Satou"),
                "data.id", hasSize(24),
                "data[-2].employee_salary", equalTo(106450),
                "data.employee_age", hasItems(40,21, 19));

        response.then().body("data[10].id", equalTo(11),
                    "data[10].employee_name", equalTo("Jena Gaines"),
                "data[10].employee_salary", equalTo(90560),
                "data[10].employee_age", equalTo(30),
                "data[10].profile_image", equalTo(""));
    }

    // Jsonpath
    @Test
    public void test02(){
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(spec02).when().get("{p1}/{p2}/{p3}");

        HashMap<String, Object> actData = response.as(HashMap.class);

        RestfulTestData restfulTestData = new RestfulTestData();
        HashMap<String, Object> expData = restfulTestData.emp10ExpdataMap();

        JsonPath json = response.jsonPath();
        List<Integer> ageList = json.getList("data.employee_age");
        // System.out.println(ageList);
        Integer dataSize = ageList.size();

        assertEquals(expData.get("4themployee_name"), json.get("data[4].employee_name"));
        assertEquals(expData.get("dataSize"), dataSize);
        assertEquals(expData.get("TheLastButEmployeeSalary"), json.get("data[-2].employee_salary"));

        List<Integer> expAgeList = Arrays.asList(19, 21, 40);
        // System.out.println(expAgeList);

        assertTrue(ageList.containsAll(expAgeList));
        assertEquals(expData.get("10thEmployeeId"), json.get("data[10].id"));
        assertEquals(expData.get("10thEmployeeName"), json.get("data[10].employee_name"));
        assertEquals(expData.get("10thEmployeeSalary"), json.get("data[10].employee_salary"));
        assertEquals(expData.get("10thEmployeeAge"), json.get("data[10].employee_age"));
        assertEquals(expData.get("10thEmployeeProfileImage"), json.get("data[10].profile_image"));
    }

    // Deserialization
    @Test
    public void test03(){
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(spec02).when().get("{p1}/{p2}/{p3}");

        List<Integer> expAgeList = new ArrayList<>();
        expAgeList.add(19);
        expAgeList.add(21);
        expAgeList.add(40);

        HashMap<String, Object> actData = response.as(HashMap.class);

        RestfulTestData restfulTestData = new RestfulTestData();
        HashMap<String, Object> expData = restfulTestData.emp10ExpdataMap();
        List<?> actdataList = (List<?>) actData.get("data");
        List<Integer> ageList = new ArrayList<>();

        for (int i = 0; i<actdataList.size(); i++) {
            ageList.add(((HashMap<String, Integer>) ((List<?>) actData.get("data")).get(i)).get("employee_age"));
        }
        System.out.println("ageList = " + ageList);

        JsonPath json = response.jsonPath();

        assertEquals(expData.get("statusCode"), response.statusCode());
        assertEquals(expData.get("4themployee_name"), ((Map)((List) actData.get("data")).get(4)).get("employee_name"));
        assertEquals(expData.get("dataSize"), ((List) actData.get("data")).size());
//        assertEquals(expData.get("SalaryOfTheLastButEmployee"),
//                    ((HashMap<?, ?>) ((List<?>) actData.get("data")).get(actdataList.size()-2)).get("employee_salary"));
        assertEquals(106450, ((HashMap<?, ?>) ((List<?>) actData.get("data")).get(actdataList.size()-2)).get("employee_salary"));
        assertTrue(ageList.containsAll( expAgeList ));
        assertEquals(11, ((HashMap<?, ?>) ((List<?>) actData.get("data")).get(10)).get("id"));
        assertEquals("Jena Gaines", ((HashMap<?, ?>) ((List<?>) actData.get("data")).get(10)).get("employee_name"));
        assertEquals(90560, ((HashMap<?, ?>) ((List<?>) actData.get("data")).get(10)).get("employee_salary"));
        assertEquals(30, ((HashMap<?, ?>) ((List<?>) actData.get("data")).get(10)).get("employee_age"));
        assertEquals("", ((HashMap<?, ?>) ((List<?>) actData.get("data")).get(10)).get("profile_image"));

    /*
    Status kodun 200 olduğunu,
    5. Çalışan isminin "Airi Satou" olduğunu ,
    çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
    40,21 ve 19 yaslarında çalışanlar olup olmadığını
    11. Çalışan bilgilerinin
    {
        “id”:”11”
        "employee_name": "Jena Gaines",
        "employee_salary": "90560",
        "employee_age": "30",
        "profile_image": "" }
    }   olduğunu test edin.
    */
    }

    // Pojo
    @Test
    public void test04(){
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(spec02).when().get("{p1}/{p2}/{p3}");

        //1) URL
        //2) EXPECTED DATA
        //3) REQUEST & RESPONSE
        //4) ASSERTION

    /*
    Status kodun 200 olduğunu,
    5. Çalışan isminin "Airi Satou" olduğunu ,
    çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
    40,21 ve 19 yaslarında çalışanlar olup olmadığını
    11. Çalışan bilgilerinin
    {
        “id”:”11”
        "employee_name": "Jena Gaines",
        "employee_salary": "90560",
        "employee_age": "30",
        "profile_image": "" }
    }   olduğunu test edin.
    */
    }

    // ObjectMapper
    @Test
    public void test05(){
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(spec02).when().get("{p1}/{p2}/{p3}");

    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    5. Çalışan isminin "Airi Satou" olduğunu ,
    çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
    40,21 ve 19 yaslarında çalışanlar olup olmadığını
    11. Çalışan bilgilerinin
    {
        “id”:”11”
        "employee_name": "Jena Gaines",
        "employee_salary": "90560",
        "employee_age": "30",
        "profile_image": "" }
    }   olduğunu test edin.
    */
    }

    //1) URL
    //2) EXPECTED DATA
    //3) REQUEST & RESPONSE
    //4) ASSERTION
}
