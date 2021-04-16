import java.sql.*;
import java.util.Random;
import java.util.Scanner;

/*
DB Installation: Derby side
1. Install Derby. Do not install under C/program files/java; install it anywehre else you have permission;
2. once done, change your environment with windows-env and change working environment accordingly. locate both %DERBY_HOME% and %JAVA_HOME% accordingly;
3. Start Derby service by clicking startNetworkServer.bat;
4. get the opening port properly;
5. create a new db in derby console;
6. in derby db, execute the employees.sql, and then go to derby folder /bin, you should be able to see the folder in file explorer.
7. IMPORTANT: everytime when you have to use intellij, make sure the startNetworkServer.bat is open. otherwise Intellij won't have access on java.

DB link: IntelliJ
1. get the plugin named "DB navigator"
2. make sure derby server is open and running. when configuring DB connection, do not use absolute path but localhost: XXXX (4 digit of your port)/dbname;
3. make sure choose derbyclient as your driver
4. in Intellij, go file-project structure-library, add both derby.jar and derbyclient.jar into the libraby of the project.

 */
public class EmployeeDB {

    public static void addEmployee(int userEmploymentTypeInput, Statement statement, Connection connection, String DATABASE_URL) throws SQLException {
        String userInputFirstName = null;
        String userInputLastName = null;
        String userInputSSN = null;
        String userInputBD = null;
        String[] department = {"R&D", "SALES", "HR"};
        String userInputDepartmentName = null;
        String userInputEmploymentType = null;
        double userInputBaseSalary = 0.00;
        double userInputBonus = 0.00;
        double userInputCommissionRate = 0.00;
        double userInputGrossSales = 0.00;
        double userInputHours = 0.00;
        double userInputWage = 0.00;
        double userInputWeeklySalary = 0.00;
        PreparedStatement insertNewPerson;

        if (userEmploymentTypeInput == 1) {
            System.out.println("We will ask you some basic questions of the Base+Commission Employee");

            // get user input of the Employee class. general questions.
            Scanner BE = new Scanner(System.in);
            System.out.println("please enter the first name of the Employee");
            userInputFirstName = BE.nextLine();
            System.out.println("please enter the last name of the Employee");
            userInputLastName = BE.nextLine();
            System.out.println("please enter the SSN# of the Employee");
            userInputSSN = BE.nextLine();

            // add function for SSN validation.

            System.out.println("please enter the birthday of the Employee.(YYYY-MM-DD");
            userInputBD = BE.nextLine();

            // employment type will be assigned
            userInputEmploymentType = "BasePlusCommissionEmployee";

            // department will be assigned randomly
            Random random = new Random();
            int index = random.nextInt(department.length);
            userInputDepartmentName = department[index];

            // get user input for BC specific questions.userInputGrossSales,userInputCommissionRate,userInputBaseSalary, userInputBonus
            System.out.println("please enter the gross sales of the Employee. KEEP 2 digits");
            userInputGrossSales = BE.nextDouble();
            System.out.println("please enter the commission rate of the Employee. KEEP 2 digits");
            userInputCommissionRate = BE.nextDouble();
            System.out.println("please enter the base salary of the Employee. KEEP 2 digits");
            userInputBaseSalary = BE.nextDouble();
            System.out.println("please enter the bonus of the Employee. KEEP 2 digits");
            userInputBonus = BE.nextDouble();

            // create new object of BE.
            BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee(userInputFirstName, userInputLastName, userInputSSN, userInputBD, userInputEmploymentType, userInputDepartmentName, userInputGrossSales, userInputCommissionRate, userInputBaseSalary, userInputBonus);
            // test print the whole thing
            //System.out.printf("%s%n%s: $%,.2f%n%n", basePlusCommissionEmployee, "earned", basePlusCommissionEmployee.earnings());
            //System.out.println(userInputDepartmentName);
            //System.out.println(userInputEmploymentType);

            //Statement statement = connection.createStatement();
            //statement.executeUpdate("INSERT INTO EMPLOYEES"+"VALUES ('123-12-1234', '1920-01-01', 'R&D', 'HOURLYEMPLOYEES', 'TEST', 'TESTOR' );

            // send the basic employee info to sql table employment: first name, last name, SSN, birthday, employmentType, Department
            connection = DriverManager.getConnection(DATABASE_URL);

            insertNewPerson = connection.prepareStatement(
                    "INSERT INTO EMPLOYEES" +
                            "(SOCIALSECURITYNUMBER, BIRTHDAY, DEPARTMENTNAME, EMPLOYEETYPE, FIRSTNAME, LASTNAME)" +
                            "values (?, ?, ?, ?, ?, ?)"
            );
            insertNewPerson.setString(1, userInputSSN);
            insertNewPerson.setString(2, userInputBD);
            insertNewPerson.setString(3, userInputDepartmentName);
            insertNewPerson.setString(4, userInputEmploymentType);
            insertNewPerson.setString(5, userInputFirstName);
            insertNewPerson.setString(6, userInputLastName);
            insertNewPerson.executeUpdate();

            insertNewPerson.close();
            // send the rest BE info to sql table BasePlusCommissionEmployees: gross sales, commission rate, base salary, bonus
            insertNewPerson = connection.prepareStatement(
                    "INSERT INTO BASEPLUSCOMMISSIONEMPLOYEES" +
                            "(SOCIALSECURITYNUMBER, BASESALARY, BONUS, COMMISSIONRATE, GROSSSALES)" +
                            "VALUES (?, ?, ?, ?, ?)"
            );
            insertNewPerson.setString(1, userInputSSN);
            insertNewPerson.setDouble(2, userInputBaseSalary);
            insertNewPerson.setDouble(3, userInputBonus);
            insertNewPerson.setDouble(4, userInputCommissionRate);
            insertNewPerson.setInt(5, (int) userInputGrossSales);
            insertNewPerson.executeUpdate();

            insertNewPerson.close();
        } else if (userEmploymentTypeInput == 2) {
            System.out.println("We will ask you some basic questions of the Commission Employee");
            // get user input of the Employee class. general questions.
            // get user input for BC specific questions.
            // create new object of CE.
            // send the data into sql.
        } else if (userEmploymentTypeInput == 3) {
            System.out.println("We will ask you some basic questions of the Hourly Employee");
            // get user input of the Employee class. general questions.
            // get user input for BC specific questions.
            // create new object of HE.
            // send the data into sql.
        } else if (userEmploymentTypeInput == 4) {
            System.out.println("We will ask you some basic questions of the Salaried Employee");
            // get user input of the Employee class. general questions.
            // get user input for BC specific questions.
            // create new object of SE.
            // send the data into sql.
        }
    }

