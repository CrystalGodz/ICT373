package script.ict373_a1_clarencelim_35008001;
import java.util.ArrayList;

public class AssociateCustomer extends Customer{
    private PayingCustomer payingCustomer;
    
    public AssociateCustomer(){
        super();
    }
    
    public AssociateCustomer(String custName, String custEmail, ArrayList<Supplement> list, String payingCust){
        super(custName, custEmail, list);
        this.payingCustomer = new PayingCustomer(payingCust);
    }
    
    public PayingCustomer getPayingCustomer(){
        return payingCustomer;
    }
    
    @Override
    public String toString(){
        return super.toString() + 
                "\n" + super.getCustName() + " is an associate customer, has been paid by " + payingCustomer.getCustName();
    }
}
