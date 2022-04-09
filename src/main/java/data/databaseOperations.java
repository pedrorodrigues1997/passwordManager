package data;

import com.google.common.hash.Hashing;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

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

        return verifyUserIsInDB(username, UserField.username, db);
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

    public static boolean editOneParameterDb(String queryParameter, String newParameter, UserField field, DB db){
        DBCollection collection = db.getCollection("Clients");

        if(field.toString().equals("password")) {
            BasicDBObject query = new BasicDBObject("username", queryParameter);
            String passwordHash = Hashing.sha256()
                    .hashString(newParameter, StandardCharsets.UTF_8)
                    .toString();

            BasicDBObject newDocument = new BasicDBObject(field.toString(), passwordHash);
            BasicDBObject updateObject = new BasicDBObject("$set", newDocument);
            collection.update(query, updateObject);
            return verifyUserIsInDB(newParameter, field, db);

        }

        if(field.toString().equals("encryptionType")) {
            BasicDBObject query = new BasicDBObject("username", queryParameter);
            BasicDBObject newDocument = new BasicDBObject(field.toString(), newParameter);
            BasicDBObject updateObject = new BasicDBObject("$set", newDocument);
            collection.update(query, updateObject);
            return verifyUserIsInDB(newParameter, field, db);

        }

        BasicDBObject query = new BasicDBObject(field.toString(), queryParameter);
        BasicDBObject newDocument = new BasicDBObject(field.toString(), newParameter);
        BasicDBObject updateObject = new BasicDBObject("$set", newDocument);
        collection.update(query, updateObject);
        return verifyUserIsInDB(newParameter, field, db);
    }

    public static boolean editEncryptionType(String username, String newParameter, UserField field, DB db){
        DBCollection collection = db.getCollection("Clients");

        if(field.toString().equals("encryptionType")) {
            BasicDBObject query = new BasicDBObject("username", username);
            BasicDBObject newDocument = new BasicDBObject(field.toString(), newParameter);
            BasicDBObject updateObject = new BasicDBObject("$set", newDocument);
            collection.update(query, updateObject);
        }
        return verifyUserIsInDB(newParameter, field, db);
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


    public static boolean verifyUserIsInDB(String paramter, UserField field, DB db){
        DBCollection collection = db.getCollection("Clients");
        BasicDBObject userQuery = new BasicDBObject();
        userQuery.put(field.toString(), paramter);
        DBCursor cursor = collection.find(userQuery);
        if(cursor.hasNext()) {
            LOGGER.info("Is in DB ----- {}: {}",field.toString(), paramter);
            return true;
        }
        LOGGER.debug("User not found in DB after creation --- Username {}", paramter);
        return false;
    }
}
