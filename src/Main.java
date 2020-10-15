import java.io.*;

public class Main {
    public static void main(String[] args) {
        boolean continuing = true;
        while (continuing) {
            boolean registerOrLogin = Authentication.askLoginOrRegistration();
            if (registerOrLogin) {
                String memberId = Authentication.login();
            } else if (!registerOrLogin) {
                String user = Authentication.register();
                String[] userNew = user.split(" ");
                User newUser = new User(userNew[0], 1, userNew[1], userNew[2]);
                writeObject(newUser);
            }
        }
    }

    private static void writeObject(Object userOrBoat) {
        try {
            FileOutputStream fileOut = new FileOutputStream("storing.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(userOrBoat);
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object readObject() {
        try {
            FileInputStream fileIn = new FileInputStream("storing.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
