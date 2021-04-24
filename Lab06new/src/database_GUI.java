/*
Name: Zhibin "Bing" Hong
Class: CS2551 Intermediate JAVA
Lab6-2, GUI/Rational DB
 */

import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

/*
provide a combobox and a textarea to allow the user to perform a query that is either selected from the Combo Box or input into the TextArea.
1) select all employees working in department of SALES;
2) select hourly employees working over 30 hours;
3) select all commission employees in descending order of commission rate.
4) increase base salary by 10% for all base-plus-commission employees.
5) if the employee's birthday is in the current month, add a $100 bonus.
6) for all commission employees with gross sales over $10,000, add $100 bonus.
 */

// create db gui based on JFrame
public class database_GUI extends JFrame{
    // combobox elements
    String[] comboboxOptions = {
            "1. Select all employees",
            "2. Select hourly employees (>30 hours)",
            "3. Select commission employees",
            "4. Increase base salary for B+C employees",
            "5. Birthday bonus increase ($100)",
            "6. Good gross sales bonus ($100)"
    };
    // label for combobox
    private JLabel cblabel;

    // if not using combobox then create group of buttons.
    private final JButton button1;
    private final JButton button2;
    private final JButton button3;
    private final JButton button4;
    private final JButton button5;
    private final JButton button6;

    // button7 is excute for combobox.
    private final JButton button7;
    private JComboBox dbmanagement = new JComboBox(comboboxOptions);

    // output area
    private final JTextArea textArea;
    //private final JTable testtable;

    private JPanel dbpanel;

    // setup basic variables for DB initialization
    private final String DATABASE_URL = "jdbc:derby://localhost:1527/test;create=true";

