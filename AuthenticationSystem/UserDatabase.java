import java.util.ArrayList;

/**
 * UserDatabase class manages the in-memory user database.
 * Uses ArrayList to store User objects and provides CRUD operations.
 * Automatically loads users from file on initialization and saves on modifications.
 */
public class UserDatabase {
    
    // ArrayList to store all users in memory
    private ArrayList<User> users;
    
    /**
     * Constructor - loads users from file on initialization
     */
    public UserDatabase() {
        this.users = FileManager.loadUsers();
    }
    
    /**
     * Adds a new user to the database
     * @param user User object to add
     * @return true if user was added successfully, false if username/email already exists
     */
    public boolean addUser(User user) {
        // Check if username already exists
        if (findUserByUsername(user.getUsername()) != null) {
            return false;
        }
        
        // Check if email already exists
        if (findUserByEmail(user.getEmail()) != null) {
            return false;
        }
        
        // Add user to list and save to file
        users.add(user);
        return saveToDisk();
    }
    
    /**
     * Finds a user by username
     * @param username Username to search for
     * @return User object if found, null otherwise
     */
    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Finds a user by email
     * @param email Email to search for
     * @return User object if found, null otherwise
     */
    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Finds a user by username or email (for login)
     * @param identifier Username or email
     * @return User object if found, null otherwise
     */
    public User findUserByUsernameOrEmail(String identifier) {
        User user = findUserByUsername(identifier);
        if (user == null) {
            user = findUserByEmail(identifier);
        }
        return user;
    }
    
    /**
     * Updates a user's password
     * @param username Username of the user
     * @param newPassword New password to set
     * @return true if password was updated, false if user not found
     */
    public boolean updatePassword(String username, String newPassword) {
        User user = findUserByUsername(username);
        if (user != null) {
            user.setPassword(newPassword);
            return saveToDisk();
        }
        return false;
    }
    
    /**
     * Updates a user's password by email (for forgot password)
     * @param email Email of the user
     * @param newPassword New password to set
     * @return true if password was updated, false if user not found
     */
    public boolean updatePasswordByEmail(String email, String newPassword) {
        User user = findUserByEmail(email);
        if (user != null) {
            user.setPassword(newPassword);
            return saveToDisk();
        }
        return false;
    }
    
    /**
     * Gets the total number of users in the database
     * @return Number of users
     */
    public int getUserCount() {
        return users.size();
    }
    
    /**
     * Gets all users in the database
     * @return ArrayList of all users
     */
    public ArrayList<User> getAllUsers() {
        return users;
    }
    
    /**
     * Checks if a username exists in the database
     * @param username Username to check
     * @return true if username exists, false otherwise
     */
    public boolean usernameExists(String username) {
        return findUserByUsername(username) != null;
    }
    
    /**
     * Checks if an email exists in the database
     * @param email Email to check
     * @return true if email exists, false otherwise
     */
    public boolean emailExists(String email) {
        return findUserByEmail(email) != null;
    }
    
    /**
     * Saves the current user list to disk
     * @return true if save was successful, false otherwise
     */
    private boolean saveToDisk() {
        return FileManager.saveUsers(users);
    }
    
    /**
     * Reloads users from disk (useful if file was modified externally)
     */
    public void reloadFromDisk() {
        this.users = FileManager.loadUsers();
    }
}
