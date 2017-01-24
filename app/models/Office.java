package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Office {

    @Id
    @GeneratedValue
    public Long id;


    @Column(unique=true, nullable=false)
    public String officeLoc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoc() {
        return officeLoc;
    }

    public void setLoc(String officeLoc) {
        this.officeLoc = officeLoc;
    }

}
