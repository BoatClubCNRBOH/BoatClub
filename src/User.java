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
    }

    static void removeUser(String memID) {
        //  Check permission level or check if the member is trying to remove itself
        //  allow to remove itself but ask for confirmation, if perm level high they
        //  can remove anyone with confirmation
    }
}
