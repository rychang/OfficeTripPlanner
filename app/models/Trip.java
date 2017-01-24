package models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Trips", uniqueConstraints = {@UniqueConstraint(columnNames = {"location", "startDate", "endDate"})})
public class Trip {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private  String location;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocations(String location) {
        this.location = location;
    }

    public String getStart() {
        return startDate;
    }

    public void setStart(String startDate) {
        this.startDate = startDate;
    }

    public String getEnd() {
        return endDate;
    }

    public void setEnd(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Historical weather details for" + location +
               "office starting st " +
               startDate +
               ".";
    }

}