import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Login {

    static Path userFile = Paths.get("./userDB.csv");
    static Charset cs = StandardCharsets.UTF_8;

    public String userExists(String authID) {
        for (String member: getMembers()) {
            if (member.substring(0, member.indexOf(",")).equals(authID)) return member;
        }
        return null;
    }

    public ArrayList<String> getMembers() {
        try {
            List<String> lines = Files.readAllLines(userFile, cs);
            return new ArrayList<>(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getAuthenticatedPage() {
        // Work In Progress
        // we could move this to some other class if needed.
        System.out.println("1. register boat\n2. remove boat");
    }
}
