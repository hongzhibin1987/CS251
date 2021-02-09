public class Main
{
	public static void main(String[] args)
	{
		RoadVehicle vehicles[] = new RoadVehicle[2];
		
		//TODO Create one car and one motorcycle and 
		//put them in the vehicles array
		Car c = new Car();
		Motorcycle m = new Motorcycle();
		vehicles[0] = c;
		vehicles[1] = m;
		/* Alternatively:
		vehicles[0] = new Car();
		vehicles[1] = new Motorcycle();
		*/
		
		for(int i=0; i<vehicles.length; i++)
		{
			System.out.println("Fuel before "+vehicles[i].getFuel());
			vehicles[i].drive();
			System.out.println("Fuel after "+vehicles[i].getFuel());
		}
		
		//TODO Call the car's open and close door methods
		((Car)vehicles[0]).openDoors();
		c.closeDoors();
		
		//TODO Call the motorcycle's popAWheelie method
		((Motorcycle)vehicles[1]).popAWheelie();
		
		//TODO Demonstrate that calling openDoor on the motorcycle causes an error
		//Neither of these works
		//vehicles[1].openDoors();
		//((Car)vehicles[1]).openDoors();
	}
}