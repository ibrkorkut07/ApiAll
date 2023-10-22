package completedTestsWithPojo.pojos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingdatesPojo {
    /*
    {
        "firstname": "Susan",
        "lastname": "Wilson",
        "totalprice": 543,
        "depositpaid": true,
        "bookingdates": {
                "checkin": "2021-03-30",
                "checkout": "2022-07-18"
                },
        // "additionalneeds": "Breakfast"
    }
    */

    private String checkin;
    private String checkout;

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public BookingdatesPojo() {
    }

    public BookingdatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "BookingdatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
