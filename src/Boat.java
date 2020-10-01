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

    static void addBoat(String memID) {
        //  Asks for details on boat, like length, and type, Type can also be the specified types said
        //  on requirements.
    }

    static void removeBoat(String memID) {
        //  Same as remove  user.
    }

    static void changeBoatInfo(String memID) {
        // Same as change user info
    }
}
