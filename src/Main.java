import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String memID;
    public static void main(String[] args) {
        Login login = new Login();
        User user;
        boolean authenticated = false;
        while (true) {
            // if user is authenticated we generate another menu
            if (authenticated) {
                String option = login.getAuthenticatedPage();
                if (option.equals("Logout")) {
                    authenticated = false;
                    continue;
                }
                else if (option.equals("DelMem")) authenticated = false;
                callOptionsFunction(option);
            }
            //otherwise we ask the user to login or register
            else if (Authentication.askLoginOrRegistration()) {
                // ask user for member ID
                memID = Authentication.login();
                // compare member ID against registered users
                String member = login.userExists(memID);
                if (member != null) {
                    // display logged in screen
                    String[] params = member.split(",");
                    user = new User(params[0], Integer.parseInt(params[1]), params[2], params[3]);
                    authenticated = true;
                } else System.out.println("Authentication failed: member does not exist!");
            } else {
                String[] userData = Authentication.register();
                writeObject(userData, "userDB.csv");
            }
        }
    }

    /**
     * Redirects the user depending on their choices
     * @param option the option the user submitted
     */
    private static void callOptionsFunction(String option) {
        // Something like this to call the functions depending on what options the user chose...
        // Can change so attributes get changed with, it is not complete or working...
        switch (option) {
            case "BoatAdd":
                Boat.addBoat(memID);
                break;
            case "BoatRem":
                removeEntry("boatDB.csv");
                break;
            case "BoatEd":
                Boat.changeBoatInfo(memID);
                break;
            case "MemInf":
                User.changeInfo(memID);
                break;
            case "DelMem":
                removeEntry("userDB.csv");
                break;
        }
    }

    /**
     * Takes care of writing an object to a specified database
     * @param userOrBoat the object to be written to the db files
     * @param fileName
     */
    public static void writeObject(String[] userOrBoat, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write("\n");
            fw.write(String.join(",", userOrBoat));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Removes an entry from the given file which matches the current logged in member ID
     * @param fileName
     */
    public static void removeEntry(String fileName) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you sure? (y/n)");
        if (sc.nextLine().toUpperCase().equals("N")) return;
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            new FileWriter(fileName, false).close();
            FileWriter fw = new FileWriter(fileName);
            for (String line : lines) {
                if (!line.contains(memID)) fw.write(line);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readObject(String fileName) {
        try {
            // read objects from specified txt
            return new ObjectInputStream(
                    new FileInputStream(fileName)).readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
