
public class Main
{
	public static void main(String[] args)
	{
		/* All of these types are Animals so we can declare
		them as Animals if we want to, for example, put them
		all in the same array. */
		Animal generic_animal1 = new Mammal();
		Animal generic_animal2 = new Human();
		Animal generic_animal3 = new Dog();
		
		//Cannot do this because generic_animal2 is an Animal.
		//System.out.println( "Number of legs: "+generic_animal2.numberOfLegs() );
		//Can do this:
		System.out.println( "Number of legs: "+
			((Human)generic_animal2).numberOfLegs() );
		
		//This is allowed because all animals eat and travel
		generic_animal2.eat();
		System.out.println();


		System.exit(1); //FOR TESTING



		/* Here we see we can put Mammals of "different" types in
		the same array. */
		Mammal person = new Human();
		Mammal pet = new Dog();
		Mammal generic_mammal = new Mammal();
		Mammal[] house_animals = new Mammal[2]; //Polymorphism
		house_animals[0] = person;
		house_animals[1] = pet;
		house_animals[2] = generic_mammal;
		for(int i=0; i<house_animals.length; i++)
		{
			System.out.print("Mammal on "+house_animals[i].numberOfLegs()+" legs says ");
			house_animals[i].speak();
		}
		System.out.println();
		
		
		//Can't do this because person is a Mammal.
		//person.getTool("Hammer");
		
		
		//Can do this.
		Human new_person = new Human();
		new_person.getTool("Axe");
		new_person.getTool("Hammer");
		new_person.useTool();
		
		((Human)person).getTool("Hammer");
	}
}