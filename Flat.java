import java.util.*;

public class Flat
{
    public String street;
    public int buildingNo;
    public String buildingMeter;
    public int currentReading;
    public String currentReadingDate;
    public int previousReading;
    public String previousReadingDate;
    public ArrayList tenant;
        
    public Flat()
    {
        
    }
    
    public Flat(String street, int buildingNo, String buildingMeter, 
    int currentReading,String currentReadingDate, int previousReading, 
    String previousReadingDate, ArrayList tenant) 
    {
        this.street = street;
        this.buildingNo = buildingNo;
        this.buildingMeter = buildingMeter;
        this.currentReading = currentReading;
        this.currentReadingDate = currentReadingDate;
        this.previousReading = previousReading;
        this.previousReadingDate = previousReadingDate;
        this.tenant = tenant;
    }
    
    public String getstreet() {return street;}
    public int getbuildingNo() {return buildingNo;}
    public ArrayList getTenant() {return tenant;}
    public String getbuildingMeter() {return buildingMeter;}
    public int getcurrentReading() {return currentReading;}
    public String getcurrentReadingDate() {return currentReadingDate;}
    public int getpreviousReading() {return previousReading;}
    public String getpreviousReadingDate() {return previousReadingDate;}
    
    public void printFlatDetails()
    {
        System.out.printf("|%3s|%3d|%9s|%8d|%7s|%8d|%7s|\n", 
        street, buildingNo, buildingMeter, currentReading, currentReadingDate, 
        previousReading, previousReadingDate);
    }
    
    @Override
    public String toString() {
        return "Flat Tenants: "+ this.getTenant();
    }
    
    /*public void printTenants(ArrayList list) 
    {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.getTenant());
        }    
    }*/
}
