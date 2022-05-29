/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyBankApp;

import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Anton Roycar Nababan
 */
public class ConnectDB{
    private ResultSet rs; 
    private Connection connection;
    private Statement stmt; 
    
    public void connect() {
    try {
         Class. forName("org.h2.Driver");
            try {
                    connection =DriverManager.getConnection("jdbc:h2:./src/DB","admin","qwert");
                    stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            }
            catch (SQLException ex) {
            // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
            }
            
    }catch (Exception ex) {
           ex.printStackTrace();
        }
   }
    
    public void adisconnect(){
        try{
        rs.close();
        stmt.close();
        connection.close();}
        catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
   
    public ArrayList<User> getAllUser(){
        ArrayList<User> ua = new ArrayList<User>();
        try {
            rs = stmt.executeQuery("select * from userdata order by id");
            while(rs.next() == true){
                User u = new User();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setPin(rs.getString(3));
                u.setBlockFlag(rs.getInt(4));
                ua.add(u);
            }
            
        }
            catch (SQLException ex) {
            // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
            }
        
        return ua;
    }
    
    public int checkUsername(String newUser){
        int a = 0;
        try{
            rs = stmt.executeQuery("select id, name from userdata where name = '" + newUser + "'");
            if(rs.next() == true){
                a = rs.getInt(1);
            }
        }catch (SQLException ex) {
                 System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
            }
        return a;
    }
    
    public void adminAddUser(String name, String pin, int balance){
        try{
            int a = getLastUserID() + 1;
            stmt.executeUpdate("insert into userdata values (" + a + ",'" + name + "','" + pin + "',0,0)" );
            String sql = "create table balancedatausr" + a + "(\n" +
                        "    transID int primary key,\n" +
                        "    transDate date not null,\n" +
                        "    prevBalance int not null, \n" +
                        "    afterBalance int not null, \n" +
                        "    transactiontype varchar(20) not null\n" +
                        ")";
            stmt.executeUpdate(sql);
            
            sql = "insert into balancedatausr" + a + " values(1,curdate()," + balance + "," + balance + ",'REGISTRASI')";
            stmt.executeUpdate(sql);
        }catch (SQLException ex) {
                 System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
            }
    }
    
    
    public void adminDeluser(String nama){
        try{
            int k = checkUsername(nama);
            String sql = "drop table balancedatausr" + k;
            stmt.executeUpdate(sql);
            sql = "delete from userdata where id = " + k;
            stmt.executeUpdate(sql);
        }catch (SQLException ex) {
                 System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
            }
    }
    
    public boolean adminDeluser(int id){
        boolean a =true;
        try{
            int k = id;
            String sql = "drop table balancedatausr" + k;
            stmt.executeUpdate(sql);
            sql = "delete from userdata where id = " + k;
            stmt.executeUpdate(sql);
        }catch (SQLException ex) {
                 
                    a = false;
            }
        return a;
    }
    
    private int getLastUserID(){
        int a = 0;
        try{
            rs = stmt.executeQuery("select max(ID) from userdata");
            rs.next();
            a = rs.getInt(1);
        }catch (SQLException ex) {
                 System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
            }
        return a;
    }
    
    public boolean checkID(int newID){
        boolean a = true;
        try{
            rs = stmt.executeQuery("select id from userdata where id = " + newID);
            if(rs.next() == false){
                a = false;
            }
        }catch (SQLException ex) {
                 
                    
            }
        return a;
    }
    
    public String getUsername(int newID){
        String a = "";
        try{
            rs = stmt.executeQuery("select name from userdata where id = " + newID);
            if(rs.next() == true){
                a = rs.getString(1);
            }
        }catch (SQLException ex) {
                 System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
            }
        return a;
    }
    
    public ArrayList<BalanceData> getUserBalance(int id){
        ArrayList<BalanceData> ua = new ArrayList<BalanceData>();
        try {
            rs = stmt.executeQuery("select * from balancedatausr" + id);
            while(rs.next() == true){
                BalanceData u = new BalanceData();
                u.setID(rs.getInt(1));
                u.setDate(rs.getString(2));
                u.setprevBalance(rs.getInt(3));
                u.setAfterBalance(rs.getInt(4));
                u.setTransaction(rs.getString(5));
                ua.add(u);
            }
        }
            catch (SQLException ex) {
            // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
            }
        return ua;
    }
    
    public void userPullBalance(int id, int amount){
        try{
            int newTransID = getLastTransID(id) + 1;
            int curAmount = getLastBalance(id) + amount;
            String sql = "INSERT INTO balancedatausr" + id + " values(" + newTransID + ",curdate()," + getLastBalance(id) + "," + curAmount + ", 'PENARIKAN')";
            stmt.executeUpdate(sql);
            
        } catch (SQLException ex) {
            // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
            }
    }
    
    public void userPushBalance(int id, int amount){
        try{
            int newTransID = getLastTransID(id) + 1;
            int curAmount = getLastBalance(id) + amount;
            String sql = "INSERT INTO balancedatausr" + id + " values(" + newTransID + ",curdate()," + getLastBalance(id) + "," + curAmount + ", 'PENYETORAN')";
            stmt.executeUpdate(sql);
            
        } catch (SQLException ex) {
            // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
            }
    }
    
    public void adminPushChange(int id, int amount, String desc){
        try{
            int newTransID = getLastTransID(id) + 1;
            int curAmount = getLastBalance(id) + amount;
            String sql = "INSERT INTO balancedatausr" + id + " values(" + newTransID + ",curdate()," + getLastBalance(id) + "," + curAmount + ", '" + desc + "')";
            stmt.executeUpdate(sql);
            
        } catch (SQLException ex) {
            // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
            }
    }
    
    public int getLastTransID(int id){
        int a = 0;
        try{
            rs = stmt.executeQuery("select max(transid) from balancedatausr" + id);
            rs.next();
            a = rs.getInt(1);
        } catch(SQLException e){
            
        }
        return a;
    }
    
    public int getLastBalance(int id){
        int lastBalance = 0;
        try{
            rs = stmt.executeQuery("select afterBalance from balancedatausr" + id);
            if(rs.last() == true){
                lastBalance = rs.getInt(1);
            }
        } catch (SQLException ex) {
            // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
        }
        return lastBalance;
    }
    
    public void delLastTrans(int id){
        try{
            
            String sql = "delete from balancedatausr" + id + " where transid = (select max(transid) from balancedatausr" + id + ")";
            stmt.executeUpdate(sql);
            
        } catch (SQLException ex) {
            // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
            }
    }
    
    public void adminUnblockUSer(int id){
        try{
            stmt.executeUpdate("UPDATE userdata SET BLOCKFLAG = 0 WHERE ID = " + id);
            stmt.executeUpdate("UPDATE userdata SET FALSECOUNTER = 0 WHERE ID = " + id);
        }catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    public void adminblockUSer(int id){
        try{
            stmt.executeUpdate("UPDATE userdata SET BLOCKFLAG = 1 WHERE ID = " + id);
            stmt.executeUpdate("UPDATE userdata SET FALSECOUNTER = 0 WHERE ID = " + id);
        }catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    public void blockUser(int id){
        int fAmount = 0;
        try{
            rs = stmt.executeQuery("SELECT falsecounter FROM userdata where id = " + id);
            if(rs.last() == true){
                fAmount = rs.getInt(1);
            }
            
            if(fAmount == 2){
                //block user
                stmt.executeUpdate("UPDATE userdata SET BLOCKFLAG = 1 WHERE ID = " + id);
                stmt.executeUpdate("UPDATE userdata SET FALSECOUNTER = 0 WHERE ID = " + id);
            } else{
                stmt.executeUpdate("UPDATE userdata SET FALSECOUNTER = FALSECOUNTER + 1 WHERE ID = " + id);
            }
            
            
        }catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public boolean getBlockInfo(int id){
        int checks = 0;
        try{
            rs = stmt.executeQuery("SELECT BLOCKFLAG FROM userdata where id = " + id);
            if(rs.last() == true){
                checks = rs.getInt(1);
            }
        }catch (SQLException ex) {
                 System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
        }
        if(checks == 0){
            return false;
        } else{
            return true;
        }
    }
    
    public void adminChangePin(int id, String newPin){
        try{
            stmt.executeUpdate("update userdata set pin = '" +newPin + "' where id = " + id);
        }catch (SQLException ex) {
                 System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
