import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class MedianList
{
    ArrayList<Integer> medianList;
    int median;

    //push value in array
    void push(int number)
    {
        if(medianList.size() == 0)
        {
            System.out.println("Type the number you want to push in");
            medianList.add(number);
            System.out.println("After push:");
            System.out.println(medianList);
            return;
        }
        else
        {
            System.out.println("Before push:");
            System.out.println(medianList);
            // add number into the median list.
            medianList.add(number);
            try {
                // determine the value of number input and median number.
                assert(number > median);
                System.out.println("The number you entered is larger than the median");

                // create a temp array and copy the old array into the temp one.
                Object[] temp=medianList.toArray();
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
                        median=array[i];
                    }
                }

            } catch (Exception e) {
                System.out.println("The number you entered is smaller or equal to the median one");
                Object[] temp=medianList.toArray();
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
                        median=array[i];
                    }
                }
            }
            System.out.println("After push the new number:");
            System.out.println(medianList);
        }
    }

    //pop out the median value from array
    int pop()
    {
        try {
            int index=medianList.indexOf(median);
            int disposalNumber;
            // get the index of the median and list the index
            System.out.println("The index of the  Median list is "+index);
            System.out.println("Before pop the array is:");
            System.out.println(medianList);

            // pop out the median
            disposalNumber = medianList.remove(medianList.indexOf(median));
            Object[] temp=medianList.toArray();
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
                    median=array[i];
                }
            }
            System.out.println("After pop the array is :");
            System.out.println(medianList);
            return disposalNumber;

        } catch (Exception e) {
            System.out.println("The array is empty.");
        }
        return 0;

    }

    //find median
    int peek()
    {
        return median;
    }

    //array to string
    public String toString ()
    {
        return  medianList.toString();
    }

    public  MedianList() {
        // get the user input of array
        medianList=new ArrayList<Integer>();
        System.out.println("Enter the array:");
        Scanner input= new Scanner(System.in);
        String line=input.nextLine();
        Scanner arrayinput = new Scanner(line);

        while (arrayinput.hasNextInt())
        {
            medianList.add(arrayinput.nextInt());
        }
        System.out.println("The following added:");

        System.out.print(medianList);

        // create temp array for future list
        Object[] temp=medianList.toArray();
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
                median=array[i];
            }
        }
        System.out.println("The median is "+median);
    }
}