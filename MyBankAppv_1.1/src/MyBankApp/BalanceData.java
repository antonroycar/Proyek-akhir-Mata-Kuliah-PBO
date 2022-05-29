/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyBankApp;

/**
 *
 * @author Guntur Augustin
 */
public class BalanceData {
    private int id;
    private String date;
    private int prevBalance;
    private int afterBalance;
    private String transaction;
    
    //setter
    public void setID(int newid){
        id = newid;
    }
    
    public void setDate(String newDate){
        date = newDate;
    }
    
    public void setprevBalance(int newPrevBalance){
        prevBalance = newPrevBalance;
    }
    
    public void setAfterBalance(int newAfterBalance){
        afterBalance = newAfterBalance;
    }
    
    public void setTransaction(String newTransaction){
        transaction = newTransaction;
    }
    
    //getter
    public String getDate(){
        return date;
    }
    
    public int getPrevBalance(){
        return prevBalance;
    }
    
    public int getAfterBalance(){
        return afterBalance;
    }
    
    public String getTransaction(){
        return transaction;
    }
    
    public int getId(){
        return id;
    }
}
