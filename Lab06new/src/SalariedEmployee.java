public class SalariedEmployee extends Employee{
    private double weeklySalary;
    private double bonus;

    // constructor
    public SalariedEmployee(String firstName, String lastName, String socialSecurityNumber, String birthDay, String employeeType, String departmentName, double weeklySalary, double bonus) {
        super(firstName, lastName, socialSecurityNumber, birthDay, employeeType, departmentName);

        if (weeklySalary< 0.0)
            throw new IllegalArgumentException("Weekly salary must be >= 0.0");

        if (bonus < 0.0) {
            throw new IllegalArgumentException("Bonus must be >= 0.0");
        }


        this.weeklySalary = weeklySalary;
        this.bonus = bonus;
    }

    // set salary
    public void setWeeklySalary(double weeklySalary){
        if (weeklySalary < 0.0)
            throw new IllegalArgumentException( "Weekly salary must be >=0.0");

        this.weeklySalary = weeklySalary;}

    public double getWeeklySalary () {
        return weeklySalary;
    }

    @Override
    public double earnings()
    {
        return (getWeeklySalary()+getBonus());
    }

    public void setBonus (double bonus) {
        if (bonus < 0.0) {
            throw new IllegalArgumentException("bonus should not be minus");
        }
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    @Override
    public String toString()
    {
        return String.format("salaried employee: %s%n%s: $%,.2f; %s$%,.2f", super.toString(), "Weekly salary", getWeeklySalary(), "bonus is", getBonus());
    }
}
