package service;

import dao.UserDAO;
import model.User;

public class UserService {
    public static Integer saveUser(User user) {
        try {
            // if already exists then return 0
            if (UserDAO.userExists(user.getEmail())) {
                System.out.println("User already exists");
                return 0;
            } else {
                // if not exists then save user and return 1
                UserDAO.saveUser(user);
                System.out.println("User saved successfully 1");
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return -1;
        }
    }

}
