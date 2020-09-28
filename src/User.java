public class User {
    private String memberID;
    private int permissionLevel;
    private String fullName;
    private int personalNumber;

    public User(String memID, int permLvl, String fullN, int personalNum){
        memberID = memID;
        permissionLevel = permLvl;
        fullName = fullN;
        personalNumber = personalNum;
    }

    public void deleteMember(int permissionLevel) {

    }

    public void changeInformation() {

    }

    public String getMemberID() {
        return memberID;
    }

    public void listMembers(int permissionLevel) {

    }
}
