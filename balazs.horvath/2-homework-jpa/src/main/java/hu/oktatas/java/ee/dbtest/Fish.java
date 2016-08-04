package hu.oktatas.java.ee.dbtest;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Fish.countOfRedFishes",
            query = "SELECT COUNT(f) FROM Fish f WHERE "
            + "f.color=hu.oktatas.java.ee.dbtest.Color.RED")
})
public class Fish extends Animal implements Serializable {

    @Enumerated(EnumType.STRING)
    private Color color;
    private String livesAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;

    public Fish() {
        //Default constructor
    }

    public Fish(Color color, String livesAt, Owner owner) {
        this.color = color;
        this.livesAt = livesAt;
        this.owner = owner;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getLivesAt() {
        return livesAt;
    }

    public void setLivesAt(String livesAt) {
        this.livesAt = livesAt;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
