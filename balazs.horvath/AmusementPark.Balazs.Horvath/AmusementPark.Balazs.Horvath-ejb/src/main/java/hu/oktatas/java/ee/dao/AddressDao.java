package hu.oktatas.java.ee.dao;

import hu.oktatas.java.ee.dao.visitor.Address;
import javax.ejb.Stateless;

@Stateless
public class AddressDao extends AbstractDao<Address> {
    
    public AddressDao() {
        super(Address.class);
    }
    
}
