//https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class FrameFlowLayout extends JFrame 
{
	private final JButton button1;
	private final JButton button2;
	private final JButton button3;
	private final JButton button4;

	private final JTextField textField1;
	private final JTextField textField2;
	
	public FrameFlowLayout()
	{
		super("Testing Layouts");
		
		
		//With FlowLayout, the buttons and text flow depending
		//on the size of the screen
		setLayout(new FlowLayout()); 



		button1 = new JButton("Button 1");
		button1.setBounds(100,100,100,100); //This has no apparent effect in a flow layout.
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
		textField1.setBounds(300,100,300,60); //No Effect
		add(textField1); // add textField1 to JFrame

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
			String which_button = event.getActionCommand();
			if(which_button.equals("Button 1"))
			{
				JOptionPane.showMessageDialog(FrameFlowLayout.this, "Run a particular SQL query");
			}
			else
			{
				JOptionPane.showMessageDialog(FrameFlowLayout.this, String.format("You pressed: %s", event.getActionCommand()));
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
			
			JOptionPane.showMessageDialog(FrameFlowLayout.this,
			String.format("You wrote: %s", text_from_textfield));
		}
	}
}

