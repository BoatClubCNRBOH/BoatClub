import java.util.Scanner;

public class Authentication {

    public static boolean askLoginOrRegistration() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Welcome to BoatClub!\n\n\t1. Login\n\t2. Register\n\t3. Exit\n\nSelect option above: ");
        String input = scan.nextLine();
        if (input.equals("1"))
            return true;
        else if (input.equals("2"))
            return false;
        else if (input.equals("3"))
            System.exit(1);
        else
            System.out.println("\n\nInvalid input try again!\n\n");
            askLoginOrRegistration();
        return true;
    }

    public static void login() {

    }

    private static void invalidLogin() {

    }

    public static String register () {
        String personalNum;
        String fullName;
        String memberId;
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\nFull name: ");
        fullName = scan.nextLine();
        System.out.print("\n\n(Has to be only numbers) Personal number: ");
        personalNum = scan.nextLine();
        memberId = generateMemberID(fullName);
        return memberId + " " +  fullName + " " + personalNum;
    }

    private static String generateMemberID(String fullName) {
        String[] name = fullName.split(" ");
        String firstName = String.valueOf(name[0].charAt(0));
        String lastName = String.valueOf(name[1].charAt(0));
        int randomNum = (int)(Math.random()*9000) + 1000;
        String memberId = firstName + lastName + randomNum;
        return memberId;
    }


}
