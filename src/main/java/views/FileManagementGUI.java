package views;

import dao.DataDAO;
import model.Data;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

import static dao.DataDAO.*;

public class FileManagementGUI extends JFrame {
    private JTextField filePathField;
    private JTextArea outputArea;
    private String userEmail;

    public FileManagementGUI(String userEmail) {
        this.userEmail = userEmail;

        setTitle("File Management");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel for File Input
        JPanel topPanel = new JPanel(new BorderLayout());
        filePathField = new JTextField();
        JButton browseButton = new JButton("Browse");
        topPanel.add(new JLabel("File Path:"), BorderLayout.WEST);
        topPanel.add(filePathField, BorderLayout.CENTER);
        topPanel.add(browseButton, BorderLayout.EAST);

        // Center Panel for Output
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Bottom Panel for Buttons
        JPanel bottomPanel = new JPanel();
        JButton encryptButton = new JButton("Encrypt");
        JButton decryptButton = new JButton("Decrypt");
        JButton viewHiddenButton = new JButton("View Hidden Files");
        bottomPanel.add(encryptButton);
        bottomPanel.add(decryptButton);
        bottomPanel.add(viewHiddenButton);

        // Add components to frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event Listeners
        browseButton.addActionListener(e -> chooseFile());
        encryptButton.addActionListener(e -> encryptFile());
        decryptButton.addActionListener(e -> decryptFile());
        viewHiddenButton.addActionListener(e -> viewHiddenFiles());
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void encryptFile() {
        try {
            String filePath = filePathField.getText();
            File file = new File(filePath);

            if (!file.exists()) {
                outputArea.setText("File does not exist.");
                return;
            }

            Data data = new Data(0, file.getName(), filePath, userEmail, true);
            hideFile(data);
            outputArea.setText("File encrypted and hidden successfully.");
        } catch (Exception e) {
            outputArea.setText("Error encrypting file: " + e.getMessage());
        }
    }

    private void decryptFile() {
        // Implement decryption functionality
    }

    private void viewHiddenFiles() {
        try {
            List<Data> files = DataDAO.getHiddenFiles(userEmail);
            outputArea.setText("Hidden Files:\n");
            for (Data file : files) {
                outputArea.append(file.getId() + " - " + file.getFileName() + "\n");
            }
        } catch (Exception e) {
            outputArea.setText("Error fetching hidden files: " + e.getMessage());
        }
    }
}