    // create sql queries
    private final String SELECT_ALL =
            "select\n" +
            "EMPLOYEES.firstname, EMPLOYEES.lastname, EMPLOYEES.birthday, EMPLOYEES.DEPARTMENTNAME, EMPLOYEES.EMPLOYEETYPE, EMPLOYEES.SOCIALSECURITYNUMBER, BASEPLUSCOMMISSIONEMPLOYEES.BASESALARY, BASEPLUSCOMMISSIONEMPLOYEES.BONUS, BASEPLUSCOMMISSIONEMPLOYEES.COMMISSIONRATE, BASEPLUSCOMMISSIONEMPLOYEES.GROSSSALES, COMMISSIONEMPLOYEES.BONUS,  COMMISSIONEMPLOYEES.COMMISSIONRATE, COMMISSIONEMPLOYEES.GROSSSALES, HOURLYEMPLOYEES.BONUS, HOURLYEMPLOYEES.HOURS, HOURLYEMPLOYEES.WAGE, SALARIEDEMPLOYEES.BONUS, SALARIEDEMPLOYEES.WEEKLYSALARY from EMPLOYEES\n" +
            "LEFT JOIN BASEPLUSCOMMISSIONEMPLOYEES on EMPLOYEES.socialSecurityNumber = BASEPLUSCOMMISSIONEMPLOYEES.SOCIALSECURITYNUMBER\n" +
            "LEFT JOIN COMMISSIONEMPLOYEES on EMPLOYEES.socialsecurityNumber = COMMISSIONEMPLOYEES.SOCIALSECURITYNUMBER\n" +
            "LEFT JOIN HOURLYEMPLOYEES on EMPLOYEES.socialsecurityNumber = HOURLYEMPLOYEES.SOCIALSECURITYNUMBER\n" +
            "LEFT JOIN SALARIEDEMPLOYEES on EMPLOYEES.socialsecurityNumber = SALARIEDEMPLOYEES.SOCIALSECURITYNUMBER\n" +
            "ORDER BY EMPLOYEES.SOCIALSECURITYNUMBER";
    private final String SELECT_30 =
            "SELECT EMPLOYEES.FIRSTNAME, EMPLOYEES.LASTNAME, EMPLOYEES.DEPARTMENTNAME, EMPLOYEES.BIRTHDAY, EMPLOYEES.EMPLOYEETYPE, EMPLOYEES.SOCIALSECURITYNUMBER, HOURLYEMPLOYEES.HOURS, HOURLYEMPLOYEES.WAGE, HOURLYEMPLOYEES.BONUS\n" +
            "From EMPLOYEES\n" +
            "LEFT JOIN HOURLYEMPLOYEES\n" +
            "on EMPLOYEES.SOCIALSECURITYNUMBER = HOURLYEMPLOYEES.SOCIALSECURITYNUMBER\n" +
            "WHERE HOURS>30";
    private final String SELECT_CE =
            "SELECT EMPLOYEES.FIRSTNAME, EMPLOYEES.LASTNAME, EMPLOYEES.DEPARTMENTNAME, EMPLOYEES.BIRTHDAY, EMPLOYEES.EMPLOYEETYPE, EMPLOYEES.SOCIALSECURITYNUMBER, COMMISSIONEMPLOYEES.GROSSSALES, COMMISSIONEMPLOYEES.COMMISSIONRATE\n" +
            "From EMPLOYEES\n" +
            "LEFT JOIN COMMISSIONEMPLOYEES\n" +
            "on EMPLOYEES.SOCIALSECURITYNUMBER = COMMISSIONEMPLOYEES.SOCIALSECURITYNUMBER\n" +
            "WHERE EMPLOYEES.EMPLOYEETYPE = 'commissionEmployee'\n" +
            "ORDER BY COMMISSIONEMPLOYEES.COMMISSIONRATE";
    private final String INCREASE10_BE =
            "UPDATE BASEPLUSCOMMISSIONEMPLOYEES\n" +
            "SET baseSalary = baseSalary * 1.10";
    private final String INCREASE_BD1 =
            "UPDATE BASEPLUSCOMMISSIONEMPLOYEES\n" +
            "SET BONUS = BONUS +100\n" +
            "WHERE EXISTS (\n" +
            "SELECT * \n" +
            "FROM EMPLOYEES\n" +
            "WHERE BASEPLUSCOMMISSIONEMPLOYEES.SOCIALSECURITYNUMBER = EMPLOYEES.SOCIALSECURITYNUMBER\n" +
            "AND MONTH(EMPLOYEES.BIRTHDAY) = MONTH(CURRENT_DATE))" ;
    private final String INCREASE_BD2 = "UPDATE COMMISSIONEMPLOYEES\n" +
            "SET BONUS = BONUS +100\n" +
            "WHERE EXISTS (\n" +
            "SELECT * \n" +
            "FROM EMPLOYEES\n" +
            "WHERE COMMISSIONEMPLOYEES.SOCIALSECURITYNUMBER = EMPLOYEES.SOCIALSECURITYNUMBER\n" +
            "AND MONTH(EMPLOYEES.BIRTHDAY) = MONTH(CURRENT_DATE)\n" +
            ")";
    private final String INCREASE_BD3 = "UPDATE HOURLYEMPLOYEES\n" +
            "SET BONUS = BONUS +100\n" +
            "WHERE EXISTS (\n" +
            "SELECT * \n" +
            "FROM EMPLOYEES\n" +
            "WHERE HOURLYEMPLOYEES.SOCIALSECURITYNUMBER = EMPLOYEES.SOCIALSECURITYNUMBER\n" +
            "AND MONTH(EMPLOYEES.BIRTHDAY) = MONTH(CURRENT_DATE)\n" +
            ")";
    private final String INCREASE_BD4 = "UPDATE SALARIEDEMPLOYEES\n" +
            "SET BONUS = BONUS +100\n" +
            "WHERE EXISTS (\n" +
            "SELECT * \n" +
            "FROM EMPLOYEES\n" +
            "WHERE SALARIEDEMPLOYEES.SOCIALSECURITYNUMBER = EMPLOYEES.SOCIALSECURITYNUMBER\n" +
            "AND MONTH(EMPLOYEES.BIRTHDAY) = MONTH(CURRENT_DATE)\n" +
            ")\n";

