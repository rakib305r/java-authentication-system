import javax.swing.*;
import java.awt.*;

/**
 * RegisterPanel - GUI panel for user registration
 */
public class RegisterPanel extends JPanel {
    
    private MainGUI mainGUI;
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    
    public RegisterPanel(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("User Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        
        // Form panel with scroll
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Full Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Full Name:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        fullNameField = new JTextField(20);
        formPanel.add(fullNameField, gbc);
        
        // Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        emailField = new JTextField(20);
        formPanel.add(emailField, gbc);
        
        // Username
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Username:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        usernameField = new JTextField(20);
        formPanel.add(usernameField, gbc);
        
        // Password
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Password:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        passwordField = new JPasswordField(20);
        formPanel.add(passwordField, gbc);
        
        // Confirm Password
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Confirm Password:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        confirmPasswordField = new JPasswordField(20);
        formPanel.add(confirmPasswordField, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> handleRegistration());
        
        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(e -> {
            clearFields();
            mainGUI.showPanel("LOGIN");
        });
        
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        
        // Add panels to main panel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void handleRegistration() {
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        if (fullName.isEmpty() || email.isEmpty() || username.isEmpty() || 
            password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please fill in all fields", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Attempt registration
        String result = mainGUI.getAuthService().register(
            fullName, email, username, password, confirmPassword);
        
        if (result.startsWith("Registration successful")) {
            JOptionPane.showMessageDialog(this, 
                result, 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            mainGUI.showPanel("LOGIN");
        } else {
            JOptionPane.showMessageDialog(this, 
                result, 
                "Registration Failed", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearFields() {
        fullNameField.setText("");
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }
}
