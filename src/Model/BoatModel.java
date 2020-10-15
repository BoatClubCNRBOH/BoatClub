package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BoatModel {

    private static final String fileName = "boatDB.csv";
    private static final Path filePath = Paths.get(fileName);
    private Charset cs = StandardCharsets.UTF_8;


    public enum BoatType {
        Sailboat, Motorsailer, KayakOrCanoe, Other
    }

    private BoatType boatType;
    private String boatLength;

    /**
     * Gets all the boats from the database
     * @return List of boats
     */
    public List<String> getBoats() {
        try {
            return Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets a list of the users boats from the total boat selection
     * @param memID user member ID
     * @return list of personal boats
     */
    public List<String> getPersonalBoats(String memID) {
        List<String> boats = getBoats();
        List<String> userBoats = new ArrayList<>();
        for (String boat : boats) {
            if (boat.contains(memID)) {
                userBoats.add(boat);
            }
        }
        return userBoats;
    }

    /**
     * Generates a printable string of the user's boat
     * @param memID user member ID
     * @return string of boats
     */
    public String personalBoatsToString(String memID) {
        List<String> boats = getPersonalBoats(memID);
        StringBuilder sb = new StringBuilder();
        AtomicInteger boatIndex = new AtomicInteger(1);
        boats.forEach(boat -> {
            sb.append(boatIndex.get()).append(". ").append(boat).append("\n");
            boatIndex.getAndIncrement();
        });
        return sb.toString();
    }

    /**
     * Removes a selected boat from the database
     * @param memID member ID
     * @param choice the user choice of boat
     */
    public boolean deleteBoat(String memID, String choice) {
        List<String> allBoats = getBoats();
        List<String> userBoats = getPersonalBoats(memID);
        try {
            new FileWriter("boatDB.csv", false).close();
            FileWriter fw = new FileWriter("boatDB.csv");
            for(String boat: allBoats) {
                if (!boat.equals(userBoats.get(Integer.parseInt(choice) - 1))) {
                    fw.write(boat + "\n");
                }
            };
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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
                fw.write(line + "\n"); // write each line
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
