package model;

public class User{
    private String name;
    private String email;
    
    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    //setter and getter methods
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }



}