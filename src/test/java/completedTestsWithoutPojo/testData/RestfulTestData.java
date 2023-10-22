package completedTestsWithoutPojo.testData;

import java.util.HashMap;

public class RestfulTestData {

    public HashMap<String, Object> statusCodeSetUp() {
        HashMap<String, Object> expStatusCode = new HashMap<>();
        expStatusCode.put("statusCode", 200);
        expStatusCode.put("contentType", "application/json; charset=utf-8");
        expStatusCode.put("statusLine", "HTTP/1.1 200 OK");
        return expStatusCode;
    }

    public HashMap<String, Object> booking1SetUp () {
        HashMap<String, Object> expData = new HashMap<>();
        expData.put("firstname", "Eric");
        expData.put("lastname", "Smith");
        expData.put("totalprice", 605);
        expData.put("depositpaid", true);
        expData.put("bookingdates.checkin", "2016-10-21");
        expData.put("bookingdates.checkout", "2020-04-02");
        return expData;
    }

    public HashMap<String, Object> booking7DataSetUp () {
        HashMap<String, Object> expBooking7Data = new HashMap<>();
        expBooking7Data.put("statusCode", 200);
        expBooking7Data.put("contentType", "application/json; charset=utf-8");
        expBooking7Data.put("firstname", "Mary");
        expBooking7Data.put("lastname", "Smith");
        expBooking7Data.put("totalprice", 390);
        expBooking7Data.put("depositpaid", true);
        expBooking7Data.put("bookingdates.checkin", "2022-12-05");
        expBooking7Data.put("bookingdates.checkout", "2022-12-11");
        return expBooking7Data;
    }

    public HashMap<String, Object> emp10ExpdataMap () {
        HashMap<String, Object> expDataMap = new HashMap<>();
        expDataMap.put("statusCode", 200);
        expDataMap.put("4themployee_name", "Airi Satou");
        expDataMap.put("dataSize", 24);
        expDataMap.put("TheLastButEmployeeSalary", 106450);
        expDataMap.put("expAge1", 19);
        expDataMap.put("expAge2", 21);
        expDataMap.put("expAge3", 40);
        expDataMap.put("10thEmployeeId", 11);
        expDataMap.put("10thEmployeeName", "Jena Gaines");
        expDataMap.put("10thEmployeeSalary", 90560);
        expDataMap.put("10thEmployeeAge", 30);
        expDataMap.put("10thEmployeeProfileImage", "");
        return expDataMap;
    }

    public HashMap<String, Object> booking5ExpDataSetUp() {

        HashMap<String, Object> booking5ExpData = new HashMap<>();
        booking5ExpData.put("statusCode", 200);
        booking5ExpData.put("contentType", "application/json; charset=utf-8");
        booking5ExpData.put("firstname", "Jim");
        booking5ExpData.put("lastname", "Jackson");
        booking5ExpData.put("totalprice", 880);
        booking5ExpData.put("depositpaid", false);
        booking5ExpData.put("bookingdates.checkin", "2016-10-04");
        booking5ExpData.put("bookingdates.checkout", "2021-12-06");
        return booking5ExpData;
    }

    public void bookingPostRequestMap () {
        HashMap<String, Object> postRequestdata = new HashMap<>();
        postRequestdata.put("firstname", "Selim");
        postRequestdata.put("lastname", "Ak");
        postRequestdata.put("totalprice", 11111);
        postRequestdata.put("depositpaid", true);
        postRequestdata.put("bookingdates.checkin", "2020-09-09");
        postRequestdata.put("bookingdates.checkout", "2020-09-21");
    }

    public HashMap<String, Object> bookingPostExpData () {
        HashMap<String, Object> postExpData = new HashMap<>();
        postExpData.put("statusCode", 200);
        postExpData.put("firstname", "Selim");
        postExpData.put("lastname", "Ak");
        postExpData.put("totalprice", 11111);
        postExpData.put("depositpaid", true);
        postExpData.put("bookingdates.checkin", "2020-09-01");
        postExpData.put("bookingdates.checkout", "2020-09-21");
        return postExpData;
}



}
