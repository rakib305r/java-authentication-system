import javax.swing.*;
import java.awt.*;

/**
 * LoginPanel - GUI panel for user login
 */
public class LoginPanel extends JPanel {
    
    private MainGUI mainGUI;
    private JTextField identifierField;
    private JPasswordField passwordField;
    
    public LoginPanel(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("User Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Username/Email label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Username or Email:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        identifierField = new JTextField(20);
        formPanel.add(identifierField, gbc);
        
        // Password label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Password:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        passwordField = new JPasswordField(20);
        formPanel.add(passwordField, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> handleLogin());
        
        JButton registerButton = new JButton("Create Account");
        registerButton.addActionListener(e -> mainGUI.showPanel("REGISTER"));
        
        JButton forgotPasswordButton = new JButton("Forgot Password?");
        forgotPasswordButton.addActionListener(e -> mainGUI.showPanel("FORGOT_PASSWORD"));
        
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(forgotPasswordButton);
        
        // Add panels to main panel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void handleLogin() {
        String identifier = identifierField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (identifier.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please fill in all fields", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Check if login is locked
        if (mainGUI.getAuthService().isLoginLocked()) {
            JOptionPane.showMessageDialog(this, 
                "Too many failed login attempts. Please restart the application.", 
                "Login Locked", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Attempt login
        User user = mainGUI.getAuthService().login(identifier, password);
        
        if (user != null) {
            mainGUI.setCurrentUser(user);
            JOptionPane.showMessageDialog(this, 
                "Login successful!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            mainGUI.showPanel("DASHBOARD");
            
            // Clear fields
            identifierField.setText("");
            passwordField.setText("");
        } else {
            int remaining = mainGUI.getAuthService().getRemainingLoginAttempts();
            if (remaining > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Invalid credentials. " + remaining + " attempt(s) remaining.", 
                    "Login Failed", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Too many failed login attempts. Login is now locked.", 
                    "Login Locked", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
