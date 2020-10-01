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

    private static final String fileName = "boatDB.csv";
    private static final Path filePath = Paths.get(fileName);
    private static Scanner sc;

    static void addBoat(String memID) {
        //  Asks for details on boat, like length, and type, Type can also be the specified types said
        //  on requirements.
        sc = new Scanner(System.in);
        System.out.print("Boat type: ");
        String boatType = sc.nextLine();
        System.out.println("Boat length: ");
        String boatLength = sc.nextLine();
        Main.writeObject(new String[]{memID, boatType, boatLength}, fileName);
    }

    static void removeBoat(String memID) {
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            System.out.println("Current boats\n\n");
            int i = 1;
            for (String boat : lines) {
                System.out.println("\t" + i + ". " + boat);
                i++;
            }
            System.out.println("\nChoose boat: ");
            String choice = sc.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void changeBoatInfo(String memID) {
        // Same as change user info
    }
}
