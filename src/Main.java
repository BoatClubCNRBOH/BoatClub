import java.io.*;

public class Main {
    private static String memID;
    public static void main(String[] args) {
        Login login = new Login();
        User user;
//        boolean continuing = true;
        while (true) {
            if (Authentication.askLoginOrRegistration()) {
                // ask user for member ID
                memID = Authentication.login();
                // compare member ID against registered users
                String member = login.userExists(memID);
                if (member != null) {
                    // display logged in screen
                    String[] params = member.split(",");
                    user = new User(params[0], Integer.parseInt(params[1]), params[2], params[3]);
                    String option = login.getAuthenticatedPage();
                    callOptionsFunction(option);
                    break;
                } else System.out.println("Authentication failed");
            } else {
                String[] userData = Authentication.register();
                writeObject(userData, "userDB.csv");
            }
        }
    }

    private static void callOptionsFunction(String option) {
        // Something like this to call the functions depending on what options the user chose...
        // Can change so attributes get changed with, it is not complete or working...
        if (option.equals("BoatAdd")) Boat.addBoat(memID);
        else if (option.equals("BoatRem")) Boat.removeBoat(memID);
        else if (option.equals("BoatEd")) Boat.changeBoatInfo(memID);
        else if (option.equals("MemInf")) User.changeInfo(memID);
        else if (option.equals("DelMem")) User.removeUser(memID);
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
