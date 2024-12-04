package views;

import dao.DataDAO;
import model.Data;

import javax.swing.*;
//import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class UserView extends JFrame {
    private String email;
    private JList<String> fileList;
    private DefaultListModel<String> listModel;
    private JButton hideButton, unhideButton, viewButton;

    public UserView(String email) {
        this.email = email;
        setTitle("File Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        listModel = new DefaultListModel<>();
        fileList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(fileList);

        hideButton = new JButton("Hide");
        unhideButton = new JButton("Unhide");
        viewButton = new JButton("View");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(hideButton);
        buttonPanel.add(unhideButton);
        buttonPanel.add(viewButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load visible files for the user
        loadFiles();

        // Add action listeners for buttons
        hideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic for hiding the selected file
                int selectedIndex = fileList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedFileName = listModel.get(selectedIndex);
                    try {
                        // Find the file by name or ID and call the hideFile method
                        Data file = new Data(); // Populate with actual data retrieval
                        file.setFileName(selectedFileName);
                        DataDAO.hideFile(file);
                        JOptionPane.showMessageDialog(null, "File hidden successfully.");
                        loadFiles();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error hiding file.");
                    }
                }
            }
        });

        unhideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = fileList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedFileName = listModel.get(selectedIndex);
                    try {
                        // Find the file by name or ID and call the unhideFile method
                        int fileId = getFileIdByName(selectedFileName); // Implement this method
                        DataDAO.unhideFile(fileId);
                        JOptionPane.showMessageDialog(null, "File unhidden successfully.");
                        loadFiles();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error unhiding file.");
                    }
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = fileList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedFileName = listModel.get(selectedIndex);
                    // Logic for viewing the file content (if not hidden)
                    JOptionPane.showMessageDialog(null, "Viewing file: " + selectedFileName);
                }
            }
        });
    }

    private void loadFiles() {
        try {
            List<Data> files = DataDAO.getVisibleFiles(this.email);
            listModel.clear();
            for (Data file : files) {
                listModel.addElement(file.getFileName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implement this method to find the file ID by name
    private int getFileIdByName(String fileName) {
        // Logic to retrieve the file ID from the database by file name
        return 0; // Placeholder, implement database logic here
    }
}
