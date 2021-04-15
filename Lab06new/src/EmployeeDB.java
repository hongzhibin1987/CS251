import java.sql.*;

/*
a) Add employees to the employee table.
b) Add payroll information to the appropriate table for each new employee.For example, for a salaried employee add the payroll information to the *salariedEmployees* table.
c) Complete the assignment using only a command prompt interface. No GUI (yet).
d) Write a while or do-while loop in main that repeatedly asks the user if they would like to enter another employee.
e) hen the user says "yes", a new method named addEmployee should be called.
f) This method prompts the user for the employee's first and last name, social security number, and birthday.
g) Validate the formatting of the SSN and the birthday and make the user re-enter this information if they mess up.
h) Randomly generate the employeeType data for the new employee as either: salariedEmployee or commissionEmployee
i) Randomly generate the departmentName data for the new employee from among: Sales, R&D, or HR
 */
public class EmployeeDB {

    public static void main(String args[]) throws SQLException, ClassNotFoundException {

        final String DATABASE_URL = "jdbc:derby://localhost:1527/test;create=true";

        // query, need to remove.
        final String SELECT_QUERY = "select \n" +
                "EMPLOYEES.firstname, EMPLOYEES.lastname, EMPLOYEES.birthday, EMPLOYEES.DEPARTMENTNAME, EMPLOYEES.EMPLOYEETYPE, EMPLOYEES.SOCIALSECURITYNUMBER, BASEPLUSCOMMISSIONEMPLOYEES.BASESALARY, BASEPLUSCOMMISSIONEMPLOYEES.BONUS, BASEPLUSCOMMISSIONEMPLOYEES.COMMISSIONRATE, BASEPLUSCOMMISSIONEMPLOYEES.GROSSSALES, COMMISSIONEMPLOYEES.BONUS,  COMMISSIONEMPLOYEES.COMMISSIONRATE, COMMISSIONEMPLOYEES.GROSSSALES, HOURLYEMPLOYEES.BONUS, HOURLYEMPLOYEES.HOURS, HOURLYEMPLOYEES.WAGE, SALARIEDEMPLOYEES.BONUS, SALARIEDEMPLOYEES.WEEKLYSALARY from EMPLOYEES\n" +
                "LEFT JOIN BASEPLUSCOMMISSIONEMPLOYEES on EMPLOYEES.socialSecurityNumber = BASEPLUSCOMMISSIONEMPLOYEES.SOCIALSECURITYNUMBER\n" +
                "LEFT JOIN COMMISSIONEMPLOYEES on EMPLOYEES.socialsecurityNumber = COMMISSIONEMPLOYEES.SOCIALSECURITYNUMBER\n" +
                "LEFT JOIN HOURLYEMPLOYEES on EMPLOYEES.socialsecurityNumber = HOURLYEMPLOYEES.SOCIALSECURITYNUMBER\n" +
                "LEFT JOIN SALARIEDEMPLOYEES on EMPLOYEES.socialsecurityNumber = SALARIEDEMPLOYEES.SOCIALSECURITYNUMBER\n" +
                "ORDER BY EMPLOYEES.SOCIALSECURITYNUMBER";

        // use try-with-resource to connect and query the database
        try (

            Connection connection = DriverManager.getConnection(DATABASE_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) {

            // get ResultSet's meta data
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            System.out.printf("Welcome to Employee Info Database\n");

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
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


}

