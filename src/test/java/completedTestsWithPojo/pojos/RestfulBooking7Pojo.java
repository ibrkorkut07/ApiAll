package completedTestsWithPojo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestfulBooking7Pojo {
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
    "additionalneeds": "Breakfast"
    }
    */
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingdatesPojo bookingdates;
    // private String additionalneeds;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingdatesPojo getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingdatesPojo bookingdates) {
        this.bookingdates = bookingdates;
    }

    public RestfulBooking7Pojo() {
    }

    public RestfulBooking7Pojo(String susan, String wilson, int i, BookingdatesPojo dates) {
    }

    public RestfulBooking7Pojo(String firstname, String lastname, int totalprice, boolean depositpaid, BookingdatesPojo bookingdates) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        // this.additionalneeds = additionalneeds;
    }

    @Override
    public String toString() {
        return "RestfulBooking7Pojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                '}';
    }
}
