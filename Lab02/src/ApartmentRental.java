public class ApartmentRental extends RentalProperty {

    public ApartmentRental(String ID, String TypeofProperty, String PropertyID, int NumberofRoom, Double Rental) {
        super (ID, TypeofProperty, PropertyID, NumberofRoom, Rental);
    }

    public double CalculateRentDue(){
        double amount = 0;
        if (super.getTypeofProperty().equals("A")){
            if (super.getRental() == 600){
                return 600 * 1.04;
            }
            else if (super.getRental() == 850){
                return 850 * 1.04;
            }
            else if (super.getRental() == 900){
                return 900 * 1.04;
            }
        }
        return amount;
    }
}

/*
An alternative. longer.
public class ApartmentRental extends RentalProperty {

    public ApartmentRental(String ID, String TypeofProperty, String PropertyID, int NumberofRoom, Double Rental) {
        super(ID, TypeofProperty, PropertyID, NumberofRoom, Rental);
    }

    public double CalculateRentDue(){
        double amount = 0;
        if (super.getTypeofProperty().equals("A")){
            switch (super.getPropertyID()){
                case "AABQ205":
                    return (900 * 0.04) + 900;
                case "AABQ302":
                    return (850 * 0.04) + 850;
                case "AABQ201":
                    return (600 * 0.04) + 600;
            }
        }
        return amount;
    }
}

 */