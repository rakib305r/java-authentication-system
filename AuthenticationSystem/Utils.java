import java.util.Scanner;

/**
 * Utils class provides utility methods for console UI and input handling.
 * Contains methods for creating ASCII borders, reading input, and clearing screen.
 * This class helps create a professional-looking console interface.
 */
public class Utils {
    
    // Scanner object for reading user input
    private static final Scanner scanner = new Scanner(System.in);
    
    /**
     * Prints a welcome screen with ASCII art border
     */
    public static void printWelcomeScreen() {
        clearScreen();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║           WELCOME TO AUTHENTICATION SYSTEM                 ║");
        System.out.println("║                                                            ║");
        System.out.println("║                   Core Java Project                        ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    /**
     * Prints the main menu with ASCII border
     */
    public static void printMainMenu() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                        MAIN MENU                          ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Register                                               ║");
        System.out.println("║  2. Login                                                  ║");
        System.out.println("║  3. Forgot Password                                        ║");
        System.out.println("║  4. Exit                                                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
    }
    
    /**
     * Prints the user dashboard with ASCII border
     * @param username Username of the logged-in user
     */
    public static void printDashboard(String username) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                      USER DASHBOARD                       ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Welcome, " + username + "                                          ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. View Profile                                            ║");
        System.out.println("║  2. Change Password                                        ║");
        System.out.println("║  3. Logout                                                 ║");
        System.out.println("║  4. Exit                                                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
    }
    
    /**
     * Prints a header with ASCII border
     * @param title Title to display
     */
    public static void printHeader(String title) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  " + centerText(title, 58) + "║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    /**
     * Prints a success message
     * @param message Success message to display
     */
    public static void printSuccess(String message) {
        System.out.println("✓ " + message);
        System.out.println();
    }
    
    /**
     * Prints an error message
     * @param message Error message to display
     */
    public static void printError(String message) {
        System.out.println("✗ " + message);
        System.out.println();
    }
    
    /**
     * Prints an info message
     * @param message Info message to display
     */
    public static void printInfo(String message) {
        System.out.println("ℹ " + message);
        System.out.println();
    }
    
    /**
     * Centers text within a given width
     * @param text Text to center
     * @param width Width of the container
     * @return Centered text
     */
    private static String centerText(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - padding - text.length());
    }
    
    /**
     * Clears the console screen (platform-independent)
     */
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // If clearing fails, print newlines as fallback
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    
    /**
     * Reads a string input from the user
     * @param prompt Prompt to display
     * @return User input as string
     */
    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    /**
     * Reads a password input (masks the input with asterisks)
     * @param prompt Prompt to display
     * @return User input as string
     */
    public static String readPassword(String prompt) {
        System.out.print(prompt);
        String password = "";
        char ch;
        
        // Console-based password masking
        try {
            while (true) {
                ch = (char) System.in.read();
                if (ch == '\r' || ch == '\n') {
                    System.out.println();
                    break;
                } else if (ch == '\b') {
                    if (password.length() > 0) {
                        password = password.substring(0, password.length() - 1);
                        System.out.print("\b \b");
                    }
                } else {
                    password += ch;
                    System.out.print("*");
                }
            }
        } catch (Exception e) {
            // Fallback to regular input if console masking fails
            password = scanner.nextLine();
        }
        
        return password;
    }
    
    /**
     * Reads an integer input from the user with validation
     * @param prompt Prompt to display
     * @return User input as integer
     */
    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                Utils.printError("Invalid input. Please enter a number.");
            }
        }
    }
    
    /**
     * Reads an integer input within a specified range
     * @param prompt Prompt to display
     * @param min Minimum value
     * @param max Maximum value
     * @return User input as integer within range
     */
    public static int readIntRange(String prompt, int min, int max) {
        while (true) {
            int choice = readInt(prompt);
            if (choice >= min && choice <= max) {
                return choice;
            }
            Utils.printError("Please enter a number between " + min + " and " + max + ".");
        }
    }
    
    /**
     * Pauses execution and waits for user to press Enter
     * @param message Message to display
     */
    public static void pressEnterToContinue(String message) {
        System.out.print(message);
        scanner.nextLine();
    }
    
    /**
     * Closes the scanner (call this when exiting the application)
     */
    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
