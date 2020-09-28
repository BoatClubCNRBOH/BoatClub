import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        boolean continuing = true;
        File fileToWrite = new File("storing.txt");
        try {
            fileToWrite.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (continuing) {
            continuing = Authentication.askLoginOrRegistration();
        }
    }
}
