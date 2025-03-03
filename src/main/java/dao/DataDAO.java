package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.MyConnection;
import model.Data;

public class DataDAO {
    public static List<Data> getFiles(String email) throws SQLException {
        Connection connection = MyConnection.connect();
        
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM data WHERE email = ?");

        ps.setString(1, email);

        ResultSet result =  ps.executeQuery();
        List<Data> files = new ArrayList<>();
        if(result.next()){  
            int id  = result.getInt(1);
            String fileName = result.getString(2);
            String path = result.getString(3);
            files.add(new Data(id, fileName, path, email));            
        }

        return files;
    }

    //hide file method
    public static int hideFile(Data data) throws SQLException, FileNotFoundException, IOException {
        Connection connection = MyConnection.connect();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO data (file_name, path, email,bin_data) VALUES (?, ?, ?, ?)");

        ps.setString(1, data.getFileName());
        ps.setString(2, data.getPath());
        ps.setString(3, data.getEmail());

        File file = new File(data.getPath());
        FileReader fr = new FileReader(file);
        if(!file.exists()){
            throw new FileNotFoundException("File not found");
        }
        ps.setCharacterStream(4, fr, file.length());
        int ans = ps.executeUpdate();
        fr.close();
        file.delete();
        return ans;
    }

    //unhides file
    public static void unHideFiles(int id) throws SQLException, IOException {
        Connection connection = MyConnection.connect();
        PreparedStatement ps = connection.prepareStatement("SELECT path,bin_data FROM data WHERE id = ?");
        ps.setInt(1, id);
        ResultSet result = ps.executeQuery();
        if(result.next()){
            String path = result.getString("path");
            Clob clob = result.getClob("bin_data");

            Reader reader = clob.getCharacterStream();
            FileWriter fw = new FileWriter(path);
            int i;


            while((i=reader.read())!=-1){
                fw.write((char)i);
            }
            fw.close();
            ps = connection.prepareStatement("DELETE FROM data WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate(); 
            System.out.println("File unhidden");

        }

    }
    
}
