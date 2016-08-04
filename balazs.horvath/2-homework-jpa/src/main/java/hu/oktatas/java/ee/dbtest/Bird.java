package hu.oktatas.java.ee.dbtest;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@NamedQueries({
    @NamedQuery(name = "Bird.speedOver50AndNotFly",
            query = "SELECT b FROM Bird b WHERE b.isFly=FALSE AND b.speed>50"),
    @NamedQuery(name = "Bird.livesInEurope",
            query = "SELECT b FROM Bird b WHERE 'Europe' MEMBER OF b.livesAt")
})
public class Bird extends Animal implements Serializable {

    private Boolean isFly;
    private Integer speed;

    @Transient
    private String spiece;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "livesAt")
    private List<String> livesAt = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "biggestEnemy")
    private Cat biggestEnemy;

    public Bird() {
        //Default constructor
    }

    public Bird(Boolean isFly, Integer speed) {
        this.isFly = isFly;
        this.speed = speed;
    }

    public Bird(Boolean isFly, Integer speed, Cat biggestEnemy) {
        this.isFly = isFly;
        this.speed = speed;
        this.biggestEnemy = biggestEnemy;
    }

    public Boolean getIsFly() {
        return isFly;
    }

    public void setIsFly(Boolean isFly) {
        this.isFly = isFly;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public List<String> getLivesAt() {
        return livesAt;
    }

    public void setLivesAt(List<String> livesAt) {
        this.livesAt = livesAt;
    }

    public Cat getBiggestEnemy() {
        return biggestEnemy;
    }

    public void setBiggestEnemy(Cat biggestEnemy) {
        this.biggestEnemy = biggestEnemy;
    }

    public String getSpiece() {
        return spiece;
    }

    public void setSpiece(String spiece) {
        this.spiece = spiece;
    }

}
