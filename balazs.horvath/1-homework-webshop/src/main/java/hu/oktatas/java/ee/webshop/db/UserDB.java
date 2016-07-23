package hu.oktatas.java.ee.webshop.db;

import hu.oktatas.java.ee.webshop.beans.UserDTO;
import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;

public class UserDB {

    private UserDB() {
    }

    public static final UserDB INSTANCE = new UserDB();

    private final Map<String, UserDTO> userDataBase = new HashMap<>();

    public Map<String, UserDTO> getUserDataBase() {
        return userDataBase;
    }

    public static UserDB getINSTANCE() {
        return INSTANCE;
    }

    public Calendar getRegTime() {
        return regTime;
    }
    private final Calendar regTime = Calendar.getInstance();

    public UserDTO registrate(UserDTO user) {
        String userName = user.getUserName();
        user.setRegistrationDate(regTime);
        userDataBase.put(userName, user);
        return user;
    }

    public UserDTO getUser(String username) {
        return userDataBase.get(username);
    }

    public boolean authenticate(String username, String password) {
        if (userDataBase.containsKey(username)) {
            return userDataBase.get(username).getPassword().equals(password);
        }
        return false;
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nUser Database:\n");
        for (UserDTO user : userDataBase.values()) {
            stringBuilder.append(user);
        }
        return stringBuilder.toString();
    }
}
