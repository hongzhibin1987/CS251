public class SingleFamilyRental extends RentalProperty {

    public SingleFamilyRental(String ID, String TypeofProperty, String PropertyID, int NumberofRoom, Double Rental){
        super (ID, TypeofProperty, PropertyID, NumberofRoom, Rental);
    }

    public double CalculateRentDue(){
        double amount = 0;
        if (super.getTypeofProperty().equals("S")){
            switch (super.getPropertyID()){
                case "SABQ138":
                    return (1400 * 0.04) + 1400;
                case "SABQ127":
                    return (900 * 0.04) + 900;
                case "SABQ126":
                    return (1200 * 0.04) + 1200;
            }
        }
        return amount;
    }
}
