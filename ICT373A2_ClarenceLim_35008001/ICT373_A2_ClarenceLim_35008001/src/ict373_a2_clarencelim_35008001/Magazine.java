package ict373_a2_clarencelim_35008001;
import java.io.Serializable;
import java.util.ArrayList;

public class Magazine implements Serializable{
    private String magazineName;
    private double m_WeeklyCost;
    private ArrayList<Supplement> supplementList;
    private ArrayList<Customer> customerList;
    
    public Magazine(){
        this.magazineName = "";
        this.m_WeeklyCost = 0.00;
        this.supplementList = new ArrayList<>();
        this.customerList = new ArrayList<>();
    }
    
    public Magazine(String newMagazineName, double newM_WeeklyCost){
        this.magazineName = newMagazineName;
        this.m_WeeklyCost = newM_WeeklyCost;
        this.supplementList = new ArrayList<>();
        this.customerList = new ArrayList<>();
    }
    
    
    //modifiers for magazineName
    public void setMagazineName(String newMagazineName){
        this.magazineName = newMagazineName;
    }
    
    public String getMagazineName(){
        return this.magazineName;
    }
    
    
    //modifiers for m_WeeklyCost
    public void setM_WeeklyCost(double newM_WeeklyCost){
        this.m_WeeklyCost = newM_WeeklyCost;
    }
    
    public double getM_WeeklyCost(){
        return this.m_WeeklyCost;
    }
    
    
    //modifiers for SupplementList
    public void setSupplementList(ArrayList<Supplement> newSubscribedSupplement){
        this.supplementList = newSubscribedSupplement;
    }
    
    public void addSupplement(Supplement supplement){
        this.supplementList.add(supplement);
    }
    
    public void removeSupplement(Supplement supplement){
        this.supplementList.remove(supplement);
    }
    
    public ArrayList<Supplement> getSupplement(){
        return this.supplementList;
    }
    
    
    //modifiers for CustomerList
    public void setCustomerList(ArrayList<Customer> newCustomerList){
        this.customerList = newCustomerList;
    }
    
    public void addCustomer(Customer customer){
        this.customerList.add(customer);
    }
    
    public void removeCustomer(Customer customer){
        this.customerList.remove(customer);
    }
    
    public ArrayList<Customer> getCustomer(){
        return this.customerList;
    }
    
    
    public String toString(){
        return String.format("%n%s %s%n%s%s",
                "Magazine:", this.magazineName, "Cost: $", this.m_WeeklyCost);
    }
}
