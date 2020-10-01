import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Authentication {

    /**
     * Prints the login/register screen
     * @return the users choice
     */
    public static boolean askLoginOrRegistration() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Welcome to BoatClub!\n\n\t1. Login\n\t2. Register\n\t3. Exit\n\nSelect option above: ");
        String input = scan.nextLine();
        scan.close();
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
     * Prints the login screen
     * @return the users choice
     */
    public static String login() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter member ID: ");
        return scan.nextLine();
    }

    /**
     * Prints the register screen
     * Prompts the user for a full name and a personal number
     * @return the users choice
     */
    public static String[] register () {
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\nFull name: ");
        String fullName = scan.nextLine();
        System.out.print("\n\n(Has to be only numbers) Personal number: ");
        String personalNum = scan.nextLine();
        String memberId = generateMemberID(firstToUpper(fullName));
        return new String[]{memberId, "1", fullName, personalNum};
    }

    /**
     * Converts the first letters in each part of a given string to uppercase
     * @param name the given input string
     * @return the formatted string
     */
    private static String firstToUpper(String name) {
        List<String> nameSplit = Arrays.asList(name.split(" "));
        StringBuilder result = new StringBuilder();
        nameSplit.forEach(p -> result.append(p.substring(0, 1).toUpperCase())
                .append(p, 1, p.length()).append(" "));
        return result.toString().trim();
    }

    /**
     * Generates a unique member ID which will be associated with a new user
     * @param fullName The new users full name
     * @return a random ID
     */
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
