package Database;

/**
 * Enum for SQL Strings.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 7/28/2026
 */

public enum SQL_Tables {
  PLAYER_TABLE("player", """
      CREATE TABLE IF NOT EXISTS player (
          id          INTEGER PRIMARY KEY AUTOINCREMENT,
          username    TEXT UNIQUE NOT NULL,
          password    TEXT NOT NULL,
          name        TEXT NOT NULL,
          character   INTEGER NOT NULL DEFAULT 0,
          monstruos   TEXT NOT NULL DEFAULT 0,
          level       INTEGER NOT NULL DEFAULT 0,
          health      REAL NOT NULL DEFAULT 100.0,
          created     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
      )
      """)

  // Add next enum here
  ;

  // Title of sql table.
  private final String TITLE;
  // String of SQL statement.
  private final String SQL;


  /**
   * Constructor for Database.SQL_Tables enum.
   *
   * @param title title of SQL table.
   * @param sql SQL statement.
   */
  SQL_Tables(String title, String sql) {
    TITLE = title;
    SQL = sql;
  }

  /**
   * sql getter
   *
   * @return sql String.
   */
  public String getSql() {
    return SQL;
  }


  /**
   * Overridden toString
   *
   * @return String of SQL table info.
   */
  @Override
  public String toString() {
    return "SQL_Tables{" + "title=" + TITLE + ", sql=" + SQL + '}';
  }
}
