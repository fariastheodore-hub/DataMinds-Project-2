import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Establishes connection to database and closes connection at stop of application. Singleton
 * implementation provides single instance of DatabaseManager upon call of getInstance().
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 7/28/2026
 */

public class DatabaseManager {

  //database URL to connect to app.db SQLite database
  private static final String DB_URL = "jdbc:sqlite:app.db";
  //Holds reference of instance of DatabaseManager
  private static DatabaseManager instance;
  //Connection to SQLite database
  private Connection connection;


  /**
   * Constructor establishes connection to database or provides error message if connection cannot
   * be established. Calls createTables() method.
   */
  private DatabaseManager() {
    try {
      connection = DriverManager.getConnection(DB_URL);
      System.out.println("Database connected.");
      createTables();
    } catch (SQLException e) {
      System.err.println("Connection failed: " + e.getMessage());
    }
  }

  /**
   * Creates the database tables based on the sql entered in the SQL_Bank.
   */
  private void createTables() {
    SQL_Bank[] tables = SQL_Bank.values();
    for (SQL_Bank table : tables) {
      try (Statement stmt = connection.createStatement()) {
        stmt.execute(table.getSql());
        System.out.println("Table created: " + table.getSql());
      } catch (SQLException e) {
        System.err.println("createTables failed: " + e.getMessage());
      }
    }

  }

  /**
   * Singleton implementation for instantiating the DatabaseManger or getting the single instance
   * that has been instantiated.
   *
   * @return single instance of DatabaseManager
   */
  public static DatabaseManager getInstance() {
    if (instance == null) {
      instance = new DatabaseManager();
    }
    return instance;
  }

  /**
   * Attempts to close the connection to the database. Called by Main.Java's stop() method.
   */
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
