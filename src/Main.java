public class Main {
    public static void main(String[] args) {
        boolean continuing = true;
        while (continuing) {
            continuing = Authentication.askLoginOrRegistration();
        }
    }
}
