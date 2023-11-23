package pojos;

public class HighestLowestEmployeeValues {

    private Integer highestSalary;
    private Integer lowestAge;
    private Integer secondHighestSalary;

    public HighestLowestEmployeeValues() {
    }

    public HighestLowestEmployeeValues(Integer highestSalary, Integer lowestAge, Integer secondHighestSalary) {
        this.highestSalary = highestSalary;
        this.lowestAge = lowestAge;
        this.secondHighestSalary = secondHighestSalary;
    }

    public Integer getHighestSalary() {
        return highestSalary;
    }

    public void setHighestSalary(Integer highestSalary) {
        this.highestSalary = highestSalary;
    }

    public Integer getLowestAge() {
        return lowestAge;
    }

    public void setLowestAge(Integer lowestAge) {
        this.lowestAge = lowestAge;
    }

    public Integer getSecondHighestSalary() {
        return secondHighestSalary;
    }

    public void setSecondHighestSalary(Integer secondHighestSalary) {
        this.secondHighestSalary = secondHighestSalary;
    }

    @Override
    public String toString() {
        return "HighestLowestEmployeeValues{" +
                "highestSalary=" + highestSalary +
                ", lowestAge=" + lowestAge +
                ", secondHighestSalary=" + secondHighestSalary +
                '}';
    }
}
