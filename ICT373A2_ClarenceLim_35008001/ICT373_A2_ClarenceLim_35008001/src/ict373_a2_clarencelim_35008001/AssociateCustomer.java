package ict373_a2_clarencelim_35008001;
import java.util.ArrayList;

public class AssociateCustomer extends Customer{
    private PayingCustomer payingCustomer;
    
    public AssociateCustomer(){
        super();
    }
    
    public AssociateCustomer(String newName, String newEmail, Address newAddress,
            ArrayList<Magazine> newMagazine, ArrayList<Supplement> newSupplement, PayingCustomer newPayingCustomer){
        super(newName, newEmail, newAddress, newMagazine, newSupplement);
        this.payingCustomer = newPayingCustomer;
    }
    
    
    //modifiers for payingCustomer
    public void setPayingCustomer(PayingCustomer newPayingCustomer) {
        this.payingCustomer = newPayingCustomer;
    }
    
    public PayingCustomer getPayingCustomer(){
        return this.payingCustomer;
    }
}
