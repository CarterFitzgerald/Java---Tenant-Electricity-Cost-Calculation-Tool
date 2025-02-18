import java.util.*;

public class BC_Bill
{
    public int streetNum;
    public String streetName;
    public String meterNo;
    public int currentReading;
    public int previousReading;
    public String currentDate;
    public String previousDate;
    public int usage = 0;
    public double rate = 0.205;
    public double billUsage = 0;
    public double baseC = 0.00;
    public double adj = 0.00;
    public double total = 0.00;
    public ArrayList tenants;
    public double bc_bill;
    public double difference;
    
    public BC_Bill()
    {
        
    }

    public BC_Bill(int streetNum, String streetName, String meterNo, 
                int currentReading, int previousReading, String currentDate, 
                String previousDate, int usage, double baseC, 
                double adj, double total, ArrayList tenants, double bc_bill
                , double difference)
    {
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.meterNo = meterNo;
        this.currentReading = currentReading;
        this.usage = usage;
        this.previousReading = previousReading;
        this.currentDate = currentDate;
        this.previousDate = previousDate;
        this.baseC = baseC;
        this.adj = adj;
        this.total = total;
        this.tenants = tenants;
        this.bc_bill = bc_bill;
        this.difference = difference;
    }
    
    public void setstreetNum(int value) {streetNum = value;}
    public void setstreetName(String value) {streetName = value;}
    public void setmeterNo(String value) {meterNo = value;}
    public void setcurrentReading(int value) {currentReading = value;}
    public void setusage(int value) {usage = value;}
    public void setpreviousReading(int value) {previousReading = value;}
    public void setcurrentDate(String value) {currentDate = value;}
    public void setpreviousDate(String value) {previousDate = value;}
    public void setbaseC(double value) {baseC = value;}
    public void setadj(double value) {adj = value;}
    public void settotal(double value) {total = value;}
    public void settenants(ArrayList value) {tenants = value;}
    public void setbc_bill(double value) {bc_bill = value;}
    public void setdifference(double value) {difference = value;}
    
    public int getstreetNum() {return streetNum;}
    public String getstreetName() {return streetName;}
    public String getmeterNo() {return meterNo;}
    public int getcurrentReading() {return currentReading;}
    public int getusage() {return usage;}
    public int getpreviousReading() {return previousReading;}
    public String getcurrentDate() {return currentDate;}
    public String getpreviousDate() {return previousDate;}
    public double getbaseC() {return baseC;}
    public double getadj() {return adj;}
    public double gettotal() {return total;}
    public ArrayList gettenants() {return tenants;}
    public double getbc_bill() {return bc_bill;}
    public double getdifference() {return difference;}
    
    public static void addBills(ArrayList<Flat> flats, ArrayList<BC_Bill> bills) {
        double rate = 0.205;
        int usage1 = 0;
        for (Flat flat : flats) {
            String flatStreet = flat.getstreet();
            int flatBuildingNo = flat.getbuildingNo();
            String flatMeterNo = flat.getbuildingMeter();
            int flatCurrentReading = flat.getcurrentReading();
            int flatPreviousReading = flat.getpreviousReading();
            String flatCurrentDate = flat.getcurrentReadingDate();
            String flatPreviousDate = flat.getpreviousReadingDate();
            ArrayList<Tenant> flatTenants = flat.getTenant();
            if (flatCurrentReading < flatPreviousReading) {
                    usage1 = (flatCurrentReading + 1000000) - flatPreviousReading;
                } else {
                    usage1 = flatCurrentReading - flatPreviousReading;
                }
            double pcnt = 0.00;
            double baseC = 0.00;
            double adj = 0.00;
            double total = 0.00;
            double bc_bill = 0.00;
            double difference = 0.00;
            BC_Bill bcBill = new BC_Bill(flatBuildingNo, flatStreet, flatMeterNo,
                    flatCurrentReading, flatPreviousReading, flatCurrentDate, flatPreviousDate,
                    usage1, baseC, adj, total, flatTenants, bc_bill, difference);
            boolean isDuplicate = false;
            for (BC_Bill bill : bills) {
                if (bill.equals(bcBill)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                bills.add(bcBill);
            }
        }
    }
    
    public void infoString() {
        System.out.printf("%-5d%-26s%7.2f$%12.2f$%9.2f$%13.2f$%n", this.getstreetNum()
                , this.getstreetName(), this.getbc_bill(), this.getdifference(), this.getadj(), this.gettotal());
    }
}
