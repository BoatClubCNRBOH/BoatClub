package View;

import Model.UserModel;

import java.util.Scanner;

public class InterfaceView {

    /**
     * Prints the login/register screen
     * @return the users choice
     */
    public boolean askLoginOrRegistration() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Welcome to BoatClub!\n\n\t1. Login\n\t2. Register\n\t3. Exit\n\nSelect option above: ");
        String input = scan.nextLine();
        switch (input) {
            case "1":
                return true;
            case "2":
                return false;
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
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\nFull name: ");
        String fullName = scan.nextLine();
        System.out.print("\n\n(Has to be only numbers) Personal number: ");
        String personalNum = scan.nextLine();
        String memberId = UserModel.generateMemberID(UserModel.firstToUpper(fullName));
        return new String[]{memberId, "1", fullName, personalNum};
    }

    /**
     * Prints the login screen
     * @return the users choice
     */
    public String login() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter member ID: ");
        return scan.nextLine();
    }
}
