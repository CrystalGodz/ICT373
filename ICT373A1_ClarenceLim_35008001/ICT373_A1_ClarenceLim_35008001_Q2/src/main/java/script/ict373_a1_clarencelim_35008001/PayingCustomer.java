package script.ict373_a1_clarencelim_35008001;
import java.util.ArrayList;

public class PayingCustomer extends Customer{
    private PaymentMethod paymentMethod;
    private ArrayList<Customer> associateCustomer;
    
    public PayingCustomer(){
        super();
        paymentMethod = new PaymentMethod();
        this.associateCustomer = new ArrayList<>();
    }
    
    public PayingCustomer(String custName){
        super.custName = custName;
    }
    
    public PayingCustomer(String custName, String custEmail, ArrayList<Supplement> list, String paymentType, ArrayList<Customer> aList){
        super(custName, custEmail, list);
        this.paymentMethod = new PaymentMethod(paymentType);
        this.associateCustomer = aList;
        
    }
    
    public String getAssociateName(){
        String name = "";
        String printName = "";
        for(int i=0;i<associateCustomer.size();i++){
            name = associateCustomer.get(i).getCustName();
            printName += "\n" + name;
        }
        return printName;
    }
    
    @Override
    public String getPaymentName(){
        return this.paymentMethod.getPaymentName();
    }
    
    @Override
    public PaymentMethod getPaymentMethod(){
        return paymentMethod;
    }
    
    @Override
    public ArrayList<Customer> getAssociateCustomer(){
        return this.associateCustomer;
    }
    
    @Override
    public String toString(){
        return super.toString() + "\n" +
                paymentMethod.toString() + "\n" +
                "to pay for the following associate customers:\n" + 
                getAssociateName();
    }
}
