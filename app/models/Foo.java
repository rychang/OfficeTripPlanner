package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class Foo {

    public Foo(){
    }
    public Foo(String num, String date1, String date2){
        this.randomNumber = num;
        this.Date1 = date1;
        this.Date2 = date2;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private  String randomNumber;

    @Column(nullable = false)
    private String Date1;

    @Column(nullable = false)
    private String Date2;


    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getDate1() {
        return Date1;
    }

    public void setDate1(String Date1) {
        this.Date1 = Date1;
    }

    public String getDate2() {
        return Date2;
    }

    public void setDate2(String Date2) {
        this.Date2 = Date2;
    }
    /**
    @Override
    public String toString() {
        return "Historical weather details for" + location +
               "office starting st " +
               startDate +
               ".";
    }
    **/
}
