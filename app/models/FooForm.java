package models;

import play.data.validation.Constraints.Required;

public class FooForm {
    @Required(message = "You must select a number.")
    private String randomNumber;

    @Required(message = "You must select a start date for you trip.")
    private String date1;

    private String date2;

    public String getRandomNumber() {
        return randomNumber;
    }

    public String getDate1() {
        return date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public void setRandomNumber(String num) {
        this.randomNumber = num;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

}
