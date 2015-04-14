package outline;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Data {
	
	public static String projectName = "Outline";
	public static String databaseName = "/outline.db";
	public static String path = "jdbc:sqlite:" + Data.getCurrentDirectory() + databaseName; 

	public static boolean doesDatabaseExist(String tableName) {
		boolean check = true;
		
		// first checking if the database .db file even exists
	    File file = new File(getCurrentDirectory() + databaseName);
		if (!file.exists())
			check = false;
		
		// if it does exist, we now check if all tables needed are there
		if (check) {
			Connection connection = null;
		    Statement statement = null;
		    try {
		      Class.forName("org.sqlite.JDBC");

		      connection = DriverManager.getConnection(path);
		      statement = connection.createStatement();
		     
	    	  ResultSet rs1 = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "';");
	    	  try {
	    		  rs1.getString(1);
	    	  } catch (Exception e) {check = false;}

		      statement.close();
		      connection.close();
		    } catch ( Exception e ) {
		    	e.printStackTrace();
		      System.exit(0);
		    }
		}
		
	    return check;
	}
	
	public static String getCurrentDirectory() {
		String currentDirectory = System.getProperty("user.dir");
		currentDirectory = currentDirectory.replace("\\", "/");
	    currentDirectory = currentDirectory.substring(0, currentDirectory.length() - projectName.length());
		return currentDirectory;
	}
	
	public static void createTable(String sql) {
	    Connection connection = null;
	    Statement statement = null;
	    try {
	      Class.forName("org.sqlite.JDBC");

	      connection = DriverManager.getConnection(Data.path);
	      statement = connection.createStatement();
	      
	      try {
	    	  statement.executeUpdate(sql);
	      } catch(Exception e) {}

	      statement.close();
	      connection.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.exit(0);
	    }
	}
}
