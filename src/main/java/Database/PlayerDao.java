package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * player table DAO for CRUD operations.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/1/2026
 */
public interface PlayerDao {

  /**
   * Adds a player to the database.
   * @param username Player entered username.
   * @param password Player entered password.
   * @param name Player entered display name
   * @return message based on result.
   */
  static String createPlayer(String username, String password, String name) {
    try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(
        SQL_CRUD.PLAYER_ADD.getSql())) {
      pstmt.setString(1, username.toLowerCase());
      pstmt.setString(2, password);
      pstmt.setString(3, name);
      pstmt.executeUpdate();
      return "Account successfully created";
    } catch (SQLException e) {
      if (e.getErrorCode() == 19) {
        return "Username already exists";
      }
      else {
        return e.getMessage();
      }
    }
  }

  /**
   * Checks the passed login credentials against the database.
   * @param username Player provided username.
   * @param password Player provided password.
   * @return boolean result of whether the username and password combo were found.
   */
  static boolean checkLogin(String username, String password) {
    try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(SQL_CRUD.LOGIN_CHECK.getSql())) {
      pstmt.setString(1, username.toLowerCase());
      ResultSet resultSet = pstmt.executeQuery();
      String result = resultSet.getString("password");
      if (result == null) {
        return false;
      }
      System.out.println(result);
      return result.equals(password);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

}
