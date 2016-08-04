package hu.oktatas.java.ee.dbtest;

import java.io.Serializable;
import javax.persistence.Entity;
import java.util.Calendar;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Dog extends Animal implements Serializable {

    private Boolean likesCat;
    @Temporal(TemporalType.DATE)
    @OrderBy("dateOfBirth ASC")
    private Calendar dateOfBirth;

    public Dog() {
        //Default constructor
    }

    public Dog(Boolean likesCat, Calendar birtofDate) {
        this.likesCat = likesCat;
        this.dateOfBirth = birtofDate;
    }

    public Boolean getLikesCat() {
        return likesCat;
    }

    public void setLikesCat(Boolean likesCat) {
        this.likesCat = likesCat;
    }

    public Calendar getBirtofDate() {
        return dateOfBirth;
    }

    public void setBirtofDate(Calendar birtofDate) {
        this.dateOfBirth = birtofDate;
    }

}