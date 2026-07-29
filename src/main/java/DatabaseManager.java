import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

  private static final String DB_URL = "jdbc:sqlite:app.db";
  private static DatabaseManager instance;
  private Connection connection;

  private DatabaseManager() {
    try {
      connection = DriverManager.getConnection(DB_URL);
      System.out.println("Database connected.");
      //createTables();
    } catch (SQLException e) {
      System.err.println("Connection failed: " + e.getMessage());
    }
  }

  public static DatabaseManager getInstance() {
    if (instance == null) {
      instance = new DatabaseManager();
    }
    return instance;
  }

  public void close() {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    instance = null;
  }

}
