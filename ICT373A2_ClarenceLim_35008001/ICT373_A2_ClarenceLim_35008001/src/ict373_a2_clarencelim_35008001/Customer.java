package ict373_a2_clarencelim_35008001;
import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable{
    private String name;
    private String email;
    private Address address;
    private ArrayList<Magazine> subscribedMagazine;
    private ArrayList<Supplement> subscribedSupplement;
    
    public Customer(){
        this.name = "";
        this.email = "";
        this.address = new Address();
        this.subscribedSupplement = new ArrayList<>();
        this.subscribedMagazine = new ArrayList<>();
    }
    
    public Customer(String newName, String newEmail, Address newAddress, ArrayList<Magazine> newMagazine, ArrayList<Supplement> newSupplement){
        this.name = newName;
        this.email = newEmail;
        this.address = newAddress;
        this.subscribedMagazine = newMagazine;
        this.subscribedSupplement = newSupplement;
    }
    
    
    //modifiers for name
    public void setName(String newName){
        this.name = newName;
    }
    
    public String getName(){
        return this.name;
    }
    
    
    //modifiers for email
    public void setEmail(String newEmail){
        this.email = newEmail;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    
    //modifiers for address
    public void setAddress(Address newAddress){
        this.address = newAddress;
    }
    
    public Address getAddress(){
        return this.address;
    }
    
    
    //modifiers for subscribedMagazine
    public void setMagazine(ArrayList<Magazine> newSubscribedMagazine){
        this.subscribedMagazine = newSubscribedMagazine;
    }
    
    public void addMagazine(Magazine magazine){
        this.subscribedMagazine.add(magazine);
    }
    
    public void removeMagazine(Magazine magazine){
        this.subscribedMagazine.remove(magazine);
    }
    
    public ArrayList<Magazine> getMagazine(){
        return this.subscribedMagazine;
    }
    
    
    //modifiers for subscribedSupplement
    public void setSupplement(ArrayList<Supplement> newSubscribedSupplement){
        this.subscribedSupplement = newSubscribedSupplement;
    }
    
    public void addSupplement(Supplement supplement){
        this.subscribedSupplement.add(supplement);
    }
    
    public void removeSupplement(Supplement supplement){
        this.subscribedSupplement.remove(supplement);
    }
    
    public ArrayList<Supplement> getSupplement(){
        return this.subscribedSupplement;
    }
    
    
    public String toString(){
        return this.name;
    }
}

