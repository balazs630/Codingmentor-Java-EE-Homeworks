package hu.oktatas.java.ee.webshop.beans;

import hu.oktatas.java.ee.webshop.constraint.Manufacturer;
import java.util.UUID;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Manufacturer
public class MobileType {

    @NotNull
    @Size(min = 36, max = 36)
    private final String id = UUID.randomUUID().toString();
    @NotNull
    private ManufacturerType manufacturer;
    @NotNull
    @Size(min = 3)
    private String type;
    @NotNull
    @Min(1)
    private int price;
    @NotNull
    private Currency coin;
    @NotNull
    private Color color;

    public MobileType(ManufacturerType manufacturer, String type, int price, Currency coin, Color color) {
        this.manufacturer = manufacturer;
        this.type = type;
        this.price = price;
        this.coin = coin;
        this.color = color;
    }

    public String getId() {
        return id;
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

    public Currency getCoin() {
        return coin;
    }

    public void setCoin(Currency coin) {
        this.coin = coin;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
