public class BasePlusCommissionEmployee extends CommissionEmployee {
    private double baseSalary;

    // constructor
    public BasePlusCommissionEmployee (String firstName, String lastName, String socialSecurityNumber, String birthDay, String employeeType, String departmentName, double grossSales, double commissionRate, double baseSalary, double bonus) {
        super (firstName, lastName, socialSecurityNumber, birthDay, employeeType,departmentName,  grossSales, commissionRate, bonus);
        if (baseSalary <0.0) {
            throw new IllegalArgumentException("Base salary must be >= 0,0");
        }
        this.baseSalary = baseSalary;
    }

    public void setBaseSalary(double baseSalary){
        if (baseSalary < 0.0) {
        throw new IllegalArgumentException("Base salary must be >= 0,0");
        }
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
    @Override
    public double earnings(){
        return getBaseSalary() + super.earnings();
    }

    @Override
    public String toString(){
        return String.format("%s %s; %s: $%,.2f", "base-salaried", super.toString(), "base salary", getBaseSalary());
    }
}

