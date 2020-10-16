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
                    if (!user.contains(memID)) fw.write(user);
                }
                new FileWriter("./boatDB.csv", false).close();
                for (String boat : lines) {
                    if (boat.contains(memID)) {
                        fw.write(boat);
                    }
                }
            } else {
                int check = 0;
                for (String boat : lines) {
                    if (check == entryIndex - 1) {
                        fw.write(boat);
                    } else if (boat.contains(memID)) check++;
                }

            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
