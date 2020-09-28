import java.util.Scanner;

public class Authentication {

    public static boolean askLoginOrRegistration() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Welcome to BoatClub!\n\n\t1. Login\n\t2. Register\n\t3. Exit\n\nSelect option above: ");
        String input = scan.nextLine();
        if (input.equals("1")) login();
        else if (input.equals("2")) register();
        else if (input.equals("3")) return false;
        else
            System.out.println("\n\nInvalid input try again!\n\n");
            askLoginOrRegistration();
        return true;
    }

    private static void login() {

    }

    public static void invalidLogin() {

    }

    public static void register () {

    }


}
