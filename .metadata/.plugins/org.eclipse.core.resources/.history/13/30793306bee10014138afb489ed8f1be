package outline;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TodayData {
	
	private static String projectName = "Outline";
	public static String databaseName = "/outline.db";
	private String path = "jdbc:sqlite:" + this.getCurrentDirectory() + databaseName; 
	private String createTableToday = "CREATE TABLE today ("
      		+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
      		+ "activity VARCHAR, "
      		+ "period VARCHAR);";
	
	public String[] selectAllActivities() {
		
		if (!doesDatabaseExist()) {
			createDatabaseAndTables();
		}
		
		ArrayList<String> allActivities = new ArrayList<String>();
		
		Connection connection = null;
	    Statement statement = null;
	    try {
	      Class.forName("org.sqlite.JDBC");

	      connection = DriverManager.getConnection(path);
	      statement = connection.createStatement();

	      String sql = "SELECT * FROM today;";
	      ResultSet rs = statement.executeQuery(sql);
	      while (rs.next()) {
	    	  allActivities.add(rs.getString(2));
	    	  allActivities.add(rs.getString(3));
	      }
	      
	      statement.close();
	      connection.close();
	    } catch ( Exception e ) {
	      e.printStackTrace();
	      System.exit(0);
	    }
	    
	    return allActivities.toArray(new String[allActivities.size()]);
	}
	
	public String getCurrentDirectory() {
		String currentDirectory = System.getProperty("user.dir");
		currentDirectory = currentDirectory.replace("\\", "/");
	    currentDirectory = currentDirectory.substring(0, currentDirectory.length() - projectName.length());
		return currentDirectory;
	}
	
	public boolean doesDatabaseExist() {
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
		      
	    	  ResultSet rs1 = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='today' AND name='people';");
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
	
	public void createDatabaseAndTables() {
	    Connection connection = null;
	    Statement statement = null;
	    try {
	      Class.forName("org.sqlite.JDBC");

	      connection = DriverManager.getConnection(path);
	      statement = connection.createStatement();
	      
	      try {
	    	  statement.executeUpdate(createTableToday);
	      } catch(Exception e) {}

	      statement.close();
	      connection.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.exit(0);
	    }
	}

	public void addActivity(String activity, char periodChar) {
	    String period = periodChar + "";
	    period = period.toUpperCase();
		
		Connection connection = null;
	    Statement statement = null;
	    try {
	      Class.forName("org.sqlite.JDBC");

	      connection = DriverManager.getConnection(path);
	      statement = connection.createStatement();
	      
	      statement.executeUpdate("INSERT INTO today (activity, period) VALUES ('" + activity + "','" + period + "');");

	      statement.close();
	      connection.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.exit(0);
	    }
	}

}
