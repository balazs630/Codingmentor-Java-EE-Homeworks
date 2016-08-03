package hu.oktatas.java.ee.webshop.main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.oktatas.java.ee.webshop.beans.MobileType;
import hu.oktatas.java.ee.webshop.beans.UserDTO;
import hu.oktatas.java.ee.webshop.db.MobileDB;
import hu.oktatas.java.ee.webshop.db.UserDB;
import hu.oktatas.java.ee.webshop.db.exceptions.UsernameAlreadyTakenException;
import hu.oktatas.java.ee.webshop.shoppingcart.ShoppingCart;
import hu.oktatas.java.ee.webshop.shoppingcart.exceptions.MobileNotExistInTheCartException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static final UserDB USER_DB = UserDB.INSTANCE;
    private static final MobileDB MOBILE_DB = MobileDB.INSTANCE;
    private static final String USER_JSON_RESOURCE = "json/users.json";
    private static final String MOBILE_JSON_RESOURCE = "json/mobiles.json";

    private Main() {
    }

    public static void main(String[] args)
            throws IOException, UsernameAlreadyTakenException, MobileNotExistInTheCartException {

        List<UserDTO> users = MAPPER.readValue(Main.class.getClassLoader()
                .getResource(USER_JSON_RESOURCE),
                new TypeReference<List<UserDTO>>() {
        });

        for (UserDTO user : users) {
            USER_DB.registrate(user);
        }

        List<MobileType> mobiles = MAPPER.readValue(Main.class.getClassLoader().
                getResource(MOBILE_JSON_RESOURCE),
                new TypeReference<List<MobileType>>() {
        });

        for (MobileType mobile : mobiles) {
            MOBILE_DB.addNewMobileType(mobile);
        }

        LOGGER.log(Level.INFO, "{0}", USER_DB.toString());
        LOGGER.log(Level.INFO, "{0}", MOBILE_DB.toString());

        ShoppingCart cart = new ShoppingCart();

        cart.addItem(mobiles.get(0), 2);
        cart.addItem(mobiles.get(1), 3);
        cart.removeItem(mobiles.get(1), 1);
        cart.checkout();
    }
}