    private final String INCREASE_GROSSSALES =
            "UPDATE COMMISSIONEMPLOYEES\n" +
            "SET BONUS = BONUS +100\n" +
            "WHERE GROSSSALES >10000 ";

    // initialize connection and statement/preparedstatement
    private Connection connection = null;
    private Statement ps = null;

    public database_GUI(){
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            ps = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        /*
        //With BoxLayout, the buttons and text can be aligned along an axis
        BoxLayout boxlayout = new BoxLayout(getContentPane(),
                BoxLayout.PAGE_AXIS);
        getContentPane().setLayout(boxlayout);

         */

        // GUI setups
        setTitle("Employee Management Database V0.1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50,50,800,560);
        dbpanel = new JPanel();
        setContentPane(dbpanel);
        dbpanel.setLayout(null);

        dbmanagement = new JComboBox(comboboxOptions);
        dbmanagement.setBounds(250,10,200,20);
        dbpanel.add(dbmanagement);

        button1 = new JButton("Select All");
        button1.setBounds(10,10,160,20);
        dbpanel.add(button1); // add button to JFrame

        button2 = new JButton("Select >30 HE");
        button2.setBounds(10,40,160,20);
        add(button2); // add button to JFrame

        button3 = new JButton("Select All CE");
        button3.setBounds(10,70,160,20);
        add(button3); // add button to JFrame

        button4 = new JButton("Increase 10% (BE)");
        button4.setBounds(10,100,160,20);
        add(button4); // add button to JFrame

        button5 = new JButton("Increase Bonus (BD)");
        button5.setBounds(10,130,160,20);
        add(button5); // add button to JFrame

        button6 = new JButton("Increase Bonus (GS)");
        button6.setBounds(10,160,160,20);
        add(button6); // add button to JFrame

        button7 = new JButton("Execute");
        button7.setBounds(250,40,160,20);
        add(button7); // add button to JFrame

        cblabel = new JLabel("test combobox");
        cblabel.setBounds(250, 70, 160, 80);
        add(cblabel);

        // create new ButtonHandler for button event handling
        ButtonHandler handler = new ButtonHandler();
        button1.addActionListener(handler);
        button2.addActionListener(handler);
        button3.addActionListener(handler);
        button4.addActionListener(handler);
        button5.addActionListener(handler);
        button6.addActionListener(handler);
        button7.addActionListener(handler);

        // textFiled (optional)
        JTextField textField = new JTextField("Enter your sql query here. Hit Enter when finished.");
        textField.setBounds(10,400, 440, 100);
        add(textField); // add textField1 to JFrame
        TextHandler text_handler = new TextHandler();
        textField.addActionListener(text_handler);

        textArea = new JTextArea("RESULT WILL SHOW HERE");
        JScrollPane sp = new JScrollPane(textArea);
        sp.setBounds(10,270,440,100);

        add(sp); // add textField1 to JFrame

        //testtable = new JTable();
        //testtable.setBounds(10,400, 440, 100);
        //add(testtable);

    }

    // inner class for button event handling
    private class ButtonHandler implements ActionListener
    {
        // handle button event
        @Override
        public void actionPerformed(ActionEvent event)
        {
            String which_button = event.getActionCommand();
            switch(which_button) {
                case "Select All": {
                    JOptionPane.showMessageDialog(database_GUI.this, "Select all employees from Database");
                    sqlButton1();
                    break;
                }
                case "Select >30 HE": {
                    JOptionPane.showMessageDialog(database_GUI.this, "Select hourly employees who worked more than 30 hours");
                    sqlButton2();
                    break;
                }
                case "Select All CE": {
                    JOptionPane.showMessageDialog(database_GUI.this, "Select all commission-based employees");
                    sqlButton3();
                    break;
                }
                case "Increase 10% (BE)": {
                    JOptionPane.showMessageDialog(database_GUI.this, "Increase basic salary 10% for B+C employess");
                    sqlButton4();
                    break;
                }
                case "Increase Bonus (BD)": {
                    JOptionPane.showMessageDialog(database_GUI.this, "For employees whose birthday is this month, give $100 bonus");
                    sqlButton5();
                    break;
                }
                case "Increase Bonus (GS)": {
                    JOptionPane.showMessageDialog(database_GUI.this, "For employees who has gross sales more than 10,000, give $100 bonus");
                    sqlButton6();
                    break;
                }
                case "Execute":{
                    try {
                        sqlButton7();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        }

        public void sqlButton1()
        {
            try (ResultSet resultSet = ps.executeQuery(SELECT_ALL))
            {
                // get ResultSet's meta data
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                String to_display = "";

                // display the names of the columns in the ResultSet
                for (int i = 1; i <= numberOfColumns; i++) {
                    //System.out.printf("%-8s\t", metaData.getColumnName(i));
                    to_display += metaData.getColumnName(i)+"\t";
                    if(i==5) {
                        to_display += "\t";
                    }

                }
                //System.out.println();
                to_display += "\n";

                // display query results
                while (resultSet.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        //System.out.printf("%-8s\t", resultSet.getObject(i));
                        to_display += resultSet.getObject(i)+"\t";
                        if(i==4 || i==6) {
                            to_display += "\t";
                        }
                        if(i==5 && !resultSet.getObject(i).toString().equals("basePlusCommissionEmployee")) {
                            to_display += "\t";
                        }

                    }
                    //System.out.println();
                    to_display += "\n";
                }

                textArea.setText(to_display);
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        public void sqlButton2()
        {
            try (ResultSet resultSet = ps.executeQuery(SELECT_30))
            {
                // get ResultSet's meta data
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                String to_display = "";

                // display the names of the columns in the ResultSet
                for (int i = 1; i <= numberOfColumns; i++) {
                    //System.out.printf("%-8s\t", metaData.getColumnName(i));
                    to_display += metaData.getColumnName(i)+"\t";
                    if(i==5) {
                        to_display += "\t";
                    }

                }
                //System.out.println();
                to_display += "\n";

                // display query results
                while (resultSet.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        //System.out.printf("%-8s\t", resultSet.getObject(i));
                        to_display += resultSet.getObject(i)+"\t";
                        if(i==4 || i==6) {
                            to_display += "\t";
                        }
                        if(i==5 && !resultSet.getObject(i).toString().equals("basePlusCommissionEmployee")) {
                            to_display += "\t";
                        }
                    }
                    //System.out.println();
                    to_display += "\n";
                }

                textArea.setText(to_display);
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        public void sqlButton3()
        {
            try (ResultSet resultSet = ps.executeQuery(SELECT_CE))
            {
                // get ResultSet's meta data
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                String to_display = "";

                // display the names of the columns in the ResultSet
                for (int i = 1; i <= numberOfColumns; i++) {
                    //System.out.printf("%-8s\t", metaData.getColumnName(i));
                    to_display += metaData.getColumnName(i)+"\t";
                    if(i==5) {
                        to_display += "\t";
                    }

                }
                //System.out.println();
                to_display += "\n";

                // display query results
                while (resultSet.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        //System.out.printf("%-8s\t", resultSet.getObject(i));
                        to_display += resultSet.getObject(i)+"\t";
                        if(i==4 || i==6) {
                            to_display += "\t";
                        }
                        if(i==5 && !resultSet.getObject(i).toString().equals("basePlusCommissionEmployee")) {
                            to_display += "\t";
                        }
                    }
                    //System.out.println();
                    to_display += "\n";
                }

                textArea.setText(to_display);
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }


        public void sqlButton4()
        {
            try{
                Connection connection = DriverManager.getConnection(DATABASE_URL);
                PreparedStatement update10;
                update10 = connection.prepareStatement(
                        INCREASE10_BE);
                update10.executeUpdate();
                update10.close();
                int count = ps.executeUpdate(INCREASE10_BE);
                String to_display = "";
                to_display = ("Number of rows updated by executing INCREASE10_BE = " + count);
                textArea.setText(to_display);
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        }

        public void sqlButton5()
        {
            try{
                Connection connection = DriverManager.getConnection(DATABASE_URL);
                PreparedStatement updatebd1;
                updatebd1 = connection.prepareStatement(
                        INCREASE_BD1);
                updatebd1.executeUpdate();
                updatebd1.close();

                PreparedStatement updatebd2;
                updatebd2 = connection.prepareStatement(
                        INCREASE_BD2);
                updatebd2.executeUpdate();
                updatebd2.close();

                PreparedStatement updatebd3;
                updatebd3 = connection.prepareStatement(
                        INCREASE_BD3);
                updatebd3.executeUpdate();
                updatebd3.close();

                PreparedStatement updatebd4;
                updatebd4 = connection.prepareStatement(
                        INCREASE_BD4);
                updatebd4.executeUpdate();
                updatebd4.close();

                int count1 = ps.executeUpdate(INCREASE_BD1);
                int count2 = ps.executeUpdate(INCREASE_BD2);
                int count3 = ps.executeUpdate(INCREASE_BD3);
                int count4 = ps.executeUpdate(INCREASE_BD4);
                int totalCount = count1+count2+count3+count4;
                String to_display = "";
                to_display = ("Number of rows updated by executing Birthday Bouns = " + totalCount);
                textArea.setText(to_display);
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        }

        public void sqlButton6()
        {
            try{
                Connection connection = DriverManager.getConnection(DATABASE_URL);
                PreparedStatement updateGS;
                updateGS = connection.prepareStatement(
                        INCREASE_GROSSSALES);
                updateGS.executeUpdate();
                updateGS.close();
                int count = ps.executeUpdate( INCREASE_GROSSSALES);
                String to_display = "";
                to_display = ("Number of rows updated by executing INCREASE_GROSSSALES = " + count);
                textArea.setText(to_display);
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        public void sqlButton7() throws SQLException {
            String data = "Option "
                    + (dbmanagement.getSelectedIndex()+1);
            cblabel.setText(data);
            switch(data)
            {
                case "Option 1":{
                    sqlButton1();
                    break;
                }
                case "Option 2":{
                    sqlButton2();
                    break;
                }
                case "Option 3":{
                    sqlButton3();
                    break;
                }
                case "Option 4":{
                    sqlButton4();
                    break;
                }
                case "Option 5":{
                    sqlButton5();
                    break;
                }
                case "Option 6":{
                    sqlButton6();
                    break;
                }
            }
        }
    }
    // inner class for text event handling
    private class TextHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            String text_from_textfield = event.getActionCommand();

            JOptionPane.showMessageDialog(database_GUI.this,
                    String.format("You wrote: %s", text_from_textfield));

            // conduct queries from user input
            try(ResultSet resultSet = ps.executeQuery(text_from_textfield)){
                Connection connection = DriverManager.getConnection(DATABASE_URL);
                PreparedStatement userInputSQL;
                userInputSQL = connection.prepareStatement(
                        text_from_textfield);
                userInputSQL.executeQuery();
                userInputSQL.close();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                String to_display = "";

                // display the names of the columns in the ResultSet
                for (int i = 1; i <= numberOfColumns; i++) {
                    //System.out.printf("%-8s\t", metaData.getColumnName(i));
                    to_display += metaData.getColumnName(i)+"\t";

                }
                //System.out.println();
                to_display += "\n";

                // display query results
                while (resultSet.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        //System.out.printf("%-8s\t", resultSet.getObject(i));
                        to_display += resultSet.getObject(i)+"\t";
                    }
                    //System.out.println();
                    to_display += "\n";
                }

                textArea.setText(to_display);
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

    }
}
