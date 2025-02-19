/*
 * @author (Carter Fitzgerald)
 * @version (31/03/23)
 */

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class main
{
    String meterFileName = "";
    String flatFileName = "";
    ArrayList<Tenant> tenants = new ArrayList<>();
    ArrayList<Flat> flats = new ArrayList<>();
    public static ArrayList<BC_Bill> bills = new ArrayList<>();
    public ArrayList<Tenant_Bill> tBills = new ArrayList<>();

    
    public main()
    {
       
    }
    
    public void readMeterFile(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                } else {
                    String[] splitArray = line.split(",");
            
                    int int_CMeterReading = Integer.parseInt(splitArray[5]);
                    int int_PMeterReading = Integer.parseInt(splitArray[7]);

                    Tenant Tenant = new Tenant(splitArray[0], splitArray[1], 
                    splitArray[2], splitArray[3], splitArray[4], int_CMeterReading, 
                    splitArray[6], int_PMeterReading, splitArray[8]);
            
                    tenants.add(Tenant);
                }
            }
            
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void readFlatFile(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                } else {
                    String[] splitArray = line.split(",");
                    int size = splitArray.length;
                        
                    int int_buildingNo = Integer.parseInt(splitArray[1]);
                    int int_CReading = Integer.parseInt(splitArray[3]);
                    int int_PReading = Integer.parseInt(splitArray[5]);
            
                    ArrayList<String> tenantAList = new ArrayList<String>();
            
                    for(int i = 7; i < size; i++) 
                    {
                        tenantAList.add(splitArray[i]);
                    }
        
                    Flat Flat = new Flat(splitArray[0], int_buildingNo, 
                    splitArray[2], int_CReading, splitArray[4], int_PReading, 
                    splitArray[6], tenantAList);
                    
                    flats.add(Flat);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public static void main (String[] args)
    {
        main main = new main();
        System.out.print("\u000c");
        main.menu();
    }
    
    public void menu()
    {
        String menuOpt = ""; 
        Scanner in = new Scanner(System.in);
        do 
        {
            System.out.printf("\nST2-2023 Assignment 1\n");
            System.out.printf("E - Exit\n");
            System.out.printf("F - Read Flats (Task 1)\n");
            System.out.printf("M - Read Meters (Task 1)\n");
            System.out.printf("C - Compute BC Bill For one Flat (Task2)\n");
            System.out.printf("A - Compute BC Bill For All Flats (Task2)\n");
            System.out.printf("S - Sort the meter file into meter order (Task3)\n");
            System.out.printf("P – Prove meter file sort and find (task3) \n");
            System.out.printf("O - Compute Full Bill For One Flat (Task4)\n");
            System.out.printf("5 - Compute Full Bill For All Flats (Task5)\n");
            System.out.printf("0 - Set Dev0 environment\n");
            System.out.printf("1 - Set Dev1 environment\n");
            System.out.printf("2 - Set Test environment\n");
            System.out.printf("3 - Set prod environment\n");
            System.out.printf("Select Option:\n");
            
            menuOpt = in.nextLine();
            if (menuOpt.compareToIgnoreCase("F") == 0) {optF();}
            if (menuOpt.compareToIgnoreCase("M") == 0) {optM();}
            if (menuOpt.compareToIgnoreCase("C") == 0) {optC();}
            if (menuOpt.compareToIgnoreCase("A") == 0) {optA();}
            if (menuOpt.compareToIgnoreCase("S") == 0) {optS();}
            if (menuOpt.compareToIgnoreCase("P") == 0) {optP();}
            if (menuOpt.compareToIgnoreCase("O") == 0) {optO();}
            if (menuOpt.compareToIgnoreCase("5") == 0) {opt5();}
            if (menuOpt.compareToIgnoreCase("0") == 0) {opt0();}
            if (menuOpt.compareToIgnoreCase("1") == 0) {opt1();}
            if (menuOpt.compareToIgnoreCase("2") == 0) {opt2();}
            if (menuOpt.compareToIgnoreCase("3") == 0) {opt3();} 
        } while (menuOpt.compareToIgnoreCase("E")!=0);  
        System.out.printf("\nEnding Now...\n");
        }
    
    public void optF()
    {
        // F - Read Flats (Task 1) Goes Here
        System.out.printf("Reading flat file "+flatFileName+"\n");
        readFlatFile(flatFileName);
        System.out.printf("Number of Flats Read in is: %d%n", flats.size());
        
        int count = 0;
        for (Flat Flats:flats) {
            ArrayList<Integer> arrayListToCount = Flats.getTenant();   
            count += arrayListToCount.size();
        }
        System.out.printf("Number of Meters Read in is: %d%n", count);        

        double sCRCount = 0.00;
        for (Flat Flats:flats) {
            sCRCount += Flats.getcurrentReading(); 
        }
        System.out.printf("Total sum (checksum) of all current flat readings is: %.7E%n", sCRCount); 

        int stCRCount = 0;
        for (Flat Flats:flats) {
            stCRCount += Flats.getcurrentReading(); 
        }
        System.out.printf("Total sum (checksum) of all current flat readings is: %,d%n", stCRCount);
    }   
    
    public void optM()
    {
        // M - Read Meters (Task 1)
        System.out.printf("Reading meter file "+meterFileName+"\n");
        readMeterFile(meterFileName);
        System.out.printf("%nNumber of Meters Read in is: %d%n", tenants.size());

        double sCRCount = 0.00;
        for (Tenant Tenants:tenants) {
            sCRCount += Tenants.getcurrentMeterReading(); 
        }
        System.out.printf("Total sum (checksum) of all current meter readings is: %.8E%n", sCRCount); 
    
        int stCRCount = 0;
        for (Tenant Tenants:tenants) {
            stCRCount += Tenants.getcurrentMeterReading(); 
        }
        System.out.printf("Total sum (checksum) of all current meter readings is: %,d%n", stCRCount);
    }   
    
    public void optC()
    {
        // C - Compute BC Bill For one Flat (Task2)
        double rate = 0.205;
        int billusage = 0;
        double billTUsage = 0.00;
        int billCurrentReading = 0;
        int billPreviousReading = 0;
        String billCurrentDate = "";
        String billPreviousDate = "";
        
        System.out.println("Compute bill for one Block of flats:");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Street Number: ");
        int streetNum = Integer.parseInt(input.nextLine());
        System.out.print("Enter Street Name: ");
        String streetName = input.nextLine();
        System.out.printf("Showing Bill for:%3d %13s%n", streetNum, streetName);
        System.out.printf("-----------------------------%n");
        
        BC_Bill.addBills(flats, bills);
                
        for (BC_Bill Bills: bills) {
            if (Bills.getstreetNum() == streetNum && Bills.getstreetName().equals(streetName)) {
                billCurrentReading = Bills.getcurrentReading();
                billPreviousReading = Bills.getpreviousReading();
                billCurrentDate = Bills.getcurrentDate();
                billPreviousDate = Bills.getpreviousDate();
                billusage = Bills.getusage();
                billTUsage = rate * billusage;
                break;
            }
        }
        System.out.printf("Current  meter reading %6d %11s%n", billCurrentReading, billCurrentDate);
        System.out.printf("Previous meter reading %6d %11s%n", billPreviousReading, billPreviousDate);
        
        System.out.printf("Usage%24d%n", billusage);
        System.out.printf("Rate%21s/kwh%n", rate);
        System.out.printf("Bill Usage%19s%8.2f%n", "$", billTUsage);
    }   
    
    public void optA()
    {
        // A - Compute BC Bill For All Flats (Task2)
        int flatCurrentReading = 0;
        int flatPreviousReading = 0;
        String flatCurrentDate = "";
        String flatPreviousDate = "";
        double totalBill = 0.00;
        
        System.out.printf("Compute bill for all Blocks of flats:%n");
        System.out.println("       Total for All Flats");
        System.out.println("--------------------------");
        
        for (Flat Flats: flats) {
            flatCurrentReading = Flats.getcurrentReading();
            flatPreviousReading = Flats.getpreviousReading();
            flatCurrentDate = Flats.getcurrentReadingDate();
            flatPreviousDate = Flats.getpreviousReadingDate();
            
            int usage = flatCurrentReading - flatPreviousReading;
            double rate = 0.205;
            double billUsage = rate * usage;
            
            totalBill += billUsage;
        }
        System.out.printf("Total            :%,15.2f%n", totalBill);
        System.out.printf("Records Processed:%15d%n", flats.size());
        
    }   
    
    public void optS()
    {
        // S - Sort the meter file into meter order (Task3)
        bubbleSort(tenants);
        for (int i = 0; i < tenants.size(); i++) {
            if (i == 0 || i == 9 || i == tenants.size() - 1) {
                try
                {
                    SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = inputFormat.parse(tenants.get(i).getcurrentMeterDate());
                    SimpleDateFormat outputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    String outputDate = outputFormat.format(date);
                    
                    String check = "";
                if (i < tenants.size()-1) {
                    check = "Check";
                } else {
                    check = "Last";
                }
                
                Tenant tenant = tenants.get(i);
                String fname = tenant.getfName();
                String lname = tenant.getlName();
                String meter = tenant.getmeterNo();
                int currentReading = tenant.getcurrentMeterReading();
                
                System.out.printf("%6s[ %d] %s %s %s %d %s%n", check, i, fname, lname, meter, currentReading, outputDate);
                }
                catch (java.text.ParseException pe)
                {
                    pe.printStackTrace();
                }
            }
        }
    }   
    
    public void optP() 
    {
        //P – Prove meter file sort and find (task3) 
        long start;
        long end;
        long diff;
        
        optS();
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) 
        {
            sequentialSearch("m163987");
            sequentialSearch("m163966");
            sequentialSearch("m163973");
        }
        end = System.currentTimeMillis();
        diff = end - start;
        System.out.printf("%nFind Sequential: "+diff+" milliseconds.%n");
        
        start = System.currentTimeMillis();
        int size = tenants.size();
        for (int i = 0; i < 10000; i++) 
        {
            binarySearch("m163987", 0, size);
            binarySearch("m163966", 0, size);
            binarySearch("m163973", 0, size);
        }
        end = System.currentTimeMillis();
        diff = end - start;
        System.out.println("Find Binary    : "+diff+" milliseconds.");
    }
    
    public void optO()
    {
        // O - Compute Full Bill For One Flat (Task4)
        double rate = 0.205;
        int billusage = 0;
        double billTUsage = 0.00;
        int billCurrentReading = 0;
        int billPreviousReading = 0;
        String billCurrentDate = "";
        String billPreviousDate = "";
        String honorificT;
        String fNameT;
        String lNameT;
        String meterNoT;
        int cMeterReadingT;
        int pMeterReadingT;
        double percentUT;
        int usageCulmative = 0;
        double baseCulmative = 0.00;
        double totalCulmative = 0.00;
        int usage;
        
        
        System.out.println("Compute bill for one Block of flats:");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Street Number: ");
        int streetNum = Integer.parseInt(input.nextLine());
        System.out.print("Enter Street Name: ");
        String streetName = input.nextLine();
        System.out.printf("Showing Bill for:%3d %13s%n", streetNum, streetName);
        System.out.printf("-----------------------------%n");
        
        BC_Bill.addBills(flats, bills);
        
        for (BC_Bill Bills: bills) {
            if (Bills.getstreetNum() == streetNum && Bills.getstreetName().equals(streetName)) {
                billCurrentReading = Bills.getcurrentReading();
                billPreviousReading = Bills.getpreviousReading();
                billCurrentDate = Bills.getcurrentDate();
                billPreviousDate = Bills.getpreviousDate();
                billusage = Bills.getusage();
                billTUsage = rate * billusage;
                
                System.out.printf("Current  meter reading %6d %11s%n", billCurrentReading, billCurrentDate);
                System.out.printf("Previous meter reading %6d %11s%n", billPreviousReading, billPreviousDate);
                
                System.out.printf("Usage%24d%n", billusage);
                System.out.printf("Rate%21s/kwh%n", rate);
                System.out.printf("Bill Usage%19s%8.2f%n", "$", billTUsage);
                
                System.out.printf("%n%-24s%-8s%-7s%-9s%-6s%-9s%-9s%-7s%-11s%n", "Tenant", 
                        "Meter", "Curr", "Prev", "Usage", "Pcnt%", "Base", "Adj", "Total");
                System.out.printf("----------------------------------------------------------------------------------%n");
                
                for (Object tenantID : Bills.gettenants()) {
                    String id = (String) tenantID;
                    for (Tenant tenant : tenants) {
                        if (tenant.getmeterNo().equals(id)) {
                            honorificT = tenant.gethonorific();
                            fNameT = tenant.getfName();
                            lNameT = tenant.getlName();
                            cMeterReadingT = tenant.getcurrentMeterReading();
                            pMeterReadingT = tenant.getpreviousMeterReading();
                            meterNoT = tenant.getmeterNo();
                            String name = honorificT + " " + fNameT + " " + lNameT;
                            
                            if (cMeterReadingT < pMeterReadingT) {
                                usage = (cMeterReadingT + 1000000) - pMeterReadingT;
                            } else {
                                usage = cMeterReadingT - pMeterReadingT;
                            }
                            double baseC = usage * rate;
                                                        
                            Tenant_Bill Tenant_Bill = new Tenant_Bill(name, meterNoT
                                            , cMeterReadingT, pMeterReadingT, usage
                                            , 0.00, baseC, 0.00, 0.00); 
                            
                            tBills.add(Tenant_Bill);
                            usageCulmative += usage;
                            baseCulmative += baseC;
                            break;
                        }
                    }
                }                
                double diff = billTUsage - baseCulmative;
                
                for (Tenant_Bill tenant_bill: tBills) {
                    double pcnt = (double) tenant_bill.getusage() / usageCulmative * 100;
                    tenant_bill.setpcnt(pcnt);
                    if (diff > 0) {
                        tenant_bill.setadjC(diff * (tenant_bill.getpcnt() / 100));
                    } else {
                        tenant_bill.setadjC(0.00);
                    }
                    tenant_bill.settotalC(tenant_bill.getadjC() + tenant_bill.getbaseC());
                
                    totalCulmative += tenant_bill.gettotalC();
                    tenant_bill.infoString();
                }
                
                Bills.setbaseC(baseCulmative);
                Bills.setadj(diff);
                Bills.settotal(totalCulmative);
                Bills.setbc_bill(billTUsage);
                
                System.out.printf("%nTotal Tenant bills (metered)%12.2f", Bills.getbaseC());
                System.out.printf("%nTotal Tenant bills Diff%17.2f", Bills.getadj());
                System.out.printf("%nTotal Tenant bills Adjusted%13.2f%n", Bills.gettotal());
                break;
            }
        }        
    }   
    
    public void opt5()
    {
        // 5 - Compute Full Bill For All Flats (Task5)
        double rate = 0.205;
        int billusage = 0;
        double billTUsage = 0.00;
        int billCurrentReading = 0;
        int billPreviousReading = 0;
        String billCurrentDate = "";
        String billPreviousDate = "";
        String honorificT;
        String fNameT;
        String lNameT;
        String meterNoT;
        int cMeterReadingT;
        int pMeterReadingT;
        double percentUT;
        int usageCulmative = 0;
        double baseCulmative = 0.00;
        double totalCulmative = 0.00;
        double adjCulmative = 0.00;
        double totalBC_Bill = 0.0;
        double totalDifference = 0.0;
        double totalAdj = 0.0;
        double totalTenantBill = 0.0;
        int usage;
        
        System.out.println("List Adjusted bill for all Blocks of flats");
        
        System.out.printf("%n%-5s%-26s%8s%13s%10s%14s%n"
                        , "Flat", "Address", "BC Bill", "Difference"
                        , "DiffAdj", "Tenant Bill");
        System.out.printf("----------------------------------------------------------------------------%n%n");

        BC_Bill.addBills(flats, bills);
        
        for (BC_Bill Bills: bills) {
            billusage = Bills.getusage();
            billTUsage = rate * billusage;
            totalCulmative = 0.00;
            baseCulmative = 0.00;
            usageCulmative = 0;
            adjCulmative = 0.00;
            double diff = 0.00;
                
            for (Object tenantID : Bills.gettenants()) {
                String id = (String) tenantID;
                for (Tenant tenant : tenants) {
                    if (tenant.getmeterNo().equals(id)) {
                        honorificT = tenant.gethonorific();
                        fNameT = tenant.getfName();
                        lNameT = tenant.getlName();
                        cMeterReadingT = tenant.getcurrentMeterReading();
                        pMeterReadingT = tenant.getpreviousMeterReading();
                        meterNoT = tenant.getmeterNo();
                        String name = honorificT + " " + fNameT + " " + lNameT;
                            
                        if (cMeterReadingT < pMeterReadingT) {
                                usage = (cMeterReadingT + 1000000) - pMeterReadingT;
                            } else {
                                usage = cMeterReadingT - pMeterReadingT;
                            }
                        double baseC = usage * rate;
                            
                        Tenant_Bill Tenant_Bill = new Tenant_Bill(name, meterNoT
                                        , cMeterReadingT, pMeterReadingT, usage
                                        , 0.00, baseC, 0.00, 0.00); 
                            
                        tBills.add(Tenant_Bill);
                        usageCulmative += usage;
                        baseCulmative += baseC;
                        break;
                    }
                }
            }                
            diff = billTUsage - baseCulmative;

            for (Tenant_Bill tenant_bill: tBills) {
                double pcnt = (double) tenant_bill.getusage() / usageCulmative * 100;
                tenant_bill.setpcnt(pcnt);
                if (diff > 0) {
                    tenant_bill.setadjC(diff * (tenant_bill.getpcnt() / 100));
                } else {
                    tenant_bill.setadjC(0.00);
                }
                tenant_bill.settotalC(tenant_bill.getadjC() + tenant_bill.getbaseC());
                
                adjCulmative += tenant_bill.getadjC();
                totalCulmative += tenant_bill.gettotalC();
                totalTenantBill += tenant_bill.gettotalC();
            }
            
            Bills.setbaseC(baseCulmative);
            Bills.setadj(diff);
            Bills.settotal(totalCulmative);
            Bills.setdifference(adjCulmative);
            Bills.setbc_bill(billTUsage);
            totalBC_Bill += Bills.getbc_bill();
            totalDifference += Bills.getdifference();
            totalAdj += Bills.getadj();
            
            Bills.infoString();
            tBills.clear();
        }
        
        System.out.printf("----------------------------------------------------------------------------%n");
        System.out.printf("%,38.2f$%,12.2f$%,9.2f$%,13.2f$%n%n", totalBC_Bill, totalDifference, totalAdj, totalTenantBill);
    }   

    public void opt0()
    {
        // 0 - Set Dev0 environment
        System.out.printf("Dev0 Environment Selected\n");
        flatFileName = "Dev0_Flat.txt";
        meterFileName = "Dev0_Meter.txt";
        flats.clear();
        tenants.clear();
        bills.clear();
        tBills.clear();
    }   

    public void opt1()
    {
        // 1 - Set Dev1 environment
        System.out.printf("Dev1 Environment Selected\n");
        flatFileName = "Dev1_Flat.txt";
        meterFileName = "Dev1_Meter.txt";
        flats.clear();
        tenants.clear();
        bills.clear();
        tBills.clear();
    }   
    
    public void opt2()
    {
        // 2 - Set Test environment
        System.out.printf("Test Environment Selected\n");
        flatFileName = "test_Flat.txt";
        meterFileName = "test_Meter.txt";
        flats.clear();
        tenants.clear();
        bills.clear();
        tBills.clear();
    }   
    
    public void opt3()
    {
        // 3 - Set prod environment
        System.out.printf("Prod Environment Selected\n");
        flatFileName = "prod_Flat.txt";
        meterFileName = "prod_Meter.txt";
        flats.clear();
        tenants.clear();
        bills.clear();
        tBills.clear();
    }   
    
    public static void bubbleSort (ArrayList<Tenant> list) {
        int length = list.size();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < length; i++) {
                int a = Integer.parseInt(((Tenant)list.get(i-1)).getmeterNo().substring(1));
                int b = Integer.parseInt(((Tenant)list.get(i)).getmeterNo().substring(1));
                if (a > b) {
                    Tenant temp = list.get(i-1);
                    list.set(i-1, list.get(i));
                    list.set(i, temp);
                    swapped = true;
                }
            }
            length--;
        } while (swapped);
    }
    
    public void sequentialSearch(String find) {        
        for (int i = 0; i < tenants.size(); i++) {
            Tenant tenant = tenants.get(i);
            if (tenant.getmeterNo() == find) {
                break;
            }
        }
    }
    
    public void binarySearch(String find, int start, int end) {
        int intFind = Integer.parseInt(find.substring(1)); 
        while (start <= end) {
            int middle = start + (end - start) / 2;
            int middleCheck = Integer.parseInt(tenants.get(middle).getmeterNo().substring(1));
            if (middleCheck == intFind) {
                return;
            } else if (middleCheck > intFind) {
                end = middle - 1;    
            } else {
                start = middle + 1;    
            }
        }
    }
}
