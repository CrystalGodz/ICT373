package ict373_a2_clarencelim_35008001;
import java.io.Serializable;

public class Supplement implements Serializable{
    private String supplementName;
    private double s_WeeklyCost;
    
    public Supplement(){
        this.supplementName = "";
        this.s_WeeklyCost = 0.00;
    }
    
    public Supplement(String newSupName, double newSWeeklyCost){
        this.supplementName = newSupName;
        this.s_WeeklyCost = newSWeeklyCost;
    }
    
    
    //modifiers for supplementName
    public void setSupplementName(String newSupplementName){
        this.supplementName = newSupplementName;
    }
    
    public String getSupplementName(){
        return this.supplementName;
    }
    
    
    //modifiers for s_WeeklyCost
    public void setS_WeeklyCost(double newS_WeeklyCost){
        this.s_WeeklyCost = newS_WeeklyCost;
    }
    
    public double getS_WeeklyCost(){
        return this.s_WeeklyCost;
    }
    
    
    public String toString(){
        return this.supplementName;
    }
}
