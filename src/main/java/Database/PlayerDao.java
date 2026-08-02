package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PlayerDao {

  public static void createPlayer(String username, String password, String name) {
    try (PreparedStatement pstmt = DatabaseManager.getInstance().getConnection().prepareStatement(
        SQL_CRUD.PLAYER_ADD.getSql())) {
      pstmt.setString(1, username);
      pstmt.setString(2, password);
      pstmt.setString(3, name);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.err.println("InsertItem failed: " + e.getMessage());
    }
  }
}
