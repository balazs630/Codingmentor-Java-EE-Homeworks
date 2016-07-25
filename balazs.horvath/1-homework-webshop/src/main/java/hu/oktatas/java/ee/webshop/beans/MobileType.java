package hu.oktatas.java.ee.webshop.beans;

import hu.oktatas.java.ee.webshop.constraint.Manufacturer;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Manufacturer
public class MobileType {

    @NotNull
    @Size(min = 36, max = 36)
    private String id;

    @NotNull
    private ManufacturerType manufacturer;

    @NotNull
    @Size(min = 3)
    private String type;

    @NotNull
    @Min(1)
    private int price;

    @NotNull
    private Currency currency;

    @NotNull
    private Color color;

    public MobileType() {
        //Default constructor
    }

    public MobileType(String id, ManufacturerType manufacturer, String type, int price, Currency currency, Color color) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.type = type;
        this.price = price;
        this.currency = currency;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ManufacturerType getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerType manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
