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
   * @return DaoCode based on result.
   */
  static DaoCode createPlayer(String username, String password, String name) {
    String hashedPassword = PasswordHasher.hashPassword(password);
    try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(
        SQL_CRUD.PLAYER_ADD.getSql())) {
      pstmt.setString(1, username.toLowerCase());
      pstmt.setString(2, hashedPassword);
      pstmt.setString(3, name);
      pstmt.executeUpdate();
      return DaoCode.CREATION_SUCCESS;
    } catch (SQLException e) {
      if (e.getErrorCode() == 19) {
        return DaoCode.USERNAME_TAKEN;
      } else {
        return DaoCode.CREATION_FAILURE;
      }
    }
  }

  /**
   * Checks the passed login credentials against the database.
   * @param username Entities.Player provided username.
   * @param password Entities.Player provided password.
   * @return DaoCode result of whether the username and password combo were found.
   */
  static DaoCode checkLogin(String username, String password) {
    try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(
        SQL_CRUD.LOGIN_CHECK.getSql())) {
      pstmt.setString(1, username.toLowerCase());
      ResultSet resultSet = pstmt.executeQuery();
      String result = resultSet.getString("password");
      if (result == null) {
        return DaoCode.LOGIN_FAILURE;
      }
      // If there is something in the password field, use PasswordHasher to verify it.
      if (PasswordHasher.verifyPassword(password, result)) {
        return DaoCode.LOGIN_SUCCESS;
      }
      else return DaoCode.LOGIN_FAILURE;
    } catch (SQLException e) {
      return DaoCode.LOGIN_FAILURE;
    }
  }

  /**
   * Provides player stats from player table
   * @param username username to search table
   * @return String array of stat info.
   */
  static String[] getPlayerStats(String username) {
    String[] stats = new String[5];
    try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(
        SQL_CRUD.PLAYER_STATS.getSql())) {
      pstmt.setString(1, username.toLowerCase());
      ResultSet resultSet = pstmt.executeQuery();
      stats[0] = resultSet.getString("name");
      stats[1] = Integer.toString(resultSet.getInt("character"));
      stats[2] = resultSet.getString("monstruos");
      stats[3] = Integer.toString(resultSet.getInt("level"));
      stats[4] = Float.toString(resultSet.getFloat("health"));
      return stats;
    } catch (SQLException e) {
      return stats;
    }
  }

  /**
   * Update password in player table
   * @param username username of password to delete
   * @param password new password.
   * @return DaoCode result
   */
  static DaoCode updatePassword(String username, String password) {
    String hashedPassword = PasswordHasher.hashPassword(password);
    try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(
        SQL_CRUD.PASSWORD_CHANGE.getSql())) {
      pstmt.setString(1, hashedPassword);
      pstmt.setString(2, username.toLowerCase());
      int rowsUpdated = pstmt.executeUpdate();
      if (rowsUpdated > 0) {
        return DaoCode.PASSWORD_UPDATE_SUCCESS;
      } else {
        return DaoCode.PASSWORD_UPDATE_FAILURE;
      }
    } catch (SQLException e) {
      return DaoCode.PASSWORD_UPDATE_FAILURE;
    }
  }

  /**
   * Updates the character column based on username.
   * @param username active user username.
   * @param character chosen character index.
   * @return DaoCode result.
   */
  static DaoCode updateCharacter(String username, int character) {
    try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(
        SQL_CRUD.UPDATE_CHARACTER.getSql())) {
      pstmt.setInt(1, character);
      pstmt.setString(2, username.toLowerCase());
      int rowsUpdated = pstmt.executeUpdate();
      if (rowsUpdated > 0) {
        return DaoCode.CHARACTER_UPDATE_SUCCESS;
      } else {
        return DaoCode.CHARACTER_UPDATE_FAILURE;
      }
    } catch (SQLException e) {
      return DaoCode.CHARACTER_UPDATE_FAILURE;
    }
  }

  /**
   * Delete user account from player table.
   * @param username username to search.
   * @return DaoCode result of deletion.
   */
  static DaoCode deleteAccount(String username) {
    try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(
        SQL_CRUD.DELETE_ACCOUNT.getSql())) {
      pstmt.setString(1, username.toLowerCase());
      int rowsDeleted = pstmt.executeUpdate();
      if (rowsDeleted > 0) {
        return DaoCode.ACCOUNT_DELETE_SUCCESS;
      } else {
        return DaoCode.ACCOUNT_DELETE_FAILURE;
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return DaoCode.ACCOUNT_DELETE_FAILURE;
    }
  }

}
