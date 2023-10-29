package tests.Sollutions;

import baseUrl.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class M06_GetRequest06 extends DummyBaseUrl {

    /*
    http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
    1) Butun calisanlarin isimlerini consola yazdıralim
    2) 3. calisan kisinin ismini konsola yazdıralim
    3) Ilk 5 calisanin adini konsola yazdiralim
    4) En son calisanin adini konsola yazdiralim
    */

    @Test
    public void test00 () {
        specDummy.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(specDummy).when().get("{p1}/{p2}/{p3}");
    }

    // Matchers
    @Test
    public void test01(){
    // Matchers Class is to match exp and act data. It is not related to printing.
    }

    // Jsonpath
    @Test
    public void test02(){
        specDummy.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(specDummy).when().get("{p1}/{p2}/{p3}");
        JsonPath json = response.jsonPath();
        System.out.println("All Employee Names : " + json.getList("data.employee_name"));
        System.out.println("3rd Employee Name = " + json.getString("data[2].employee_name"));
        System.out.println("First 5 Employee Names = " + json.getList("data.findAll{it.id<6}.employee_name"));
        System.out.println("Last Employee Name = " + json.getString("data[-1].employee_name"));
    }

    // Deserialization
    @Test
    public void test03(){
        specDummy.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(specDummy).when().get("{p1}/{p2}/{p3}");

        HashMap<String, Object> actDataMap = response.as(HashMap.class);
        List<Object> actData = (List<Object>) actDataMap.get("data");
        // System.out.println("actData.size() = " + actData.size());

        List<List> nameList = new ArrayList<>();
        for (int i = 0; i<actData.size(); i++) {
            nameList.add(Collections.singletonList(((String) ((Map<?, ?>) ((List<?>) actDataMap.get("data")).get(i)).get("employee_name"))));
        }
        System.out.println("nameList = " + nameList);
        System.out.println("3rd Employee Name = " + nameList.get(2));

        for (int i = 0; i<5; i++) {
            System.out.println("Name of employee " + (i+1) + ": " + nameList.get(i));
        }

        System.out.println("Last Employee Name = " + nameList.get(nameList.size() - 1));

    /*  1) Butun calisanlarin isimlerini consola yazdıralim
        2) 3. calisan kisinin ismini konsola yazdıralim
        3) Ilk 5 calisanin adini konsola yazdiralim
        4) En son calisanin adini konsola yazdiralim    */
    }

    // Pojo
    @Test
    public void test04(){
        //1) URL
        specDummy.pathParams("p1", "api", "p2", "v1", "p3", "employees");

        //2) EXPECTED DATA

        //3) REQUEST & RESPONSE
        Response response = given().spec(specDummy).when().get("{p1}/{p2}/{p3}");

        //4) ASSERTION
    }

    // ObjectMapper
    @Test
    public void test05(){

        //1) URL
        specDummy.pathParams("p1", "api", "p2", "v1", "p3", "employees");

        //2) EXPECTED DATA

        //3) REQUEST & RESPONSE
        Response response = given().spec(specDummy).when().get("{p1}/{p2}/{p3}");

        //4) ASSERTION
    }
}
