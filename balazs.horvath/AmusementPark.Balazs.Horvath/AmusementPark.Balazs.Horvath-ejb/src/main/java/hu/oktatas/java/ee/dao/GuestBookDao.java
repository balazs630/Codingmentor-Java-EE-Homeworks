package hu.oktatas.java.ee.dao;

import hu.oktatas.java.ee.guestbook.GuestBook;
import javax.ejb.Stateless;

@Stateless
public class GuestBookDao extends AbstractDao<GuestBook> {
    
    public GuestBookDao() {
        super(GuestBook.class);
    } 
}
