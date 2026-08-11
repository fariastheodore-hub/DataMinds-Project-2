package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Establishes connection to database and closes connection at stop of application. Singleton
 * implementation provides single instance of Database.DatabaseManager upon call of getInstance().
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 7/28/2026
 */

public class DatabaseManager {

  //database URL to connect to app.db SQLite database
  private static final String DB_URL = "jdbc:sqlite:app.db";
  //Holds reference of instance of Database.DatabaseManager
  private static DatabaseManager instance;
  //Connection to SQLite database - Package private
  protected static Connection connection;


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
   * Overloaded contructor for testing with separate DB.
   * @param dbUrl url of test database.
   */
  private DatabaseManager(String dbUrl) {
    try {
      connection = DriverManager.getConnection(dbUrl);
      System.out.println("Database connected.");
      createTables();
    } catch (SQLException e) {
      System.err.println("Connection failed: " + e.getMessage());
    }
  }

  /**
   * Creates the database tables based on the sql entered in the Database.SQL_Tables.
   */
  private void createTables() {
    SQL_Tables[] tables = SQL_Tables.values();
    for (SQL_Tables table : tables) {
      try (Statement stmt = connection.createStatement()) {
        stmt.execute(table.getSql());
        System.out.println("Table created: " + table.toString());
      } catch (SQLException e) {
        System.err.println("createTables failed: " + e.getMessage());
      }
    }

  }

  /**
   * Singleton implementation for instantiating the DatabaseManger or getting the single instance
   * that has been instantiated.
   *
   * @return single instance of Database.DatabaseManager
   */
  public static DatabaseManager getInstance() {
    if (instance == null) {
      instance = new DatabaseManager();
    }
    return instance;
  }

  /**
   * Overloaded instance return for testing using separate test url for DB.
   * @param dbUrl Database url for testing
   * @return instance of databaseManager
   */
  public static DatabaseManager getInstance(String dbUrl) {
    if (instance == null) {
      instance = new DatabaseManager(dbUrl);
    }
    return instance;
  }

  /**
   * Attempts to close the connection to the database. Called by SceneBuilding.Main.Java's stop() method.
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
