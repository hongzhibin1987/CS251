// Fig. 28.5: Client.java
// Client portion of a stream-socket connection between client and server.
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
	private final Scanner user_input = new Scanner(System.in);
	private ObjectOutputStream output; // output stream to server
	private ObjectInputStream input; // input stream from server
	private String message = ""; // message from server
	private String chatServer; // host server for this application
	private Socket client; // socket to communicate with server

	// initialize chatServer
	public Client(String host)
	{
		chatServer = host; // set server to which this client connects
	}

	// connect to server and process messages from server
	public void runClient() 
	{
		try // connect to server, get streams, process connection
		{
			connectToServer(); // create a Socket to make connection
			getStreams(); // get the input and output streams
			processConnection(); // process connection
		} 
		catch (EOFException eofException) 
		{
			System.out.println("\nClient terminated connection");
		} 
		catch (IOException ioException) 
		{
			ioException.printStackTrace();
		} 
		finally 
		{
			closeConnection(); // close connection
		}
	}

	// connect to server
	private void connectToServer() throws IOException
	{		
		System.out.println("Attempting connection\n");

		// create Socket to make connection to server
		client = new Socket(InetAddress.getByName(chatServer), 12345);

		// display connection information
		System.out.println("Connected to: " + client.getInetAddress().getHostName());
	}

	// get streams to send and receive data
	private void getStreams() throws IOException
	{
		// set up output stream for objects
		output = new ObjectOutputStream(client.getOutputStream());		
		output.flush(); // flush output buffer to send header information

		// set up input stream for objects
		input = new ObjectInputStream(client.getInputStream());

		System.out.println("\nGot I/O streams\n");
	}

	// process connection with server
	private void processConnection() throws IOException
	{
		String message = "";
		
		do // process messages sent from server
		{
			//Let user send messages of their own
			sendData();

			try // read message and display it
			{
				//receive message from the server
				message = (String) input.readObject();
				System.out.println("Message from Server: "+message);
			} 
			catch (ClassNotFoundException classNotFoundException) 
			{
				System.out.println("\nUnknown object type received");
			}

		} while(!message.equals("TERMINATE"));
	}

	// close streams and socket
	private void closeConnection() 
	{
		System.out.println("\nClosing connection");
		try 
		{
			output.close(); // close output stream
			input.close(); // close input stream
			client.close(); // close socket
		} 
		catch (IOException ioException) 
		{
			ioException.printStackTrace();
		} 
	}

	// send message to server
	private void sendData()
	{
		int data_type;
		Object data = null;
		
		System.out.print("What type of data do you want to send? (0 for int, 1 for double, 2 for String, 3 to terminate the connection) ");
		data_type = user_input.nextInt();
		user_input.nextLine(); //clear the scanner
		
		if(data_type == 0) {
			System.out.print("Enter an integer: ");
			data = new Integer(user_input.nextInt());
			user_input.nextLine(); //clear the scanner
		} else if(data_type == 1) {
			System.out.print("Enter a double: ");
			data = new Double(user_input.nextDouble());
			user_input.nextLine(); //clear the scanner
		} else if(data_type == 2) {
			System.out.print("Enter a String: ");
			data = new String(user_input.nextLine());
		}
		
		try // send object to server
		{
			System.out.println("\nSending to Server...");
			output.writeObject(data);
			output.flush(); // flush data to output
		}
		catch (IOException ioException)
		{
			System.out.println("\nError writing object");
		}
	}
}

/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and					*
 * Pearson Education, Inc. All Rights Reserved.									*
 *																								*
 * DISCLAIMER: The authors and publisher of this book have used their	  *
 * best efforts in preparing the book. These efforts include the			 *
 * development, research, and testing of the theories and programs		  *
 * to determine their effectiveness. The authors and publisher make		 *
 * no warranty of any kind, expressed or implied, with regard to these	 *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or		 *
 * consequential damages in connection with, or arising out of, the		 *
 * furnishing, performance, or use of these programs.							*
 *************************************************************************/
