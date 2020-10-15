package Model;

import java.util.Arrays;
import java.util.List;

public class UserModel {
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
