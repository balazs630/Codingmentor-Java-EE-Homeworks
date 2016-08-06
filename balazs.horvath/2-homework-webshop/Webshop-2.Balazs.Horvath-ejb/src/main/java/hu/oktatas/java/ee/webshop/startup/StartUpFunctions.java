package hu.oktatas.java.ee.webshop.startup;

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
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class StartUpFunctions {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = Logger.getLogger(StartUpFunctions.class.getName());
    private static final String USER_JSON_RESOURCE = "json/users.json";
    private static final String MOBILE_JSON_RESOURCE = "json/mobiles.json";

    @Inject
    private UserDB userDB;

    @Inject
    private MobileDB mobileDB;

    @PostConstruct
    public void businessMethod() throws MobileNotExistInTheCartException, IOException, UsernameAlreadyTakenException {
        List<UserDTO> users = MAPPER.readValue(StartUpFunctions.class.getClassLoader()
                .getResource(USER_JSON_RESOURCE),
                new TypeReference<List<UserDTO>>() {
        });

        for (UserDTO user : users) {
            userDB.registrate(user);
        }

        List<MobileType> mobiles = MAPPER.readValue(StartUpFunctions.class.getClassLoader().
                getResource(MOBILE_JSON_RESOURCE),
                new TypeReference<List<MobileType>>() {
        });

        mobiles.stream().forEach((mobile) -> {
            mobileDB.addNewMobileType(mobile);
        });

        LOGGER.log(Level.INFO, "{0}", userDB.toString());
        LOGGER.log(Level.INFO, "{0}", mobileDB.toString());

        ShoppingCart cart = new ShoppingCart();

        cart.addItem(mobiles.get(0), 4);
        cart.addItem(mobiles.get(1), 5);
        cart.removeItem(mobiles.get(1), 1);
        cart.checkout();

    }

}
