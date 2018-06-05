package tees;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection 
{
    public Connection Connection()
    {
        try 
        {

        	Class.forName("com.mysql.jdbc.Driver"); 
        	  Connection myConnection =DriverManager.getConnection("jdbc:mysql://localhost:3306/tees","root","root");		
            
            return myConnection;
        } catch (ClassNotFoundException | SQLException ex) {Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);}
        return null;
    }
}
