package hu.oktatas.java.ee.webshop.db;

import hu.oktatas.java.ee.webshop.beans.MobileType;
import hu.oktatas.java.ee.webshop.db.exceptions.MobileNotExistException;
import hu.oktatas.java.ee.webshop.interceptor.BeanValidation;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.ejb.Singleton;
import java.io.Serializable;

@Singleton
public class MobileDB implements Serializable {

    public static final MobileDB INSTANCE = new MobileDB();
    private final Map<MobileType, Integer> reservedMobileDB = new HashMap<>();

    public Map<MobileType, Integer> getReservedMobileDB() {
        return reservedMobileDB;
    }

    public MobileType addNewMobileType(MobileType mobileType) {
        reservedMobileDB.put(mobileType, 1);
        return mobileType;
    }

    @BeanValidation
    public boolean reserveMobile(MobileType mobileType, int reqestedQuantity) {
        if (reservedMobileDB.get(mobileType) == null) {
            return false;
        } else if (reservedMobileDB.containsKey(mobileType)) {
            int stockQuantity = reservedMobileDB.get(mobileType);
            if (reqestedQuantity <= stockQuantity) {
                reservedMobileDB.put(mobileType, stockQuantity - reqestedQuantity);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @BeanValidation
    public boolean returnMobile(MobileType mobileType, int quantity) {
        int stockQuantity;
        if (reservedMobileDB.containsKey(mobileType)) {
            stockQuantity = reservedMobileDB.get(mobileType);
        } else {
            stockQuantity = 0;
        }
        reservedMobileDB.put(mobileType, stockQuantity + quantity);
        return true;
    }

    public Integer countById(String id) {
        Integer quantity = 0;
        for (Entry<MobileType, Integer> entry : reservedMobileDB.entrySet()) {
            if (entry.getKey().getId().equals(id)) {
                quantity += entry.getValue();
            }
        }
        return quantity;
    }

    public MobileType getMobileTypeByID(String id) throws MobileNotExistException {
        MobileType type = null;
        for (Entry<MobileType, Integer> entry : reservedMobileDB.entrySet()) {
            if (entry.getKey().getId().equals(id)) {
                type = entry.getKey();
            }
        }
        if (type == null) {
            throw new MobileNotExistException("getMobileTypeById failed: mobile not exist");
        } else {
            return type;
        }
    }

    @BeanValidation
    public boolean remove(String removeType) throws MobileNotExistException {
        MobileType type = null;
        for (Entry<MobileType, Integer> entry : reservedMobileDB.entrySet()) {
            if (entry.getKey().getType().equals(removeType)) {
                type = entry.getKey();
            }
        }
        if (type == null) {
            throw new MobileNotExistException("remove failed: mobile not exist");
        } else {
            reservedMobileDB.remove(type);
            return true;
        }
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
