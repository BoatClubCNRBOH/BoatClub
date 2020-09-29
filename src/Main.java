import java.io.*;

public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        User user;
//        boolean continuing = true;
        while (true) {
            if (Authentication.askLoginOrRegistration()) {
                // ask user for member ID
                String memberID = Authentication.login();
                // compare member ID against registered users
                String member = login.userExists(memberID);
                if (member != null) {
                    // display logged in screen
                    String[] params = member.split(",");
                    user = new User(params[0], Integer.parseInt(params[1]), params[2], params[3]);
                    login.getAuthenticatedPage();
                    break;
                } else System.out.println("Authentication failed");
            } else {
                String[] userData = Authentication.register();
                writeObject(userData, "userDB.csv");
            }
        }
    }
    private static void writeObject(String[] userOrBoat, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(String.join(",", userOrBoat));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readObject(String fileName) {
        try {
            // read objects from specified txt
            return new ObjectInputStream(
                    new FileInputStream(fileName)).readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
