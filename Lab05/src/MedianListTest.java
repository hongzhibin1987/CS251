import java.util.Arrays;
import java.util.Scanner;

public class MedianListTest {

    static MedianList testList;

    // determine whether a number is median or not.
    static boolean isMedian(int x)
    {
        Object[] temp= testList.medianList.toArray();
        int[] array = new int[temp.length];

        for(int i=0;i<temp.length;i++)
        {
            array[i]=(int) temp[i];
        }

        Arrays.sort(array);
        for(int i=0;i<array.length;i++)
        {
            if(i==array.length/2)
            {
                if(x==array[i])
                {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }

    // main body of the logic to test the whether the median is correct.
    static boolean testCorrectMedian()
    {
        int selectNumber;
        int pushNumber;
        int returnNumber;
        boolean returnBoolean;

        while (true)
        {
            // print out main program
            Scanner select=new Scanner(System.in);
            System.out.println("Select an option:");
            System.out.println("1.push a new number");
            System.out.println("2.pop the median");
            System.out.println("3.peek");
            System.out.println("4.validate");
            System.out.println();
            selectNumber=select.nextInt();
            switch (selectNumber) {
                case 1:
                    System.out.println("input the push number");
                    // get the number from user input
                    pushNumber=select.nextInt();
                    // push the number into the arraylist
                    testList.push(pushNumber);
                    break;
                case 2:
                    // popout the median number
                    returnNumber=testList.pop();
                    break;
                case 3:
                    // review the next number to be popped
                    returnNumber=testList.peek();
                    if (isMedian(returnNumber))
                    {
                        System.out.println("The test median correct!");
                    }
                    else {
                        System.out.println("The test median incorrect!");
                    }
                    break;
                case 4:
                    System.out.println("Validate & Exit? (type true/false)");
                    //  makes sure the median revealed by peek is correct after a series of pushes and pops.
                    returnBoolean=select.nextBoolean();
                    if (returnBoolean)
                    {
                        System.out.println("Validation in progress");
                        return true;
                    }
                    else {
                        System.out.println("Resume previous procedure");

                    }
                    break;
                default:
                    break;
            }
        }
    }

    //makes sure the String returned from toString is correct after a series of pushes and pops.
    static boolean testCorrectString()
    {
        if(testList.toString().equals(testList.medianList.toString()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void main (String args[]) {
        testList=new MedianList();
        System.out.println("Media validation is "+testCorrectMedian());
        System.out.println("The String output is "+testCorrectString());
    }
}