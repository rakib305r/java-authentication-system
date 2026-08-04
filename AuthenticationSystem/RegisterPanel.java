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
    private JTextField passwordVisibleField;
    private JButton togglePasswordButton;
    private boolean passwordVisible = false;
    private JPasswordField confirmPasswordField;
    private JTextField confirmVisibleField;
    private JButton toggleConfirmButton;
    private boolean confirmVisible = false;
    
    public RegisterPanel(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setOpaque(false);
        
        // Main card panel with white background
        JPanel cardPanel = new JPanel(new BorderLayout(20, 20));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20),
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true)
        ));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(420, 400));
        
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("User Registration");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(102, 187, 106));
        titlePanel.add(titleLabel);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Full Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(fullNameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        fullNameField = new JTextField(20);
        fullNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        fullNameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        formPanel.add(fullNameField, gbc);
        
        // Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(emailLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        emailField = new JTextField(20);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        formPanel.add(emailField, gbc);
        
        // Username
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(usernameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        formPanel.add(usernameField, gbc);
        
        // Password with toggle
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(passwordLabel, gbc);
        
        // Initialize password fields
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
        
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        passwordPanel.add(passwordVisibleField, BorderLayout.CENTER);
        passwordPanel.add(togglePasswordButton, BorderLayout.EAST);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(passwordPanel, gbc);
        
        // Confirm Password with toggle
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(confirmPasswordLabel, gbc);
        
        // Initialize confirm password fields
        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        
        confirmVisibleField = new JTextField(20);
        confirmVisibleField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        confirmVisibleField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        confirmVisibleField.setVisible(false);
        
        toggleConfirmButton = new JButton("👁");
        toggleConfirmButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        toggleConfirmButton.setPreferredSize(new Dimension(40, 30));
        toggleConfirmButton.setFocusPainted(false);
        toggleConfirmButton.setBorderPainted(false);
        toggleConfirmButton.setContentAreaFilled(false);
        toggleConfirmButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggleConfirmButton.addActionListener(e -> toggleConfirmVisibility());
        
        JPanel confirmPanel = new JPanel(new BorderLayout());
        confirmPanel.setBackground(Color.WHITE);
        confirmPanel.add(confirmPasswordField, BorderLayout.CENTER);
        confirmPanel.add(confirmVisibleField, BorderLayout.CENTER);
        confirmPanel.add(toggleConfirmButton, BorderLayout.EAST);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(confirmPanel, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        JButton registerButton = createStyledButton("Register", new Color(102, 187, 106));
        registerButton.addActionListener(e -> handleRegistration());
        
        JButton backButton = new JButton("Back to Login");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        backButton.setForeground(new Color(100, 100, 100));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> {
            clearFields();
            mainGUI.showPanel("LOGIN");
        });
        
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        
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
    
    private void togglePasswordVisibility() {
        passwordVisible = !passwordVisible;
        if (passwordVisible) {
            passwordVisibleField.setText(new String(passwordField.getPassword()));
            passwordField.setVisible(false);
            passwordVisibleField.setVisible(true);
            togglePasswordButton.setText("🔒");
        } else {
            passwordField.setText(passwordVisibleField.getText());
            passwordVisibleField.setVisible(false);
            passwordField.setVisible(true);
            togglePasswordButton.setText("👁");
        }
    }
    
    private void toggleConfirmVisibility() {
        confirmVisible = !confirmVisible;
        if (confirmVisible) {
            confirmVisibleField.setText(new String(confirmPasswordField.getPassword()));
            confirmPasswordField.setVisible(false);
            confirmVisibleField.setVisible(true);
            toggleConfirmButton.setText("🔒");
        } else {
            confirmPasswordField.setText(confirmVisibleField.getText());
            confirmVisibleField.setVisible(false);
            confirmPasswordField.setVisible(true);
            toggleConfirmButton.setText("👁");
        }
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
    
    private void handleRegistration() {
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordVisible ? passwordVisibleField.getText() : new String(passwordField.getPassword());
        String confirmPassword = confirmVisible ? confirmVisibleField.getText() : new String(confirmPasswordField.getPassword());
        
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
        passwordVisibleField.setText("");
        confirmPasswordField.setText("");
        confirmVisibleField.setText("");
        if (passwordVisible) togglePasswordVisibility();
        if (confirmVisible) toggleConfirmVisibility();
    }
}
