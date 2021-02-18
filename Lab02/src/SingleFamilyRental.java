public class SingleFamilyRental extends RentalProperty {

    public SingleFamilyRental(String ID, String TypeofProperty, String PropertyID, int NumberofRoom, Double Rental){
        super (ID, TypeofProperty, PropertyID, NumberofRoom, Rental);
    }

    public double CalculateRentDue(){
        double amount = 0;
        if (super.getTypeofProperty().equals("S")){
            if (super.getRental() == 900){
                return 900 * 1.04;
            }
            else if (super.getRental() == 1200){
                return 1200 * 1.04;
            }
            else if (super.getRental() == 1400){
                return 1400 * 1.04;
            }
        }
        return amount;
    }
}