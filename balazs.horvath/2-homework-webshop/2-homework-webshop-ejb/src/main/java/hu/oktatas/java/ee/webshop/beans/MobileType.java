package hu.oktatas.java.ee.webshop.beans;

import hu.oktatas.java.ee.webshop.constraint.Manufacturer;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import hu.oktatas.java.ee.webshop.annotations.Validate;
import java.io.Serializable;

@Validate
@Manufacturer
public class MobileType implements Serializable {

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.manufacturer);
        hash = 73 * hash + Objects.hashCode(this.type);
        hash = 73 * hash + this.price;
        hash = 73 * hash + Objects.hashCode(this.currency);
        hash = 73 * hash + Objects.hashCode(this.color);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MobileType other = (MobileType) obj;
        if (this.price != other.price) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (this.manufacturer != other.manufacturer) {
            return false;
        }
        if (this.currency != other.currency) {
            return false;
        }
        if (this.color != other.color) {
            return false;
        }
        return true;
    }

}
