/**
 * Enum for SQL Strings.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 7/28/2026
 */

public enum SQL_Bank {
  PLAYER("player", """
      CREATE TABLE IF NOT EXISTS player (
          id          INTEGER PRIMARY KEY AUTOINCREMENT,
          username    TEXT NOT NULL,
          password    TEXT NOT NULL,
          name        TEXT NOT NULL,
          character   INTEGER NOT NULL DEFAULT 0,
          monstruos   TEXT DEFAULT NULL,
          level       INTEGER NOT NULL DEFAULT 0,
          health      REAL NOT NULL DEFAULT 100.0,
          updated     DATETIME DEFAULT CURRENT_TIMESTAMP,
          created     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP      )
      """);

  // Add next table enum here

  // Name of table
  private final String name;
  // String of sql create statement
  private final String sql;

  /**
   * Constructor for SQL_Bank enum.
   *
   * @param name name of table
   * @param sql  table create statement.
   */
  SQL_Bank(String name, String sql) {
    this.name = name;
    this.sql = sql;
  }

  /**
   * sql getter
   *
   * @return sql String.
   */
  public String getSql() {
    return sql;
  }

  /**
   * name getter
   *
   * @return table name String
   */
  public String getName() {
    return name;
  }
}
