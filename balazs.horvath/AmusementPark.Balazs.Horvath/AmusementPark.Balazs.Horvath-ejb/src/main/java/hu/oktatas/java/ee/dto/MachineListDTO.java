package hu.oktatas.java.ee.dto;

import hu.oktatas.java.ee.dao.amusementpark.Machine;
import java.util.List;

public class MachineListDTO {
    
    private List<Machine> machines;

    public MachineListDTO() {
        //Default constructor
    }

    public MachineListDTO(List<Machine> machines) {
        this.machines = machines;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    } 
}
