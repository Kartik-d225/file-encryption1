//package model;
//
////public class Data {
////    private int id;
////    private String fileName;
////    private String filePath;
////    private String userEmail;
////    private boolean isHidden;
////
////    public Data(int id, String fileName, String filePath, String userEmail, boolean isHidden) {
////        this.id = id;
////        this.fileName = fileName;
////        this.filePath = filePath;
////        this.userEmail = userEmail;
////        this.isHidden = isHidden;
////    }
////
////    public int getId() {
////        return id;
////    }
////
////    public String getFileName() {
////        return fileName;
////    }
////
////    public String getFilePath() {
////        return filePath;
////    }
////
////    public String getUserEmail() {
////        return userEmail;
////    }
////
////    public boolean isHidden() {
////        return isHidden;
////    }
////}
//public class Data {
//    private int id;
//    private String fileName;
//    private String filePath;
//    private String userEmail;
//    private boolean hidden; // New attribute to indicate if the file is hidden
//
//    // Getters and Setters for the new hidden attribute
//    public boolean isHidden() {
//        return hidden;
//    }
//
//    public void setHidden(boolean hidden) {
//        this.hidden = hidden;
//    }
//
//
//    public int getId() {
//
//    }
//}
package model;

public class Data {
    private int id;
    private String fileName;
    private String filePath;
    private String userEmail;
    private boolean hidden;

    // Constructor
    public Data() {}

    public Data(int id, String fileName, String filePath, String userEmail, boolean hidden) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.userEmail = userEmail;
        this.hidden = hidden;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
