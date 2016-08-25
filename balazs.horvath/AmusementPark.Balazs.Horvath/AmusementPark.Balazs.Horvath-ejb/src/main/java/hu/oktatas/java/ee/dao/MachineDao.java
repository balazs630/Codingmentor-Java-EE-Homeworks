package hu.oktatas.java.ee.dao;

import hu.oktatas.java.ee.amusementpark.Machine;
import hu.oktatas.java.ee.dto.VisitorListDTO;
import javax.ejb.Stateless;

@Stateless
public class MachineDao extends AbstractDao<Machine> {

    public MachineDao() {
        super(Machine.class);
    }
    
    public VisitorListDTO findRiders(Long machineId) {
        return new VisitorListDTO(find(machineId).getPeople());
    }
}
