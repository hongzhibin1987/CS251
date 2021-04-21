/*
https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html

https://www.tutorialspoint.com/what-is-the-use-of-setbounds-method-in-java
*/
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class FrameNullLayout extends JFrame 
{
	private final JButton button1;
	private final JButton button2;
	private final JButton button3;
	private final JButton button4;

	private final JTextField textField1;
	private final JTextField textField2;
	
	public FrameNullLayout()
	{
		super("Testing Layouts");
		
		
		//With this setting, nothing shows up unless you use setBounds
		setLayout(null); 
		

		button1 = new JButton("Button 1");
		button1.setBounds(100,100,100,100);
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



		textField1 = new JTextField("Textfield 1");
		add(textField1); // add textField1 to JFrame
		textField1.setBounds(300,100,300,60);

		textField2 = new JTextField("Textfield 2");
		add(textField2); // add textField1 to JFrame

		TextHandler text_handler = new TextHandler();
		textField1.addActionListener(text_handler);
		textField2.addActionListener(text_handler);
	}

	// inner class for button event handling
	private class ButtonHandler implements ActionListener 
	{
		// handle button event
		@Override
		public void actionPerformed(ActionEvent event)
		{
			JOptionPane.showMessageDialog(FrameNullLayout.this,
			String.format("You pressed: %s", event.getActionCommand()));
		}
	}
	
	
	// inner class for text event handling
	private class TextHandler implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			JOptionPane.showMessageDialog(FrameNullLayout.this,
			String.format("You wrote: %s", event.getActionCommand()));
		}
	}
}