    public static void removeEmployee(Statement statement, Connection connection, String DATABASE_URL) throws SQLException {
        String userDeleteSSN;
        PreparedStatement deleteEmployee;

        System.out.println("********WARNING: THIS WILL REMOVE THE EMPLOYEE FROM DB*********");
        // get user input of the SSN#
        Scanner userInputDelete = new Scanner(System.in);
        System.out.println("please enter the SSN");
        userDeleteSSN = userInputDelete.nextLine();
        // send the basic employee info to sql table employment: first name, last name, SSN, birthday, employmentType, Department
        connection = DriverManager.getConnection(DATABASE_URL);
        deleteEmployee = connection.prepareStatement(
                "DELETE FROM BASEPLUSCOMMISSIONEMPLOYEES WHERE SOCIALSECURITYNUMBER = ?"
        );
        deleteEmployee.setString(1, userDeleteSSN);
        deleteEmployee.executeUpdate();
        deleteEmployee.close();

        deleteEmployee = connection.prepareStatement(
                "DELETE FROM BASEPLUSCOMMISSIONEMPLOYEES WHERE SOCIALSECURITYNUMBER = ?"
        );
        deleteEmployee.setString(1, userDeleteSSN);
        deleteEmployee.executeUpdate();
        deleteEmployee.close();

        deleteEmployee = connection.prepareStatement(
                "DELETE FROM BASEPLUSCOMMISSIONEMPLOYEES WHERE SOCIALSECURITYNUMBER = ?"
        );
        deleteEmployee.setString(1, userDeleteSSN);
        deleteEmployee.executeUpdate();
        deleteEmployee.close();

        deleteEmployee = connection.prepareStatement(
                "DELETE FROM EMPLOYEES WHERE SOCIALSECURITYNUMBER = ?"
        );
        deleteEmployee.setString(1, userDeleteSSN);
        deleteEmployee.executeUpdate();
        deleteEmployee.close();


        System.out.print("The staff with SSN of " + userDeleteSSN + " has been deleted.\n");
    }

    public static void ssnValidation() {
    }

