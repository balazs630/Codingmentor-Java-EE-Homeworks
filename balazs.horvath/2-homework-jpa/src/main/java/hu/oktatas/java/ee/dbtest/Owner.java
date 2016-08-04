package hu.oktatas.java.ee.dbtest;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Owner implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "owner")
    private List<Fish> fishes = new ArrayList<>();

    public Owner() {
        //Default constructor
    }

    public Owner(String name) {
        this.name = name;
    }

    public List<Fish> getFishes() {
        return fishes;
    }

    public void setFishes(List<Fish> fishes) {
        this.fishes = fishes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}