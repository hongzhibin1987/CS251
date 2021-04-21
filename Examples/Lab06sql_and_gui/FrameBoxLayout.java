/*
https://docs.oracle.com/javase/7/docs/api/javax/swing/BoxLayout.html

https://www.geeksforgeeks.org/java-awt-boxlayout-class/

https://stackoverflow.com/questions/761341/error-upon-assigning-layout-boxlayout-cant-be-shared
*/
import java.awt.Font;
import java.awt.Color;

import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class FrameBoxLayout extends JFrame 
{
	private final JButton button1;
	private final JButton button2;
	private final JButton button3;
	private final JButton button4;

	private final JTextField textField; //input
	private final JTextArea textArea; //output

	private final String DATABASE_URL = "jdbc:derby:employees";
	private final String SELECT_QUERY = "SELECT * FROM employees";
	private Connection connection = null;
	private Statement statement = null;

	public FrameBoxLayout()
	{
		super("Testing Layouts");

		//Initialize my database variables
		try {
			connection = DriverManager.getConnection(DATABASE_URL, "deitel", "deitel");
			statement = connection.createStatement();
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		//With BoxLayout, the buttons and text can be aligned along an axis
		BoxLayout boxlayout = new BoxLayout(getContentPane(),
										BoxLayout.PAGE_AXIS);
		getContentPane().setLayout(boxlayout);


		button1 = new JButton("Button 1");
		add(button1); // add button to JFrame

		button2 = new JButton("Button 2");
		add(button2); // add button to JFrame

		button3 = new JButton("Button 3");
		add(button3); // add button to JFrame

		button4 = new JButton("Button 4");
		add(button4); // add button to JFrame

		// create new ButtonHandler for button event handling 
		ButtonHandler handler = new ButtonHandler();
		button1.addActionListener(handler);
		button2.addActionListener(handler);
		button3.addActionListener(handler);
		button4.addActionListener(handler);



		textField = new JTextField("Textfield 1");
		add(textField); // add textField1 to JFrame

		textArea = new JTextArea("TextArea");
		textArea.setFont(new Font("Serif", Font.PLAIN, 20));
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.yellow);
		add(textArea); // add textField1 to JFrame

		TextHandler text_handler = new TextHandler();
		textField.addActionListener(text_handler);
	}

	// inner class for button event handling
	private class ButtonHandler implements ActionListener 
	{
		// handle button event
		@Override
		public void actionPerformed(ActionEvent event)
		{
			String which_button = event.getActionCommand();
			if(which_button.equals("Button 1"))
			{
				JOptionPane.showMessageDialog(FrameBoxLayout.this, "Run a particular SQL query");
				
				runSelect(); //run the select query
			}
			else
			{
				JOptionPane.showMessageDialog(FrameBoxLayout.this, String.format("You pressed: %s", event.getActionCommand()));
			}
		}
		
		public void runSelect()
		{
			try (ResultSet resultSet = statement.executeQuery(SELECT_QUERY))
			{
				// get ResultSet's meta data
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
	
	
	// inner class for text event handling
	private class TextHandler implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			String text_from_textfield = event.getActionCommand();
			
			JOptionPane.showMessageDialog(FrameBoxLayout.this,
			String.format("You wrote: %s", text_from_textfield));
		}
	}
}