    public static void main(String args[]) throws SQLException, ClassNotFoundException {

        // employees = new ArrayList();

        final String DATABASE_URL = "jdbc:derby://localhost:1527/test;create=true";

        // query, need to remove.
        String SELECT_QUERY = "select \n" +
                "EMPLOYEES.firstname, EMPLOYEES.lastname, EMPLOYEES.birthday, EMPLOYEES.DEPARTMENTNAME, EMPLOYEES.EMPLOYEETYPE, EMPLOYEES.SOCIALSECURITYNUMBER, BASEPLUSCOMMISSIONEMPLOYEES.BASESALARY, BASEPLUSCOMMISSIONEMPLOYEES.BONUS, BASEPLUSCOMMISSIONEMPLOYEES.COMMISSIONRATE, BASEPLUSCOMMISSIONEMPLOYEES.GROSSSALES, COMMISSIONEMPLOYEES.BONUS,  COMMISSIONEMPLOYEES.COMMISSIONRATE, COMMISSIONEMPLOYEES.GROSSSALES, HOURLYEMPLOYEES.BONUS, HOURLYEMPLOYEES.HOURS, HOURLYEMPLOYEES.WAGE, SALARIEDEMPLOYEES.BONUS, SALARIEDEMPLOYEES.WEEKLYSALARY from EMPLOYEES\n" +
                "LEFT JOIN BASEPLUSCOMMISSIONEMPLOYEES on EMPLOYEES.socialSecurityNumber = BASEPLUSCOMMISSIONEMPLOYEES.SOCIALSECURITYNUMBER\n" +
                "LEFT JOIN COMMISSIONEMPLOYEES on EMPLOYEES.socialsecurityNumber = COMMISSIONEMPLOYEES.SOCIALSECURITYNUMBER\n" +
                "LEFT JOIN HOURLYEMPLOYEES on EMPLOYEES.socialsecurityNumber = HOURLYEMPLOYEES.SOCIALSECURITYNUMBER\n" +
                "LEFT JOIN SALARIEDEMPLOYEES on EMPLOYEES.socialsecurityNumber = SALARIEDEMPLOYEES.SOCIALSECURITYNUMBER\n";
        //"ORDER BY EMPLOYEES.SOCIALSECURITYNUMBER";

        // use try-with-resource to connect and query the database
        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL))
        {
            int userInput = 0;
            int userEmploymentTypeInput;
            while (userInput != 4) {
                PreparedStatement statement = connection.prepareStatement(SELECT_QUERY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = statement.executeQuery();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();
                resultSet.beforeFirst();
                System.out.printf("***WELCOME TO EMPLOYEE PAYROLL MANAGEMENT SYSTEM***\n");
                // print out main program body with loop and options
                Scanner select = new Scanner(System.in);
                System.out.println("Please make a selection...");
                System.out.println("1. Display all employees' information");
                System.out.println("2. Enter new employee's payroll information");
                System.out.println("3. Remove employee's payroll information");
                System.out.println("4. Exit Program");
                System.out.println("");
                userInput = select.nextInt();
                //resultSet.updateRow();
                switch (userInput) {
                    // first option, display all updated db;
                    case 1:

                        // display the names of the columns in the ResultSet
                        for (int i = 1; i <= numberOfColumns; i++) {
                            System.out.printf("%-24s\t", metaData.getColumnName(i));
                        }
                        System.out.println();

                        // display query results
                        while (resultSet.next()) {
                            for (int i = 1; i <= numberOfColumns; i++) {
                                System.out.printf("%-24s\t", resultSet.getObject(i));
                            }
                            System.out.println();
                        }

                        break;
                    // main body of using different "addEmployee"
                    case 2:
                        System.out.println("Please enter type of the employment. Please enter \n1 for BE: based+commission \n2 for CE: commission \n3 for HE: hourly \n4 for SE: salaried \n5 return to previous menu");
                        userEmploymentTypeInput = select.nextInt();

                        if (userEmploymentTypeInput == 5) {
                            break;
                        } else if (userEmploymentTypeInput < 5) {
                            addEmployee(userEmploymentTypeInput, statement, connection, DATABASE_URL);
                        }
                        break;
                    case 3:
                        System.out.println("Please enter the social security of the entry you want to remove");
                        removeEmployee(statement, connection, DATABASE_URL);
                        break;
                    case 4:
                        System.out.println("Thank you for using the program. Bye!");
                        break;
                    // the following statement takes care the validation
                    default:
                        System.out.println("Option not available!");
                        break;
                }
            }
        }
        catch(SQLException e){
                e.printStackTrace();
            }
        }
    }


/*
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

 */