package hu.oktatas.java.ee.dao;

import hu.oktatas.java.ee.dao.amusementpark.AmusementPark;
import hu.oktatas.java.ee.dto.MachineListDTO;
import hu.oktatas.java.ee.dto.VisitorListDTO;
import javax.ejb.Stateless;
import static hu.oktatas.java.ee.dao.visitor.VisitorStateEnum.INACTIVE;

@Stateless
public class AmusementParkDao extends AbstractDao<AmusementPark> {
    
    public AmusementParkDao() {
        super(AmusementPark.class);
    }
    
    public VisitorListDTO findVisitors(Long parkId) {
        return new VisitorListDTO(find(parkId).getVisitors());
    }
    
    public MachineListDTO findMachines(Long parkId) {
        return new MachineListDTO(find(parkId).getMachines());
    }
    
    public Long countVisitorsInRestState(Long parkId) {
        return entityManager
                .createQuery("SELECT COUNT(v.id) FROM Visitor v WHERE v.park.id = :parkId AND v.state = :state", Long.class)
                .setParameter("parkId", parkId)
                .setParameter("state", INACTIVE)
                .getSingleResult();
    }
    
    public Long countInactiveMachines(Long parkId) {
        return entityManager
                .createQuery("SELECT COUNT(m.id) FROM Machine m WHERE m.park.id = :parkId AND m.active = :active", Long.class)
                .setParameter("parkId", parkId)
                .setParameter("active", false)
                .getSingleResult();
    }
    
}
