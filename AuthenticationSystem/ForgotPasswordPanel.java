import javax.swing.*;
import java.awt.*;

/**
 * ForgotPasswordPanel - GUI panel for password recovery
 */
public class ForgotPasswordPanel extends JPanel {
    
    private MainGUI mainGUI;
    private JTextField emailField;
    private JPasswordField newPasswordField;
    private JTextField newVisibleField;
    private JButton toggleNewButton;
    private boolean newVisible = false;
    private JPasswordField confirmPasswordField;
    private JTextField confirmVisibleField;
    private JButton toggleConfirmButton;
    private boolean confirmVisible = false;
    
    public ForgotPasswordPanel(MainGUI mainGUI) {
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
        cardPanel.setPreferredSize(new Dimension(400, 320));
        
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Forgot Password");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(255, 152, 0));
        titlePanel.add(titleLabel);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Email
        gbc.gridx = 0;
        gbc.gridy = 0;
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
        
        // New Password with toggle
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(newPasswordLabel, gbc);
        
        newPasswordField = new JPasswordField(20);
        newPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        newPasswordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        
        newVisibleField = new JTextField(20);
        newVisibleField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        newVisibleField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        newVisibleField.setVisible(false);
        
        toggleNewButton = new JButton("👁");
        toggleNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        toggleNewButton.setPreferredSize(new Dimension(40, 30));
        toggleNewButton.setFocusPainted(false);
        toggleNewButton.setBorderPainted(false);
        toggleNewButton.setContentAreaFilled(false);
        toggleNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggleNewButton.addActionListener(e -> toggleNewVisibility());
        
        JPanel newPanel = new JPanel(new BorderLayout());
        newPanel.setBackground(Color.WHITE);
        newPanel.add(newPasswordField, BorderLayout.CENTER);
        newPanel.add(newVisibleField, BorderLayout.CENTER);
        newPanel.add(toggleNewButton, BorderLayout.EAST);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(newPanel, gbc);
        
        // Confirm New Password with toggle
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        JLabel confirmPasswordLabel = new JLabel("Confirm New Password:");
        confirmPasswordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(confirmPasswordLabel, gbc);
        
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
        
        JButton resetButton = createStyledButton("Reset Password", new Color(255, 152, 0));
        resetButton.addActionListener(e -> handlePasswordReset());
        
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
        
        buttonPanel.add(resetButton);
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
    
    private void toggleNewVisibility() {
        newVisible = !newVisible;
        if (newVisible) {
            newVisibleField.setText(new String(newPasswordField.getPassword()));
            newPasswordField.setVisible(false);
            newVisibleField.setVisible(true);
            toggleNewButton.setText("🔒");
        } else {
            newPasswordField.setText(newVisibleField.getText());
            newVisibleField.setVisible(false);
            newPasswordField.setVisible(true);
            toggleNewButton.setText("👁");
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
    
    private void handlePasswordReset() {
        String email = emailField.getText().trim();
        String newPassword = newVisible ? newVisibleField.getText() : new String(newPasswordField.getPassword());
        String confirmPassword = confirmVisible ? confirmVisibleField.getText() : new String(confirmPasswordField.getPassword());
        
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
        newVisibleField.setText("");
        confirmPasswordField.setText("");
        confirmVisibleField.setText("");
        if (newVisible) toggleNewVisibility();
        if (confirmVisible) toggleConfirmVisibility();
    }
}
