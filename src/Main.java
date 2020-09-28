public class Main {
    public static void main(String[] args) {
        boolean continueing = true;
        while (continueing) {
            continueing = Authentication.askLoginOrRegistration();
        }
    }
}
