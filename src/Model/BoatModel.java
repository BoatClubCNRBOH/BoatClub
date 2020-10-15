package Model;

import View.BoatView;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BoatModel {

    private static final String fileName = "boatDB.csv";
    private static final Path filePath = Paths.get(fileName);
    private Charset cs = StandardCharsets.UTF_8;


    public enum BoatType {
        Sailboat, Motorsailer, KayakOrCanoe, Other
    }

    private BoatType boatType;
    private String boatLength;

    public List<String> getBoats() {
        try {
            return Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPersonalBoats(String memID) {
        StringBuilder sb = new StringBuilder();
        List<String> boats = getBoats();
        int numOfBoat = 1;
        for (String boat : boats) {
            if (boat.contains(memID)) {
                sb.append("\n").append("\t").append(numOfBoat).append(". ").append(boat);
                numOfBoat++;
            }
        }
        return sb.toString();
    }

    /**
     * Lets the user change information regarding themselves
     * @param memID the current users memberId which will be used to find the user in the db
     */
    public void changeInfo(String memID, String newInfo, int optionChoice, int boatChoice) {
        //  Fetch user from db and keep as temp object here then remove from db
        //  then change ifo of class and then add back the object to db.
        try {
            int currentBoat = 0;
            List<String> lines = Files.readAllLines(filePath, cs);
            new FileWriter("boatDB.csv", false).close(); // add one to the end to be able to use the number as an index
            FileWriter fw = new FileWriter("boatDB.csv");
            for (String line: lines) { // we check each line for a match with the member ID, then we ask the user enter the new info
                if (line.contains(memID)) {
                    currentBoat++;
                    if (currentBoat == boatChoice) {
                        String[] values = line.split(",");
                        values[optionChoice] = newInfo;
                        line = String.join(",", values); //reformat the line
                    }
                }
                fw.write(line); // write each line
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] makeBoat(String memID) {
        return new String[]{memID, String.valueOf(getBoatType()), getBoatLength()};
    }


    public String getBoatLength() {
        return boatLength;
    }

    public void setBoatLength(String boatLength) {
        this.boatLength = boatLength;
    }

    public BoatType getBoatType() {
        return boatType;
    }

    public void setBoatType(String boat) {
        if (boat.equals("1")) boatType = BoatType.Sailboat;
        else if (boat.equals("2")) boatType = BoatType.Motorsailer;
        else if (boat.equals("3")) boatType = BoatType.KayakOrCanoe;
        else if (boat.equals("4")) boatType = BoatType.Other;
    }
}
