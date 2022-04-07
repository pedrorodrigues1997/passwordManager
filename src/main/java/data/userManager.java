package data;

import com.google.common.hash.Hashing;
import com.mongodb.DB;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class userManager {

    private static final Logger LOGGER = LogManager.getLogger(userManager.class);

    public userManager() {
    }

    public static User createNewUser(String username, String password, String email, String encryptionType, DB db) {
        String passwordHash = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        User newUser = new User(username, passwordHash, email, encryptionType);
        databaseOperations.addUsertoDb(username, passwordHash, email, encryptionType, db);
        return newUser;
    }

    //Authenticate/login


    //Edit Account


    //Delete Account

    public static void deleteUser(User user, DB db) {
        databaseOperations.removeUserFromDb(user.getUsername(), db);
        //This is deleting an account and should close the main menu
        //Deauthenticate
    }

    //add new Password combo

    //delete new Password combo

    //edit new password combo


    public static List<Pair<String, String>> getUserPasswords(User username, boolean isAuthenticated) {
        //get DB data from username. Data consists of every username and password this User has saved in DB


        return null;
    }
}
