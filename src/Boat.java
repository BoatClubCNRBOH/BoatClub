import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Boat implements Serializable {
    private String boatType, boatOwner;
    private int boatLength;

    private static final String fileName = "boatDB.csv";
    private static final Path filePath = Paths.get(fileName);

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
        Scanner sc = new Scanner(System.in);
        System.out.print("Boat type: ");
        String boatType = sc.nextLine();
        System.out.println("Boat length: ");
        String boatLength = sc.nextLine();
        Main.writeObject(new String[]{memID, boatType, boatLength}, fileName);
    }

    static void removeBoat(String memID) {

    }

    static void changeBoatInfo(String memID) {
        // Same as change user info
    }
}
