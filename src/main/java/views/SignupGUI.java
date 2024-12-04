package views;

import dao.UserDAO;
import model.User;
import services.GenerateOTP;
import services.SendOTPService;

import javax.swing.*;
import java.awt.*;

public class SignupGUI extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public SignupGUI() {
        setTitle("Signup");
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
        JButton signupButton = new JButton("Signup");
        buttonPanel.add(signupButton);

        // Add panels to frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event Listeners
        signupButton.addActionListener(e -> register());
    }

    private void register() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String otp = GenerateOTP.generate(6);

        // Simulate OTP sending
        SendOTPService.send(email, otp);

        String enteredOTP = JOptionPane.showInputDialog(this, "Enter the OTP sent to your email:");

        if (otp.equals(enteredOTP)) {
            try {
                User user = new User(email, password);
                UserDAO.register(user);
                JOptionPane.showMessageDialog(this, "Registration successful!");
                dispose();
                new LoginGUI().setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid OTP. Please try again.");
        }
    }
}
