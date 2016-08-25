package hu.oktatas.java.ee.dto;

import hu.oktatas.java.ee.visitor.Address;
import java.util.List;

public class AddressListDTO {
    
    private List<Address> addresses;

    public AddressListDTO() {
        //Default constructor
    }

    public AddressListDTO(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }     
}
