package hu.oktatas.java.ee.amusementpark;

import hu.oktatas.java.ee.visitor.Visitor;
import hu.oktatas.java.ee.exceptions.VisitorAgeLimitException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static javax.persistence.CascadeType.ALL;
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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Machine implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer machinePrice;

    @NotNull
    @Column(name = "area_required")
    private Integer spaceRequired;

    @NotNull
    @Enumerated(STRING)
    private MachineTypeEnum type;

    @NotNull
    private Integer income = 100;

    @NotNull
    private Integer ticketPrice = 10;

    @NotNull
    private Integer ageLimit = 12;

    @NotNull
    private Integer maxPeopleUsing = 20;

    @NotNull
    private Boolean active = false;

    @NotNull
    @OneToMany(cascade = ALL)
    private List<Visitor> people = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "amusement_park_fk")
    private AmusementPark park;

    public Visitor rideVisitor(Visitor visitor) throws VisitorAgeLimitException {
        if (this.getActive() && isVisitorLegal(visitor)) {
            return addRider(visitor);
        }
        throw new VisitorAgeLimitException(visitor.toString());
    }

    public Visitor getOffVisitor(Visitor visitor) {
        if (people.contains(visitor)) {
            return removeVisitor(visitor);
        }
        throw new IllegalArgumentException(visitor.toString());
    }

    public void transferIncomeToPark() {
        if (null != park) {
            park.getIncomeFromMachine(income);
            income = 100;
        }
    }

    public void sellTicket() {
        income += ticketPrice;
    }

    private boolean isVisitorLegal(Visitor visitor) {
        return visitor.getPark().equals(park)
                && ageLimit <= visitor.getAge()
                && ticketPrice <= visitor.getMoney()
                && people.size() < maxPeopleUsing;
    }

    private Visitor addRider(Visitor visitor) {
        visitor.payTicket(this);
        people.add(visitor);
        return visitor;
    }

    private Visitor removeVisitor(Visitor visitor) {
        people.remove(visitor);
        return visitor;
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

    public Integer getMachinePrice() {
        return machinePrice;
    }

    public void setMachinePrice(Integer machinePrice) {
        this.machinePrice = machinePrice;
    }

    public Integer getSpaceRequired() {
        return spaceRequired;
    }

    public void setSpaceRequired(Integer spaceRequired) {
        this.spaceRequired = spaceRequired;
    }

    public MachineTypeEnum getType() {
        return type;
    }

    public void setType(MachineTypeEnum type) {
        this.type = type;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public Integer getMaxPeopleUsing() {
        return maxPeopleUsing;
    }

    public void setMaxPeopleUsing(Integer maxPeopleUsing) {
        this.maxPeopleUsing = maxPeopleUsing;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Visitor> getPeople() {
        return people;
    }

    public void setPeople(List<Visitor> people) {
        this.people = people;
    }

    public AmusementPark getPark() {
        return park;
    }

    public void setPark(AmusementPark park) {
        this.park = park;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.machinePrice);
        hash = 89 * hash + Objects.hashCode(this.spaceRequired);
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + Objects.hashCode(this.income);
        hash = 89 * hash + Objects.hashCode(this.ticketPrice);
        hash = 89 * hash + Objects.hashCode(this.ageLimit);
        hash = 89 * hash + Objects.hashCode(this.maxPeopleUsing);
        hash = 89 * hash + Objects.hashCode(this.active);
        hash = 89 * hash + Objects.hashCode(this.people);
        hash = 89 * hash + Objects.hashCode(this.park);
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
        final Machine other = (Machine) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.machinePrice, other.machinePrice)) {
            return false;
        }
        if (!Objects.equals(this.spaceRequired, other.spaceRequired)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.income, other.income)) {
            return false;
        }
        if (!Objects.equals(this.ticketPrice, other.ticketPrice)) {
            return false;
        }
        if (!Objects.equals(this.ageLimit, other.ageLimit)) {
            return false;
        }
        if (!Objects.equals(this.maxPeopleUsing, other.maxPeopleUsing)) {
            return false;
        }
        if (!Objects.equals(this.active, other.active)) {
            return false;
        }
        if (!Objects.equals(this.people, other.people)) {
            return false;
        }
        if (!Objects.equals(this.park, other.park)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Machine{" + "id=" + id + ", name=" + name + ", machinePrice="
                + machinePrice + ", spaceRequired=" + spaceRequired + ", type="
                + type + ", income=" + income + ", ticketPrice=" + ticketPrice
                + ", ageLimit=" + ageLimit + ", maxPeopleUsing=" + maxPeopleUsing
                + ", active=" + active + ", people=" + people + ", park="
                + park + '}';
    }

}
