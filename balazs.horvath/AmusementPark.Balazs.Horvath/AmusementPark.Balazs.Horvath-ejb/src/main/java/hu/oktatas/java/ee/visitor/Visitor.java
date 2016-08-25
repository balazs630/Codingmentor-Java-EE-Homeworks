package hu.oktatas.java.ee.visitor;

import hu.oktatas.java.ee.amusementpark.AmusementPark;
import hu.oktatas.java.ee.amusementpark.Machine;
import hu.oktatas.java.ee.guestbook.Opinion;
import hu.oktatas.java.ee.exceptions.ParkIsFullException;
import hu.oktatas.java.ee.exceptions.VisitorAgeLimitException;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.EnumType.STRING;
import javax.persistence.Enumerated;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.validation.constraints.NotNull;
import static hu.oktatas.java.ee.visitor.VisitorStateEnum.NOT_ENTERED;
import static hu.oktatas.java.ee.visitor.VisitorStateEnum.INACTIVE;
import static hu.oktatas.java.ee.visitor.VisitorStateEnum.ACTIVE;

@Entity
public class Visitor implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer money;

    @NotNull
    private Integer age;

    @NotNull
    @Enumerated(STRING)
    @Column(name = "visitor_state")
    private VisitorStateEnum state = NOT_ENTERED;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "amusement_park_fk")
    private AmusementPark park;

    @Temporal(DATE)
    private Date entranceDate;

    public Visitor() {
        //Default constructor
    }

    public Visitor(String name, Integer money, Integer age) {
        this.name = name;
        this.money = money;
        this.age = age;
    }

    public void enterAmusementPark(AmusementPark park) throws ParkIsFullException {
        park.enterVisitor(this);
        this.setPark(park);
        this.setEntranceDate(new Date());
        this.setState(INACTIVE);
    }

    public void leaveAmusementPark(AmusementPark park) {
        park.leaveVisitor(this);
        this.setPark(null);
        this.setEntranceDate(null);
        this.setState(NOT_ENTERED);
    }

    public void rideMachine(Machine machine) throws VisitorAgeLimitException {
        machine.rideVisitor(this);
        this.setState(ACTIVE);
    }

    public void getOffMachine(Machine machine) {
        machine.getOffVisitor(this);
        this.setState(INACTIVE);
    }

    public void addReportToGuestBook(Opinion report) {
        if (null != park) {
            park.getGuestBook().addNewReport(report);
        }
    }

    public Opinion writeReport(String description) {
        return new Opinion(park.getId(), id, new Date(), description);
    }

    public void payEntranceFee(AmusementPark park) {
        money -= park.getEntranceFee();
        park.sellTicket();
    }

    public void payTicket(Machine machine) {
        money -= machine.getTicketPrice();
        machine.sellTicket();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VisitorStateEnum getState() {
        return state;
    }

    public void setState(VisitorStateEnum state) {
        this.state = state;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public AmusementPark getPark() {
        return park;
    }

    public void setPark(AmusementPark park) {
        this.park = park;
    }

    public Date getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(Date entranceDate) {
        this.entranceDate = entranceDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.money);
        hash = 97 * hash + Objects.hashCode(this.age);
        hash = 97 * hash + Objects.hashCode(this.state);
        hash = 97 * hash + Objects.hashCode(this.entranceDate);
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
        final Visitor other = (Visitor) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.money, other.money)) {
            return false;
        }
        if (!Objects.equals(this.age, other.age)) {
            return false;
        }
        if (this.state != other.state) {
            return false;
        }
        if (!Objects.equals(this.entranceDate, other.entranceDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Visitor{" + "id=" + id + ", name=" + name + ", money=" + money 
                + ", age=" + age + ", state=" + state + ", parkId=" 
                + park.getId() + ", entranceDate=" + entranceDate + '}';
    }
}
