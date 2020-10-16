package Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DatabaseControll {
    /**
    * Takes care of writing an object to a specified database
    * @param userOrBoat the object to be written to the db files
    * @param fileName
    */
    public void writeObject(String[] userOrBoat, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(String.join(",", userOrBoat) + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Removes an entry from the given file which matches the current logged in member ID
    * @param fileName
    */
    public void removeEntry(String fileName, int entryIndex, String memID) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            new FileWriter(fileName, false).close();
            FileWriter fw = new FileWriter(fileName);
            if (entryIndex == 0) {
                for (String user : lines) {
                    String[] userString = user.split(",");
                    if (!user.contains(memID)) writeObject(userString, "./userDB.csv");
                }
                lines = Files.readAllLines(Paths.get("./boatDB.csv"), StandardCharsets.UTF_8);
                new FileWriter("./boatDB.csv", false).close();
                for (String boat : lines) {
                    String[] boatString = boat.split(",");
                    if (!boat.contains(memID)) {
                        writeObject(boatString, "./boatDB.csv");
                    }
                }
            } else {
                int check = 0;
                for (String boat : lines) {
                    String[] boatString = boat.split(",");
                    if (check == entryIndex - 1) {
                        writeObject(boatString, "./boatDB.csv");
                    } else if (boat.contains(memID)) check++;
                }

            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
