import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RentalDue{
    ArrayList<RentalProperty> ApartmentArray = new ArrayList<>();
    ArrayList<RentalProperty> SingleFamilyArray = new ArrayList<>();
    public void FileImport(){
        File file = new File("src/rentalDB.txt");
        String ID;
        String TypeofProperty;
        String PropertyID;
        int NumberofRoom;
        Double Rental;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()){
                ID = sc.next();
                TypeofProperty = sc.next();
                PropertyID = sc.next();
                NumberofRoom = sc.nextInt();
                Rental = sc.nextDouble();
                if (TypeofProperty.equals("A")){
                    ApartmentArray.add(new ApartmentRental(ID, TypeofProperty, PropertyID, NumberofRoom, Rental));
                }

                if (TypeofProperty.equals("S")) {
                    SingleFamilyArray.add(new SingleFamilyRental(ID, TypeofProperty, PropertyID, NumberofRoom, Rental));
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.print("File does not exist, please double check...");
        }
    }

    public void SortArray(){
    Collections.sort(ApartmentArray);
    Collections.sort(SingleFamilyArray);
    }

    public void PrintArray(){
        System.out.println();
        System.out.println("Single-Family Rental Summary:");
        System.out.println("Apartment ID No    Rental Due");
        System.out.println("===============    ==========");
        for (int i = 0; i < ApartmentArray.size(); i++){
            System.out.print(ApartmentArray.get(i).getPropertyID() + "            " + ApartmentArray.get(i).CalculateRentDue());
            System.out.println();
        }
        System.out.println();
        System.out.println("Single-Family Rental Summary:");
        System.out.println("House ID Number    Rental Due");
        System.out.println("===============    ==========");
        for (int j = 0; j < SingleFamilyArray.size(); j++){
            System.out.print(SingleFamilyArray.get(j).getPropertyID() + "            "+ SingleFamilyArray.get(j).CalculateRentDue());
            System.out.println();
        }
    }
}
