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

    private static final String fileName = "../boatDB.csv";
    private static final Path filePath = Paths.get(fileName);
    private static Scanner sc;

    static void addBoat(String memID) {
        //  Asks for details on boat, like length, and type, Type can also be the specified types said
        //  on requirements.
        sc = new Scanner(System.in);
        System.out.print("Boat type: ");
        String boatType = sc.nextLine();
        System.out.print("Boat length: ");
        String boatLength = sc.nextLine();
        Main.writeObject(new String[]{memID, boatType, boatLength}, fileName);
    }

    static void removeBoat(String memID) {
        List<String> boats = getBoats();
        if (boats == null) {
            System.out.println("You haven't registered any boats.");
            return;
        }
        System.out.println("Current boats\n");
        int c = 1;
        for (String boat: boats) {
            if (boat.contains(memID)) {
                System.out.println(c + ". " + boat);
                c++;
            }
        }
        System.out.print("\nChoose boat: ");
        int choice = sc.nextInt();
        Main.removeEntry(fileName, choice);
    }

    public static List<String> getBoats() {
        try {
            return Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static void changeBoatInfo(String memID) {
        // Same as change user info
        List<String> boats = getBoats();
        if (boats == null) {
            System.out.println("You haven't registered any boats.");
            return;
        }

        for (String boat: boats) {
            if (boat.contains(memID)) {
                System.out.println();
            }
        }
    }
}
