package hu.oktatas.java.ee.webshop.db;

import hu.oktatas.java.ee.webshop.beans.MobileType;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

public class MobileDB {

    public static final MobileDB INSTANCE = new MobileDB();

    private final Map<MobileType, Integer> reservedMobileDB = new HashMap<>();

    private MobileDB() {
    }

    public MobileType addNewMobileType(MobileType mobileType) {
        String uuid = UUID.randomUUID().toString();
        mobileType.setId(uuid);
        reservedMobileDB.put(mobileType, 0);
        return mobileType;
    }

    public boolean reserveMobile(MobileType mobileType, int reqestedQuantity) {
        int stockQuantity = reservedMobileDB.get(mobileType);

        if (reqestedQuantity <= stockQuantity) {
            reservedMobileDB.put(mobileType, stockQuantity - reqestedQuantity);
            return true;
        } else {
            return false;
        }
    }

    public boolean returnMobile(MobileType mobileType, int quantity) {
        int stockQuantity;
        if (reservedMobileDB.get(mobileType) != null) {
            stockQuantity = reservedMobileDB.get(mobileType);
        } else {
            stockQuantity = 0;
        }
        reservedMobileDB.put(mobileType, stockQuantity + quantity);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nMobile Database:\n");
        reservedMobileDB.entrySet().stream().forEach(entry
                -> stringBuilder
                .append(entry.getKey().getManufacturer()).append(" ")
                .append(entry.getKey().getType()).append(", ID:")
                .append(entry.getKey().getId())
                .append(", Stock: ")
                .append(entry.getValue()).append("\n")
        );
        return stringBuilder.toString();
    }
}
