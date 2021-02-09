public class Car extends RoadVehicle
{
	private boolean doors_open = false;
	
	public Car()
	{
		//4 wheels, 3 passengers, 0.5 fuel efficiency
		super(4, 3, 0.5);
	}
	
	public void openDoors()
	{
		doors_open = true;
		System.out.println("Doors are now open");
	}
	
	public void closeDoors()
	{
		doors_open = false;
		System.out.println("Doors are now closed");
	}
}
