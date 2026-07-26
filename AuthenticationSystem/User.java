import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * User class represents a user in the authentication system.
 * It implements Serializable to support file-based data persistence.
 * Uses encapsulation with private fields and public getters/setters.
 */
public class User implements Serializable {
    
    // Serial version UID for serialization compatibility
    private static final long serialVersionUID = 1L;
    
    // Private fields (Encapsulation)
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String registrationDate;
    
    /**
     * Default constructor
     */
    public User() {
        // Set registration date to current date/time when user is created
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.registrationDate = now.format(formatter);
    }
    
    /**
     * Parameterized constructor
     * @param fullName User's full name
     * @param email User's email address
     * @param username User's username
     * @param password User's password
     */
    public User(String fullName, String email, String username, String password) {
        this();
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    
    // Getter and Setter methods
    
    /**
     * @return User's full name
     */
    public String getFullName() {
        return fullName;
    }
    
    /**
     * @param fullName Set user's full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    /**
     * @return User's email address
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @param email Set user's email address
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return User's username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @param username Set user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * @return User's password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param password Set user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return User's registration date
     */
    public String getRegistrationDate() {
        return registrationDate;
    }
    
    /**
     * @param registrationDate Set user's registration date
     */
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    /**
     * Override toString() method for easy display of user information
     * @return String representation of user
     */
    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                '}';
    }
    
    /**
     * Check if two users are equal based on username and email
     * @param obj Object to compare
     * @return true if users are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return username.equals(user.username) && email.equals(user.email);
    }
}
