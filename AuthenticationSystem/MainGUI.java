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
        setSize(550, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create gradient background panel
        mainPanel = new JPanel(new CardLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(66, 135, 245);
                Color color2 = new Color(102, 187, 106);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        cardLayout = (CardLayout) mainPanel.getLayout();
        mainPanel.setOpaque(false);
        
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
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });
    }
}
