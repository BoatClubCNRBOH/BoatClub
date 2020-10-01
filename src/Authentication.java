import java.util.Scanner;

public class Authentication {

    public static boolean askLoginOrRegistration() {
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

    public static String login() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter member ID: ");
        return scan.nextLine();
    }

    public static String[] register () {
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\nFull name: ");
        String fullName = scan.nextLine();
        System.out.print("\n\n(Has to be only numbers) Personal number: ");
        String personalNum = scan.nextLine();
        String memberId = generateMemberID(fullName);
        return new String[]{memberId, "1", fullName, personalNum};
    }

    private static String generateMemberID(String fullName) {
        String[] name = fullName.split(" ");
        String firstName = String.valueOf(name[0].charAt(0));
        String lastName = String.valueOf(name[1].charAt(0));
        int randomNum = (int)(Math.random()*9000) + 1000;
        String memberId = firstName + lastName + randomNum;
        System.out.println("Your member ID is: " + memberId);
        return memberId;
    }
}
