package hu.oktatas.java.ee.dao;

import hu.oktatas.java.ee.dao.visitor.Visitor;
import javax.ejb.Stateless;

@Stateless
public class VisitorDao extends AbstractDao<Visitor> {
    
    public VisitorDao() {
        super(Visitor.class);
    }
    
}
