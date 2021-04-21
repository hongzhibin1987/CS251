import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DBTester extends JFrame {
    private JPanel dbPanel;
    private JTextField firstnameTextField;
    private JTextField lastnameTextField;
    private JTextField dobTextField;
    private JTextField ssnTextField;
    private JComboBox employeeTypeComboBox;
    private JTextField baseSalaryBCTextField;
    private JComboBox departmentNameComboBox;
    private JButton addEmployeeButton;
    private JComboBox comboBox3;
    private JScrollBar scrollBar2;
    private JTextField grossSaleBCTextField;
    private JTextField commissionBCRateTextField;
    private JTextField bonusBCTextField;
    private JTextField baseSalaryCETextField;
    private JTextField commissionRateCETextField;
    private JTextField bonusCETextField;
    private JTextField hoursHETextField;
    private JTextField wageHETextField;
    private JTextField bonusHETextField;
    private JTextField weeklywageSETextField;
    private JTextField bonusSETextField;
    private JTable table1;
    private JTextArea textArea1;

    public DBTester (String title) {
        super (title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(dbPanel);
        this.pack();
        firstnameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // grab the text of first name
                // send to define the
            }
        });
    }

    public DBTester() {

    }

    public static void main(String[] args){
        JFrame frame = new DBTester("DBTest program for Lab6-1");
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}




