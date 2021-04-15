public class PayrollSystemTest {
    public static void main(String[] args){
        // create subclass objects
        SalariedEmployee salariedEmployee = new SalariedEmployee("Jong-un", "Kim", "555-55-5555", "01-01-1900", "SE", "R&D", 1000.00, 100.00);
        HourlyEmployee hourlyEmployee = new HourlyEmployee("Fidel", "Castro", "666-66-6666", "01-02-1900", "HE", "SALES", 50.00, 30,100);
        CommissionEmployee commissionEmployee = new CommissionEmployee("Joseph", "Stalin", "777-77-7777", "01-03-1099", "CE", "SALES", 25000,0.35, 500);
        BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Chi-min", "Ho", "888-88-8888", "01-04-1900", "BE", "SALES",30000, 0.45, 750, 375);

        System.out.println("Employees processed individually:");
        System.out.printf("%n%s%n%s: $%,.2f%n%n", salariedEmployee, "earned", salariedEmployee.earnings());
        System.out.printf("%s%n%s: $%,.2f%n%n", hourlyEmployee, "earned", hourlyEmployee.earnings());
        System.out.printf("%s%n%s: $%,.2f%n%n", commissionEmployee, "earned", commissionEmployee.earnings());
        System.out.printf("%s%n%s: $%,.2f%n%n", basePlusCommissionEmployee, "earned", basePlusCommissionEmployee.earnings());

        //create array
        Employee[] employees = new Employee[4];

        // initialize array with Employees
        employees[0] = salariedEmployee;
        employees[1] = hourlyEmployee;
        employees[2] = commissionEmployee;
        employees[3] = basePlusCommissionEmployee;

        System.out.printf("Employees processed poolymorphically:%n%n");

        // generically process each element in array employees
        for (Employee currentEmployee : employees){
            System.out.println(currentEmployee);

            if (currentEmployee instanceof BasePlusCommissionEmployee)
            {
                BasePlusCommissionEmployee employee = (BasePlusCommissionEmployee) currentEmployee;
                employee.setBaseSalary(1.10 * employee.getBaseSalary());
                System.out.printf("new base salary with 10%% increase is: $%,.2f%n", employee.getBaseSalary());
            }

            System.out.printf("earned $%,.2f%n%n", currentEmployee.earnings());
        }

        for (int j = 0; j < employees.length; j++) {
            System.out.printf("Employee %d is a %s%n", j, employees[j].getClass().getName());}
    }
}
