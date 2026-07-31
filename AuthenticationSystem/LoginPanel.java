import javax.swing.*;
import java.awt.*;

/**
 * LoginPanel - GUI panel for user login
 */
public class LoginPanel extends JPanel {
    
    private MainGUI mainGUI;
    private JTextField identifierField;
    private JPasswordField passwordField;
    private JTextField passwordVisibleField;
    private JButton togglePasswordButton;
    private boolean passwordVisible = false;
    
    public LoginPanel(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setOpaque(false);
        
        // Main card panel with white background and rounded corners
        JPanel cardPanel = new JPanel(new BorderLayout(20, 20));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20),
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true)
        ));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(400, 350));
        
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("User Login");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(66, 135, 245));
        titlePanel.add(titleLabel);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Username/Email label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        JLabel usernameLabel = new JLabel("Username or Email:");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(usernameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        identifierField = new JTextField(20);
        identifierField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        identifierField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        formPanel.add(identifierField, gbc);
        
        // Password label and field with toggle
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(passwordLabel, gbc);
        
        // Password field panel
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setBackground(Color.WHITE);
        
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        
        passwordVisibleField = new JTextField(20);
        passwordVisibleField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordVisibleField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        passwordVisibleField.setVisible(false);
        
        togglePasswordButton = new JButton("👁");
        togglePasswordButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        togglePasswordButton.setPreferredSize(new Dimension(40, 30));
        togglePasswordButton.setFocusPainted(false);
        togglePasswordButton.setBorderPainted(false);
        togglePasswordButton.setContentAreaFilled(false);
        togglePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        togglePasswordButton.addActionListener(e -> togglePasswordVisibility());
        
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        passwordPanel.add(passwordVisibleField, BorderLayout.CENTER);
        passwordPanel.add(togglePasswordButton, BorderLayout.EAST);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(passwordPanel, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        JButton loginButton = createStyledButton("Login", new Color(66, 135, 245));
        loginButton.addActionListener(e -> handleLogin());
        
        JButton registerButton = createStyledButton("Create Account", new Color(102, 187, 106));
        registerButton.addActionListener(e -> mainGUI.showPanel("REGISTER"));
        
        JButton forgotPasswordButton = new JButton("Forgot Password?");
        forgotPasswordButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        forgotPasswordButton.setForeground(new Color(66, 135, 245));
        forgotPasswordButton.setBorderPainted(false);
        forgotPasswordButton.setContentAreaFilled(false);
        forgotPasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordButton.addActionListener(e -> mainGUI.showPanel("FORGOT_PASSWORD"));
        
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(forgotPasswordButton);
        
        // Add panels to card
        cardPanel.add(titlePanel, BorderLayout.NORTH);
        cardPanel.add(formPanel, BorderLayout.CENTER);
        cardPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Center the card panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints centerGbc = new GridBagConstraints();
        centerGbc.gridx = 0;
        centerGbc.gridy = 0;
        centerGbc.weightx = 1.0;
        centerGbc.weighty = 1.0;
        centerPanel.add(cardPanel, centerGbc);
        
        add(centerPanel, BorderLayout.CENTER);
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private void togglePasswordVisibility() {
        passwordVisible = !passwordVisible;
        
        if (passwordVisible) {
            // Show password
            passwordVisibleField.setText(new String(passwordField.getPassword()));
            passwordField.setVisible(false);
            passwordVisibleField.setVisible(true);
            togglePasswordButton.setText("🔒");
        } else {
            // Hide password
            passwordField.setText(passwordVisibleField.getText());
            passwordVisibleField.setVisible(false);
            passwordField.setVisible(true);
            togglePasswordButton.setText("👁");
        }
    }
    
    private void handleLogin() {
        String identifier = identifierField.getText().trim();
        String password = passwordVisible ? 
            passwordVisibleField.getText() : 
            new String(passwordField.getPassword());
        
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
            passwordVisibleField.setText("");
            if (passwordVisible) {
                togglePasswordVisibility();
            }
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
