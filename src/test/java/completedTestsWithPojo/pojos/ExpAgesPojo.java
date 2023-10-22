package completedTestsWithPojo.pojos;

public class ExpAgesPojo {
    private int age1;
    private int age2;
    private int age3;

    public int getAge1() {
        return age1;
    }

    public void setAge1(int age1) {
        this.age1 = age1;
    }

    public int getAge2() {
        return age2;
    }

    public void setAge2(int age2) {
        this.age2 = age2;
    }

    public int getAge3() {
        return age3;
    }

    public void setAge3(int age3) {
        this.age3 = age3;
    }

    public ExpAgesPojo() {
    }

    public ExpAgesPojo(int age1, int age2, int age3) {
        this.age1 = age1;
        this.age2 = age2;
        this.age3 = age3;
    }

    @Override
    public String toString() {
        return "ExpAgesPojo{" +
                "age1=" + age1 +
                ", age2=" + age2 +
                ", age3=" + age3 +
                '}';
    }
}
