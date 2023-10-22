package completedTestsWithPojo.pojos;

public class DummyEmployeesPojo {
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

    private int statusCode;
    private String contentType;
    private int numberOfEmployees;
    private String employeeName5;
    private int employeeSalary6;
    private String anEmployeeName;
    private ExpAgesPojo ages;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getEmployeeName5() {
        return employeeName5;
    }

    public void setEmployeeName5(String employeeName5) {
        this.employeeName5 = employeeName5;
    }

    public int getEmployeeSalary6() {
        return employeeSalary6;
    }

    public void setEmployeeSalary6(int employeeSalary6) {
        this.employeeSalary6 = employeeSalary6;
    }

    public String getAnEmployeeName() {
        return anEmployeeName;
    }

    public void setAnEmployeeName(String anEmployeeName) {
        this.anEmployeeName = anEmployeeName;
    }

    public ExpAgesPojo getAges() {
        return ages;
    }

    public void setAges(ExpAgesPojo ages) {
        this.ages = ages;
    }

    public DummyEmployeesPojo() {
    }

    public DummyEmployeesPojo(int statusCode, String contentType, int numberOfEmployees,
                              String employeeName5, int employeeSalary6, String anEmployeeName, ExpAgesPojo ages) {
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.numberOfEmployees = numberOfEmployees;
        this.employeeName5 = employeeName5;
        this.employeeSalary6 = employeeSalary6;
        this.anEmployeeName = anEmployeeName;
        this.ages = ages;
    }

    @Override
    public String toString() {
        return "DummyEmployeesPojo{" +
                "statusCode=" + statusCode +
                ", contentType='" + contentType + '\'' +
                ", numberOfEmployees=" + numberOfEmployees +
                ", employeeName5='" + employeeName5 + '\'' +
                ", employeeSalary6=" + employeeSalary6 +
                ", anEmployeeName='" + anEmployeeName + '\'' +
                ", ages=" + ages +
                '}';
    }


}
