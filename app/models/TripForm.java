package models;

import play.data.validation.Constraints.Required;

public class TripForm {

    @Required(message = "You must select on office location.")
    private String officeLoc;

    @Required(message = "You must select a start date for your trip.")
    private String startDate;

    @Required(message = "You must select an end date for your trip.")
    private String endDate;

    public String getOfficeLoc() {
        return officeLoc;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setOfficeLoc(String officeLoc) {
        this.officeLoc = officeLoc;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

}
