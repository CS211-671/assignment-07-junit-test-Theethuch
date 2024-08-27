package ku.cs.models;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;

    public UserList() {
        users = new ArrayList<>();
    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.isUsername(username)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(String username, String password) {
        User exist = findUserByUsername(username);
        if (exist == null) {
            users.add(new User(username, password));
        }
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = findUserByUsername(username);
        if (user == null || !user.validatePassword(oldPassword)) {
            return false;
        }
        user.setPassword(newPassword);
        return true;
    }

    public User login(String username, String password) {
        User user = findUserByUsername(username);
        if (user != null && user.validatePassword(password)) {
            return user;
        }
        return null;
    }
}
