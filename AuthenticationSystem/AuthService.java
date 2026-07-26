/**
 * AuthService class handles the business logic for authentication operations.
 * This class separates business logic from the user interface.
 * It manages registration, login, password changes, and account recovery.
 */
public class AuthService {
    
    // UserDatabase instance for data access
    private UserDatabase userDatabase;
    
    // Track failed login attempts for security
    private int failedLoginAttempts;
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    
    /**
     * Constructor - initializes the user database
     */
    public AuthService() {
        this.userDatabase = new UserDatabase();
        this.failedLoginAttempts = 0;
    }
    
    /**
     * Registers a new user with validation
     * @param fullName User's full name
     * @param email User's email
     * @param username User's username
     * @param password User's password
     * @param confirmPassword Password confirmation
     * @return Success message or error message
     */
    public String register(String fullName, String email, String username, 
                          String password, String confirmPassword) {
        // Validate input fields
        String validationError = Validation.validateRegistration(
            fullName, email, username, password, confirmPassword);
        
        if (validationError != null) {
            return validationError;
        }
        
        // Check if username already exists
        if (userDatabase.usernameExists(username)) {
            return "Username already exists. Please choose another.";
        }
        
        // Check if email already exists
        if (userDatabase.emailExists(email)) {
            return "Email already registered. Please use another email.";
        }
        
        // Create new user and add to database
        User newUser = new User(fullName, email, username, password);
        if (userDatabase.addUser(newUser)) {
            return "Registration successful! You can now login.";
        } else {
            return "Registration failed. Please try again.";
        }
    }
    
    /**
     * Authenticates a user with username/email and password
     * @param identifier Username or email
     * @param password User's password
     * @return User object if authentication successful, null otherwise
     */
    public User login(String identifier, String password) {
        // Check if login is locked due to too many failed attempts
        if (failedLoginAttempts >= MAX_LOGIN_ATTEMPTS) {
            return null;
        }
        
        // Find user by username or email
        User user = userDatabase.findUserByUsernameOrEmail(identifier);
        
        if (user == null) {
            failedLoginAttempts++;
            return null;
        }
        
        // Verify password
        if (user.getPassword().equals(password)) {
            // Reset failed attempts on successful login
            failedLoginAttempts = 0;
            return user;
        } else {
            failedLoginAttempts++;
            return null;
        }
    }
    
    /**
     * Changes the password for a logged-in user
     * @param user User whose password to change
     * @param currentPassword Current password for verification
     * @param newPassword New password
     * @param confirmPassword Confirmation of new password
     * @return Success message or error message
     */
    public String changePassword(User user, String currentPassword, 
                                String newPassword, String confirmPassword) {
        // Verify current password
        if (!user.getPassword().equals(currentPassword)) {
            return "Current password is incorrect.";
        }
        
        // Validate new password
        if (!Validation.isValidPassword(newPassword)) {
            return "New password must be at least 8 characters.";
        }
        
        // Check if new password matches current password
        if (currentPassword.equals(newPassword)) {
            return "New password must be different from current password.";
        }
        
        // Verify password confirmation
        if (!Validation.passwordsMatch(newPassword, confirmPassword)) {
            return "New passwords do not match.";
        }
        
        // Update password in database
        if (userDatabase.updatePassword(user.getUsername(), newPassword)) {
            return "Password changed successfully!";
        } else {
            return "Failed to change password. Please try again.";
        }
    }
    
    /**
     * Handles forgot password functionality
     * @param email User's email address
     * @param newPassword New password to set
     * @param confirmPassword Confirmation of new password
     * @return Success message or error message
     */
    public String forgotPassword(String email, String newPassword, String confirmPassword) {
        // Validate email format
        if (!Validation.isValidEmail(email)) {
            return "Invalid email format.";
        }
        
        // Check if email exists in database
        if (!userDatabase.emailExists(email)) {
            return "Email not found in our records.";
        }
        
        // Validate new password
        if (!Validation.isValidPassword(newPassword)) {
            return "New password must be at least 8 characters.";
        }
        
        // Verify password confirmation
        if (!Validation.passwordsMatch(newPassword, confirmPassword)) {
            return "Passwords do not match.";
        }
        
        // Update password in database
        if (userDatabase.updatePasswordByEmail(email, newPassword)) {
            return "Password reset successful! You can now login with your new password.";
        } else {
            return "Failed to reset password. Please try again.";
        }
    }
    
    /**
     * Gets the number of remaining login attempts
     * @return Number of remaining attempts
     */
    public int getRemainingLoginAttempts() {
        return MAX_LOGIN_ATTEMPTS - failedLoginAttempts;
    }
    
    /**
     * Checks if login is locked due to too many failed attempts
     * @return true if locked, false otherwise
     */
    public boolean isLoginLocked() {
        return failedLoginAttempts >= MAX_LOGIN_ATTEMPTS;
    }
    
    /**
     * Resets the failed login attempts counter
     * (useful when returning to main menu)
     */
    public void resetFailedAttempts() {
        this.failedLoginAttempts = 0;
    }
    
    /**
     * Gets the total number of registered users
     * @return Number of users
     */
    public int getTotalUsers() {
        return userDatabase.getUserCount();
    }
}
