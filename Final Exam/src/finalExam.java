public class finalExam {
}

/*

==============================================

QUESTION 1
Given the Tile class and Main in the folder scrabble_array_list, modify the Tile class so that it implements comparable.

Tiles should be compared alphabetically based on their letter such that a comes before b comes before c and so on.

The given code should run without errors and print the tiles first out of order and then in order.

You should look up the comparable interface if you need to, but all code should be your own.

WRITE YOUR ANSWER IN THE scrabble_array_list FOLDER.

==============================================

QUESTION 2
Given the code in the folder interface_question, create a class named Box that implements the interface Holder.

The code in Main should run correctly, including a catch of an exception at the end when you are finished.

Throw an exception if someone tries to remove an object that is not in the Holder.

You MAY implement your Box using an ArrayList, but if you use an array instead and write code to dynamically grow the size of the array as more items are added you can earn up to 10 points of extra credit.

WRITE YOUR ANSWER IN THE interface_question FOLDER.

==============================================

QUESTION 3

The following method takes a generic Object as input. Write code that uses instanceof to check if the Object is a String and if the input is a String, print it out.

public static void whatsTheInput(Object mystery)
{
	//WRITE YOUR ANSWER HERE
}

==============================================

For all of the following written response questions, write your own answer. You may not copy from any source though you can use any source for research as long as you do not ask another person other than the instructor.

It is wise to cite any sources you use.

==============================================

QUESTION 4

Suppose Writer is an object that writes data to a shared buffer named Buffer and Reader reads from Buffer. Both Writer and Reader extend Thread and operate concurrently.

Part A: Describe how notifyAll can be useful for Writer to avoid writing when Buffer is full.

WRITE YOUR ANSWER HERE

Part B: Describe how notifyAll can be useful for Reader to avoid reading when Buffer is empty.

WRITE YOUR ANSWER HERE

Part C: Describe how notifyAll can improve the general efficiency of this producer-consumer relationship.

WRITE YOUR ANSWER HERE

==============================================

QUESTION 5
What is the primary difference between the Transmission Control Protocol (TCP) and the User Datagram Protocol (UDP)?

WRITE YOUR ANSWER HERE

Give examples of applications that are suited to each.

WRITE YOUR ANSWER HERE

==============================================

QUESTION 6
The following code is part of a larger program implementing a Graphical User Interface (GUI). The snippet shown attaches an ActionListener to a button. What effect does attaching an ActionListener have in general and why is it important?
      ...
      // create new ButtonHandler for button event handling
      ButtonHandler handler = new ButtonHandler();
      plainJButton.addActionListener(handler);
   }

   // inner class for button event handling
   private class ButtonHandler implements ActionListener
   {
      // handle button event
      @Override
      public void actionPerformed(ActionEvent event)
      {
         JOptionPane.showMessageDialog(ButtonFrame.this, String.format(
            "You pressed: %s", event.getActionCommand()));
      }
   }
   ...

WRITE YOUR ANSWER HERE

==============================================

QUESTION 7
What does the setEditable method do?

    textField1.setEditable(false);
    textField2.setEditable(true);

Give an example of an application in which you want a textfield to have editable set to true and an example of when you would NOT want a textfield to have editable set to true.

WRITE YOUR ANSWER HERE

==============================================

QUESTION 8
Will a computer with only one single core processor ever benefit from multi-threading? Why or why not?

WRITE YOUR ANSWER HERE

==============================================

QUESTION 9
What is the purpose of primary keys and foreign keys in a relational database?

WRITE YOUR ANSWER HERE

 */