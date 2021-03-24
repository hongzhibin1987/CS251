/*
Question 1. In the board game Scrabble, each tile contains a letter, which is used to spell words in rows and columns, and a score, which is used to determine the value of words.




 */

//Part A. Write a class named Tile that represents Scrabble tiles. Include two private instance variables, a character named letter and an integer named value.
public class Tile {
    char letter;
    int value;

    // Part F. What is printed when the variable that refers to your tile object is printed directly?
    // (Answer this in a comment at the top of Main.
    // "Directly" means without quotation marks or use of toString. If t is the variable name of the tile, what does System.out.println(t); print?)
    // Answer: System will print out, "Tile@27973e9b". We are trying to print the object directly; without using "toString", it will print the name of the object, "@" joins the string together, and "27973e9b" the hashcode of the object.

    // Part I. Override the default equals method for Tile with a custom method that returns true if the tiles being compared have the same letter and number.
    // (You are encouraged to research the default equals method as much as you like.)
    // Are tiles t1 and t2 equal now? (Answer this in a comment at the top of Main.)
    // Answer: before override, since t1 and t2 are in two different memory poll, these aren't identical since the hashcode is different;
    // After the override, two values are stored in the same memory address so they get the same getValue. instead of compare hashcode
    // it will compare the value so the result returns true.

    public static void main(String[] args) {
        testTile();
        tileZ();
    }

    // Part B. Write a constructor for Tile that takes parameters named letter and value and initializes the instance variables.
    public Tile (char letter, int value){
        this.letter = letter;
        this.value = value;
    }

    // Part C. Write two public getter methods in Tile that return the values of the instance variables.
    public char getLetter(){
        return this.letter;
    }

    public int getValue(){
        return this.value;
    }
    // Part D. Create a driver class named Main.java with a public static void main method. Write another static method in Main named printTile that takes a Tile object as a parameter and displays the instance variables in a reader-friendly format.
    public static void printTile(Tile theTile){
        System.out.println("The value of tile " + theTile.letter + " is " + theTile.value);
    }

    // Part E. Write a static method in Main named testTile that instantiates a Tile object with the letter 'Z' and the value 10, and then uses printTile to display the state of the object.
    public static void testTile(){
        Tile test = new Tile('z', 10);
        //printTile(test);
        //System.out.println(test);
    }

    //Part H. Create two instances of Tile (name the variables t1 and t2) that both have the letter Z and the value of 10.
    //Is it true that t1.equals(t2)? (Answer this in a comment at the top of Main.)
    public static void tileZ(){
        Tile t1 = new Tile('Z', 10);
        Tile t2 = new Tile('Z', 10);
        // Part I (first part). invoking hashcode() method to get the value of the hashcode to see whether 2 are identical.
        int a = t1.hashCode();
        int b = t2.hashCode();
        System.out.println("hashcode of t1 = " + a);
        System.out.println("hashcode of t2 = " + b);
        System.out.println("Comparing t1 and t2 is identical = " + t1.equals(t2));

        System.out.println(t1);
        System.out.println(t2);
    }

    // Part G. Override the default toString method for Tile with a custom toString method that returns the same text as printTile prints. Then modify printTile to make use of toString.
    @Override
    public String toString(){
        return String.format("The value of tile " + letter + " is " + value);
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        if (obj == this){
            return true;
        }
        return this.getValue() == ((Tile)obj). getValue();
    }



}
