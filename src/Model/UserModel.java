package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserModel {
    private Path pathFile = Paths.get("userDB.csv");
    private Charset cs = StandardCharsets.UTF_8;
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
                fw.write(line); // write each line
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lists users
     */
    public String listUsersSimple() {
        StringBuilder list = new StringBuilder();
        try {
            for (String user: Files.readAllLines(pathFile, cs)) {
                list.append(user).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.toString();
    }


    public String listUsersAndBoat(List<String> boats) {
        StringBuilder sb = new StringBuilder();
        try {
            for (String user : Files.readAllLines(pathFile, cs)) {
                sb.append(user);
                String memberID = user.substring(0, user.indexOf(","));
                assert boats != null;
                for (String boat : boats) {
                    if (boat.contains(memberID)) sb.append("\n").append("\t").append(boat);
                }
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        return  persNum;
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
