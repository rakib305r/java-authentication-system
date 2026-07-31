import javax.swing.*;
import java.awt.*;

/**
 * ForgotPasswordPanel - GUI panel for password recovery
 */
public class ForgotPasswordPanel extends JPanel {
    
    private MainGUI mainGUI;
    private JTextField emailField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    
    public ForgotPasswordPanel(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Forgot Password");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Email
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        emailField = new JTextField(20);
        formPanel.add(emailField, gbc);
        
        // New Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(new JLabel("New Password:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        newPasswordField = new JPasswordField(20);
        formPanel.add(newPasswordField, gbc);
        
        // Confirm New Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Confirm New Password:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        confirmPasswordField = new JPasswordField(20);
        formPanel.add(confirmPasswordField, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        JButton resetButton = new JButton("Reset Password");
        resetButton.addActionListener(e -> handlePasswordReset());
        
        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(e -> {
            clearFields();
            mainGUI.showPanel("LOGIN");
        });
        
        buttonPanel.add(resetButton);
        buttonPanel.add(backButton);
        
        // Add panels to main panel
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void handlePasswordReset() {
        String email = emailField.getText().trim();
        String newPassword = new String(newPasswordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        if (email.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please fill in all fields", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Attempt password reset
        String result = mainGUI.getAuthService().forgotPassword(
            email, newPassword, confirmPassword);
        
        if (result.startsWith("Password reset successful")) {
            JOptionPane.showMessageDialog(this, 
                result, 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            mainGUI.getAuthService().resetFailedAttempts();
            clearFields();
            mainGUI.showPanel("LOGIN");
        } else {
            JOptionPane.showMessageDialog(this, 
                result, 
                "Password Reset Failed", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearFields() {
        emailField.setText("");
        newPasswordField.setText("");
        confirmPasswordField.setText("");
    }
}
