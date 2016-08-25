package hu.oktatas.java.ee.guestbook;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.validation.constraints.NotNull;

@Entity
public class Opinion implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long parkId;

    @NotNull
    private Long visitorId;

    @NotNull
    @Temporal(DATE)
    private Date dateOfReport;

    private String description;

    public Opinion() {
        //Default constructor
    }

    public Opinion(Long parkId, Long visitorId, Date dateOfReport, String description) {
        this.parkId = parkId;
        this.visitorId = visitorId;
        this.dateOfReport = dateOfReport;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParkId() {
        return parkId;
    }

    public void setParkId(Long parkId) {
        this.parkId = parkId;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public Date getDateOfReport() {
        return dateOfReport;
    }

    public void setDateOfReport(Date dateOfReport) {
        this.dateOfReport = dateOfReport;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.parkId);
        hash = 89 * hash + Objects.hashCode(this.visitorId);
        hash = 89 * hash + Objects.hashCode(this.dateOfReport);
        hash = 89 * hash + Objects.hashCode(this.description);
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
        final Opinion other = (Opinion) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.parkId, other.parkId)) {
            return false;
        }
        if (!Objects.equals(this.visitorId, other.visitorId)) {
            return false;
        }
        if (!Objects.equals(this.dateOfReport, other.dateOfReport)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Opinion{" + "id=" + id + ", parkId=" + parkId + ", visitorId=" 
                + visitorId + ", dateOfReport=" + dateOfReport 
                + ", description=" + description + '}';
    }

}
