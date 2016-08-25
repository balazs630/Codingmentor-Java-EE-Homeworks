package hu.oktatas.java.ee.dao;

import hu.oktatas.java.ee.guestbook.Opinion;
import hu.oktatas.java.ee.dto.ReportListDTO;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class ReportDao extends AbstractDao<Opinion> {
    
    public ReportDao() {
        super(Opinion.class);
    }
    
    public ReportListDTO findReportsOfVisitorWrittenInPark(Long visitorId, Long parkId) {
        List<Opinion> reports = entityManager
                .createQuery("SELECT r FROM Report r WHERE r.visitorId = :visitorId AND r.parkId = :parkId")
                .setParameter("visitorId", visitorId)
                .setParameter("parkId", parkId)
                .getResultList();
        return new ReportListDTO(reports);
    }
    
}
