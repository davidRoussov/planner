package outline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TodayData {
	

	public static String createTableToday = "CREATE TABLE today ("
      		+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
      		+ "activity VARCHAR, "
      		+ "period VARCHAR);";
	
	public String[] selectAllActivities() {
		
		if (!Data.doesDatabaseExist("today")) {
			Data.createTable(createTableToday);
		}
		
		ArrayList<String> allActivities = new ArrayList<String>();
		
		Connection connection = null;
	    Statement statement = null;
	    try {
	      Class.forName("org.sqlite.JDBC");

	      connection = DriverManager.getConnection(Data.path);
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

	public void addActivity(String activity, char periodChar) {
	    String period = periodChar + "";
	    period = period.toUpperCase();
		
		Connection connection = null;
	    Statement statement = null;
	    try {
	      Class.forName("org.sqlite.JDBC");

	      connection = DriverManager.getConnection(Data.path);
	      statement = connection.createStatement();
	      
	      statement.executeUpdate("INSERT INTO today (activity, period) VALUES ('" + activity + "','" + period + "');");

	      statement.close();
	      connection.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.exit(0);
	    }
	}

	public void updateData(String[] allActivities) {
		
		Connection connection = null;
	    Statement statement = null;
	    try {
	      Class.forName("org.sqlite.JDBC");

	      connection = DriverManager.getConnection(Data.path);
	      statement = connection.createStatement();
	      
	      statement.executeUpdate("DELETE FROM today");
	      statement.executeUpdate("DELETE FROM sqlite_sequence WHERE name='today';");
	      
	      for (int i = 0; i < allActivities.length; i += 2) {
	    	  statement.executeUpdate("INSERT INTO today (activity, period) VALUES ('" + allActivities[i] + "','" + allActivities[i+1] + "');");
	      }
	      
	      statement.close();
	      connection.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.exit(0);
	    }	
	}

	public void deleteActivity(String activity, String period) {
		Connection connection = null;
	    Statement statement = null;
	    try {
	      Class.forName("org.sqlite.JDBC");

	      connection = DriverManager.getConnection(Data.path);
	      statement = connection.createStatement();
	      
	      statement.executeUpdate("DELETE FROM today WHERE activity='" + activity + "' AND period='" + period + "';");
	      
	      statement.close();
	      connection.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.exit(0);
	    }	
	}

}
