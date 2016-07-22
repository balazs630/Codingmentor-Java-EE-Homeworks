package hu.oktatas.java.ee.webshop.db;

import hu.oktatas.java.ee.webshop.beans.MobileType;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

public class MobileDB {

    private final Map<MobileType, Integer> reservedMobileDB = new HashMap<>();

    public MobileType addNewMobileType(MobileType mobileType) {
        String uuid = UUID.randomUUID().toString();
        mobileType.setId(uuid);
        reservedMobileDB.put(mobileType, 0);
        return mobileType;
    }

    public boolean reserveMobile(MobileType mobileType, int reqestedQuantity) {
        int stockQuantity = reservedMobileDB.get(mobileType);

        if (reqestedQuantity <= stockQuantity) {
            reservedMobileDB.put(mobileType, reqestedQuantity);
            return true;
        } else {
            return false;
        }
    }

    public void returnMobile(MobileType mobileType, int quantity) {
        int stockQuantity = reservedMobileDB.get(mobileType);
        reservedMobileDB.put(mobileType, quantity + stockQuantity);
    }
}
