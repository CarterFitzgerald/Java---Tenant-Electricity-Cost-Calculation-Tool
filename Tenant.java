import java.util.*;

public class Tenant
{
    public String honorific;
    public String fName;
    public String lName;
    public String CN;
    public String meterNo;
    public int currentMeterReading;
    public String currentMeterDate;
    public int previousMeterReading;
    public String previousMeterDate;

    
    public Tenant()
    {
        
    }
    
    public Tenant(String honorific, String fName, 
                String lName, String CN, String meterNo, int currentMeterReading,
                String currentMeterDate, int previousMeterReading, 
                String previousMeterDate) 
    {
        this.honorific = honorific;
        this.fName = fName;
        this.lName = lName;
        this.CN = CN;
        this.meterNo = meterNo;
        this.currentMeterReading = currentMeterReading;
        this.currentMeterDate = currentMeterDate;
        this.previousMeterReading = previousMeterReading;
        this.previousMeterDate = previousMeterDate;
    }
    
    public String gethonorific() {return honorific;}
    public String getfName() {return fName;}
    public String getlName() {return lName;}
    public int getcurrentMeterReading() {return currentMeterReading;}
    public int getpreviousMeterReading() {return previousMeterReading;}
    public String getmeterNo() {return meterNo;}
    public String getcurrentMeterDate() {return currentMeterDate;}

    
    @Override
    public String toString() {
        return "First Name: "+ this.getfName() +
               "Last Name "+ this.getlName();
    }
}
