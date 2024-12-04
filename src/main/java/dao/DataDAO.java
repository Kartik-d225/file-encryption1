package dao;
//
//import model.Data;
//import utils.MyConnection;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.List;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class DataDAO {
//    public static void hideFile(Data file) throws IOException {
//        // Encrypt the file before hiding (assuming encryption logic exists)
//        encryptFile(file);
//
//        // Hide the file using the setHidden method
//        File f = new File(file.getFilePath() + ".enc");
//        if (f.exists()) {
//            f.setHidden(true); // This hides the file at the OS level
//        } else {
//            System.out.println("File not found or already hidden.");
//        }
//    }
//
//    public static void unhideFile(int id) throws IOException {
//        // Retrieve the file path based on the ID (logic to get path should be implemented)
//        Data file = getFileById(id);
//        if (file != null) {
//            File f = new File(file.getFilePath() + ".enc");
//            if (f.exists()) {
//                f.setHidden(false); // This makes the file visible again at the OS level
//            }
//        } else {
//            System.out.println("File not found.");
//        }
//    }
//
//    private static void encryptFile(Data file) throws IOException {
//        // Implement the actual encryption logic here
//        // For simplicity, copying the file contents as a placeholder
//        byte[] data = Files.readAllBytes(Paths.get(file.getFilePath()));
//        String encryptedData = new String(data); // Replace this with actual encryption logic
//        Files.write(Paths.get(file.getFilePath() + ".enc"), encryptedData.getBytes());
//    }
//
//    private static Data getFileById(int id) {
//        // Logic to retrieve the file from the database based on ID
//        // Placeholder for database retrieval logic
//        return new Data(id, "exampleFile.txt", "/path/to/exampleFile.txt", "user@example.com", true);
//    }
//}
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataDAO {
    public static void hideFile(Data file) throws IOException {
        // Encrypt the file before hiding (assuming encryption logic exists)
        encryptFile(file);

        File f = new File(file.getFilePath() + ".enc");
        if (f.exists()) {
            // Windows command to hide a file
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "attrib +h " + f.getAbsolutePath());
            pb.start();
            System.out.println("File hidden successfully.");
        } else {
            System.out.println("File not found or already hidden.");
        }
    }

    public static void unhideFile(int id) throws IOException {
        // Retrieve the file path based on the ID (logic to get path should be implemented)
        Data file = getFileById(id);
        if (file != null) {
            File f = new File(file.getFilePath() + ".enc");
            if (f.exists()) {
                // Windows command to unhide a file
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "attrib -h " + f.getAbsolutePath());
                pb.start();
                System.out.println("File unhidden successfully.");
            }
        } else {
            System.out.println("File not found.");
        }
    }

    private static void encryptFile(Data file) throws IOException {
        // Implement the actual encryption logic here
        byte[] data = Files.readAllBytes(Paths.get(file.getFilePath()));
        String encryptedData = new String(data); // Replace this with actual encryption logic
        Files.write(Paths.get(file.getFilePath() + ".enc"), encryptedData.getBytes());
    }

    private static Data getFileById(int id) {
        // Logic to retrieve the file from the database based on ID
        // Placeholder for database retrieval logic
        return new Data(id, "exampleFile.txt", "/path/to/exampleFile.txt", "user@example.com", true);
    }
}
