import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
/*
public class Boat implements Serializable {

    private static Scanner sc;

    static void removeBoat(String memID) {
        //not working
        List<String> boats = getBoats();
        if (boats == null) {
            System.out.println("You haven't registered any boats.");
            return;
        }

        System.out.print("\nChoose boat: ");
        listBoats(boats, memID);
        int choice = sc.nextInt();
        Main.removeEntry(fileName, choice);
    }



    private static void listBoats(List<String> boats, String memID) {
        System.out.println("Current boats\n");
        int c = 1;
        for (String boat: boats) {
            if (boat.contains(memID)) {
                System.out.println(c + ". " + boat);
                c++;
            }
        }
    }

    static void changeBoatInfo(String memID) {
        // Not working
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

 */
