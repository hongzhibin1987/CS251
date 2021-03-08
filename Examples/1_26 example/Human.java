
/* A Human is a more specific type of Mammal.
Since Mammal implements Animal, a Human is also an Animal. */
public class Human extends Mammal
{
	//Humans have some private attributes that are not 
	//available to other Mammals.
	private String[] tools;
	private int index = 0;
	
	public Human() {
		tools = new String[10];
	}
	
	/* Humans have to override the Mammal's number of legs
	method because Humans have 2 legs. To override a
	method all we need to do is create a method with the
	same name, parameters, and return type. The child class's
	version of a method will always be executed rather than
	the parent's version. */
	//Override Mammal class's method
	@Override
	public int numberOfLegs() {
		return 2;
	}
	
	//Override Mammal class's method
	@Override
	public void speak() {
		System.out.println("Blah blah blah human speech noises");
	}
	
	//A new method that Mammal doesn't have
	public void getTool(String tool) {
		tools[index] = tool;
		index = (index + 1)%tools.length;
	}
	
	//A new method that Mammal doesn't have
	public void useTool() {
		int most_recent_tool = index -1;
		if(most_recent_tool < 0)
			most_recent_tool = tools.length + most_recent_tool;
		System.out.println(tools[most_recent_tool]+" goes chop or bang or something");
	}
}