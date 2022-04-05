package launcher;

import data.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class PwManagerLauncher {

    private static final Logger LOGGER = LogManager.getLogger(PwManagerLauncher.class);

    public static void main(String[] args){
        LOGGER.debug("Hello world");
        Scanner scan = new Scanner(System.in);

         String username = scan.nextLine();
         String password = scan.nextLine();
         String email = scan.nextLine();
         String encryptionType = scan.nextLine();

        User user = userManager.createNewUser(username, password, email, encryptionType);
        if (user == null){
            LOGGER.debug("Error creating new user");
        }
    }

}