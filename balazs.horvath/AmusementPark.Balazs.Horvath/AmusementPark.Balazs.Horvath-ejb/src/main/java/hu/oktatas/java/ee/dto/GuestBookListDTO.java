package hu.oktatas.java.ee.dto;

import hu.oktatas.java.ee.guestbook.GuestBook;
import java.util.List;

public class GuestBookListDTO {
    
    private List<GuestBook> guestbooks;

    public GuestBookListDTO() {
        //Default constructor
    }

    public GuestBookListDTO(List<GuestBook> books) {
        this.guestbooks = books;
    }

    public List<GuestBook> getBooks() {
        return guestbooks;
    }

    public void setBooks(List<GuestBook> books) {
        this.guestbooks = books;
    }
}
