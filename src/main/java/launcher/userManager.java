package launcher;

import com.google.common.hash.Hashing;
import data.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

public class userManager{

    private static final Logger LOGGER = LogManager.getLogger(userManager.class);

    public userManager() {
    }

    public static User createNewUser(String username, String password, String email, String encryptionType){
        String passwordHash = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        User newUser = new User(username, passwordHash, email, encryptionType);

        LOGGER.info("New User Created ----- Username: {}", newUser.getUsername());
        return newUser;
    }
}
