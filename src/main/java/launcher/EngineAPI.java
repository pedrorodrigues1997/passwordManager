package launcher;

import com.mongodb.DB;
import data.User;
import data.UserField;
import data.userManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EngineAPI {

    private static final Logger LOGGER = LogManager.getLogger(EngineAPI.class);
    private static User currentUser;
    public static void start(DB db) { //ALSO RECEIVES GUI ELEMENTS?
        /*while(true){
            //AWAITS INPUT FROM GUI
            //DOES SHIT BASED ON IT
        }*/
        //currentUser = CreateNewUser("Joao", "12@RP7123", "pedrex1997@gmail.com", "AES128", db);
        //EditUser(currentUser, "JOJO", UserField.username, db);
        //databaseOperations.editAddToPasswordListDb("Joao", "MANEL", "ORIGIN", "heheheh", db);
        //User user = new User("Joao", "12@RP7123", "pedrex1997@gmail.com", "AES128");
        //userManager.deletePasswordFromList(user, "ORIGIN", null, null, db);
    }



    private static User CreateNewUser(String username, String password, String email, String encryptionType, DB db) {
        User newUser = userManager.createNewUser(username, password, email, encryptionType, db);
        return newUser;
    }

    private static boolean DeleteUser(User user, DB db) {
       userManager.deleteUser(user, db);
        return true;
    }

    private static boolean EditUser(User user, String parameter, UserField field, DB db) {
       userManager.editUserField(user, parameter, field, db);
        return true;
    }

}