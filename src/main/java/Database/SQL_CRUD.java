package Database;

public enum SQL_CRUD {
  PLAYER_ADD("Create new player", """
      INSERT INTO player (username, password, name)
      VALUES (?, ?, ?)
      ON CONFLICT(username) DO NOTHING;""");

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
