package Controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AuthenticationController {
    private final Path userFile = Paths.get("userDB.csv");
    private final Charset cs = StandardCharsets.UTF_8;

    public String checkUser(String memID) {
        for (String member: Objects.requireNonNull(getMembers())) {
            if (member.substring(0, member.indexOf(",")).equals(memID)) return memID;
        }
        // no user found
        return null;
    }

    /**
     * Gets all the members from the database
     * @return ArrayList of members
     */
    private ArrayList<String> getMembers() {
        try {
            List<String> lines = Files.readAllLines(userFile, cs);
            return new ArrayList<>(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("No members to show!");
        return null;
    }
}
