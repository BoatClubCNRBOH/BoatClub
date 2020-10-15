package View;

public class BoatView {
    public enum BoatType {
        Sailboat, Motorsailer, Canoe, Other
    }
    private BoatType boatType;
    private String boatLength;
    private String boat;


    public String getBoat() {
        return boat;
    }

    public void setBoat(String boat) {
        this.boat = boat;
    }

    public String getBoatLength() {
        return boatLength;
    }

    public void setBoatLength(String boatLength) {
        this.boatLength = boatLength;
    }

    public BoatType getBoatType() {
        return boatType;
    }

    public void setBoatType(BoatType boatType) {
        this.boatType = boatType;
    }
}
