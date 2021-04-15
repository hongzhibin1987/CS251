public class HourlyEmployee extends Employee {
    private double wage;
    private double hours;
    private double bonus;

    // constructor
    public HourlyEmployee(String firstName, String lastName, String socialSecurityNumber, String birthDay, String employeeType, String departmentName, double wage, double hours, double bonus) {
        super(firstName, lastName, socialSecurityNumber, birthDay, employeeType, departmentName);
        if (wage < 0.0) {
            throw new IllegalArgumentException("Hourly wage must be >=0.0");
        }

        if ((hours <0.0) || (hours > 168.0)) {
            throw new IllegalArgumentException("Hours worked must be more than 0 and less than 168");
        }
        if (bonus < 0.0) {
            throw new IllegalArgumentException("Bonus must be >= 0.0");
        }

        this.wage = wage;
        this.hours = hours;
        this.bonus = bonus;
    }

    // set wage
    public void setWage(double wage) {
        if (wage <0.0) {
            throw new IllegalArgumentException ("Hourly wage must be >= 0.0");
        }
        this.wage = wage;
    }

    // return wage
    public double getWage()
    {
        return wage;
    }

    // set hours worked
    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getHours() {
        return hours;
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
    public double earnings()
    {
        if (getHours() <= 40) {
            return (getWage() * getHours())+getBonus();
        }
        else {
            return (40 * getWage() + (getHours()-40) * getWage()*1.5)+getBonus();
        }
    }

    @Override
    public String toString(){
        return String.format("hourly employee: %s%n%s: $%,.2f; %s: %,.2f; %s: %,.2f", super.toString(), "hourly wage", getWage(), "hours worked", getHours(), "bonus is", getBonus());
    }
}
