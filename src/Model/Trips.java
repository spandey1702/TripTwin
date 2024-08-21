package Model;

import java.sql.Date;

public class Trips 
{
    private int tripID;
    private String Destination;
    private String starDate;
    private String endDate;
    private Double budget;
    private String Description;

    public Trips(String Destination, String StartDate,String EndDate)
    {
        this.Destination=Destination;
        this.starDate=StartDate;
        this.endDate=EndDate;

    }
    
    public int getTripID() 
    {
        return tripID;
    }
    public void setTripID(int tripID) 
    {
        this.tripID = tripID;
    }
    public String getDestination() 
    {
        return Destination;
    }
    public void setDestination(String destination) 
    {
        Destination = destination;
    }
    public String getStarDate() 
    {
        return starDate;
    }
    public void setStarDate(String starDate) 
    {
        this.starDate = starDate;
    }
    public String getEndDate() 
    {
        return endDate;
    }
    public void setEndDate(String endDate) 
    {
        this.endDate = endDate;
    }
    public Double getBudget() {
        return budget;
    }
    public void setBudget(Double budget) {
        this.budget = budget;
    }
    public String getDescription() 
    {
        return Description;
    }
    public void setDescription(String description) 
    {
        Description = description;
    }

}
