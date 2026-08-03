package Database;

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
