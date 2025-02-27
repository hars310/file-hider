package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Welcome {
    public void WelcomeScreen(){
        // BufferedReader with InputStreamReader is used to take fast and efficient user input from the console.
        // we can use scanner as well but it is slower than BufferedReader.
        // like in c++ we use cin and cout for input and output.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Welcome to FileHider");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        try {
            // Integer.parseInt() converts string to integer.
            // readLine() reads the input from the console.
            int choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1:
                    Login();
                    break;
                case 2:
                    Register();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // sample methods to show the flow of the program
    public static void Login() {
        System.out.println("Login");
    }
    
    public static void Register() {
        System.out.println("Register");
    }

}
