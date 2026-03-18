package script.ict373_a1_clarencelim_35008001;
import java.util.ArrayList;

public class Magazine{
    private String magName;
    private double mWeeklyCost;
    private ArrayList<Supplement> supplementList;
    
    public Magazine(){}
    
    public Magazine(String newMName, double newMWeeklyCost, ArrayList<Supplement> magSupList){
        this.magName = newMName;
        this.mWeeklyCost = newMWeeklyCost;
        this.supplementList = magSupList;
    }
    
    public String getMagName(){
        return this.magName;
    }
    
    public double getMagWeeklyCost(){
        return this.mWeeklyCost;
    }
    
    public String toString(){
        return String.format("%n%s %s%n%s%s%n%s%n%s%n",
                "Magazine:", magName, "Cost: $", mWeeklyCost, 
                "This weeks Highlights:", supplementList.toString());
    }
}
