package data;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class databaseOperations {
    private static final Logger LOGGER = LogManager.getLogger(databaseOperations.class);


    public static boolean addUsertoDb(String username, String passwordHash, String email, String encryptionType, DB db){
        DBCollection collection = db.getCollection("Clients");
        BasicDBObject doc = new BasicDBObject("username", username)
                .append("password", passwordHash)
                .append("email", email)
                .append("encryptionType",encryptionType)
                .append("passwordList", new BasicDBObject("username", "").append("password", ""));
        collection.insert(doc);

        return verifyUserIsInDB(username, db);
    }



    public static boolean removeUserFromDb(String username, DB db){
        DBCollection collection = db.getCollection("Clients");
        BasicDBObject doc = new BasicDBObject("username", username);
        DBCursor cursor = collection.find(doc);
        if(cursor.hasNext()) {
            collection.remove(cursor.next());
            LOGGER.info("User removed from DB ----- Username: {}", username);
            return true;
        }else{
            LOGGER.info("User not found in DB ----- Username: {}", username);
            return false;
        }
    }

    public static boolean editUsernameDb(String username, DB db){
        DBCollection collection = db.getCollection("Clients");
        BasicDBObject doc = new BasicDBObject("username", username);
        DBCursor cursor = collection.find(doc);
        if(cursor.hasNext()) {
            collection.remove(cursor.next());
            LOGGER.info("User removed from DB ----- Username: {}", username);
            return true;
        }else{
            LOGGER.info("User not found in DB ----- Username: {}", username);
            return false;
        }
    }

    public static boolean editEmailDb(String username, DB db){
        DBCollection collection = db.getCollection("Clients");
        BasicDBObject doc = new BasicDBObject("username", username);
        DBCursor cursor = collection.find(doc);
        if(cursor.hasNext()) {
            collection.remove(cursor.next());
            LOGGER.info("User removed from DB ----- Username: {}", username);
            return true;
        }else{
            LOGGER.info("User not found in DB ----- Username: {}", username);
            return false;
        }
    }

    public static boolean editPasswordDb(String username, DB db){
        DBCollection collection = db.getCollection("Clients");
        BasicDBObject doc = new BasicDBObject("username", username);
        DBCursor cursor = collection.find(doc);
        if(cursor.hasNext()) {
            collection.remove(cursor.next());
            LOGGER.info("User removed from DB ----- Username: {}", username);
            return true;
        }else{
            LOGGER.info("User not found in DB ----- Username: {}", username);
            return false;
        }
    }

    public static boolean editEncryptionDb(String username, DB db){
        DBCollection collection = db.getCollection("Clients");
        BasicDBObject doc = new BasicDBObject("username", username);
        DBCursor cursor = collection.find(doc);
        if(cursor.hasNext()) {
            collection.remove(cursor.next());
            LOGGER.info("User removed from DB ----- Username: {}", username);
            return true;
        }else{
            LOGGER.info("User not found in DB ----- Username: {}", username);
            return false;
        }
    }

    public static boolean editAddToPasswordListDb(String username, DB db){
        DBCollection collection = db.getCollection("Clients");
        BasicDBObject doc = new BasicDBObject("username", username);
        DBCursor cursor = collection.find(doc);
        if(cursor.hasNext()) {
            collection.remove(cursor.next());
            LOGGER.info("User removed from DB ----- Username: {}", username);
            return true;
        }else{
            LOGGER.info("User not found in DB ----- Username: {}", username);
            return false;
        }
    }

    public static boolean editRemoveFromPasswordListDb(String username, DB db){
        DBCollection collection = db.getCollection("Clients");
        BasicDBObject doc = new BasicDBObject("username", username);
        DBCursor cursor = collection.find(doc);
        if(cursor.hasNext()) {
            collection.remove(cursor.next());
            LOGGER.info("User removed from DB ----- Username: {}", username);
            return true;
        }else{
            LOGGER.info("User not found in DB ----- Username: {}", username);
            return false;
        }
    }


    public static boolean verifyUserIsInDB(String username, DB db){
        DBCollection collection = db.getCollection("Clients");
        BasicDBObject userQuery = new BasicDBObject();
        userQuery.put("username", username);
        DBCursor cursor = collection.find(userQuery);
        if(cursor.hasNext()) {
            LOGGER.info("New User Created/Inserted in DB ----- Username: {}", username);
            return true;
        }
        LOGGER.debug("User not found in DB after creation --- Username {}", username);
        return false;
    }
}
