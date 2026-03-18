package ict373_a2_clarencelim_35008001;
import java.util.ArrayList;

public class PayingCustomer extends Customer{
    private PaymentMethod paymentMethod;
    private ArrayList<Customer> associateCustomer;
    
    public PayingCustomer(){
        super();
        this.paymentMethod = new PaymentMethod();
        this.associateCustomer = new ArrayList<>();
    }
    
    public PayingCustomer(String newName, String newEmail, Address newAddress,
            ArrayList<Magazine> newMagazine, ArrayList<Supplement> newSupplement, PaymentMethod newPaymentMethod){
        super(newName, newEmail, newAddress, newMagazine, newSupplement);
        this.paymentMethod = newPaymentMethod;
        this.associateCustomer = new ArrayList<>();
        
    }
    
    
    //modifiers for payingCustomer
    public void setPaymentMethod(PaymentMethod newPaymentMethod){
        this.paymentMethod = newPaymentMethod;
    }
    
    public PaymentMethod getPaymentMethod(){
        return this.paymentMethod;
    }
    
    
    //modifiers for associateCustomer
    public void setAssociateCustomer(ArrayList<Customer> newAssociateCustomers){
        this.associateCustomer = newAssociateCustomers;
    }
    
    public void addAssociateCustomer(Customer customer){
        this.associateCustomer.add(customer);
    }

    public void removeAssociateCustomer(Customer customer){
        this.associateCustomer.remove(customer);
    }
    
    public ArrayList<Customer> getAssociateCustomer(){
        return this.associateCustomer;
    }
    
    
    //comparing associate customers
    public boolean associateCustomerIsEqual(String thisAssociateName){
        for(int i=0; i<this.associateCustomer.size(); i++){
            if(this.associateCustomer.get(i) instanceof AssociateCustomer){
                AssociateCustomer temp = (AssociateCustomer)this.associateCustomer.get(i);
                if(temp.getName().equalsIgnoreCase(thisAssociateName)){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    //calculate total cost for magazines subscribed
    public double getTotalMagazineCost(Customer thisCustomer){
        double tempCost = 0;
        ArrayList<Magazine> subscribedMagazines = thisCustomer.getMagazine();
        for(int i=0; i<subscribedMagazines.size(); i++){
            tempCost += (subscribedMagazines.get(i).getM_WeeklyCost() * 4);
        }
        return tempCost;
    }
    
    //calculate total cost for supplements subscribed
    public double getTotalSupplementCost(Customer thisCustomer){
        double tempCost = 0;
        ArrayList<Supplement> subscribedSupplements = thisCustomer.getSupplement();
        for(int i=0; i<subscribedSupplements.size(); i++){
            tempCost += (subscribedSupplements.get(i).getS_WeeklyCost() * 4);
        }
        return tempCost;
    }
    
    
    //calculate total cost for magazines subscribed by all associates
    public double getAssociatesTotalMagazineCost(Customer thisCustomer){
        double tempCost = 0;
        ArrayList<Customer> temp = getAssociateCustomer();
        for(int i=0; i<temp.size(); i++){
            for(int ii=0; ii<temp.get(i).getMagazine().size(); ii++){
                tempCost += (temp.get(i).getMagazine().get(ii).getM_WeeklyCost() * 4);
            }
        }
        return tempCost;
    }
    
    //calculate total cost for supplements subscribed by all associates
    public double getAssociatesTotalSupplementCost(Customer thisCustomer){
        double tempCost = 0;
        ArrayList<Customer> temp = getAssociateCustomer();
        for(int i=0; i<temp.size(); i++){
            for(int ii=0; ii<temp.get(i).getSupplement().size(); ii++){
                tempCost += (temp.get(i).getSupplement().get(ii).getS_WeeklyCost() * 4);
            }
        }
        return tempCost;
    }
}

