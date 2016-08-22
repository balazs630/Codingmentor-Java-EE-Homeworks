package hu.oktatas.java.ee.webshop.db;

import hu.oktatas.java.ee.webshop.beans.Color;
import hu.oktatas.java.ee.webshop.beans.Currency;
import hu.oktatas.java.ee.webshop.beans.ManufacturerType;
import hu.oktatas.java.ee.webshop.beans.MobileType;
import org.junit.Assert;
import org.junit.Test;
import java.util.UUID;
import org.junit.Before;

public class MobileDBTest {

    private MobileDB mobileDB;
    private MobileType mobile;

    @Before
    public void setUpClass() {
        String id = UUID.randomUUID().toString();
        mobile = new MobileType(id, ManufacturerType.APPLE, "Iphone4", 50, Currency.GBP, Color.BLACK);
    }

    @Test
    public void addNewMobileTypeTestTrue() {
        MobileType addedType = mobileDB.addNewMobileType(mobile);
        Assert.assertEquals(mobile, addedType);
    }

    @Test
    public void reserveMobileTypeTestTrue() {
        mobileDB.returnMobile(mobile, 3);
        boolean isReservable = mobileDB.reserveMobile(mobile, 2);
        Assert.assertEquals(true, isReservable);
    }

    @Test
    public void reserveMobileTypeTestFalse() {
        mobileDB.returnMobile(mobile, 3);
        boolean isReservable = mobileDB.reserveMobile(mobile, 4);
        Assert.assertEquals(false, isReservable);
    }

    @Test
    public void returnMobileTypeTestTrue() {
        boolean isReturned = mobileDB.returnMobile(mobile, 1);
        Assert.assertEquals(true, isReturned);
    }
}
