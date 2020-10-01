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
        boolean authenticated = false;
        System.out.println(System.getProperty("user.dir"));
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
                    authenticated = true;
                } else System.out.println("Authentication failed: member does not exist!");
            } else {
                String[] userData = Authentication.register();
                writeObject(userData, "../userDB.csv");
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
                Boat.removeBoat(memID);
                break;
            case "BoatEd":
                Boat.changeBoatInfo(memID);
                break;
            case "MemInf":
                User.changeInfo(memID);
                break;
            case "DelMem":
                removeEntry("../userDB.csv", 0);
                break;
            case "ListSimple":
                User.listUsersSimple();
                break;
            case "ListAdv":
                User.listUsersAndBoat();
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
            FileWriter fw = new FileWriter(fileName);
            fw.write(String.join(",", userOrBoat));
//            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Removes an entry from the given file which matches the current logged in member ID
     * @param fileName
     */
    public static void removeEntry(String fileName, int boatToDelete) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Are you sure? (y/n): ");
        if (sc.nextLine().toUpperCase().equals("N")) return;
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            new FileWriter(fileName, false).close();
            FileWriter fw = new FileWriter(fileName);
            if (boatToDelete == 0) {
                for (String user : lines) {
                    if (!user.contains(memID)) fw.write(user);
                }
            } else {
                int check = 0;
                for (String boat : lines) {
                    if (check == boatToDelete - 1) {
                        fw.write(boat);
                    } else if (boat.contains(memID)) check++;
                }

            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
