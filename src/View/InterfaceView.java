package View;

import Controller.AuthenticationController;
import Controller.DatabaseControll;
import Model.UserModel;

import java.util.Scanner;

public class InterfaceView {
    private String memID;

    /**
     * Prints the login/register screen
     * @return the users choice
     */
    public boolean askLoginOrRegistration() {
        DatabaseControll db = new DatabaseControll();
        Scanner scan = new Scanner(System.in);
        System.out.print("Welcome to BoatClub!\n\n\t1. Login\n\t2. Register\n\t3. Exit\n\nSelect option above: ");
        String input = scan.nextLine();
        switch (input) {
            case "1":
                memID = login();
                if (memID == null) askLoginOrRegistration();
                else getAuthenticatedPage(memID);
            case "2":
                db.writeObject(register(), "userDB.csv");
                askLoginOrRegistration();
            case "3":
                System.exit(1);
            default:
                System.out.println("\n\nInvalid input try again!\n\n");
                askLoginOrRegistration();
                break;
        }
        return true;
    }

    /**
     * Prints the register screen
     * Prompts the user for a full name and a personal number
     * @return the users choice
     */
    public String[] register () {
        UserModel user = new UserModel();
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\nFull name: ");
        user.setName(scan.nextLine());
        System.out.print("\n\n(Has to be only numbers) Personal number: ");
        user.setPersNum(scan.nextLine());
        user.setMemID();
        System.out.println("Your member ID is: " + user.getMemID());
        return user.makeUser();
    }

    /**
     * Prints the login screen
     * @return the users choice
     */
    public String login() {
        AuthenticationController controll = new AuthenticationController();
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter member ID: ");
        return controll.checkUser(scan.nextLine());

    }

    /**
     * Prints authenticated/logged in page
     * @return the users choice from the menu
     */
    public String getAuthenticatedPage(String memID) {
        UserVIew view = new UserVIew(memID);
        BoatView boat = new BoatView();
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\t1. Member Options\n\t2. Boat Options\n\t3. Logout\n\nChoose option: ");
        String choice = scan.nextLine();
        if (choice.equals("1")) {
            view.userInterface();
        } else if (choice.equals("2")) {
            boat.boatInterface(memID);
        } else if (choice.equals("3")){
            askLoginOrRegistration();
        } else {
            System.out.println("\nInvalid input try again!");
            getAuthenticatedPage(memID);
        }
        return "No Option";
    }
}
