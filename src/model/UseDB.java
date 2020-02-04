package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import application.Main;
public class UseDB {
	
	  public static void createDB()
	  {
	      Connection c = null;
	        Statement stmt = null;
	        try {
	          Class.forName("org.sqlite.JDBC");
	          c = DriverManager.getConnection("jdbc:sqlite:Users.sqlite");
	          	 
	          stmt = c.createStatement();
	          String sql = "CREATE TABLE if not exists Users " +
	                       "(USERNAME    TEXT    NOT NULL, " + 
	                       " PASSWORD    TEXT    NOT NULL, PRIMARY KEY (USERNAME))";
	          stmt.executeUpdate(sql);
	          stmt.close();
	          c.close();
	        } catch ( Exception e ) {
	        	Main.logger.log(Level.SEVERE, "Exception: ", e);
	        }	        
	  }
	   
	  public static void insertDB(String User, String Pass)
	  {
	      Connection c = null;
	        Statement stmt = null;
	        try {
	          Class.forName("org.sqlite.JDBC");
	          c = DriverManager.getConnection("jdbc:sqlite:Users.sqlite");
	          c.setAutoCommit(false);
	          	 
	          stmt = c.createStatement();
	          String sql = "INSERT OR IGNORE INTO Users (USERNAME, PASSWORD) "
	                    + "VALUES ('"+User +"'," + "'" + Pass + "')";
	          stmt.executeUpdate(sql);
	          stmt.close();
	          c.commit();
	          c.close();
	        } catch ( Exception e ) {
	        	Main.logger.log(Level.SEVERE, "Exception: ", e);
	        }	        
	  }
	   
	  public static String selectDB(String User)
	  {
	        Connection c = null;
	        Statement stmt = null;
	        try {
	          Class.forName("org.sqlite.JDBC");
	          c = DriverManager.getConnection("jdbc:sqlite:Users.sqlite");
	          c.setAutoCommit(false);
	         
	          stmt = c.createStatement();
	          ResultSet rs = stmt.executeQuery( "SELECT * FROM Users where USERNAME='" + User +  "';" );
	          
	          while ( rs.next() ) {	             
	             
	             String  PASSWORD = rs.getString("PASSWORD");	             	             
	             return PASSWORD;
	          }
	          rs.close();
	          stmt.close();
	          c.close();
	        } catch ( Exception e ) {
	        	Main.logger.log(Level.SEVERE, "Exception: ", e);
	        }
	        
			return User;  
	  }
	  
	  public static String selectAllUsers(String allUser)
	  {
	        Connection c = null;
	        Statement stmt = null;
	        List<String> userList = new ArrayList<String>();
	        try {
	          Class.forName("org.sqlite.JDBC");
	          c = DriverManager.getConnection("jdbc:sqlite:Users.sqlite");
	          c.setAutoCommit(false);
	         
	          stmt = c.createStatement();
	          ResultSet rs = stmt.executeQuery( "SELECT USERNAME FROM Users;" );
	          
	          while ( rs.next() ) {	             
	             
	        	  userList.add(rs.getString("USERNAME"));
	          }
	          rs.close();
	          stmt.close();
	          c.close();
	        } catch ( Exception e ) {
	        	Main.logger.log(Level.SEVERE, "Exception: ", e);
	        }
	        allUser=userList.toString();
			return allUser;  
	  }
	      
	 	   
	 /* public static void deleteDB()
	  {
	      Connection c = null;
	        Statement stmt = null;
	        try {
	          Class.forName("org.sqlite.JDBC");
	          c = DriverManager.getConnection("jdbc:sqlite:Users.sqlite");
	          c.setAutoCommit(false);
	          	 
	          stmt = c.createStatement();
	          String sql = "DELETE from Users where USERNAME='user1';";
	          stmt.executeUpdate(sql);
	          c.commit();
	 
	          ResultSet rs = stmt.executeQuery( "SELECT * FROM Users;" );
	          while ( rs.next() ) {
	             
	             String  USERNAME = rs.getString("USERNAME");
	             String  PASSWORD = rs.getString("PASSWORD");
	             ;
	          }
	          rs.close();
	          stmt.close();
	          c.close();
	        } catch ( Exception e ) {
	          Main.logger.log(Level.SEVERE, "Exception: ", e);
	        }	        
       }*/
}
