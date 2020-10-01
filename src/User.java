import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User implements Serializable {
    private String memberID;
    private int permissionLevel;
    private String fullName;
    private String personalNumber;

    public User(String memID, int permLvl, String fullN, String personalNum){
        memberID = memID;
        permissionLevel = permLvl;
        fullName = fullN;
        personalNumber = personalNum;
    }

    public User(){}

    public void setName(String fName) {
        fullName = fName;
    }

    public String getName() {
        return fullName;
    }

    public void setMemberID(String memID) {
        memberID = memID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setPersonalNumber(String persNum) {
        personalNumber = persNum;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPermissionLevel(int permLvl) {
        permissionLevel = permLvl;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    static void changeInfo(String memID) {
        //  Fetch user from db and keep as temp object here then remove from db
        //  then change ifo of class and then add back the object to db.
        try {
            List<String> lines = Files.readAllLines(Paths.get("./userDB.csv"), StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String changeMenu() {
        int numberOfOptions = 3;
        System.out.println("\n\n1. Change full name\n2. Change personal number\n");
        if (permissionLevel == 1) {
            System.out.println("3. Change permission level");
            System.out.println("4. Cancel");
            numberOfOptions = 4;
        } else System.out.println("3. Cancel");
        System.out.println("\n\n");
        Scanner sc = new Scanner(System.in);
        String res = sc.nextLine();
        if (res.equals("1")) {

        } else if (res.equals("2")) {

        } else if (res.equals("3")) {
            if (numberOfOptions == 3) {

            } else {

            }
        } else {

        }
        return null;
    }

    static void removeUser(String memID) {
        //  Check permission level or check if the member is trying to remove itself
        //  allow to remove itself but ask for confirmation, if perm level high they
        //  can remove anyone with confirmation
    }
}
