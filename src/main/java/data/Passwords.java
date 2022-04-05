package data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class Passwords {

    private static User user;
    private static List<String> passwordList = Collections.emptyList(); //Comes from DB
    private static final Logger LOGGER = LogManager.getLogger(Passwords.class);

    public Passwords() {
    }
}
