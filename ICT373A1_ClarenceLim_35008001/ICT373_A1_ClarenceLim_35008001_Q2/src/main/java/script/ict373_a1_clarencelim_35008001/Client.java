package script.ict373_a1_clarencelim_35008001;
import java.util.*;
import java.io.*;

public class Client{
    
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Customer> payingCustomers = new ArrayList<>();
        ArrayList<Supplement> supplements = new ArrayList<>();
        ArrayList<Magazine> magazines = new ArrayList<>();
        try{
            getAssociateCustomer(customers);
            getPayingCustomer(payingCustomers);
            for(int i=0;i<payingCustomers.size();i++){
                customers.add(i,payingCustomers.get(i));
            }
            getSupplement(supplements);
            getMagazine(magazines);
            int option = 0;

            //display my information and title of program
            myInfo();
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Welcome to your Weekly Programming Magazines");

            //switch Menu
            while(option != 1){
                try{
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Please select an option: ");
                    System.out.println("Enter 1 - [Exit]");
                    System.out.println("Enter 2 - [Add a new customer]");
                    System.out.println("Enter 3 - [Remove an existing customer]");
                    System.out.println("Enter 4 - [Display the text of 4 weeks of magazine emails for all customers]");
                    System.out.println("Enter 5 - [Display all paying customer's end of month payment details]");
                    System.out.println("Enter 6 - [Display all customer's details]");
                    System.out.println("------------------------------------------------------------------------");
                    option = Integer.parseInt(keyboard.nextLine()); 
                    if(option == 1){
                        System.out.println();
                        System.out.println("Thank you for using this service.");
                        System.exit(0);
                    }
                    else if(option == 2){
                        addNewCustomer(customers);
                    }
                    else if(option == 3){
                        removeCustomer(customers);
                    }
                    else if(option == 4){
                        outputWeeklyMag(customers);
                    }
                    else if(option == 5){
                        outputMonthlyEmail(customers);
                    }
                    else if(option == 6){
                        outputDataCust(customers);
                    }
                    else{
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("Option is not available!");
                    }
                }
                catch(NumberFormatException err){
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Please enter a valid value!");
                }
            }
        }
        catch(MissingFormatArgumentException err){
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Something went wrong with the text format.");
        }
    }
    /**
     * end of switch
     */
    
    /**
     * Method for ArrayList 'supplements'
     */
    private static void getSupplement(ArrayList<Supplement> newSupplement){
        File supplementFile = new File("SupplementList.txt");
        try(Scanner reader = new Scanner(supplementFile)){
            while(reader.hasNext()){
                String oneLine = reader.nextLine();
                String[] values = oneLine.split(",");
                String supName = values[0];
                double supCost = Double.parseDouble(values[1]);
                Supplement temp = new Supplement
                            (supName,supCost);
                newSupplement.add(temp);
            }
        }
        catch(FileNotFoundException err){
            System.out.println("Error, file not found");
        }
    }
    /**
     * end of ArrayList 'supplements'
     */
    
    /**
     * Method for ArrayList 'magazines'
     */
    private static void getMagazine(ArrayList<Magazine> newMagazine){
        File magazineFile = new File("MagazineList.txt");
        try(Scanner reader = new Scanner(magazineFile)){
            ArrayList<Supplement> listOfSup = new ArrayList<>();
            getSupplement(listOfSup);
            while(reader.hasNext()){
                String oneLine = reader.nextLine();
                String[] values = oneLine.split(",");
                String magName = values[0];
                double magCost = Double.parseDouble(values[1]);
                Magazine temp = new Magazine
                            (magName,magCost,listOfSup);
                newMagazine.add(temp);
            }
        }
        catch(FileNotFoundException err){
            System.out.println("Error, file not found");
        }
    }
    /**
     * end of ArrayList 'magazines'
     */
    
    /**
     * Method for ArrayList 'customers'
     */
    private static void getAssociateCustomer(ArrayList<Customer> newCustomer){
        File aCustomerFile = new File("AssociateCustomerInfo.txt");
        try(Scanner reader = new Scanner(aCustomerFile)){
            while(reader.hasNext()){
                String oneLine = reader.nextLine();
                String[] values = oneLine.split(",");
                ArrayList<Supplement> listOfSup = new ArrayList<>();
                getSupplement(listOfSup);
                Supplement Supp1 = listOfSup.get(0);
                Supplement Supp2 = listOfSup.get(1);
                Supplement Supp3 = listOfSup.get(2);
                Supplement Supp4 = listOfSup.get(3);
                
                ArrayList<Supplement> subscribedList = new ArrayList<>();
                String custName = values[0];
                String custEmail = values[1];
                String payingCust = values[2];
                for(int i=3;i<values.length;i++){
                    if(values[i].equalsIgnoreCase("Supp1")){
                        subscribedList.add(Supp1);
                    }
                    else if(values[i].equalsIgnoreCase("Supp2")){
                        subscribedList.add(Supp2);
                    }
                    else if(values[i].equalsIgnoreCase("Supp3")){
                        subscribedList.add(Supp3);
                    }
                    else if(values[i].equalsIgnoreCase("Supp4")){
                        subscribedList.add(Supp4);
                    }
                    else{
                        System.out.println();
                    }
                }

                AssociateCustomer temp = new AssociateCustomer
                            (custName,custEmail,subscribedList,payingCust);
                newCustomer.add(temp);
            }
        }
        catch(FileNotFoundException err){
            System.out.println("Error, file not found");
        }
    }
    /**
     * end of ArrayList 'customers'
     */
    
    /**
     * Method for ArrayList 'payingCustomers'
     */
    private static void getPayingCustomer(ArrayList<Customer> newCustomer){
        File pCustomerFile = new File("PayingCustomerInfo.txt");
        try(Scanner reader = new Scanner(pCustomerFile)){
            while(reader.hasNext()){
                String oneLine = reader.nextLine();
                String[] values = oneLine.split(",");
                ArrayList<Supplement> listOfSup = new ArrayList<>();
                getSupplement(listOfSup);
                Supplement Supp1 = listOfSup.get(0);
                Supplement Supp2 = listOfSup.get(1);
                Supplement Supp3 = listOfSup.get(2);
                Supplement Supp4 = listOfSup.get(3);
                ArrayList<Customer> listOfAssociates = new ArrayList<>();
                getAssociateCustomer(listOfAssociates);
                
                ArrayList<Supplement> subscribedList = new ArrayList<>();
                ArrayList<Customer> associatesList = new ArrayList<>();
                
                String pCustName = values[0];
                String pCustEmail = values[1];
                String payMethod = values[2];
                for(int i=3;i<values.length;i++){
                    if(values[i].equalsIgnoreCase("Supp1")){
                        subscribedList.add(Supp1);
                    }
                    else if(values[i].equalsIgnoreCase("Supp2")){
                        subscribedList.add(Supp2);
                    }
                    else if(values[i].equalsIgnoreCase("Supp3")){
                        subscribedList.add(Supp3);
                    }
                    else if(values[i].equalsIgnoreCase("Supp4")){
                        subscribedList.add(Supp4);
                    }
                    else{
                        for(int a=0;a<listOfAssociates.size();a++){
                            String tempName = listOfAssociates.get(a).getCustName();
                            if(tempName.equalsIgnoreCase(values[i])){
                                associatesList.add(listOfAssociates.get(a));
                            }
                        }
                    }
                }

                PayingCustomer temp = new PayingCustomer
                            (pCustName,pCustEmail,subscribedList,payMethod,associatesList);
                newCustomer.add(temp);
            }
        }
        catch(FileNotFoundException err){
            System.out.println("Error, file not found");
        }
    }
    /**
     * end of ArrayList 'payingCustomers'
     */
    
    /**
     * Iterator method to remove a customer's record
     */
    private static void removeCustomer(ArrayList<Customer> rmvCustomer){
        Boolean found = false;
        int loop = 0;
        String YN = null;
        Scanner keyboard = new Scanner(System.in);
        Iterator<Customer> ITR = rmvCustomer.iterator();
        try{
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Enter a Customer's name that you wish to remove.");
            String removeName = keyboard.nextLine();
            while(!found){
                while(ITR.hasNext()){
                    Customer cust = (Customer)ITR.next();
                    if(cust.getCustName().equalsIgnoreCase(removeName)){
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("You have selected:");
                        System.out.println(cust);
                        System.out.println();
                        System.out.println("Are you sure you want to remove this customer's data? (Y/N)");
                        System.out.println("------------------------------------------------------------------------");
                        //confirm with user again to see if he/she wants to remove the record
                        while(!"Y".equals(YN) || !"N".equals(YN)){
                            YN = keyboard.nextLine();
                            if(YN.equalsIgnoreCase("Y")){
                                found = true;
                                ITR.remove();
                                System.out.println("------------------------------------------------------------------------");
                                System.out.println("Customer "+ cust.getCustName() +" was removed.");
                                break;
                            }
                            else if(YN.equalsIgnoreCase("N")){
                                found = true;
                                System.out.println("------------------------------------------------------------------------");
                                System.out.println("Removal of record was cancelled.");
                                break;
                            }
                            else{
                                System.out.println("------------------------------------------------------------------------");
                                System.out.println("Please enter only 'Y' or 'N'.");
                            }
                        }
                    }
                    else{
                        found = false;
                        break;
                    }
                }
                if(loop > rmvCustomer.size() && YN == null){
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("The customer you entered: " + removeName);
                    System.out.println("is not found, please select option 3 and try again.");
                    break;
                }
                else if(loop > rmvCustomer.size()){
                    break;
                }
                loop++;
            }
        }
        catch(NullPointerException err){
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Nothing was entered, operation is cancelled.");                    
        }
    }
    /**
     * end of iterator "removeCustomer
     */
    
    /**
     * Method to add a new customer's record
     */
    public static void addNewCustomer(ArrayList<Customer> addCustomer){
        Scanner keyboard = new Scanner(System.in);
        String type = "";
        boolean valid = false;
        while(!valid){
            try{
                System.out.println("------------------------------------------------------------------------");
                System.out.println("What type of customer do you want to add?");
                System.out.println("[A] - Associate Customer ");
                System.out.println("[P] - Paying Customer ");
                type = keyboard.nextLine();
                
                if(type.equalsIgnoreCase("A")||type.equalsIgnoreCase("P")){
                    valid = true;
                }
                else{
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Please enter a valid option!");
                }
            }
            catch(InputMismatchException err){
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Please enter a valid option!");
            }
        }
        if(type.equalsIgnoreCase("A")){
            createAssociateCustomer(addCustomer);
        }
        else if(type.equalsIgnoreCase("P")){
            createPayingCustomer(addCustomer);
        }
    }
    /**
     * end of addCustomer
     */
    
    /**
     *  Method to add a supplement record into a new customer's record
     */
    public static void selectSupplement(ArrayList<Supplement> addSupplement){
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Supplement> listOfSup = new ArrayList<>();
        getSupplement(listOfSup);
        int count = 0, select = 0;
        String option = "";
        boolean addNext = true, addSupp1 = false, addSupp2 = false, addSupp3 = false, addSupp4 = false;
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Supplement List");
        System.out.println("Enter 1 - [" + listOfSup.get(0).getSupName() + "]");
        System.out.println("Enter 2 - [" + listOfSup.get(1).getSupName() + "]");
        System.out.println("Enter 3 - [" + listOfSup.get(2).getSupName() + "]");
        System.out.println("Enter 4 - [" + listOfSup.get(3).getSupName() + "]");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Select a supplement you are interested in:");
        while(addNext){
            try{
                if(count == 4){
                    addNext = false;
                }
                select = keyboard.nextInt();
                if(select == 1 && addSupp1 == false){
                    addSupplement.add(listOfSup.get(0));
                    addSupp1 = true;
                    count++;
                }
                else if(select == 2 && addSupp2 == false){
                    addSupplement.add(listOfSup.get(1));
                    addSupp2 = true;
                    count++;
                }
                else if(select == 3 && addSupp3 == false){
                    addSupplement.add(listOfSup.get(2));
                    addSupp3 = true;
                    count++;
                }
                else if(select == 4 && addSupp4 == false){
                    addSupplement.add(listOfSup.get(3));
                    addSupp4 = true;
                    count++;
                }
                else{
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("The supplement you chose might have been added or is invalid, try again!");
                }
                keyboard.nextLine(); //consume new line leftover
                if(count>0){
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Do you want to add another supplement? [Y/N]");
                    option = keyboard.nextLine().toLowerCase();
                    if(option.equalsIgnoreCase("n")){
                        addNext = false; //to terminate while loop
                    }
                    else{
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("Select another supplement:");
                    }
                }
            }
            catch(InputMismatchException err){
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Please enter a valid option!");
                return;
            }
        }
    }
    /**
     * end of selectSupplement
     */
    
    /**
     * Method to create a new Associate Customer 
     */
    public static void createAssociateCustomer(ArrayList<Customer> newAssociate){
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Supplement> subscribedList = new ArrayList<>();
        boolean nameLength = false, newCust = false, exist = false;
        String assoName = "", assoEmail = "", payingCust = "";
        int checkCount = 0;
        
        System.out.println("------------------------------------------------------------------------");
        System.out.println(":Enter the new associate customer's name:");
        while(!nameLength){
            assoName = keyboard.nextLine();
            if(assoName.length()>0){
                nameLength = true;
            }
            else{
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Name must not be empty.");
            }
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println(":Enter an email address:");
        while(!newCust){
            checkCount = newAssociate.size();
            assoEmail = keyboard.nextLine();
            if(assoEmail.length()>0){
                for(int i=0;i<newAssociate.size();i++){
                    if(newAssociate.get(i).getCustEmail().equalsIgnoreCase(assoEmail)){
                        if(checkCount != 0 && !newCust){
                            System.out.println("------------------------------------------------------------------------");
                            System.out.println("The email already exists, try a new email.");
                        }
                    }
                    else{
                        checkCount--;
                        if(newAssociate.get(i).getCustEmail().equalsIgnoreCase(assoEmail) == false && checkCount == 0){
                            System.out.println("------------------------------------------------------------------------");
                            System.out.println("The email has been successfully saved.");
                            newCust = true;
                        }
                    }
                }
            }
            else{
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Email cannot be empty.");
            }
        }
        
        selectSupplement(subscribedList);
        System.out.println("------------------------------------------------------------------------");
        System.out.println(":Enter the name of the paying customer:");
        while(!exist){
            payingCust = keyboard.nextLine();
            for(int i=0;i<newAssociate.size();i++){
                if(newAssociate.get(i) instanceof PayingCustomer && newAssociate.get(i).getCustName().equalsIgnoreCase(payingCust)){
                    AssociateCustomer temp = new AssociateCustomer
                                (assoName,assoEmail,subscribedList,payingCust);
                    newAssociate.add(temp);
                    newAssociate.get(i).getAssociateCustomer().add(temp);
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("New associate was added successfully.");
                    exist = true;
                }
                else{
                    if(i == (newAssociate.size()-2) && !exist){
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println(":Paying customer not found, do you want to try again? [Y/N]: ");
                        String another = keyboard.nextLine().toLowerCase();
                        if(another.equalsIgnoreCase("n")){
                            System.out.println("------------------------------------------------------------------------");
                            System.out.println("Failed in adding associate customer.");
                            exist = true;
                        }
                        else{
                            System.out.println("------------------------------------------------------------------------");
                            System.out.println("Enter another name.");
                        }
                    }
                }
            }
        }
    }
    /*
     * end of createAssociateCustomer
     */
    
    /**
     * Method to create a new Paying Customer 
     */
    public static void createPayingCustomer(ArrayList<Customer> newPaying){
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Supplement> subscribedList = new ArrayList<>();
        ArrayList<Customer> associatesList = new ArrayList<>();
        boolean nameLength = false, newCust = false, selected = false, done = false;
        String payerName = "", payerEmail = "", payMethod = "", assoName = "", assoEmail = "";
        int checkCount = 0, checkCount2 = 0, choice = 0;
        System.out.println("------------------------------------------------------------------------");
        System.out.println(":Enter the new paying customer's name:");
        while(!nameLength){
            payerName = keyboard.nextLine();
            if(payerName.length() > 0){
                nameLength = true;
            }
            else{
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Name must not be empty.");
            }
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println(":Enter an email address:");
        while(!newCust){
            checkCount = newPaying.size();
            payerEmail = keyboard.nextLine();
            if(payerEmail.length()>0){
                for(int i=0;i<newPaying.size();i++){
                    if(newPaying.get(i).getCustEmail().equalsIgnoreCase(payerEmail)){
                        if(checkCount != 0 && !newCust){
                            System.out.println("------------------------------------------------------------------------");
                            System.out.println("The email already exists, try a new email.");
                        }
                    }
                    else{
                        checkCount--;
                        if(newPaying.get(i).getCustEmail().equalsIgnoreCase(payerEmail) == false && checkCount == 0){
                            System.out.println("------------------------------------------------------------------------");
                            System.out.println("The email has been successfully saved.");
                            newCust = true;
                        }
                    }
                }
            }
            else{
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Email cannot be empty.");
            }
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println(":Select a payment below:");
        System.out.println("\n::Payment though credit card::");
        System.out.println("Enter 1 - [Visa]");
        System.out.println("Enter 2 - [Mastercard]");
        System.out.println("Enter 3 - [American Express]");
        System.out.println("\n::Payment though direct debit transfer::");
        System.out.println("Enter 4 - [OCBC]");
        System.out.println("Enter 5 - [DBS]");
        System.out.println("Enter 6 - [POSB]");
        System.out.println("Enter 7 - [UOB]");
        System.out.println("------------------------------------------------------------------------");
        while(!selected){
            try{
                choice = keyboard.nextInt();
                if(choice == 1){
                    payMethod = "Visa";
                    selected = true;
                }
                else if(choice == 2){
                    payMethod = "Mastercard";
                    selected = true;
                }
                else if(choice == 3){
                    payMethod = "American Express";
                    selected = true;
                }
                else if(choice == 4){
                    payMethod = "OCBC";
                    selected = true;
                }
                else if(choice == 5){
                    payMethod = "DBS";
                    selected = true;
                }
                else if(choice == 6){
                    payMethod = "POSB";
                    selected = true;
                }
                else if(choice == 7){
                    payMethod = "UOB";
                    selected = true;
                }
                else{
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Please enter a valid option!");
                }
                keyboard.nextLine(); //consume new line leftover
            }
            catch(InputMismatchException err){
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Please enter a valid option!");
                return;
            }
        }
        
        
        selectSupplement(subscribedList);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Do you want to add an associate? [Y/N]: ");
        String add = keyboard.nextLine().toLowerCase();
        if(add.equalsIgnoreCase("n")){
            done = true;
        }
        while(!done){
            boolean nameLength2 = false, newCust2 = false;
            ArrayList<Supplement> subscribedList2 = new ArrayList<>();
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Enter the new associate customer's name: ");
            while(!nameLength2){
                assoName = keyboard.nextLine();
                if(assoName.length()>0){
                    nameLength2 = true;
                }
                else{
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Name must not be empty.");
                }
            }
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Enter an email address: ");
            while(!newCust2){
                checkCount2 = newPaying.size();
                assoEmail = keyboard.nextLine();
                if(assoEmail.length()>0){
                    for(int n=0;n<newPaying.size();n++){
                        if(newPaying.get(n).getCustEmail().equalsIgnoreCase(assoEmail) || assoEmail.equalsIgnoreCase(payerEmail)){
                            if(checkCount2 != 0 && !newCust2){
                                System.out.println("------------------------------------------------------------------------");
                                System.out.println("The email already exists, try a new email.");
                            }
                        }
                        else{
                            checkCount2--;
                            if(newPaying.get(n).getCustEmail().equalsIgnoreCase(assoEmail) == false || assoEmail.equalsIgnoreCase(payerEmail) == false){
                                if(checkCount2 == 0){
                                    System.out.println("------------------------------------------------------------------------");
                                    System.out.println("The email has been successfully saved.");
                                    newCust2 = true;
                                }
                            }
                        }
                    }
                }
                else{
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Email cannot be empty.");
                }
            }

            selectSupplement(subscribedList2);
            AssociateCustomer aTemp = new AssociateCustomer
                            (assoName,assoEmail,subscribedList2,payerName);
            newPaying.add(aTemp);
            associatesList.add(aTemp); 
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Do you want to add another associate? [Y/N]: ");
            String another = keyboard.nextLine().toLowerCase();
            if(another.equalsIgnoreCase("n")){
                done = true;
            }
        }
        
        PayingCustomer temp = new PayingCustomer
                        (payerName,payerEmail,subscribedList,payMethod,associatesList);
        newPaying.add(temp);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Customer(s) was added successfully.");
    }
    /*
     * end of createPayingCustomer
     */
    
    /**
     * Method for printing all 4 weeks of magazines
     */
    private static void outputWeeklyMag(ArrayList<Customer> weeklyMag){
        ArrayList<Magazine> magazines = new ArrayList<>();
        getMagazine(magazines);
        for(int i=0;i<magazines.size();i++){
            for(int n=0;n<weeklyMag.size();n++){
                System.out.println("------------------------------------------------------------------------");
                System.out.println("To: " + weeklyMag.get(n).getCustEmail());
                System.out.println("Dear Mr/Mrs. " + weeklyMag.get(n).getCustName());
                System.out.println("This weeks magazine is ready for viewing");
                System.out.println(magazines.get(i));
            }
        }
    }
    /**
     * end of outputWeeklyMag
     */
    
    /**
     * Method to generate total monthly email
     */
    private static void outputMonthlyEmail(ArrayList<Customer> monthlyEmail){
        double totalCost = 0;
        double roundOff = 0;
        ArrayList<Magazine> magList = new ArrayList<>();
        getMagazine(magList);
        for(int i=0;i<monthlyEmail.size();i++){
            if(monthlyEmail.get(i) instanceof PayingCustomer){
                for(int s=0;s<magList.size();s++){ //Magazine cost for Paying Customer
                    totalCost += magList.get(s).getMagWeeklyCost();
                }
                for(int n=0;n<monthlyEmail.get(i).getSupList().size();n++){ //Supplement cost for Paying Customer
                    totalCost += monthlyEmail.get(n).getSupList().get(n).getSupWeeklyCost();
                }
                for(int a=0;a<monthlyEmail.get(i).getAssociateCustomer().size();a++){
                    for(int p=0;p<monthlyEmail.get(i).getAssociateCustomer().get(a).getSupList().size();p++){ //Magazine cost for Associate Customer
                        totalCost += magList.get(p).getMagWeeklyCost();
                    }
                    for(int m=0;m<monthlyEmail.get(i).getAssociateCustomer().get(a).getSupList().size();m++){ //Supplement cost for Associate Customer
                        totalCost += monthlyEmail.get(i).getAssociateCustomer().get(a).getSupList().get(m).getSupWeeklyCost();
                    }
                }
                roundOff = Math.round(totalCost * 100.0) / 100.0;
                System.out.println("------------------------------------------------------------------------");
                System.out.println("To: " + monthlyEmail.get(i).getCustEmail());
                System.out.println("Dear Mr/Mrs. " + monthlyEmail.get(i).getCustName());
                System.out.println();
                System.out.println("You have a payment due in 7 days");
                System.out.println();
                System.out.println("The total of your bill is:\n$" + roundOff);
                System.out.println();
                System.out.println("The current payment will be paid through: " + monthlyEmail.get(i).getPaymentName());
                totalCost = 0;//reset the total for each loop
            }
        }
    }
    /**
     * end of outputMonthlyEmail
     */
    
    /**
     * Output customer data
     */
    private static void outputDataCust(ArrayList<Customer> customers){
        for(int i=0; i<customers.size(); i++){
            System.out.println("------------------------------------------------------------------------");
            System.out.println(customers.get(i));
            System.out.println();
        }
    }
    /**
     * end of outputDataCust
     */
            
    /**
     * Method to display author's Information
     */
    public static void myInfo(){
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Name: Lim Xing Yu Clarence");
        System.out.println("Student No: 35008001");
        System.out.println("Module: ICT373 Software Architectures(A)");
        System.out.println("Tutor's Name: Poh Kok Loo");
        System.out.println("Date Submitted: 25/02/2024");
    }
    /**
     * end of myInfo
     */
}
