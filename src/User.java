import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            new FileWriter("userDB.csv", false).close();
            // add one to the end to be able to use the number as an index
            int option = Integer.parseInt(changeMenu("Select option")) + 1;
            FileWriter fw = new FileWriter("userDB.csv");
            for (String line: lines) {
                if (line.contains(memID)) {
                    String[] values = line.split(",");
                    System.out.print("Enter your new value: ");
                    Scanner sc = new Scanner(System.in);
                    values[option] = sc.nextLine();
                    line = String.join(",", values);
                }

                fw.write(line);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String changeMenu(String msg) {
        System.out.println(msg);
        System.out.print("\n\n\t1. Change full name\n\t2. Change personal number\n\t3. Cancel\n\nChoose: ");
        Scanner sc = new Scanner(System.in);
        String res = sc.nextLine();
        if (!res.equals("1") && !res.equals("2") && !res.equals("3")) changeMenu("Invalid option");
        else if (res.equals("3")) changeMenu("Select option");
        return res;
    }

    static void removeUser(String memID) {
        //  Check permission level or check if the member is trying to remove itself
        //  allow to remove itself but ask for confirmation, if perm level high they
        //  can remove anyone with confirmation
        try {
            List<String> lines = Files.readAllLines(Paths.get("./userDB.csv"), StandardCharsets.UTF_8);
            new FileWriter("userDB.csv", false).close();
            FileWriter fw = new FileWriter("userDB.csv");
            for (String line : lines) {
                if (!line.contains(memID)) fw.write(line);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
