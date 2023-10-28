package testData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.j2objc.annotations.Property;

import java.util.HashMap;

public class RestfulTestData {
        /*
	    And "firstname" should be "Mary"
	    And "lastname" should be "Ericsson"
	    And "totalprice" should be 111
	    And "depositpaid" should be false
	    And "checkin" date should be "2022-02-19"
	    And "checkout" date should be "2023-05-10"
	    And "additionalneeds" should be "Breakfast"   // not included always
	    NOTE: Keep in mind that values keep changing
   */

    public HashMap<String, Object> bookingSingleUserBookingdatesData () {
        HashMap<String, Object> bookingSingleUserBookingdatesExpData = new HashMap<>();
        bookingSingleUserBookingdatesExpData.put("checkin", "2022-02-19");
        bookingSingleUserBookingdatesExpData.put("checkout", "2023-05-10");
        return bookingSingleUserBookingdatesExpData;
    }

    @JsonIgnoreProperties (ignoreUnknown = true)
    public HashMap<String, Object> bookingSingleUserData () {
        HashMap<String, Object> bookingSingleUserExpData = new HashMap<>();
        bookingSingleUserExpData.put("firstname", "Mary");
        bookingSingleUserExpData.put("lastname", "Ericsson");
        bookingSingleUserExpData.put("totalprice", 111);
        bookingSingleUserExpData.put("depositpaid", false);
        bookingSingleUserExpData.put("checkin", bookingSingleUserBookingdatesData().get("checkin"));
        bookingSingleUserExpData.put("checkout", bookingSingleUserBookingdatesData().get("checkout"));
        bookingSingleUserExpData.put("additionalneeds", "Breakfast");
        return bookingSingleUserExpData;
    }

    @JsonIgnoreProperties (ignoreUnknown = true)
    public HashMap<String, Object> booking2Data () {
        HashMap<String, Object> booking2ExpData = new HashMap<>();
        booking2ExpData.put("statusCode", 200);
        booking2ExpData.put("contentType", "application/json; charset=utf-8");
        booking2ExpData.put("firstname", "Mary");
        booking2ExpData.put("lastname", "Ericsson");
        booking2ExpData.put("totalprice", 111);
        booking2ExpData.put("depositpaid", false);
        booking2ExpData.put("checkin", bookingSingleUserBookingdatesData().get("checkin"));
        booking2ExpData.put("checkout", bookingSingleUserBookingdatesData().get("checkout"));
        booking2ExpData.put("additionalneeds", "Breakfast");
        return booking2ExpData;
    }


}
