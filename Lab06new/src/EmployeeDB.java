/*
Zhibin "Bing" Hong,
CS2251 JAVA Lab6-1
final project
 */


import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
DB Installation: Derby side
1. Install Derby. Do not install under C/program files/java; install it anywhere else you have permission;
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

Comments:
1. make sure when using IDE, be careful with auto-generated "{}". took me 6 hours to fix an easy loop issue.
2. derby installation is out-dated IMO. should use mysql+sql workbench to configure environment.
3. ideally, should get the sql result and send it to ArrayList<Employee> result. but i'm too tired.
4. ideally, should have a dbManager method to put all connect(), close(), and URL, Query into one method. but again, i'm too tired.
5. do not add row to multiple tables or try to delete row in multiple tables IN ONE SQL SCRIPT STRING. do it seperately. executeUpdate() and then close(), then loop it.
6. instead of using statement, use prepareStatement. easier to work with SQL.
7. if there is next verion should change employee+BE, employee+CE, employee+HE, employee+SE to employee+ (BE, CE, HE, SE) to reduce the amount of the employee intake info.

 */
public class EmployeeDB {

    public static void addEmployee(int userEmploymentTypeInput, String DATABASE_URL) throws SQLException {
        // intitialize variables
        String userInputFirstName = null;
        String userInputLastName = null;
        String userInputSSN = null;
        String userInputSSNValidation = null;
        String userInputBD = null;
        String userInputBDValidation = null;
        String[] department = {"R&D", "SALES", "HR"};
        String userInputDepartmentName = null;
        String userInputEmploymentType = null;
        double userInputBaseSalary = 0.00;
        double userInputBonus = 0.00;
        double userInputCommissionRate = 0.00;
        double userInputGrossSales = 0.00;
        Integer userInputHours = 0;
        double userInputWage = 0.00;
        double userInputWeeklySalary = 0.00;
        PreparedStatement insertNewPerson;

        // four different types of employee.
        if (userEmploymentTypeInput == 1) {
            System.out.println("We will ask you some basic questions of the Base+Commission Employee");

            // get user input of the Employee class. general questions.
            Scanner BE = new Scanner(System.in);
            System.out.println("please enter the first name of the Employee:");
            userInputFirstName = BE.nextLine();
            System.out.println("please enter the last name of the Employee:");
            userInputLastName = BE.nextLine();
            System.out.println("please enter the birthday of the Employee. (YYYY-MM-DD):");
            userInputBD = BE.nextLine();
            System.out.println("please enter the birthday of the Employee again for validation purpose:");
            userInputBDValidation = BE.nextLine();

            // DB validation (typo)
            while (!userInputBD.equals(userInputBDValidation)) {
                System.out.println("Your input does not match. \nPlease enter the birthday of the Employee.(YYYY-MM-DD):");
                userInputBD = BE.nextLine();
                System.out.println("Please confirm the birthday of the Employee.(YYYY-MM-DD):");
                userInputBDValidation = BE.nextLine();
            }

            System.out.println("Validation successful.");

            System.out.println("please enter the SSN# of the Employee (XXX-XX-XXXX):");
            userInputSSN = BE.nextLine();
            System.out.println("Please confirm the SSN# for validation purpose (XXX-XX-XXXX):");
            userInputSSNValidation = BE.nextLine();

            // SSN validation (typo)
            while (!userInputSSN.equals(userInputSSNValidation)) {
                System.out.println("Your input does not match. \nPlease reenter the SSN# of the Employee.(XXX-XX-XXXX):");
                userInputSSN = BE.nextLine();
                System.out.println("Please confirm the SSN# of the Employee.(XXX-XX-XXXX):") ;
                userInputSSNValidation = BE.nextLine();
            }
            System.out.println("Validation successful.");

            // SSN validation
            ssnValidation(DATABASE_URL, userInputSSN);
            userInputSSN = ssnValidation(DATABASE_URL, userInputSSN);

            // employment type will be assigned
            userInputEmploymentType = "BasePlusCommissionEmployee";

            // department will be assigned randomly
            Random random = new Random();
            int index = random.nextInt(department.length);
            userInputDepartmentName = department[index];

            // get user input for BC specific questions.userInputGrossSales,userInputCommissionRate,userInputBaseSalary, userInputBonus
            System.out.println("please enter the gross sales of the Employee. KEEP 2 digits:");
            userInputGrossSales = BE.nextDouble();
            System.out.println("please enter the commission rate of the Employee. KEEP 2 digits:");
            userInputCommissionRate = BE.nextDouble();
            System.out.println("please enter the base salary of the Employee. KEEP 2 digits:");
            userInputBaseSalary = BE.nextDouble();
            System.out.println("please enter the bonus of the Employee. KEEP 2 digits:");
            userInputBonus = BE.nextDouble();

            // create new object of BE.
            BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee(userInputFirstName, userInputLastName, userInputSSN, userInputBD, userInputEmploymentType, userInputDepartmentName, userInputGrossSales, userInputCommissionRate, userInputBaseSalary, userInputBonus);

            // send the basic employee info to sql table employment: first name, last name, SSN, birthday, employmentType, Department
            Connection connection = DriverManager.getConnection(DATABASE_URL);

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
            System.out.println("New employee added!!");
        }
        else if (userEmploymentTypeInput == 2) {
            System.out.println("We will ask you some basic questions of the Commission Employee");

            // get user input of the Employee class. general questions.
            Scanner CE = new Scanner(System.in);
            System.out.println("please enter the first name of the Employee:");
            userInputFirstName = CE.nextLine();
            System.out.println("please enter the last name of the Employee:");
            userInputLastName = CE.nextLine();
            System.out.println("please enter the birthday of the Employee. (YYYY-MM-DD):");
            userInputBD = CE.nextLine();
            System.out.println("please enter the birthday of the Employee again for validation purpose:");
            userInputBDValidation = CE.nextLine();

            while (!userInputBD.equals(userInputBDValidation)) {
                System.out.println("Your input does not match. \nPlease enter the birthday of the Employee.(YYYY-MM-DD):");
                userInputBD = CE.nextLine();
                System.out.println("Please confirm the birthday of the Employee.(YYYY-MM-DD):");
                userInputBDValidation = CE.nextLine();
            }

            System.out.println("Validation successful.");

            System.out.println("please enter the SSN# of the Employee (XXX-XX-XXXX):");
            userInputSSN = CE.nextLine();

            System.out.println("Please confirm the SSN# for validation purpose (XXX-XX-XXXX):");
            userInputSSNValidation = CE.nextLine();

            while (!userInputSSN.equals(userInputSSNValidation)) {
                System.out.println("Your input does not match. \nPlease reenter the SSN# of the Employee.(XXX-XX-XXXX):");
                userInputSSN = CE.nextLine();
                System.out.println("Please confirm the SSN# of the Employee.(XXX-XX-XXXX):") ;
                userInputSSNValidation = CE.nextLine();
            }
            System.out.println("Validation successful.");

            // SSN validation
            ssnValidation(DATABASE_URL, userInputSSN);
            userInputSSN = ssnValidation(DATABASE_URL, userInputSSN);

            // employment type will be assigned
            userInputEmploymentType = "CommissionEmployee";

            // department will be assigned randomly
            Random random = new Random();
            int index = random.nextInt(department.length);
            userInputDepartmentName = department[index];

            // get user input for CE specific questions.userInputGrossSales,userInputCommissionRate, userInputBonus
            System.out.println("please enter the gross sales of the Employee. KEEP 2 digits:");
            userInputGrossSales = CE.nextDouble();
            System.out.println("please enter the commission rate of the Employee. KEEP 2 digits:");
            userInputCommissionRate = CE.nextDouble();
            System.out.println("please enter the bonus of the Employee. KEEP 2 digits:");
            userInputBonus = CE.nextDouble();

            // create new object of CE.
            CommissionEmployee CommissionEmployee = new CommissionEmployee(userInputFirstName, userInputLastName, userInputSSN, userInputBD, userInputEmploymentType, userInputDepartmentName, userInputGrossSales, userInputCommissionRate, userInputBonus);

            // send the basic employee info to sql table employment: first name, last name, SSN, birthday, employmentType, Department
            Connection connection = DriverManager.getConnection(DATABASE_URL);

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
            // send the rest CE info to sql table BasePlusCommissionEmployees: gross sales, commission rate,bonus
            insertNewPerson = connection.prepareStatement(
                    "INSERT INTO COMMISSIONEMPLOYEES" +
                            "(SOCIALSECURITYNUMBER, BONUS, COMMISSIONRATE, GROSSSALES)" +
                            "VALUES (?, ?, ?, ?)"
            );
            insertNewPerson.setString(1, userInputSSN);
            insertNewPerson.setDouble(2, userInputBonus);
            insertNewPerson.setDouble(3, userInputCommissionRate);
            insertNewPerson.setInt(4, (int) userInputGrossSales);
            insertNewPerson.executeUpdate();

            insertNewPerson.close();

            System.out.println("New employee added!!");
        }
        else if (userEmploymentTypeInput == 3) {
            System.out.println("We will ask you some basic questions of the Hourly Employee");

            // get user input of the Employee class. general questions.
            Scanner HE = new Scanner(System.in);
            System.out.println("please enter the first name of the Employee:");
            userInputFirstName = HE.nextLine();
            System.out.println("please enter the last name of the Employee:");
            userInputLastName = HE.nextLine();
            System.out.println("please enter the birthday of the Employee. (YYYY-MM-DD):");
            userInputBD = HE.nextLine();
            System.out.println("please enter the birthday of the Employee again for validation purpose:");
            userInputBDValidation = HE.nextLine();

            while (!userInputBD.equals(userInputBDValidation)) {
                System.out.println("Your input does not match. \nPlease enter the birthday of the Employee.(YYYY-MM-DD):");
                userInputBD = HE.nextLine();
                System.out.println("Please confirm the birthday of the Employee.(YYYY-MM-DD):");
                userInputBDValidation = HE.nextLine();
            }

            System.out.println("Validation successful.");

            System.out.println("please enter the SSN# of the Employee (XXX-XX-XXXX):");
            userInputSSN = HE.nextLine();

            System.out.println("Please confirm the SSN# for validation purpose (XXX-XX-XXXX):");
            userInputSSNValidation = HE.nextLine();

            while (!userInputSSN.equals(userInputSSNValidation)) {
                System.out.println("Your input does not match. \nPlease reenter the SSN# of the Employee.(XXX-XX-XXXX):");
                userInputSSN = HE.nextLine();
                System.out.println("Please confirm the SSN# of the Employee.(XXX-XX-XXXX):") ;
                userInputSSNValidation = HE.nextLine();
            }
            System.out.println("Validation successful.");

            // SSN validation
            ssnValidation(DATABASE_URL, userInputSSN);
            userInputSSN = ssnValidation(DATABASE_URL, userInputSSN);

            // employment type will be assigned
            userInputEmploymentType = "hourlyEmployee";

            // department will be assigned randomly
            Random random = new Random();
            int index = random.nextInt(department.length);
            userInputDepartmentName = department[index];

            // get user input for CE specific questions.wage, hours, userInputBonus
            System.out.println("please enter the hours of the Employee. KEEP 2 digits:");
            userInputHours = HE.nextInt();
            System.out.println("please enter the wage rate of the Employee. KEEP 2 digits:");
            userInputWage = HE.nextDouble();
            System.out.println("please enter the bonus of the Employee. KEEP 2 digits:");
            userInputBonus = HE.nextDouble();

            // create new object of CE.
            HourlyEmployee hourlyEmployee = new HourlyEmployee(userInputFirstName, userInputLastName, userInputSSN, userInputBD, userInputEmploymentType, userInputDepartmentName, userInputWage, userInputHours,userInputBonus);

            // send the basic employee info to sql table employment: first name, last name, SSN, birthday, employmentType, Department
            Connection connection = DriverManager.getConnection(DATABASE_URL);

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
            // send the rest CE info to sql table hourly: wage, hour, bonus
            insertNewPerson = connection.prepareStatement(
                    "INSERT INTO HOURLYEMPLOYEES" +
                            "(SOCIALSECURITYNUMBER, BONUS, WAGE, HOURS)" +
                            "VALUES (?, ?, ?, ?)"
            );
            insertNewPerson.setString(1, userInputSSN);
            insertNewPerson.setDouble(2, userInputBonus);
            insertNewPerson.setDouble(3, userInputWage);
            insertNewPerson.setInt(4, userInputHours);
            insertNewPerson.executeUpdate();

            insertNewPerson.close();

            System.out.println("New employee added!!\n");
        }
        else if (userEmploymentTypeInput == 4) {
            System.out.println("We will ask you some basic questions of the Salaried Employee");
            // get user input of the Employee class. general questions.
            Scanner SE = new Scanner(System.in);
            System.out.println("please enter the first name of the Employee:");
            userInputFirstName = SE.nextLine();
            System.out.println("please enter the last name of the Employee:");
            userInputLastName = SE.nextLine();
            System.out.println("please enter the birthday of the Employee. (YYYY-MM-DD):");
            userInputBD = SE.nextLine();
            System.out.println("please enter the birthday of the Employee again for validation purpose:");
            userInputBDValidation = SE.nextLine();

            while (!userInputBD.equals(userInputBDValidation)) {
                System.out.println("Your input does not match. \nPlease enter the birthday of the Employee.(YYYY-MM-DD):");
                userInputBD = SE.nextLine();
                System.out.println("Please confirm the birthday of the Employee.(YYYY-MM-DD):");
                userInputBDValidation = SE.nextLine();
            }

            System.out.println("Validation successful.");

            System.out.println("please enter the SSN# of the Employee (XXX-XX-XXXX):");
            userInputSSN = SE.nextLine();

            System.out.println("Please confirm the SSN# for validation purpose (XXX-XX-XXXX):");
            userInputSSNValidation = SE.nextLine();

            while (!userInputSSN.equals(userInputSSNValidation)) {
                System.out.println("Your input does not match. \nPlease reenter the SSN# of the Employee.(XXX-XX-XXXX):");
                userInputSSN = SE.nextLine();
                System.out.println("Please confirm the SSN# of the Employee.(XXX-XX-XXXX):") ;
                userInputSSNValidation = SE.nextLine();
            }
            System.out.println("Validation successful.");

            // SSN validation
            ssnValidation(DATABASE_URL, userInputSSN);
            userInputSSN = ssnValidation(DATABASE_URL, userInputSSN);

            // employment type will be assigned
            userInputEmploymentType = "salariedEmployee";

            // department will be assigned randomly
            Random random = new Random();
            int index = random.nextInt(department.length);
            userInputDepartmentName = department[index];

            // get user input for CE specific questions.weekly salary userInputBonus
            System.out.println("please enter the weekly salary  of the Employee. KEEP 2 digits:");
            userInputWeeklySalary = SE.nextDouble();
            System.out.println("please enter the bonus of the Employee. KEEP 2 digits:");
            userInputBonus = SE.nextDouble();

            // create new object of CE.
            SalariedEmployee salariedEmployee = new SalariedEmployee(userInputFirstName, userInputLastName, userInputSSN, userInputBD, userInputEmploymentType, userInputDepartmentName, userInputWeeklySalary,userInputBonus);

            // send the basic employee info to sql table employment: first name, last name, SSN, birthday, employmentType, Department
            Connection connection = DriverManager.getConnection(DATABASE_URL);

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
            // send the rest CE info to sql table salariedemployee: wage, bonus, hours
            insertNewPerson = connection.prepareStatement(
                    "INSERT INTO SALARIEDEMPLOYEES" +
                            "(SOCIALSECURITYNUMBER, BONUS, WEEKLYSALARY)" +
                            "VALUES (?, ?, ?)"
            );
            insertNewPerson.setString(1, userInputSSN);
            insertNewPerson.setDouble(2, userInputBonus);
            insertNewPerson.setDouble(3, userInputWeeklySalary);
            insertNewPerson.executeUpdate();

            insertNewPerson.close();

            System.out.println("New employee added!!\n");
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
        connection = DriverManager.getConnection(DATABASE_URL);
        // use preparestatement to work with sql. for delete, delete foreign key table then primary key table.
        deleteEmployee = connection.prepareStatement(
                "DELETE FROM BASEPLUSCOMMISSIONEMPLOYEES WHERE SOCIALSECURITYNUMBER = ?"
        );
        deleteEmployee.setString(1, userDeleteSSN);
        deleteEmployee.executeUpdate();
        deleteEmployee.close();

        deleteEmployee = connection.prepareStatement(
                "DELETE FROM COMMISSIONEMPLOYEES WHERE SOCIALSECURITYNUMBER = ?"
        );
        deleteEmployee.setString(1, userDeleteSSN);
        deleteEmployee.executeUpdate();
        deleteEmployee.close();

        deleteEmployee = connection.prepareStatement(
                "DELETE FROM HOURLYEMPLOYEES WHERE SOCIALSECURITYNUMBER = ?"
        );
        deleteEmployee.setString(1, userDeleteSSN);
        deleteEmployee.executeUpdate();
        deleteEmployee.close();

        deleteEmployee = connection.prepareStatement(
                "DELETE FROM SALARIEDEMPLOYEES WHERE SOCIALSECURITYNUMBER = ?"
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

    // to validate whether the SSN is unique in the db.
    public static String ssnValidation(String DATABASE_URL, String userinputSSN) throws SQLException {
        String SELECT_SSN = "SELECT SOCIALSECURITYNUMBER \n" +
                "From EMPLOYEES";

        // initialize resultset
        Connection connection = DriverManager.getConnection(DATABASE_URL);
        PreparedStatement statement = connection.prepareStatement(SELECT_SSN, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = statement.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        // create an arraylist to store all SSN from employees.
        ArrayList<String> ssnResultList = new ArrayList<>(columnCount);
        while (rs.next()) {
            int i = 1;
            while (i <= columnCount){
                ssnResultList.add(rs.getString(i++));
            }
        }
        // validate userinput is in SSN
        while (ssnResultList.contains(userinputSSN)) {
            System.out.println("The SSN you entered is already in the database. Please check your record and reenter");
            Scanner input = new Scanner(System.in);
            System.out.println("please enter SSN of the employee");
            userinputSSN = input.nextLine();
        }
        return userinputSSN;
    }

    public static void main(String args[]) throws SQLException, ClassNotFoundException {

        // this is for the homework. comment this out.

        database_GUI database_gui = new database_GUI();
        database_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        database_gui.setSize(480,680);
        database_gui.setVisible(true);



        // this is for the better GUI with more functions.
        //DBTester dbtester = new DBTester();
        //dbtester.setVisible(true);

        final String DATABASE_URL = "jdbc:derby://localhost:1527/test;create=true";
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
                            addEmployee(userEmploymentTypeInput, DATABASE_URL);
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