package completedTestsWithoutPojo.tests;

import completedTestsWithoutPojo.baseUrl.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

public class M07_GetRequest07 extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/employees  url ine bir istek gönderildiğinde
    Dönen response un
    Status kodunun 200,
    1)10’dan büyük tüm id’leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
    2)30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
    3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
        bunların içerisinde "Charde Marshall" olduğunu test edin
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
        response.then().assertThat().statusCode(200);
        // 10’dan büyük 14 id olduğunu
        response.then().body("data.findAll{it.id>10}.id", hasSize(14));
        // Maası 350000 den büyük olan tüm employee name’leri içerisinde “Charde Marshall” olduğunu test edin
        response.then().body("data.findAll{it.employee_salary>350000}.employee_name", hasItem("Charde Marshall"));
    }

    // Jsonpath
    @Test
    public void test03(){
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(spec02).when().get("{p1}/{p2}/{p3}");
        response.then().assertThat().statusCode(200);

        JsonPath json = response.jsonPath();
        // List<Integer> idList = json.getList("data.id");
        System.out.println("IDs higher than 10 = " + json.getList("data.findAll{it.id>10}.id"));
        assertEquals(14, json.getList("data.findAll{it.id>10}.id").size());

        List<Integer> ageListHigherThan30 = json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println("ageListHigherThan30 = " + ageListHigherThan30);
        Collections.sort(ageListHigherThan30);
        Assert.assertEquals((Integer) 23, ageListHigherThan30.get(ageListHigherThan30.size()-1));

        System.out.println("Employees whose salary>350000 = " + json.getList("data.findAll{it.employee_salary>350000}.employee_name"));
        Assert.assertTrue(json.getList("data.findAll{it.employee_salary>350000}.employee_name").contains("Charde Marshall"));
    }

    // Deserialization
    @Test
    public void test04(){
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");
        Response response = given().spec(spec02).when().get("{p1}/{p2}/{p3}");

        Assert.assertEquals(200, response.statusCode());

        HashMap<String, Object> actDatamap = response.as(HashMap.class);
        int dataSize = ((Integer) ((List<Object>) actDatamap.get("data")).size());

        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i<dataSize; i++) {
            if ((Integer) ((HashMap) ((List) actDatamap.get("data")).get(i)).get("id") > 10) {
            idList.add((Integer) ((HashMap) ((List) actDatamap.get("data")).get(i)).get("id")); }
        }
        System.out.println("idList Higher Than 10 = " + idList);
        Assert.assertEquals(14, idList.size());

        List<Integer> ageList = new ArrayList<>();

        for (int i = 0; i<dataSize; i++) {
            if ( ( (Integer) ( (HashMap) ((List<?>) actDatamap.get("data")).get(i)).get("employee_age") ) < 30) {
                ageList.add((Integer) ( (HashMap) ((List<?>) actDatamap.get("data")).get(i)).get("employee_age"));
            }
        }
        System.out.println("ageList Higher Than 30= " + ageList);
        Collections.sort(ageList);
        Assert.assertEquals((Integer) 23, ageList.get(ageList.size()-1) );

        List<String> nameList = new ArrayList<>();

        for (int i = 0; i<dataSize; i++) {
            if ( ( (Integer) ( (HashMap) ((List<?>) actDatamap.get("data")).get(i)).get("employee_salary") ) > 350000) {
                nameList.add((String) ( (HashMap) ((List<?>) actDatamap.get("data")).get(i)).get("employee_name"));
            }
        }
        System.out.println("nameList Higher Than 350000 Salary= " + nameList);
        Assert.assertTrue(nameList.contains("Charde Marshall"));

        /*  Status kodunun 200,
            1) 10’dan büyük tüm id’leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
            2) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
            3) Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
               bunların içerisinde "Charde Marshall" olduğunu test edin  */
    }

    // Pojo
    @Test
    public void test05(){

        //1) URL
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");

        //2) EXPECTED DATA

        //3) REQUEST & RESPONSE
        Response response = given().spec(spec02).when().get("{p1}/{p2}/{p3}");

        //4) ASSERTION
    }

    // ObjectMapper
    @Test
    public void test06(){

        //1) URL
        spec02.pathParams("p1", "api", "p2", "v1", "p3", "employees");

        //2) EXPECTED DATA

        //3) REQUEST & RESPONSE
        Response response = given().spec(spec02).when().get("{p1}/{p2}/{p3}");

        //4) ASSERTION
     
    }
}
