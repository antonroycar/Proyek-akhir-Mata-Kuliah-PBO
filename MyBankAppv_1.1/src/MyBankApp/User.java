/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyBankApp;

/**
 *
 * @author Guntur Augustin
 */
public class User {
    private int id;
    private String name;
    private String pin;
    private int blockFlag;
    private int falseCounter; 
    //setter
    public void setId(int newId){
        id = newId;
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public void setPin(String newPin){
        pin = newPin;
    }
    
    public void setBlockFlag(int newBlockFlag){
        blockFlag = newBlockFlag;
    }
    
    public void setFalseCounter(int newFalseCounter){
        falseCounter = newFalseCounter;
    }
    
    //getter
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPin(){
        return pin;
    }
    
    public int getBlockFlag(){
        return blockFlag;
    }
    
    public int getFalseCounter(){
        return falseCounter;
    }
}
