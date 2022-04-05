package data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class User {

    private static String username;
    private static String passwordHash;
    private static String email;
    private static String encryptionType;
    private static boolean isAuthenticated;



    private static final Logger LOGGER = LogManager.getLogger(User.class);

    public User(String username, String passwordHash, String email, String encryptionType){
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.encryptionType = encryptionType;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        User.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        User.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        User.email = email;
    }

    public String getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(String encryptionType) {
        User.encryptionType = encryptionType;
    }

    public boolean isIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(boolean isAuthenticated) {
        User.isAuthenticated = isAuthenticated;
    }
}
