package ict373_a2_clarencelim_35008001;
import java.io.Serializable;

public class Address implements Serializable{
    private String streetNumber;
    private String streetName;
    private String suburb;
    private String postalCode;
    
    public Address() {
        this.streetNumber = "";
        this.streetName = "";
        this.suburb = "";
        this.postalCode = "";
    }
    
    public Address(String newStreetNumber, String newStreetName, String newSuburb, String newPostalcode) {
        this.streetNumber = newStreetNumber;
        this.streetName = newStreetName;
        this.suburb = newSuburb;
        this.postalCode = newPostalcode;
    }
    
    
    //modifiers for streetNumber
    public void setStreetNumber(String newStreetNumber){
        this.streetNumber = newStreetNumber;
    }
    
    public String getStreetNumber(){
        return this.streetNumber;
    }
    
    
    //modifiers for streetName
    public void setStreetName(String newStreetName){
        this.streetName = newStreetName;
    }
    
    public String getStreetName(){
        return this.streetName;
    }
    
    
    //modifiers for suburb
    public void setSuburb(String newSuburb){
        this.suburb = newSuburb;
    }
    
    public String getSuburb(){
        return this.suburb;
    }
    
    
    //modifiers for postalCode
    public void setPostalCode(String newPostalCode){
        this.postalCode = newPostalCode;
    }
    
    public String getPostalCode(){
        return this.postalCode;
    }
    
    
    public String toString(){
        return String.format("%n%s %s %s%n%s %s%n%s%s%n",
                "Street: ", this.streetNumber, this.streetName,
                "City: ", this.suburb,
                "Postal Code: ", this.postalCode);
    }
}
