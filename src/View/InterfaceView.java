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
                else getAuthenticatedPage();
            case "2":
                String[] newUser = register();
                db.writeObject(newUser, "userDB.csv");
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
    public String getAuthenticatedPage() {
        UserVIew view = new UserVIew(memID);
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\t1. Member Options\n\t2. Boat Options\n\t3. Logout\n\nChoose option: ");
        String choice = scan.nextLine();
        if (choice.equals("1")) {
            view.userInterface();
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
            askLoginOrRegistration();
        } else {
            System.out.println("\nInvalid input try again!");
            getAuthenticatedPage();
        }
        return "No Option";
    }
}
