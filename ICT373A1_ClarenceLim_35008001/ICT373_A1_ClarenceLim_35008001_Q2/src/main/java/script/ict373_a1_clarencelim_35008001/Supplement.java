package script.ict373_a1_clarencelim_35008001;

public class Supplement{
    private String supName;
    private double sWeeklyCost;
    
    public Supplement(){}
    
    public Supplement(String newSupName, double newSWeeklyCost){
        this.supName = newSupName;
        this.sWeeklyCost = newSWeeklyCost;
    }
    
    public String getSupName(){
        return this.supName;
    }
    
    public double getSupWeeklyCost(){
        return this.sWeeklyCost;
    }
    
    public String toString(){
        return String.format("%n%s%n%s%s%n",
             supName,"Cost: $", sWeeklyCost);
    }
}
