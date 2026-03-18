package script.ict373_a1_clarencelim_35008001;

public class PaymentMethod{
    private String paymentName;
    private String paymentType = "";
    
    public PaymentMethod(){}
    
    public PaymentMethod(String paymentName){
        this.paymentName = paymentName;
    }
    
    public boolean setPaymentType(){
        if(paymentName.equalsIgnoreCase("Visa")||
                paymentName.equalsIgnoreCase("Mastercard")||
                paymentName.equalsIgnoreCase("American Express")){
            paymentType = "CREDIT";
            return true;
        }
        else if(paymentName.equalsIgnoreCase("OCBC")||
                paymentName.equalsIgnoreCase("DBS")||
                paymentName.equalsIgnoreCase("POSB")||
                paymentName.equalsIgnoreCase("UOB")){
            paymentType = "DEBIT";
            return true;
        }
        else{
            System.out.println("Invalid type of payment");
            return false;
        }
    }
    
    public String getPaymentName(){
        return this.paymentName;
    }
    
    public String toString(){
        if(setPaymentType() == true){
            if(paymentType.equalsIgnoreCase("CREDIT")){
                return "The customer paid using credit card, " + paymentName;
            }
            else if(paymentType.equalsIgnoreCase("DEBIT")){
                return "The customer paid using direct debit transfer from " + paymentName;
            }
            else{
                return "Invalid type of payment";
            }
        }
        else{
            return "payment type failed to set.";
        }
    }
}
