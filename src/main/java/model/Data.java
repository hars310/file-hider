package model;

public class Data {
    private int id;
    private String fileName;
    private String path;
    private String email;

    // constructor
    public Data(int id, String fileName, String path, String email) {
        this.id = id;
        this.fileName = fileName;
        this.path = path;
        this.email = email;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return path;
    }

    public String getEmail() {
        return email;
    }

    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setEmail(String email) {
        this.email = email;
    } 

}
