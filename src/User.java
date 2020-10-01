import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class User implements Serializable {
    private static final Path filePath = Paths.get("../userDB.csv");
    private static final Charset cs = StandardCharsets.UTF_8;

    /**
     * Lets the user change information regarding themselves
     * @param memID the current users memberId which will be used to find the user in the db
     */
    static void changeInfo(String memID) {
        //  Fetch user from db and keep as temp object here then remove from db
        //  then change ifo of class and then add back the object to db.
        try {
            List<String> lines = Files.readAllLines(filePath, cs);
            new FileWriter("userDB.csv", false).close();
            // add one to the end to be able to use the number as an index
            int option = Integer.parseInt(changeMenu("Select option")) + 1;
            FileWriter fw = new FileWriter("userDB.csv");
            for (String line: lines) {
                // we check each line for a match with the member ID
                // then we ask the user enter the new info
                if (line.contains(memID)) {
                    String[] values = line.split(",");
                    System.out.print("Enter your new value: ");
                    Scanner sc = new Scanner(System.in);
                    values[option] = sc.nextLine();
                    //reformat the line
                    line = String.join(",", values);
                }
                // write each line
                fw.write(line);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a menu which gives the user options on what to change
     * @param msg Header message
     * @return the option chosen
     */
    private static String changeMenu(String msg) {
        System.out.println(msg);
        System.out.print("\n\n\t1. Change full name\n\t2. Change personal number\n\t3. Cancel\n\nChoose: ");
        Scanner sc = new Scanner(System.in);
        String res = sc.nextLine();
        if (!res.equals("1") && !res.equals("2") && !res.equals("3")) changeMenu("Invalid option");
        else if (res.equals("3")) changeMenu("Select option");
        return res;
    }

    public static void listUsersSimple() {
        try {
            for (String user: Files.readAllLines(filePath, cs)) {
                System.out.println(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void listUsersAndBoat() {
        try {
            for (String user: Files.readAllLines(filePath, cs)) {
                System.out.println(user);
                String memberID = user.substring(0, user.indexOf(","));
                List<String> boats = Boat.getBoats();
                assert boats != null;
                for (String boat : boats) {
                    if (boat.contains(memberID)) System.out.println("\t" + boat);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
