package Database;

import SceneBuilding.PopupMessage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

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
   * @param username Entities.Player entered username.
   * @param password Entities.Player entered password.
   * @param name Entities.Player entered display name
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
      } else {
        return e.getMessage();
      }
    }
  }

  /**
   * Checks the passed login credentials against the database.
   * @param username Entities.Player provided username.
   * @param password Entities.Player provided password.
   * @return boolean result of whether the username and password combo were found.
   */
  static boolean checkLogin(String username, String password) {
    try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(
        SQL_CRUD.LOGIN_CHECK.getSql())) {
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

  /**
   * Provides player stats from player table
   * @param username username to search table
   * @return String array of stat info.
   */
  static String[] getPlayerStats(String username) {
    String[] stats = new String[6];
    try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(
        SQL_CRUD.PLAYER_STATS.getSql())) {
      pstmt.setString(1, username.toLowerCase());
      ResultSet resultSet = pstmt.executeQuery();
      stats[0] = resultSet.getString("password");
      stats[1] = resultSet.getString("name");
      stats[2] = Integer.toString(resultSet.getInt("character"));
      stats[3] = resultSet.getString("monstruos");
      stats[4] = Integer.toString(resultSet.getInt("level"));
      stats[5] = Float.toString(resultSet.getFloat("health"));
      return stats;
    } catch (SQLException e) {
      return stats;
    }
  }

  /**
   * Update password in player table
   * @param username username of password to delete
   * @param password new password.
   * @return Message
   */
  static String updatePassword(String username, String password) {
    try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(
        SQL_CRUD.PASSWORD_CHANGE.getSql())) {
      pstmt.setString(1, password);
      pstmt.setString(2, username);
      pstmt.executeUpdate();
      if (checkLogin(username, password)) {
        return "Password successfully changed";
      } else {
        return "Password did not update!";
      }
    } catch (SQLException e) {
      return e.getMessage();
    }
  }

  /**
   * Delete user account from player table.
   * @param username username to search.
   * @return boolean result of deletion.
   */
  static boolean deleteAccount(String username) {
    try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(
        SQL_CRUD.DELETE_ACCOUNT.getSql())) {
      pstmt.setString(1, username.toLowerCase());
      int rowsDeleted = pstmt.executeUpdate();
      return rowsDeleted > 0;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

}
