/**
 * Main class - Entry point of the Authentication System
 * This class handles the user interface and coordinates all other classes.
 * Implements the complete authentication flow with professional console UI.
 */
public class Main {
    
    // AuthService instance for authentication operations
    private static AuthService authService;
    
    // Currently logged-in user
    private static User currentUser;
    
    /**
     * Main method - Entry point of the application
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize authentication service
        authService = new AuthService();
        
        // Display welcome screen and start main menu
        runWelcomeScreen();
    }
    
    /**
     * Displays the welcome screen and starts the main menu loop
     */
    private static void runWelcomeScreen() {
        while (true) {
            Utils.printWelcomeScreen();
            Utils.printMainMenu();
            
            int choice = Utils.readIntRange("", 1, 4);
            
            switch (choice) {
                case 1:
                    handleRegistration();
                    break;
                case 2:
                    handleLogin();
                    break;
                case 3:
                    handleForgotPassword();
                    break;
                case 4:
                    handleExit();
                    return;
            }
            
            Utils.pressEnterToContinue("Press Enter to continue...");
        }
    }
    
    /**
     * Handles the registration process
     */
    private static void handleRegistration() {
        Utils.clearScreen();
        Utils.printHeader("USER REGISTRATION");
        
        // Collect user input
        String fullName = Utils.readString("Enter Full Name: ");
        String email = Utils.readString("Enter Email: ");
        String username = Utils.readString("Enter Username: ");
        String password = Utils.readString("Enter Password: ");
        String confirmPassword = Utils.readString("Confirm Password: ");
        
        // Attempt registration
        String result = authService.register(fullName, email, username, password, confirmPassword);
        
        if (result.startsWith("Registration successful")) {
            Utils.printSuccess(result);
        } else {
            Utils.printError(result);
        }
    }
    
    /**
     * Handles the login process
     */
    private static void handleLogin() {
        // Check if login is locked
        if (authService.isLoginLocked()) {
            Utils.printError("Too many failed login attempts. Please restart the application.");
            return;
        }
        
        Utils.clearScreen();
        Utils.printHeader("USER LOGIN");
        
        // Collect login credentials
        String identifier = Utils.readString("Enter Username or Email: ");
        String password = Utils.readString("Enter Password: ");
        
        // Attempt login
        User user = authService.login(identifier, password);
        
        if (user != null) {
            Utils.printSuccess("Login successful!");
            currentUser = user;
            runDashboard();
        } else {
            int remaining = authService.getRemainingLoginAttempts();
            if (remaining > 0) {
                Utils.printError("Invalid credentials. " + remaining + " attempt(s) remaining.");
            } else {
                Utils.printError("Too many failed login attempts. Login is now locked.");
            }
        }
    }
    
    /**
     * Handles the forgot password process
     */
    private static void handleForgotPassword() {
        Utils.clearScreen();
        Utils.printHeader("FORGOT PASSWORD");
        
        // Collect email and new password
        String email = Utils.readString("Enter your registered Email: ");
        String newPassword = Utils.readString("Enter New Password: ");
        String confirmPassword = Utils.readString("Confirm New Password: ");
        
        // Attempt password reset
        String result = authService.forgotPassword(email, newPassword, confirmPassword);
        
        if (result.startsWith("Password reset successful")) {
            Utils.printSuccess(result);
            authService.resetFailedAttempts();
        } else {
            Utils.printError(result);
        }
    }
    
    /**
     * Runs the user dashboard after successful login
     */
    private static void runDashboard() {
        while (currentUser != null) {
            Utils.clearScreen();
            Utils.printDashboard(currentUser.getUsername());
            
            int choice = Utils.readIntRange("", 1, 4);
            
            switch (choice) {
                case 1:
                    handleViewProfile();
                    break;
                case 2:
                    handleChangePassword();
                    break;
                case 3:
                    handleLogout();
                    return;
                case 4:
                    handleExit();
                    System.exit(0);
            }
            
            Utils.pressEnterToContinue("Press Enter to continue...");
        }
    }
    
    /**
     * Handles viewing the user profile
     */
    private static void handleViewProfile() {
        Utils.clearScreen();
        Utils.printHeader("USER PROFILE");
        
        System.out.println("Full Name: " + currentUser.getFullName());
        System.out.println("Email: " + currentUser.getEmail());
        System.out.println("Username: " + currentUser.getUsername());
        System.out.println("Registration Date: " + currentUser.getRegistrationDate());
        System.out.println();
    }
    
    /**
     * Handles changing the user password
     */
    private static void handleChangePassword() {
        Utils.clearScreen();
        Utils.printHeader("CHANGE PASSWORD");
        
        // Collect password information
        String currentPassword = Utils.readString("Enter Current Password: ");
        String newPassword = Utils.readString("Enter New Password: ");
        String confirmPassword = Utils.readString("Confirm New Password: ");
        
        // Attempt password change
        String result = authService.changePassword(currentUser, currentPassword, 
                                                   newPassword, confirmPassword);
        
        if (result.startsWith("Password changed")) {
            Utils.printSuccess(result);
        } else {
            Utils.printError(result);
        }
    }
    
    /**
     * Handles user logout
     */
    private static void handleLogout() {
        Utils.printSuccess("Logged out successfully!");
        currentUser = null;
        authService.resetFailedAttempts();
    }
    
    /**
     * Handles application exit
     */
    private static void handleExit() {
        Utils.clearScreen();
        Utils.printHeader("EXITING");
        Utils.printSuccess("Thank you for using the Authentication System!");
        Utils.printInfo("Total registered users: " + authService.getTotalUsers());
        Utils.closeScanner();
    }
}
