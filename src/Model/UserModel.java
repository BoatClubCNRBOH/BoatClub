package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserModel {
    private final Path pathFile = Paths.get("userDB.csv");
    private final Charset cs = StandardCharsets.UTF_8;
    private String name;
    private String persNum;
    private String memID;

    /**
     * Generates a unique member ID which will be associated with a new user
     * @param fullName The new users full name
     * @return a random ID
     */
    public String generateMemberID(String fullName) {
        String[] name = fullName.split(" ");
        String firstName = String.valueOf(name[0].charAt(0));
        String lastName = String.valueOf(name[1].charAt(0));
        int randomNum = (int)(Math.random()*9000) + 1000;
        String memberId = firstName + lastName + randomNum;
        return memberId;
    }

    /**
     * Converts the first letters in each part of a given string to uppercase
     * @param name the given input string
     * @return the formatted string
     */
    public String firstToUpper(String name) {
        List<String> nameSplit = Arrays.asList(name.split(" "));
        StringBuilder result = new StringBuilder();
        nameSplit.forEach(p -> result.append(p.substring(0, 1).toUpperCase())
                .append(p, 1, p.length()).append(" "));
        return result.toString().trim();
    }

    /**
    * Lets the user change information regarding themselves
    * @param memID the current users memberId which will be used to find the user in the db
    */
    public void changeInfo(String memID, String newInfo, int optionChoice) {
        //  Fetch user from db and keep as temp object here then remove from db
        //  then change ifo of class and then add back the object to db.
        try {
            List<String> lines = Files.readAllLines(pathFile, cs);
            new FileWriter("userDB.csv", false).close(); // add one to the end to be able to use the number as an index
            int option = optionChoice + 1;
            FileWriter fw = new FileWriter("userDB.csv");
            for (String line: lines) { // we check each line for a match with the member ID, then we ask the user enter the new info
                if (line.contains(memID)) {
                    String[] values = line.split(",");
                    values[option] = newInfo;
                    line = String.join(",", values); //reformat the line
                }
                fw.write(line + "\n"); // write each line
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * List a specific user by member ID
     * @param memID member id
     * @param boats list of boats
     * @param verbose boolean
     * @return String to be printed
     */
    public String listSpecificUser(String memID, List<String> boats, boolean verbose) {
        StringBuilder sb = new StringBuilder();
        try {
            boolean foundUser = false;
            for (String user : Files.readAllLines(pathFile, cs)) {
                if (user.contains(memID)) { // find user
                    foundUser = true;
                    sb.append(formatUser(user, boats, verbose)); // format user
                }
            }
            if (!foundUser) return "No user with that member ID was found.";
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a simple
     * @param boats list of all boats in the system
     * @return String
     */
    public String listUsers(List<String> boats, boolean verbose) {
        StringBuilder list = new StringBuilder();
        list.append("\n");
        try {
            for (String user: Files.readAllLines(pathFile, cs)) {
                list.append(formatUser(user, boats, verbose));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.toString();
    }

    /**
     * Formats the user information into a printable string
     * @param user unformatted user string
     * @param boats list of boats
     * @param verbose true for verbose, false for not
     * @return
     */
    private String formatUser(String user, List<String> boats, boolean verbose) {
        StringBuilder sb = new StringBuilder();
        // defining titles for each segment of the member entry
        String[] titles = {"Member ID: ", "Permission level: ", "Full name: ", "Personal number: "};
        // counter to use to grab titles from above array
        int counter = 0;
        // go through each segment of unformatted user string and format it with appropriate title
        for(String segment : user.split(",")) {
            sb.append(titles[counter]).append(segment).append("\n");
            counter++;
        }

        // counter to keep track of amount of boats for the current user
        int boatCounter = 0;
        for (String boat : boats) {
            // check if boat contains the member ID of the current user
            if (boat.contains(user.substring(0, user.indexOf(",")))) {
                // if the mode is set to verbose we format the boat string too and add it to the stringbuilder
                if (verbose) sb.append("Boat ").append(boatCounter).append(":\n").append(formatBoat(boat));
                boatCounter++;
            }
        }
        sb.append("Number of boats: ").append(boatCounter);
        return sb.append("\n").toString();
    }

    /**
     * Format boat entry
     * @param boat unformatted boat string
     * @return formatted string
     */
    private String formatBoat(String boat){
        StringBuilder sb = new StringBuilder();
        // defining titles for each segment of the boat entry
        String[] titles = {"Type: ", "Length: "};
        String[] boatSplit = boat.split(",");
        // for loop starts at index 1 to skip over member id part in boat string
        // (format is: memID,type,length)
        for(int i = 1; i < boatSplit.length; i++) {
            sb.append("\t").append(titles[i - 1]).append(boatSplit[i]).append("\n");
        }
        return sb.toString();
    }


    public void setName (String newName) {
        name = newName;
    }

    public String getName () {
        return name;
    }

    public void setPersNum (String persNumb) {
        persNum = persNumb;
    }

    public String getPersNum () {
        return persNum;
    }

    public void setMemID () {
        memID = generateMemberID(firstToUpper(getName()));
    }

    public String getMemID () {
        return memID;
    }

    public String[] makeUser() {
        return new String[]{getMemID(), "1", getName(), getPersNum()};
    }
}
