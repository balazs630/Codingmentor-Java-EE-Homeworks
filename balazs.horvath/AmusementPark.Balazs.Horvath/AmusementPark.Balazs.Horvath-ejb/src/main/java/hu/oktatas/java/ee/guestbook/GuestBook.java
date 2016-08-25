package hu.oktatas.java.ee.guestbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class GuestBook implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<Opinion> opinions = new ArrayList<>();

    public GuestBook() {
        //Default constructor
    }

    public List<Opinion> getReports() {
        return opinions;
    }

    public Opinion addNewReport(Opinion report) {
        opinions.add(report);
        return report;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.opinions);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GuestBook other = (GuestBook) obj;
        if (!Objects.equals(this.opinions, other.opinions)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GuestBook{" + "reports=" + opinions + '}';
    }

}
