package hu.oktatas.java.ee.webshop.db;

import hu.oktatas.java.ee.webshop.db.exception.UsernameAlreadyTakenException;
import hu.oktatas.java.ee.webshop.db.exception.UsernameNotExistException;
import hu.oktatas.java.ee.webshop.beans.UserDTO;
import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;

public class UserDB {

    private final Map<String, UserDTO> userDataBase = new HashMap<>();
    private final Calendar regTime = Calendar.getInstance();
    public static final UserDB INSTANCE = new UserDB();

    private UserDB() {
    }

    public static UserDB getINSTANCE() {
        return INSTANCE;
    }

    public UserDTO registrate(UserDTO user) throws UsernameAlreadyTakenException {
        if (userDataBase.containsKey(user.getUserName())) {
            throw new UsernameAlreadyTakenException(
                    "Username is Already Taken, Choose Another One!");
        } else {
            String userName = user.getUserName();
            user.setRegistrationDate(regTime);
            userDataBase.put(userName, user);
            return user;
        }
    }

    public UserDTO getUser(String username) throws UsernameNotExistException {
        if (userDataBase.containsKey(username)) {
            return userDataBase.get(username);
        } else {
            throw new UsernameNotExistException(
                    "Username is Not Exist in the Database!");
        }
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
        userDataBase.values().stream().forEach(user
                -> stringBuilder
                .append(user.getFirstName()).append(" ")
                .append(user.getLastName()).append(", Registration time:")
                .append(user.getRegistrationDate().getTime()).append("\n")
        );
        return stringBuilder.toString();
    }
}
