package View;

import Controller.DatabaseControll;
import Model.BoatModel;
import Model.UserModel;

import java.util.Scanner;

public class UserVIew {
    UserModel userModel = new UserModel();
    DatabaseControll remove = new DatabaseControll();
    BoatModel boat = new BoatModel();
    InterfaceView mainMenu = new InterfaceView();

    private String memID;
    public UserVIew(String memID) {
        this.memID = memID;
    }

    public void userInterface() {
        Scanner scan = new Scanner(System.in);
        String choice;
        System.out.print("\nMember Options\n\n\t1. Change Info\n\t2. Delete Member\n\t3. Show members (without boats)\n\t4. Show members (with boats)\n\t5. Show specific member\n\t6. Go Back\n\nChoose: ");
        choice = scan.nextLine();
        switch (choice) {
            case "1":
                changeMenu();
            case "2":
                System.out.print("\nAre you sure you want to delete your user? (Y/N): ");
                if (scan.nextLine().toUpperCase().equals("Y")) {
                    remove.removeEntry("userDB.csv", 0, memID);
                    mainMenu.askLoginOrRegistration();
                } else userInterface();
            case "3":
                System.out.println(userModel.listUsers(boat.getBoats(), false));
                System.out.print("Go back with any input: ");
                if (scan.nextLine() != null) userInterface();
            case "4":
                System.out.println(userModel.listUsers(boat.getBoats(), true));
                System.out.print("Go back with any input: ");
                if (scan.nextLine() != null) userInterface();
            case "5":
                System.out.print("Enter member ID of the user you want to show: ");
                System.out.println(userModel.listSpecificUser(scan.nextLine(), boat.getBoats(), true));
                System.out.print("Go back with any input: ");
                if (scan.nextLine() != null) userInterface();
            case "6":
                mainMenu.getAuthenticatedPage(memID);
            default:
                System.out.println("Invalid Option");
                userInterface();
                break;
        }

    }

    /**
    * Generates a menu which gives the user options on what to change
    * @return the option chosen
    */
    private String changeMenu() {
        System.out.print("\n\n\t1. Change full name\n\t2. Change personal number\n\t3. Cancel\n\nChoose: ");
        Scanner sc = new Scanner(System.in);
        String res = sc.nextLine();
        if (!res.equals("1") && !res.equals("2") && !res.equals("3")) {
            System.out.println("Invalid");
            changeMenu();
        } else if (res.equals("3")) {
            userInterface();
        } else {
            System.out.println("\nNew value: ");
            userModel.changeInfo(memID, sc.nextLine(), Integer.parseInt(res));
            changeMenu();
        }
        return res;
    }
}
