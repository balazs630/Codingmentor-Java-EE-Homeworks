package hu.oktatas.java.ee.webshop.main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.oktatas.java.ee.webshop.beans.MobileType;
import hu.oktatas.java.ee.webshop.beans.UserDTO;
import hu.oktatas.java.ee.webshop.db.MobileDB;
import hu.oktatas.java.ee.webshop.db.UserDB;
import hu.oktatas.java.ee.webshop.db.exceptions.UsernameAlreadyTakenException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static final UserDB USERDB = UserDB.INSTANCE;
    private static final MobileDB MOBILEDB = MobileDB.INSTANCE;
    private static final String USERJSONRESOURCE = "json/users.json";
    private static final String MOBILEJSONRESOURCE = "json/mobiles.json";

    private Main() {
    }

    public static void main(String[] args) 
            throws IOException, UsernameAlreadyTakenException {

        List<UserDTO> users = MAPPER.readValue(Main.class.getClassLoader()
                .getResource(USERJSONRESOURCE),
                new TypeReference<List<UserDTO>>() {
        });

        for (UserDTO user : users) {
            USERDB.registrate(user);
        }

        List<MobileType> mobiles = MAPPER.readValue(Main.class.getClassLoader().
                getResource(MOBILEJSONRESOURCE),
                new TypeReference<List<MobileType>>() {
        });

        for (MobileType mobile : mobiles) {
            MOBILEDB.addNewMobileType(mobile);
        }

        LOGGER.log(Level.INFO, "{0}", USERDB.toString());
        LOGGER.log(Level.INFO, "{0}", MOBILEDB.toString());

    }
}
