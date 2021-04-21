import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.event.*;
import java.util.Vector;

/*
provide a combobox and a textarea to allow the user to perform a query that is either selected from the Combo Box or input into the TextArea.
1) select all employees working in department of SALES;
2) select hourly employees working over 30 hours;
3) select all commission employees in descending order of commission rate.
4) increase base salary by 10% for all base-plus-commission employees.
5) if the employee's birthday is in the current month, add a $100 bonus.
6) for all commission employees with gross sales over $10,000, add $100 bonus.
 */
public class database_GUI extends JFrame{
    String[] comboboxOptions = {
            "1. Select all employees",
            "2. Select hourly employees (>30 hours)",
            "3. Select commission employees",
            "4. Increase base salary for B+C employees",
            "5. Birthday bonus increase ($100)",
            "6. Good gross sales bonus ($100)"
    };
    private JLabel cblabel = new JLabel();
    private final JButton button1;
    private final JButton button2;
    private final JButton button3;
    private final JButton button4;
    private final JButton button5;
    private final JButton button6;
    private final JButton button7;
    private JComboBox dbmanagement = new JComboBox(comboboxOptions);
    private final JTextArea textArea;
    private final JTable testtable;

    private JPanel dbpanel;

    private final String DATABASE_URL = "jdbc:derby://localhost:1527/test;create=true";
    private final String SELECT_ALL = "SELECT * FROM employees";
    private final String SELECT_30 = "";
    private final String SELECT_CE = "";
    private final String INCREASE10_BE = "";
    private final String INCREASE_BD = "";
    private final String INCREASE_GROSSSALES = "";

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


        //textField = new JTextField("Textfield 1");
        //add(textField); // add textField1 to JFrame

        textArea = new JTextArea("RESULT WILL SHOW HERE");
        JScrollPane sp = new JScrollPane(textArea);
        sp.setBounds(10,270,440,100);
        add(sp); // add textField1 to JFrame

        testtable = new JTable();
        testtable.setBounds(10,400, 440, 100);
        add(testtable);



        //TextHandler text_handler = new TextHandler();
        //textField.addActionListener(text_handler);

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
                    sqlButton7();
                }

            }






/*

 */
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
                    to_display += metaData.getColumnName(i)+"%-24s\t";

                }
                //System.out.println();
                to_display += "\n";

                // display query results
                while (resultSet.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        //System.out.printf("%-8s\t", resultSet.getObject(i));
                        to_display += resultSet.getObject(i)+"%-24s\t";
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
            try (ResultSet resultSet = ps.executeQuery(SELECT_ALL))
            {
                // get ResultSet's meta data
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                String to_display = "";

                // display the names of the columns in the ResultSet
                for (int i = 1; i <= numberOfColumns; i++) {
                    //System.out.printf("%-8s\t", metaData.getColumnName(i));
                    to_display += metaData.getColumnName(i)+"%-24s\t";

                }
                //System.out.println();
                to_display += "\n";

                // display query results
                while (resultSet.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        //System.out.printf("%-8s\t", resultSet.getObject(i));
                        to_display += resultSet.getObject(i)+"%-24s\t";
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
            try (ResultSet resultSet = ps.executeQuery(SELECT_ALL))
            {
                // get ResultSet's meta data
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                String to_display = "";

                // display the names of the columns in the ResultSet
                for (int i = 1; i <= numberOfColumns; i++) {
                    //System.out.printf("%-8s\t", metaData.getColumnName(i));
                    to_display += metaData.getColumnName(i)+"%-24s\t";

                }
                //System.out.println();
                to_display += "\n";

                // display query results
                while (resultSet.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        //System.out.printf("%-8s\t", resultSet.getObject(i));
                        to_display += resultSet.getObject(i)+"%-24s\t";
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
            try (ResultSet resultSet = ps.executeQuery(SELECT_ALL))
            {
                // get ResultSet's meta data
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                String to_display = "";

                // display the names of the columns in the ResultSet
                for (int i = 1; i <= numberOfColumns; i++) {
                    //System.out.printf("%-8s\t", metaData.getColumnName(i));
                    to_display += metaData.getColumnName(i)+"%-24s\t";

                }
                //System.out.println();
                to_display += "\n";

                // display query results
                while (resultSet.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        //System.out.printf("%-8s\t", resultSet.getObject(i));
                        to_display += resultSet.getObject(i)+"%-24s\t";
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

        public void sqlButton5()
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
                    to_display += metaData.getColumnName(i)+"%-24s\t";

                }
                //System.out.println();
                to_display += "\n";

                // display query results
                while (resultSet.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        //System.out.printf("%-8s\t", resultSet.getObject(i));
                        to_display += resultSet.getObject(i)+"%-24s\t";
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

        public void sqlButton6()
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
                    to_display += metaData.getColumnName(i)+"%-24s\t";

                }
                //System.out.println();
                to_display += "\n";

                // display query results
                while (resultSet.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        //System.out.printf("%-8s\t", resultSet.getObject(i));
                        to_display += resultSet.getObject(i)+"%-24s\t";
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

        public void sqlButton7()
        {
            String data = "Option "
                    + (dbmanagement.getSelectedIndex()+1);
            cblabel.setText(data);
            switch(data)
            {
                case "Option 1":{
                    sqlButton1();
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
        }
    }
}
