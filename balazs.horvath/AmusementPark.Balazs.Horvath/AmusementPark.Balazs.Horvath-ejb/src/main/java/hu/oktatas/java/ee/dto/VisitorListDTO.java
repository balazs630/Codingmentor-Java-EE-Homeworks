package hu.oktatas.java.ee.dto;

import hu.oktatas.java.ee.dao.visitor.Visitor;
import java.util.List;

public class VisitorListDTO {
    
    private List<Visitor> visitors;

    public VisitorListDTO() {
        //Default constructor
    }

    public VisitorListDTO(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }
}
