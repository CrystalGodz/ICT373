package script.ict373_a1_clarencelim_35008001;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Customer{
    String custName;
    private String custEmail;
    private ArrayList<Supplement> subscribedSupplement;
    //pattern matching for customer email
    private Pattern emailFormat = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    
    public Customer(){
        this.custName = "";
        this.custEmail = "";
        this.subscribedSupplement = new ArrayList<>();
    }
    
    public Customer(String newCustName, String newCustEmail, ArrayList<Supplement> newSupplement){
        this.custName = newCustName;
        this.custEmail = newCustEmail;
        this.subscribedSupplement = newSupplement;
    }
    
    public String getCustName(){
        return this.custName;
    }
    
    public boolean setCustEmail(String newCustEmail){
        Matcher matcher = emailFormat.matcher(newCustEmail);
        if(matcher.find()){
            this.custEmail = newCustEmail;
            return true;
        }
        else{
            System.out.println("Invalid email! Try again!");
            return false;
        }
    }
    
    public String getCustEmail(){
        return this.custEmail;
    }
    
    public ArrayList<Supplement> getSupList(){
        return this.subscribedSupplement;
    }
    
    public String getPaymentName(){
        return null;
    }
    
    public PaymentMethod getPaymentMethod(){
        return null;
    }
    
    public ArrayList<Customer> getAssociateCustomer(){
        return null;
    }
    
    public String toString(){
        return String.format("%n%s %s%n%s %s%n%s%n%s%n",
                "Name: ", custName,
                "Email: ", custEmail,
                "Subscriptions:", subscribedSupplement.toString());
    }
}
