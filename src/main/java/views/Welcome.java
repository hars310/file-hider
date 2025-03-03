package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import dao.UserDAO;
import model.User;
import service.OTPgenerate;
import service.SendOTPService;
import service.UserService;

public class Welcome {
    public void WelcomeScreen() {
        // BufferedReader with InputStreamReader is used to take fast and efficient user
        // input from the console.
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
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        try {
            if (UserDAO.userExists(email)) {
                String genOTP = OTPgenerate.getOTP();
                SendOTPService.sendOTP(email, genOTP);
                System.out.println("OTP sent to your email");
                System.out.print("Enter OTP: ");
                String otp = sc.nextLine();
                if (otp.equals(genOTP)) {
                    System.out.println("OTP matched");
                } else {
                    System.out.println("OTP not matched");
                }
            } else {
                System.out.println("User does not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // sc.close();  
    }

    public static void Register() {
        System.out.println("Register yourself");

        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        
        System.out.println("Enter your email: ");
        String email = sc.nextLine();

        try {
            if(UserDAO.userExists(email)){
                System.out.println("User already exists");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // otp generation and sending
        String genOTP = OTPgenerate.getOTP();
        SendOTPService.sendOTP(email, genOTP);
        System.out.println("OTP sent to your email");
        
        // collecting user's otp
        System.out.print("Enter OTP: ");
        String otp = sc.nextLine();
        
        // if otp matched then save the user
        if (otp.equals(genOTP)) {
            System.out.println("OTP matched");
            User user = new User(name, email);
            int status = UserService.saveUser(user);
            if (status == 1) {
                System.out.println("User saved successfully 2");
            } else if (status == 0) {
                System.out.println("User already exists");
            } else {
                System.out.println("Error");
            }

        } else {
            System.out.println("OTP not matched");
        }
        // sc.close();
    }

}
