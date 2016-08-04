package hu.oktatas.java.ee.dbtest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Cat.weightMoreThan5Kg", 
            query = "SELECT c FROM Cat c WHERE c.weight>5 ORDER BY c.name"),
    @NamedQuery(name = "Cat.notLikesMouse", 
            query = "SELECT c FROM Cat c WHERE c.likesMouse=FALSE")
})
public class Cat extends Animal implements Serializable {

    private Boolean likesMouse;
    private int weight;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "fishesEaten")
    @Column(name = "value")
    private List<Fish> fishesEaten = new ArrayList<>();

    public Cat() {
        //Default constructor
    }

    public Cat(Boolean likesMouse, int weight) {
        this.likesMouse = likesMouse;
        this.weight = weight;
    }

    public Boolean getLikesMouse() {
        return likesMouse;
    }

    public void setLikesMouse(Boolean likesMouse) {
        this.likesMouse = likesMouse;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Fish> getFishesEaten() {
        return fishesEaten;
    }

    public void setFishesEaten(List<Fish> fishesEaten) {
        this.fishesEaten = fishesEaten;
    }
}
