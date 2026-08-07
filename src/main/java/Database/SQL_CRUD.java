package Database;

/**
 * Enum for database CRUD operation SQL
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/1/2026
 */
public enum SQL_CRUD {
  PLAYER_ADD("Create new player", """
      INSERT INTO player (username, password, name)
      VALUES (?, ?, ?)
      """),

  LOGIN_CHECK("Try to retrieve login info.", """
      SELECT password
      FROM player
      WHERE username = ?
      """),

  PASSWORD_CHANGE("Try to change password", """
      UPDATE player
      SET password = ?
      WHERE username = ?
      """),

  PLAYER_STATS("Gathers player stats for Entities.Player Account Scene", """
      SELECT name, character, monstruos, level, health
      FROM player
      WHERE username = ?
      """),

  DELETE_ACCOUNT("Delete Account", """
          DELETE FROM player
          WHERE username = ?
      """),

  UPDATE_CHARACTER("Change character", """
          UPDATE player
          SET character = ?
          WHERE username = ?
  """)
  //Add more SQL enums here.
  ;

  private final String DESCRIPTION;
  private final String SQL;

  private SQL_CRUD(String description, String sql) {
    DESCRIPTION = description;
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

  @Override
  public String toString() {
    return DESCRIPTION;
  }
}
