package testData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestdataPra {

    public HashMap<String, Object> expectedEmployeeData() {
        HashMap<String, Object> expEmpData = new HashMap<>();
        expEmpData.put("statusCode", 200);
        expEmpData.put("contentType", "application/json");
        expEmpData.put("dataSize", 24);
        expEmpData.put("employee_name", "Ashton Cox");
        expEmpData.put("expAge1", 19);
        expEmpData.put("expAge2", 21);
        expEmpData.put("expAge3", 61);
        return expEmpData;
    }

    public HashMap<String, Object> expectedEmployeeData02() {
        List<Integer> expAgeList = new ArrayList<>();
        expAgeList.add(21);
        expAgeList.add(23);
        expAgeList.add(61);
        HashMap<String, Object> expEmpData = new HashMap<>();
        expEmpData.put("statusCode", 200);
        expEmpData.put("dataSize", 24);
        expEmpData.put("5themployeeName", "Airi Satou");
        expEmpData.put("anEmployeeName", "Ashton Cox");
        expEmpData.put("6themployeeSalary", 372000);
        expEmpData.put("expAgeList", expAgeList);
//        expEmpData.put("expAge1", 21);
//        expEmpData.put("expAge2", 23);
//        expEmpData.put("expAge3", 61);
        return expEmpData;
    }

    public HashMap<String, Object> employee1DataMap() {
        HashMap<String, Object> expDataMap = new HashMap<>();
        expDataMap.put("statusCode", 200);
        expDataMap.put("id", 1);
        expDataMap.put("employee_name", "Tiger Nixon");
        expDataMap.put("employee_salary", 320800);
        expDataMap.put("employee_age", 61);
        expDataMap.put("profile_image", "");
        expDataMap.put("message", "Successfully! Record has been fetched.");
        return expDataMap;
    }

    public HashMap<String, Object> expAllEmployeesDataSetUp() {
        HashMap<String, Object> employee11ExpData = new HashMap<>();
        employee11ExpData.put("id", 11);
        employee11ExpData.put("employee_name", "Jena Gaines");
        employee11ExpData.put("employee_salary", 90560);
        employee11ExpData.put("employee_age", 30);
        employee11ExpData.put("profile_image", "");
        HashMap<String, Object> expAges = new HashMap<>();
        expAges.put("expAge1", 19);
        expAges.put("expAge2", 21);
        expAges.put("expAge3", 40);
//        List<Integer> expAgeList = new ArrayList<>();
//        expAgeList.add(19);
//        expAgeList.add(21);
//        expAgeList.add(40);
        HashMap<String, Object> expEmployeesData = new HashMap<>();
        expEmployeesData.put("statusCode", 200);
        expEmployeesData.put("contentType", "application/json");
        expEmployeesData.put("dataSize", 24);
        expEmployeesData.put("5thEmployeeName", "Airi Satou");
        expEmployeesData.put("anEmployeeName", "Rhona Davidson");
        expEmployeesData.put("NumberOfEmployees", 24);
        expEmployeesData.put("6thEmployeeSalary", 372000);
        expEmployeesData.put("SalaryOfTheLastButEmployee", 106450);
        expEmployeesData.put("age1", 19);
        expEmployeesData.put("age2", 21);
        expEmployeesData.put("age3", 23);
        expEmployeesData.put("age4", 40);
        expEmployeesData.put("age5", 61);
        expEmployeesData.put("11thEmployeeData", employee11ExpData);
        expEmployeesData.put("expAges", expAges);
        // expEmployeesData.put("expAgeList", expAgeList);
        return expEmployeesData;

    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    5. Çalışan isminin "Airi Satou" olduğunu ,
    çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
    40,21 ve 19 yaslarında çalışanlar olup olmadığını
    11. Çalışan bilgilerinin
    {
        “id”:11
        "employee_name": "Jena Gaines",
        "employee_salary": 90560,
        "employee_age": 30,
        "profile_image": "" }
    }   olduğunu test edin.
    */
    }

    public JSONObject postAhmetJSONObjectSetup() {
        JSONObject postRequestData = new JSONObject();
        postRequestData.put("name", "Ahmet Aksoy");
        postRequestData.put("salary", "1000");
        postRequestData.put("age", "18");
        postRequestData.put("status", "success");
        postRequestData.put("message", "Successfully! Record has been added.");
        return postRequestData;

    /* http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
    {
        "name":"Ahmet Aksoy",
        "salary":"1000",
        "age":"18"
    }
    gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin ,
    {
    "status": "success",
    "data": {
        "name": "Ahmet Aksoy",
        "salary": "1000",
        "age": "18",
    },
    "message": "Successfully! Record has been added."
    }   olduğunu test edin
    */
    }

    public HashMap<String, Object> postAhmetHashMapSetup() {
        HashMap<String, Object> postRequestData = new HashMap<String, Object>();
        postRequestData.put("name", "Ahmet Aksoy");
        postRequestData.put("salary", "1000");
        postRequestData.put("age", "18");
        postRequestData.put("status", "success");
        postRequestData.put("message", "Successfully! Record has been added.");
        return postRequestData;
    }

    public HashMap<String, Object> setUpExpectedData() {
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("status", "success");
        expectedData.put("message", "Successfully! Record has been added.");
        return expectedData;
    }

    public  JSONObject expDeleteDataObject() {
        JSONObject expDeleteData = new JSONObject();
        expDeleteData.put("status", "success");
        expDeleteData.put("data", "2");
        expDeleteData.put("message", "Successfully! Record has been deleted");
        return expDeleteData;
    }
}
