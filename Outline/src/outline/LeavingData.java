package outline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LeavingData {
	
	private static String createTableLeaving = "CREATE TABLE leaving ("
      		+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
      		+ "checkitem VARCHAR, "
      		+ "association VARCHAR);";
	
	public static String[] selectAllCommon() {
		
		if (!Data.doesDatabaseExist("leaving")) {
			Data.createTable(createTableLeaving);
		}
		
		ArrayList<String> allItems = new ArrayList<String>();
		
		Connection connection = null;
	    Statement statement = null;
	    try {
	      Class.forName("org.sqlite.JDBC");

	      connection = DriverManager.getConnection(Data.path);
	      statement = connection.createStatement();

	      String sql = "SELECT * FROM leaving;";
	      ResultSet rs = statement.executeQuery(sql);
	      while (rs.next()) {
	    	  allItems.add(rs.getString(2));
	    	  allItems.add(rs.getString(3));
	      }
	      
	      statement.close();
	      connection.close();
	    } catch ( Exception e ) {
	      e.printStackTrace();
	      System.exit(0);
	    }
	    
	    return allItems.toArray(new String[allItems.size()]);
	}

}
