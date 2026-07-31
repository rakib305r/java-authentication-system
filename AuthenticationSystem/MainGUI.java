import javax.swing.*;
import java.awt.*;

/**
 * MainGUI class - Entry point for the Swing-based Authentication System
 * This class creates the main window and manages panel switching
 */
public class MainGUI extends JFrame {
    
    private AuthService authService;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private User currentUser;
    
    // Panel references
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private DashboardPanel dashboardPanel;
    private ForgotPasswordPanel forgotPasswordPanel;
    
    public MainGUI() {
        authService = new AuthService();
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Authentication System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Create panels
        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);
        dashboardPanel = new DashboardPanel(this);
        forgotPasswordPanel = new ForgotPasswordPanel(this);
        
        // Add panels to card layout
        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(registerPanel, "REGISTER");
        mainPanel.add(dashboardPanel, "DASHBOARD");
        mainPanel.add(forgotPasswordPanel, "FORGOT_PASSWORD");
        
        add(mainPanel);
        
        // Show login panel initially
        showPanel("LOGIN");
    }
    
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
        
        if (panelName.equals("DASHBOARD") && currentUser != null) {
            dashboardPanel.updateUserInfo(currentUser);
        }
    }
    
    public AuthService getAuthService() {
        return authService;
    }
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });
    }
}
