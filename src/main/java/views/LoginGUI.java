package views;

import dao.UserDAO;
import services.GenerateOTP;
import services.SendOTPService;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginGUI() {
        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Signup");
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        // Add panels to frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event Listeners
        loginButton.addActionListener(e -> authenticate());
        signupButton.addActionListener(e -> {
            dispose();
            new SignupGUI().setVisible(true);
        });
    }

    private void authenticate() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (UserDAO.authenticate(email, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            dispose();
            new FileManagementGUI(email).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI().setVisible(true));
    }
}
