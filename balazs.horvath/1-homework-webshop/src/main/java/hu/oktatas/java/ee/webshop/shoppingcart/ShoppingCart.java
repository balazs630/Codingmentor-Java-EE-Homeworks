package hu.oktatas.java.ee.webshop.shoppingcart;

import hu.oktatas.java.ee.webshop.shoppingcart.exceptions.MobileNotExistInTheCartException;
import hu.oktatas.java.ee.webshop.beans.Main;
import hu.oktatas.java.ee.webshop.beans.MobileType;
import hu.oktatas.java.ee.webshop.db.MobileDB;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShoppingCart {

    private final Map<MobileType, Integer> cartItems;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final MobileDB MOBILEDB = MobileDB.INSTANCE;

    public ShoppingCart() {
        this.cartItems = new HashMap<>();
    }

    public void addItem(MobileType mobil, int quantity) {
        int currentQuantity = 0;
        currentQuantity += cartItems.get(mobil);
        cartItems.put(mobil, currentQuantity + quantity);
        MOBILEDB.reserveMobile(mobil, quantity);
    }

    public void removeItem(MobileType mobil, int quantity) throws MobileNotExistInTheCartException {
        if (!cartItems.containsKey(mobil)) {
            throw new MobileNotExistInTheCartException("The requested mobile ID is not in the cart!");
        } else {
            cartItems.remove(mobil);
            MOBILEDB.returnMobile(mobil, quantity);
        }
    }

    public void clear() {
        for (Entry<MobileType, Integer> entry : cartItems.entrySet()) {
            MobileType type = entry.getKey();
            Integer quantity = entry.getValue();
            MOBILEDB.returnMobile(type, quantity);
        }
        cartItems.clear();
    }

    public Float getTotal() {
        if (cartItems == null || cartItems.isEmpty()) {
            return 0f;
        } else {
            Float total = 0f;
            for (Entry<MobileType, Integer> entry : cartItems.entrySet()) {
                total += entry.getValue();
            }
            return total;
        }
    }

    public void checkout() {
        LOGGER.log(Level.INFO, "{0}", cartItems.toString());
        LOGGER.log(Level.INFO, "TOTAL Price: {0}", getTotal());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nTOTAL Price:\n");
        cartItems.entrySet().stream().forEach(entry
                -> stringBuilder
                .append(entry.getKey().getManufacturer()).append(" ")
                .append(entry.getKey().getType()).append(", ID:")
                .append(entry.getKey().getId())
        );
        return stringBuilder.toString();
    }
}
