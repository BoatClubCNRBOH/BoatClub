import java.io.Serializable;

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
}
