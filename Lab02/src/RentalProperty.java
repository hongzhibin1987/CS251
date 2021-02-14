import java.util.Collections;

public class RentalProperty implements Payment, Comparable<RentalProperty> {
    private String TypeofProperty;
    private String PropertyID;
    private double Rental;

    public RentalProperty(String ID, String TypeofProperty, String PropertyID, int NumberofRoom, Double Rental) {
        this.TypeofProperty = TypeofProperty;
        this.PropertyID = PropertyID;
        this.Rental = Rental = Rental;
    }

    public String getTypeofProperty(){
        return TypeofProperty;
    }

    public String getPropertyID(){
        return PropertyID;
    }

    public Double getRental(){
        return Rental;
    }

    @Override
    public double CalculateRentDue() {
        return 0;
    }

    @Override
    public int compareTo(RentalProperty otherRental) {
        //return otherRental.getPropertyID().compareTo(this.PropertyID);
        return this.PropertyID.compareTo(otherRental.getPropertyID());
    }
}
