/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyBankApp;

import java.util.ArrayList;

/**
 *
 * @author Guntur Augustin
 */
public class Isolation {
    public Isolation(){
        ConnectDB conDB = new ConnectDB();
        conDB.connect();
        ArrayList<User> ua = new ArrayList<User>();
        ua = conDB.getAllUser();
        for(int i = 0; i < ua.size(); i++){
            System.out.print(ua.get(i).getName());
            System.out.print(" ");
            System.out.print(ua.get(i).getPin());
        }
    }
}
