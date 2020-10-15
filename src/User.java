import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class User implements Serializable {
    /*
}




/*
    public static void listUsersSimple() {
        try {
            for (String user: Files.readAllLines(filePath, cs)) {
                System.out.println(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    public static void listUsersAndBoat() {
        try {
            for (String user: Files.readAllLines(filePath, cs)) {
                System.out.println(user);
                String memberID = user.substring(0, user.indexOf(","));
                List<String> boats = Boat.getBoats();
                assert boats != null;
                for (String boat : boats) {
                    if (boat.contains(memberID)) System.out.println("\t" + boat);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     */
}
