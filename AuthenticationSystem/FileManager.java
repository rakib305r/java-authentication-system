import java.io.*;
import java.util.ArrayList;

/**
 * FileManager class handles all file operations for user data persistence.
 * Uses Java Serialization to save and load User objects to/from a file.
 * This class provides data persistence without requiring a database.
 */
public class FileManager {
    
    // File name for storing user data
    private static final String DATA_FILE = "users.dat";
    
    /**
     * Saves a list of users to the file using serialization
     * @param users ArrayList of User objects to save
     * @return true if save was successful, false otherwise
     */
    public static boolean saveUsers(ArrayList<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(DATA_FILE))) {
            oos.writeObject(users);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Loads users from the file using deserialization
     * @return ArrayList of User objects, or empty list if file doesn't exist or error occurs
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<User> loadUsers() {
        File file = new File(DATA_FILE);
        
        // Return empty list if file doesn't exist
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(DATA_FILE))) {
            ArrayList<User> users = (ArrayList<User>) ois.readObject();
            return users;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading users: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * Checks if the data file exists
     * @return true if file exists, false otherwise
     */
    public static boolean dataFileExists() {
        return new File(DATA_FILE).exists();
    }
    
    /**
     * Deletes the data file (useful for testing or resetting)
     * @return true if deletion was successful, false otherwise
     */
    public static boolean deleteDataFile() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            return file.delete();
        }
        return true; // File doesn't exist, considered as success
    }
    
    /**
     * Gets the absolute path of the data file
     * @return Absolute path of the data file
     */
    public static String getDataFilePath() {
        return new File(DATA_FILE).getAbsolutePath();
    }
}
