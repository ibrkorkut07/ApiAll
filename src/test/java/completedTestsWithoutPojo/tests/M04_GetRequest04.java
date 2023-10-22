package completedTestsWithoutPojo.tests;

import completedTestsWithoutPojo.baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import completedTestsWithoutPojo.pojos.DummyEmployeesPojo;
import completedTestsWithoutPojo.pojos.ExpAgesPojo;
import completedTestsWithoutPojo.testData.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class M04_GetRequest04 extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/employees url'ine
    accept type'i "application/json" olan GET request'i yolladigimda gelen response'un
        status kodunun 200
        content type'inin "application/json"
        employees sayisinin 24
        5. çalışanın isminin "Airi Satou" olduğunu ,
        6. çalışanın maaşının 372000 olduğunu ,
        employee'lerden birinin "Rhona Davidson"
        gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin
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

        response.then().assertThat().statusCode(200).contentType("application/json");

        response.then().body("data.employee_name", hasSize(24),
                "data[4].employee_name", equalTo("Airi Satou"),
                "data[5].employee_salary", equalTo(372000),
                "data.employee_name", hasItem("Ashton Cox"),
                "data.employee_age", hasItems(21, 23, 61));
    }

    // Matchers with Testdata class
    @Test
    public void test02(){
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(spec02).when().get("{p1}/{p2}/{p3}");

        DummyTestData expObject = new DummyTestData();
        HashMap<String, Object> expTestData = expObject.expectedEmployeeData02();

        response.then().body("data.employee_name", hasSize((Integer) expTestData.get("dataSize")),
                "data[4].employee_name", equalTo(expTestData.get("5themployeeName")),
                "data[5].employee_salary", equalTo(expTestData.get("6themployeeSalary")),
                "data.employee_name", hasItem(expTestData.get("anEmployeeName")),
                "data.employee_age", hasItems(21, 23, 61));
    }

    // Jsonpath
    @Test
    public void test03(){
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(spec02).when().get("{p1}/{p2}/{p3}");

        DummyTestData expObject = new DummyTestData();
        HashMap<String, Object> expTestData = expObject.expectedEmployeeData02();

        JsonPath json = response.jsonPath();
        List<String> nameList = json.getList("data.employee_name");
        List<Integer> ageList = json.getList("data.employee_age");

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
        assertEquals(expTestData.get("NumberOfEmployees"), json.get(String.valueOf(nameList.size())));
        assertEquals(expTestData.get("5themployeeName"), json.getString("data[4].employee_name"));
        assertEquals(expTestData.get("6themployeeSalary"), json.getInt("data[5].employee_salary"));
        assertTrue(nameList.contains(expTestData.get("anEmployeeName")));

        //"21", "23", "61" yaşlarında employeeler olduğunu test edin

        // with expected data
        assertTrue(ageList.containsAll((Collection<?>) expTestData.get("expAgeList")));

        // by new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(21);
        list.add(23);
        list.add(61);

        Assert.assertTrue(json.getList("data.employee_age").containsAll(list));

        // by Arrays.asList();
        List<Integer> ages = Arrays.asList(21,23,61);
        Assert.assertTrue(json.getList("data.employee_age").containsAll(ages));
    }

    // Deserialization
    @Test
    public void test04(){
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().contentType(ContentType.JSON).spec(spec02).when().get("{p1}/{p2}/{p3}");
        DummyTestData dummyTestData = new DummyTestData();
        HashMap<String, Object> expData = dummyTestData.expAllEmployeesDataSetUp();
        HashMap<String, Object> actDataMap = response.as(HashMap.class);

        List<Object> actDataList = (List<Object>) actDataMap.get("data");

        Assert.assertEquals(expData.get("statusCode"), response.statusCode());
        Assert.assertEquals(expData.get("contentType"), response.contentType());
        Assert.assertEquals(expData.get("dataSize"), actDataList.size());

        Assert.assertEquals(expData.get("5thEmployeeName"), ( (Map)( (List) actDataMap.get("data")).get(4)).get("employee_name"));
        Assert.assertEquals(expData.get("6thEmployeeSalary"), ( (Map) ((List<?>) actDataMap.get("data")).get(5) ).get("employee_salary") );
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i<actDataList.size(); i++) {
            nameList.add((String) ((Map<?, ?>) ((List<?>) actDataMap.get("data")).get(i)).get("employee_name"));
        }
        System.out.println("nameList = " + nameList);
        assertTrue( nameList.contains(expData.get("anEmployeeName")));

        List<Integer> ageList = new ArrayList<>();
        for (int i = 0; i<actDataList.size(); i++) {
            ageList.add((Integer) ((Map<?, ?>) ((List<?>) actDataMap.get("data")).get(i)).get("employee_age"));
        }
        System.out.println("ageList = " + ageList);
        List<Integer> expAgeList = Arrays.asList(21, 23, 61);

        assertTrue(ageList.containsAll(expAgeList));

        /*
          status kodunun 200
          content type'inin "application/json"
          employees sayisinin 24
          5. çalışanın isminin "Airi Satou" olduğunu
          6. çalışanın maaşının "372000" olduğunu ,
          employee'lerden birinin "Ashton Cox"
          gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin
          */
    }

    // Pojo
    @Test
    public void test05(){
        //1) URL
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");

        //2) EXPECTED DATA
        ExpAgesPojo expAgesPojo = new ExpAgesPojo(21, 23, 61);
        DummyEmployeesPojo expEmployeesData = new DummyEmployeesPojo(200, "application/json",
                24, "Airi Satou", 372000,"Rhona Davidson",
                expAgesPojo);

        //3) REQUEST & RESPONSE
        Response response = given().contentType(ContentType.JSON).spec(spec02).when().get("{p1}/{p2}/{p3}");

        //4) ASSERTION
        HashMap<String, Object> actdata = response.as(HashMap.class);

        response.then().assertThat().statusCode(200).contentType("application/json");
        Assert.assertEquals(expEmployeesData.getNumberOfEmployees(), ((List) actdata.get("data")).size());
        Assert.assertEquals(expEmployeesData.getEmployeeName5(), ((Map<?, ?>) ((List<Object>) actdata.get("data")).get(4)).get("employee_name")  );
//        Assert.assertEquals(expEmployeesData.getEmployeeSalary6(), ((List) actdata.get("data")).  );
//        Assert.assertEquals(expEmployeesData.getAnEmployeeName(), ((List) actdata.get("data")).  );
//        Assert.assertEquals(expEmployeesData.getAges(), ((List) actdata.get("data")).  );

    /*
    http://dummy.restapiexample.com/api/v1/employees url'ine
    accept type'i "application/json" olan GET request'i yolladigimda gelen response'un
        status kodunun 200
        content type'inin "application/json"
        employees sayisinin 24
        5. çalışanın isminin "Airi Satou" olduğunu ,
        6. çalışanın maaşının "372000" olduğunu ,
        employee'lerden birinin "Rhona Davidson"
        gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin
    */
    }

    // ObjectMapper
    @Test
    public void test06(){

        //1) URL
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");

        //2) EXPECTED DATA
        //3) REQUEST & RESPONSE
        //4) ASSERTION

        Response response = given().spec(spec02).when().get("{p1}/{p2}/{p3}");
    }
}
