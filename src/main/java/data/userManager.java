package data;

import com.google.common.hash.Hashing;
import com.mongodb.DB;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class userManager {

    private static final Logger LOGGER = LogManager.getLogger(userManager.class);

    public userManager() {
    }

    public static User createNewUser(String username, String password, String email, String encryptionType, DB db) {
        //CHECK IF IT ALREAD EXISTS

        //CHECK PASSWORD PARAMETERs -- NEW METHOD


        String passwordHash = getPasswordHash(password);
        User newUser = new User(username, passwordHash, email, encryptionType);
        databaseOperations.addUsertoDb(username, passwordHash, email, encryptionType, db);
        return newUser;
    }



    //Authenticate/login


    //Edit Account
    public static void editUserField(User user, String newParameter, UserField fieldToChange , DB db) {
        switch (fieldToChange){
            case username:
            case password:
            case encryptionType:
                databaseOperations.editOneParameterDb(user.getUsername(), newParameter, fieldToChange, db);
                break;
            case email:
                databaseOperations.editOneParameterDb(user.getEmail(), newParameter, fieldToChange, db);
                break;
        }
    }



        //Delete Account

    public static void deleteUser(User user, DB db) {
        databaseOperations.removeUserFromDb(user.getUsername(), db);
        //This is deleting an account and should close the main menu
        //Deauthenticate
    }

    //add new Password combo
    public static void addNewPasswordToList(User user, String accountName, String username, String password, DB db) {
        //Encrypt Password


        databaseOperations.editAddToPasswordListDb(user.getUsername(), username, accountName, password, db);
        getUserPasswords(user, true, db);
    }
    //delete new Password combo
    public static void deletePasswordFromList(User user, String accountName, DB db) {

        databaseOperations.editRemoveFromPasswordListDb(user.getUsername(), accountName, db);
        getUserPasswords(user, true, db);
    }
    //edit new password combo


    public static HashMap<String, Pair<String, String>> getUserPasswords(User user, boolean isAuthenticated, DB db) {
        //get DB data from username. Data consists of every username and password this User has saved in DB
        HashMap<String, Pair<String, String>> passwordList = new HashMap<>();
        passwordList = databaseOperations.getPasswordList(user.getUsername(), db);
        if(passwordList.isEmpty()){
            LOGGER.debug("No passwords saved for user {}", user.getUsername());
            return null;
        }
        //Decrypt the password that come encrypted

        //Set passwordList in User


        return passwordList;
    }

    private static String getPasswordHash(String password) {
        String passwordHash = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        return passwordHash;
    }
}
