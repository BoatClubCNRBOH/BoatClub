package Model;

import View.BoatView;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BoatModel {

    private static final String fileName = "boatDB.csv";
    private static final Path filePath = Paths.get(fileName);


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
