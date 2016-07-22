package hu.oktatas.java.ee.webshop.db;

import hu.oktatas.java.ee.webshop.beans.UserDTO;
import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;

public class UserDB {

    private final Map<String, UserDTO> userDB = new HashMap<>();
    private final Calendar regTime = Calendar.getInstance();

    public UserDTO registrate(UserDTO user) {
        String userName = user.getUserName();
        user.setRegistrationDate(regTime);
        userDB.put(userName, user);
        return user;
    }

    public UserDTO getUser(String username) {
        return userDB.get(username);
    }

    public boolean authenticate(String username, String password) {
        if (userDB.containsKey(username)){
            return userDB.get(username).getPassword().equals(password);
        }
        return false;
    }

}
