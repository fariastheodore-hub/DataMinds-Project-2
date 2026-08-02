package Database;

/**
 * Enum for SQL Strings.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 7/28/2026
 */

public enum SQL_Bank {
  PLAYER_TABLE("player table creation", """
      CREATE TABLE IF NOT EXISTS player (
          id          INTEGER PRIMARY KEY AUTOINCREMENT,
          username    TEXT NOT NULL,
          password    TEXT NOT NULL,
          name        TEXT NOT NULL,
          character   INTEGER NOT NULL DEFAULT 0,
          monstruos   TEXT DEFAULT NULL,
          level       INTEGER NOT NULL DEFAULT 0,
          health      REAL NOT NULL DEFAULT 100.0,
          created     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
      )
      """);

  // Add next enum here

  // Description of SQL statement.
  private final String description;
  // String of SQL statement.
  private final String sql;

  /**
   * Constructor for Database.SQL_Bank enum.
   *
   * @param description description of SQL statement.
   * @param sql         SQL statement.
   */
  SQL_Bank(String description, String sql) {
    this.description = description;
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
   * Overridden toString
   *
   * @return String of SQL description.
   */
  @Override
  public String toString() {
    return "SQL description: " + description;
  }
}
