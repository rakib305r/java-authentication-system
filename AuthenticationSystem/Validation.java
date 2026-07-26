import java.util.regex.Pattern;

/**
 * Validation class provides static methods to validate user input.
 * This class follows the Single Responsibility Principle - it only handles validation.
 * All methods are static as they don't need to maintain state.
 */
public class Validation {
    
    // Regular expression pattern for email validation
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
    /**
     * Validates if a string is empty or null
     * @param value String to validate
     * @return true if string is empty or null, false otherwise
     */
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    
    /**
     * Validates email format using regex
     * @param email Email address to validate
     * @return true if email format is valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * Validates password strength
     * Password must be at least 8 characters long
     * @param password Password to validate
     * @return true if password meets requirements, false otherwise
     */
    public static boolean isValidPassword(String password) {
        if (isEmpty(password)) {
            return false;
        }
        // Password must be at least 8 characters
        return password.length() >= 8;
    }
    
    /**
     * Checks if two passwords match
     * @param password Original password
     * @param confirmPassword Password to confirm
     * @return true if passwords match, false otherwise
     */
    public static boolean passwordsMatch(String password, String confirmPassword) {
        if (password == null || confirmPassword == null) {
            return false;
        }
        return password.equals(confirmPassword);
    }
    
    /**
     * Validates username (not empty and at least 3 characters)
     * @param username Username to validate
     * @return true if username is valid, false otherwise
     */
    public static boolean isValidUsername(String username) {
        if (isEmpty(username)) {
            return false;
        }
        return username.length() >= 3;
    }
    
    /**
     * Validates full name (not empty and at least 2 characters)
     * @param fullName Full name to validate
     * @return true if full name is valid, false otherwise
     */
    public static boolean isValidFullName(String fullName) {
        if (isEmpty(fullName)) {
            return false;
        }
        return fullName.length() >= 2;
    }
    
    /**
     * Comprehensive validation for registration form
     * @param fullName User's full name
     * @param email User's email
     * @param username User's username
     * @param password User's password
     * @param confirmPassword Password confirmation
     * @return Error message if validation fails, null if all valid
     */
    public static String validateRegistration(String fullName, String email, 
                                             String username, String password, 
                                             String confirmPassword) {
        if (!isValidFullName(fullName)) {
            return "Full name must be at least 2 characters.";
        }
        if (!isValidEmail(email)) {
            return "Invalid email format.";
        }
        if (!isValidUsername(username)) {
            return "Username must be at least 3 characters.";
        }
        if (!isValidPassword(password)) {
            return "Password must be at least 8 characters.";
        }
        if (!passwordsMatch(password, confirmPassword)) {
            return "Passwords do not match.";
        }
        return null; // All validations passed
    }
}
