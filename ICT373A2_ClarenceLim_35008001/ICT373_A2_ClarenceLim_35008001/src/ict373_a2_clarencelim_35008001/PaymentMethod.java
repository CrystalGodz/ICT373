package ict373_a2_clarencelim_35008001;
import java.io.Serializable;

public class PaymentMethod implements Serializable{
    private String paymentType;
    private long serialNo;
    
    public PaymentMethod(){
        this.paymentType = "";
        this.serialNo = 0;
    }
    
    public PaymentMethod(String newPaymentType, long newSerialNo) {
        this.paymentType = newPaymentType;
        this.serialNo = newSerialNo;
    }
    
    
    //modifiers for paymentType
    public void setPaymentType(String newPaymentType){
        this.paymentType = newPaymentType;
    }
    
    public String getPaymentType(){
        return this.paymentType;
    }
    
    
    //modifiers for serialNo
    public void setSerialNo(long newSerialNo){
        this.serialNo = newSerialNo;
    }
    
    public long getSerialNo(){
        return this.serialNo;
    }
    
    
    public String toString(){
        return this.paymentType + "\nSerial Number: " + this.serialNo;
    }
}
