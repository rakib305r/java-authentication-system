import javax.swing.*;
import java.awt.*;

/**
 * DashboardPanel - GUI panel for user dashboard after login
 */
public class DashboardPanel extends JPanel {
    
    private MainGUI mainGUI;
    private JLabel usernameLabel;
    private JLabel fullNameLabel;
    private JLabel emailLabel;
    private JLabel registrationDateLabel;
    
    public DashboardPanel(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("User Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        
        // Profile info panel
        JPanel profilePanel = new JPanel(new GridBagLayout());
        profilePanel.setBorder(BorderFactory.createTitledBorder("Profile Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        profilePanel.add(new JLabel("Username:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        usernameLabel = new JLabel("");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        profilePanel.add(usernameLabel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        profilePanel.add(new JLabel("Full Name:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        fullNameLabel = new JLabel("");
        fullNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        profilePanel.add(fullNameLabel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        profilePanel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        emailLabel = new JLabel("");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        profilePanel.add(emailLabel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        profilePanel.add(new JLabel("Registration Date:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        registrationDateLabel = new JLabel("");
        registrationDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        profilePanel.add(registrationDateLabel, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.addActionListener(e -> handleChangePassword());
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> handleLogout());
        
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> handleExit());
        
        buttonPanel.add(changePasswordButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(exitButton);
        
        // Add panels to main panel
        add(titlePanel, BorderLayout.NORTH);
        add(profilePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void updateUserInfo(User user) {
        usernameLabel.setText(user.getUsername());
        fullNameLabel.setText(user.getFullName());
        emailLabel.setText(user.getEmail());
        registrationDateLabel.setText(user.getRegistrationDate());
    }
    
    private void handleChangePassword() {
        JPasswordField currentPasswordField = new JPasswordField(20);
        JPasswordField newPasswordField = new JPasswordField(20);
        JPasswordField confirmPasswordField = new JPasswordField(20);
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(new JLabel("Current Password:"));
        panel.add(currentPasswordField);
        panel.add(new JLabel("New Password:"));
        panel.add(newPasswordField);
        panel.add(new JLabel("Confirm New Password:"));
        panel.add(confirmPasswordField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, 
            "Change Password", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            String currentPassword = new String(currentPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            
            if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill in all fields", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String changeResult = mainGUI.getAuthService().changePassword(
                mainGUI.getCurrentUser(), currentPassword, newPassword, confirmPassword);
            
            if (changeResult.startsWith("Password changed")) {
                JOptionPane.showMessageDialog(this, 
                    changeResult, 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    changeResult, 
                    "Password Change Failed", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void handleLogout() {
        mainGUI.getAuthService().resetFailedAttempts();
        mainGUI.setCurrentUser(null);
        JOptionPane.showMessageDialog(this, 
            "Logged out successfully!", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
        mainGUI.showPanel("LOGIN");
    }
    
    private void handleExit() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to exit?", 
            "Confirm Exit", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, 
                "Thank you for using the Authentication System!\n" +
                "Total registered users: " + mainGUI.getAuthService().getTotalUsers(), 
                "Goodbye", 
                JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
}
