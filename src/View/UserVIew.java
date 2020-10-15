package View;

import java.util.Scanner;

public class UserVIew {
    private String memID;
    public UserVIew(String memID) {
        this.memID = memID;
    }

    public void userInterface() {
        Scanner scan = new Scanner(System.in);
        String choice;
        System.out.print("\nMember Options\n\n\t1. Change Info\n\t2. Delete Member\n\t3. Show members (without boats)\n\t4. Show members (with boats)\n\t5. Go Back\n\nChoose: ");
        choice = scan.nextLine();
        switch (choice) {
            case "1":
                changeMenu("");
            case "2":

            case "3":

            case "4":

            case "5":
                break;
            default:
                System.out.println("Invalid Option");
                userInterface();
                break;
        }

    }
/**
 * Generates a menu which gives the user options on what to change
 * @param msg Header message
 * @return the option chosen
 */

    private String changeMenu(String msg) {
        System.out.println(msg);
        System.out.print("\n\n\t1. Change full name\n\t2. Change personal number\n\t3. Cancel\n\nChoose: ");
        Scanner sc = new Scanner(System.in);
        String res = sc.nextLine();
        if (!res.equals("1") && !res.equals("2") && !res.equals("3")) changeMenu("Invalid option");
        else if (res.equals("3")) userInterface();
        return res;
    }
}
