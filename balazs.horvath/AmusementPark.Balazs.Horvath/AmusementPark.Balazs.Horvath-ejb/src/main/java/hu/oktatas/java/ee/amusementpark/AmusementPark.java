package hu.oktatas.java.ee.amusementpark;

import hu.oktatas.java.ee.visitor.Visitor;
import hu.oktatas.java.ee.visitor.Address;
import hu.oktatas.java.ee.guestbook.GuestBook;
import hu.oktatas.java.ee.exceptions.MachineNotAffordableException;
import hu.oktatas.java.ee.exceptions.ParkIsFullException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class AmusementPark implements Serializable {

    public static final String MACHINE_NAME_STRING = "Machine (name=";
    public static final String VISITOR_NAME_STRING = "Visitor (name=";
    public static final String ID_STRING = ", id=";
    public static final String EOF_STRING = ").";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    @Embedded
    private Address address;

    @NotNull
    private Integer fund;

    @NotNull
    private Integer spaceRequired;

    @NotNull
    private Integer entranceFee = 10;

    @NotNull
    @OneToMany(cascade = ALL)
    private List<Machine> machines = new ArrayList<>();

    @NotNull
    @OneToMany(cascade = ALL)
    private List<Visitor> visitors = new ArrayList<>();

    @OneToOne(cascade = PERSIST)
    private GuestBook guestBook = new GuestBook();

    public AmusementPark() {
        //Default constructor
    }

    public AmusementPark(String name, Address address, Integer fund, Integer spaceRequired) {
        this.name = name;
        this.address = address;
        this.fund = fund;
        this.spaceRequired = spaceRequired;
    }

    public Visitor enterVisitor(Visitor visitor) throws ParkIsFullException {
        if (entranceFee <= visitor.getMoney()) {
            return addVisitor(visitor);
        }
        throw new ParkIsFullException(visitor.toString());
    }

    public Visitor leaveVisitor(Visitor visitor) {
        if (visitors.contains(visitor)) {
            return removeVisitor(visitor);
        }
        throw new IllegalArgumentException(visitor.toString());
    }

    public Machine buyNewMachine(Machine machine) throws MachineNotAffordableException {
        if (isPlaceable(machine) && isPayable(machine)) {
            return addNewMachine(machine);
        }
        throw new MachineNotAffordableException(machine.toString());
    }

    public Machine removeOldMachine(Machine machine) {
        if (machines.contains(machine) && !machine.getActive()) {
            return removeMachine(machine);
        }
        throw new IllegalArgumentException(machine.toString());
    }

    public void getIncomeFromMachine(Integer income) {
        fund += income;
    }

    public void sellTicket() {
        fund += entranceFee;
    }

    private boolean isPlaceable(Machine machine) {
        return spaceRequired >= machine.getSpaceRequired();
    }

    private boolean isPayable(Machine machine) {
        return fund >= machine.getMachinePrice();
    }

    private Machine addNewMachine(Machine machine) {
        machines.add(machine);
        machine.setPark(this);
        fund -= machine.getMachinePrice();
        spaceRequired -= machine.getSpaceRequired();
        return machine;
    }

    private Machine removeMachine(Machine machine) {
        machines.remove(machine);
        machine.setActive(false);
        machine.setPark(null);
        fund += machine.getMachinePrice();
        spaceRequired += machine.getSpaceRequired();
        return machine;
    }

    private Visitor addVisitor(Visitor visitor) {
        visitor.payEntranceFee(this);
        visitors.add(visitor);
        return visitor;
    }

    private Visitor removeVisitor(Visitor visitor) {
        visitors.remove(visitor);
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getFund() {
        return fund;
    }

    public void setFund(Integer fund) {
        this.fund = fund;
    }

    public Integer getSpaceRequired() {
        return spaceRequired;
    }

    public void setSpaceRequired(Integer spaceRequired) {
        this.spaceRequired = spaceRequired;
    }

    public Integer getEntranceFee() {
        return entranceFee;
    }

    public void setEntranceFee(Integer entranceFee) {
        this.entranceFee = entranceFee;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public GuestBook getGuestBook() {
        return guestBook;
    }

    public void setGuestBook(GuestBook guestBook) {
        this.guestBook = guestBook;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + Objects.hashCode(this.address);
        hash = 19 * hash + Objects.hashCode(this.fund);
        hash = 19 * hash + Objects.hashCode(this.spaceRequired);
        hash = 19 * hash + Objects.hashCode(this.entranceFee);
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
        final AmusementPark other = (AmusementPark) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.fund, other.fund)) {
            return false;
        }
        if (!Objects.equals(this.spaceRequired, other.spaceRequired)) {
            return false;
        }
        if (!Objects.equals(this.entranceFee, other.entranceFee)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AmusementPark{" + "id=" + id + ", name=" + name + ", address=" 
                + address + ", fund=" + fund + ", area=" + spaceRequired 
                + ", entranceFee=" + entranceFee + ", machines=" + machines 
                + ", visitors=" + visitors + ", guestBook=" + guestBook + '}';
    }
}
