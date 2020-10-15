import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*
}
    private static String memID;
    public static void main(String[] args) {
        boolean continuing = true;
        while (continuing) {
            boolean registerOrLogin = Authentication.askLoginOrRegistration();
            if (registerOrLogin) {
                String memberId = Authentication.login();
            } else if (!registerOrLogin) {
                String user = Authentication.register();
                String[] userNew = user.split(" ");
                writeObject(userNew);
        Login login = new Login();
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
/*
    private static void callOptionsFunction (String option) {
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


 */
}

