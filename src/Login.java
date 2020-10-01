import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class Login {

    private static final Path userFile = Paths.get("./userDB.csv");
    private static final Charset cs = StandardCharsets.UTF_8;

    String userExists(String authID) {
        for (String member: Objects.requireNonNull(getMembers())) {
            if (member.substring(0, member.indexOf(",")).equals(authID)) return member;
        }
        // no user found
        return null;
    }

    /**
     * Gets all the members from the database
     * @return ArrayList of members
     */
    private ArrayList<String> getMembers() {
        try {
            List<String> lines = Files.readAllLines(userFile, cs);
            return new ArrayList<>(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("No members to show!");
        return null;
    }

    /**
     * Prints authenticated/logged in page
     * @return the users choice from the menu
     */
    public String getAuthenticatedPage() {
        // Work In Progress
        // we could move this to some other class if needed.
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\t1. Member Options\n\t2. Boat Options\n\t3. Logout\n\nChoose option: ");
        String choice = scan.nextLine();
        if (choice.equals("1")) {
            System.out.print("\nMember Options\n\n\t1. Change Info\n\t2. Delete Member\n\t3. Go Back\n\nChoose: ");
            choice = scan.nextLine();
            if (choice.equals("1")) return "MemInf";
            else if (choice.equals("2")) return "DelMem";
            else
                System.out.println("Invalid Option");
                getAuthenticatedPage();
        } else if (choice.equals("2")) {
            System.out.print("\nBoat Options\n\n\t1. Add Boat\n\t2. Edit Boat\n\t3. Remove Boat\n\t4. Go Back\n\nChoose: ");
            choice = scan.nextLine();
            switch (choice) {
                case "1":
                    return "BoatAdd";
                case "2":
                    return "BoatEd";
                case "3":
                    return "BoatRem";
                default:
                    System.out.println("Invalid Option");
                    getAuthenticatedPage();
                    break;
            }
        } else if (choice.equals("3")){
            return "Logout";
        } else {
            System.out.println("\nInvalid input try again!");
            getAuthenticatedPage();
        }
        return "No Option";
    }
}
