public class RoadVehicle
{
	private int wheels;
	private int passenger_capacity;
	private int num_passengers;
	private double fuel;
	private double efficiency;
	
	public RoadVehicle(int wheels, int passenger_capacity, double efficiency)
	{
		this.wheels = wheels;
		this.num_passengers = 0;
		this.passenger_capacity = passenger_capacity;
		this.efficiency = efficiency;
		this.fuel = 100.0; //What percent full the tank is
	}
	
	public void drive()
	{
		this.fuel -= 1.0/this.efficiency;
	}
	
	public double getFuel()
	{
		return this.fuel;
	}
}