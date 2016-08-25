package hu.oktatas.java.ee.dto;

import hu.oktatas.java.ee.guestbook.Opinion;
import java.util.List;

public class ReportListDTO {
    
    private List<Opinion> reports;

    public ReportListDTO() {
        //Default constructor
    }

    public ReportListDTO(List<Opinion> reports) {
        this.reports = reports;
    }

    public List<Opinion> getReports() {
        return reports;
    }

    public void setReports(List<Opinion> reports) {
        this.reports = reports;
    } 
}
