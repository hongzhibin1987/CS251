//https://www.tutorialspoint.com/java/java_interfaces.htm

/* Notice the keyword 'implements' to indicate that a
Mammal is a form of Animal and implements the methods
required of an Animal. */
public class Mammal implements Animal
{
	//Implementation of eat
	public void eat() {
		System.out.println("Mammal eats");
	}

	//Implementation of travel
	public void travel() {
		System.out.println("Mammal travels");
	}

	//A new method for mammals to make noises.
	//By default the mammal makes no noise.
	public void speak() {
		System.out.println(""); //placeholder
	}

	//A new method to ask a mammal how many legs it has.
	//By default the mammal has 4 legs.
	public int numberOfLegs() {
		return 4;
	}
}