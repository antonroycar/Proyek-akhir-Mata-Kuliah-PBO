/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyBankApp;

import java.util.ArrayList;

/**
 *
 * @author Anton Roycar Nababan
 */
public class MainDriver {
    
    private static void testingUpdate(){
        ConnectDB conDB = new ConnectDB();
        conDB.connect();
        if(conDB.checkID(4)){
            System.out.print("benar");
        } else{
            System.out.print("salah");
        }
    }
    public static void main(String[] args){
        
        runProgram();
//        testingUpdate();
    }
    
    private static void runProgram(){
                Login l = new Login();
        l.setVisible(true);
        l.setLocationRelativeTo(null);
    }    
}
