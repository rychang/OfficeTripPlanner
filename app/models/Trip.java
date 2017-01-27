package models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "Trips")
public class Trip {

    


    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "location", nullable = false)
    private  String location;

    @Column(name = "startDate", unique =  true, nullable = false)
    private String startDate;

    @Column(name = "endDate", unique = true, nullable = false)
    private String endDate;

    public Integer getId() {
        return id;
    }

    public String getLocation() { return location; }

    public String getStartDate() { return startDate; }

    public String getEndDate() { return endDate; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLocation(String location) { this.location = location; }


    public void setStartDate(String startDate) { this.startDate = startDate; }


    public void setEndDate(String endDate) { this.endDate = endDate; }

    /*
    @Override
    public String toString() {
        return "Historical weather details for" + location +
               "office starting at " +
               startDate +
               ".";
    }
    */

}