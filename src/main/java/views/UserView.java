package views;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.DataDAO;
import model.Data;

public class UserView {

    private String email;


    // constructor
    public UserView(String email) {
        this.email  = email;
    }

    public void home() throws FileNotFoundException, IOException{
        do{
            System.out.println("welcome "+email);
            System.out.println("Press 1 to show hidden files");
            System.out.println("Press 2 to hide a file");
            System.out.println("Press 3 to unhide a file");
            System.out.println("Press 0 to exit");

            Scanner sc = new Scanner(System.in);
            int choice  =  Integer.parseInt(sc.nextLine());

            if(choice==1){
                System.out.println("Showing hidden files");
                try {
                    List<Data> files = DataDAO.getFiles(this.email);
                    System.out.println("ID\tFileName\tPath");
                    for(Data file: files){
                        System.out.println(file.getId()+"\t"+file.getFileName()+"\t"+file.getPath());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }else if(choice==2){
                System.out.println("Hiding a file");
                String path = sc.nextLine();
                File file = new File(path);
                
                Data data = new Data(0, file.getName(), path, this.email);
                try {
                    DataDAO.hideFile(data);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }else if(choice==3){
                System.out.println("Unhiding a file");
                try {
                    List<Data> files = DataDAO.getFiles(this.email);
                    System.out.println("ID\tFileName\tPath");
                    for(Data file: files){
                        System.out.println(file.getId()+"\t"+file.getFileName()+"\t"+file.getPath());
                    }
                    System.out.println("enter the file id to unhide");
                    int id = Integer.parseInt(sc.nextLine());
                    boolean isvalidId = false;
                    for(Data file: files){
                        if(file.getId()==id){
                            isvalidId = true;
                            break;
                        }
                    }
                    if(isvalidId){
                        DataDAO.unHideFiles(id);
                    }else{
                        System.out.println("Invalid id");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(choice==0){
                System.out.println("Exiting...");
                System.exit(0);
            }else{
                System.out.println("Invalid choice");
            }
        }while(true);
    }
    
}
