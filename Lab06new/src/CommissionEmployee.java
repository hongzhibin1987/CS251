public class CommissionEmployee extends Employee {
    private double grossSales;
    private double commissionRate;
    private double bonus;

    // constructor
    public  CommissionEmployee(String firstName, String lastName, String socialSecurityNumber, String birthDay, String employeeType, String departmentName, double grossSales, double commissionRate, double bonus) {
        super(firstName, lastName, socialSecurityNumber,birthDay, employeeType, departmentName);

        if (commissionRate <= 0.0 || commissionRate >= 1.0) {
            throw new IllegalArgumentException("Commission rate must be >0.0 and <1.0");
        }

        if (grossSales < 0.0) {
            throw new IllegalArgumentException("Gross sales must be >= 0.0");
        }

        if (bonus < 0.0) {
            throw new IllegalArgumentException("Bonus must be >= 0.0");
        }

        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
        this.bonus = bonus;
    }

    // set gross sales amount
        public void setGrossSales (double grossSales){
            if (grossSales < 0.0){
                throw new IllegalArgumentException("Gross sales must be >= 0.0");
            }
            this.grossSales = grossSales;
         }

        // return gross sales amount
        public double getGrossSales(){
            return grossSales;
        }

        public void setCommissionRate (double commissionRate) {
            if (commissionRate <= 0.0 || commissionRate >= 1.0) {
                throw new IllegalArgumentException("Commission rate must be >0.0 and <1.0");
            }
            this.commissionRate = commissionRate;
        }

        public double getCommissionRate() {
            return commissionRate;
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
        public double earnings(){
            return (getCommissionRate() * getGrossSales()) + getBonus();
        }

        @Override
    public String toString() {
        return String.format("%s: %s%n%s: $%,.2f; %s: %.2f; %s: %,.2f", "commission employee", super.toString(), "Gross sales", getGrossSales(), "Commission rate", getCommissionRate(), "Bonus", getBonus());
        }
    }

