package hu.oktatas.java.ee.dto;

import hu.oktatas.java.ee.dao.amusementpark.AmusementPark;
import java.util.List;

public class AmusementParkListDTO {
    
    private List<AmusementPark> parks;

    public AmusementParkListDTO() {
        //Default constructor
    }

    public AmusementParkListDTO(List<AmusementPark> parks) {
        this.parks = parks;
    }

    public List<AmusementPark> getParks() {
        return parks;
    }

    public void setParks(List<AmusementPark> parks) {
        this.parks = parks;
    } 
}
