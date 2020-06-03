public class Account // class declaration. 
{
	private String name; //instance variable 实例变量，非静态变量，位于class中，method外。
	
	public void setName(String name) //method to set the name in the object
	{
		this.name = name ; //store the name 
	}
	
	public String getName() //method to retrieve the name from the object
	{
		return name; //return value of name to caller
	}
}

// notes: class variables， instance variables, and local variables. 