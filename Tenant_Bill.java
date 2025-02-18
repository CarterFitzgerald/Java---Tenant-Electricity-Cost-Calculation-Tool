import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tenant_Bill
{
    public String name;
    public String meter;
    public int cMeter;
    public int pMeter;
    public int usage;
    public double pcnt;
    public double baseC;
    public double adjC;
    public double totalC;
    
    public Tenant_Bill()
    {
        
    }

    public Tenant_Bill(String name, String meter, int cMeter, int pMeter, int usage
            , double pcnt, double baseC, double adjC, double totalC) {
        this.name = name;
        this.meter = meter;
        this.cMeter = cMeter;
        this.pMeter = pMeter;
        this.usage = usage;
        this.pcnt = pcnt;
        this.baseC = baseC;
        this.adjC = adjC;
        this.totalC = totalC;
    }
    
    public String getname() {return name;}
    public String getmeter() {return meter;}
    public int getcMeter() {return cMeter;}
    public int getpMeter() {return pMeter;}
    public int getusage() {return usage;}
    public double getpcnt() {return pcnt;}
    public double getbaseC() {return baseC;}
    public double getadjC() {return adjC;}
    public double gettotalC() {return totalC;}
    
    public void setpcnt(double value) {pcnt = value;}
    public void setbaseC(double value) {baseC = value;}
    public void setadjC(double value) {adjC = value;}
    public void settotalC(double value) {totalC = value;}
    
    public void infoString() {
        System.out.printf("%23s%8s%7d %-9d%-6d%-9.2f$%-8.2f$%-7.2f$%-9.2f%n", 
                        this.getname(), this.getmeter(), this.getcMeter(), 
                        this.getpMeter(), this.getusage(), this.getpcnt(), 
                            this.getbaseC(), this.getadjC(), this.gettotalC());
    }
}
