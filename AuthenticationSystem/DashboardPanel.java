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
        setOpaque(false);
        
        // Main card panel with white background
        JPanel cardPanel = new JPanel(new BorderLayout(20, 20));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20),
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true)
        ));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(450, 380));
        
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("User Dashboard");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(66, 135, 245));
        titlePanel.add(titleLabel);
        
        // Profile info panel
        JPanel profilePanel = new JPanel(new GridBagLayout());
        profilePanel.setBackground(Color.WHITE);
        profilePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            "Profile Information",
            0,
            0,
            new Font("Segoe UI", Font.BOLD, 16),
            new Color(66, 135, 245)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 15, 12, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        JLabel usernameLabelTitle = new JLabel("Username:");
        usernameLabelTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        profilePanel.add(usernameLabelTitle, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        usernameLabel = new JLabel("");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        profilePanel.add(usernameLabel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        JLabel fullNameLabelTitle = new JLabel("Full Name:");
        fullNameLabelTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        profilePanel.add(fullNameLabelTitle, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        fullNameLabel = new JLabel("");
        fullNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        profilePanel.add(fullNameLabel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        JLabel emailLabelTitle = new JLabel("Email:");
        emailLabelTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        profilePanel.add(emailLabelTitle, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        emailLabel = new JLabel("");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        profilePanel.add(emailLabel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        JLabel registrationDateLabelTitle = new JLabel("Registration Date:");
        registrationDateLabelTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        profilePanel.add(registrationDateLabelTitle, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        registrationDateLabel = new JLabel("");
        registrationDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        profilePanel.add(registrationDateLabel, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        JButton changePasswordButton = createStyledButton("Change Password", new Color(66, 135, 245));
        changePasswordButton.addActionListener(e -> handleChangePassword());
        
        JButton logoutButton = createStyledButton("Logout", new Color(255, 152, 0));
        logoutButton.addActionListener(e -> handleLogout());
        
        JButton exitButton = createStyledButton("Exit", new Color(244, 67, 54));
        exitButton.addActionListener(e -> handleExit());
        
        buttonPanel.add(changePasswordButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(exitButton);
        
        // Add panels to card
        cardPanel.add(titlePanel, BorderLayout.NORTH);
        cardPanel.add(profilePanel, BorderLayout.CENTER);
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
