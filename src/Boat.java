import java.io.Serializable;

public class Boat implements Serializable {
    private String boatType, boatOwner;
    private int boatLength;

    public Boat(String boatT, String boatO, int boatL) {
        boatType = boatT;
        boatLength = boatL;
        boatOwner = boatO;
    }

    public void setBoatType(String boatT) {
        boatType = boatT;
    }

    public String getBoatType() {
        return boatType;
    }

    public void setBoatOwner(String boatO) {
        boatOwner = boatO;
    }

    public String getBoatOwner() {
        return boatOwner;
    }

    public void setBoatLength(int boatL) {
        boatLength = boatL;
    }

    public int getBoatLength() {
        return boatLength;
    }

    private void addBoat() {

    }

    public void removeBoat() {

    }

    public void changeBoatInfo() {

    }

    public void showBoatInfo() {

    }
}
