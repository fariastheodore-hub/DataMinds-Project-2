package Database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing class for PlayerDao methods.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/11/2026
 */
class PlayerDaoTest {

  /**
   * Setup in-memory database before each test.
   * Create our "TheNewPlayer" player before each test.
   */
  @BeforeEach
  void setUp() {
    System.setProperty("app.db.url", "jdbc:sqlite::memory:");
    DatabaseManager.resetForTesting();
    DatabaseManager.getInstance();
    PlayerDao.createPlayer("TheNewPlayer", "Password123!", "TESTER");
  }

  /**
   * Close the connection and nullify the in-memory database after each test.
   */
  @AfterEach
  void tearDown() {
    DatabaseManager.resetForTesting();
  }

  /**
   * Test creating a player returns the proper DaoCode.
   */
  @Test
  void createPlayer() {
    // Ensure TheNewPlayer account is not there.
    PlayerDao.deleteAccount("TheNewPlayer");
    // New Player info
    String username = "TheNewPlayer";
    String password = "Password123!";
    String name = "TESTER";

    // Running PlayerDao.createPlayer
    DaoCode code = PlayerDao.createPlayer(username, password, name);

    // Should provide DaoCode of Creation Success message provided from PlayerDao.createPlayer
    assertEquals(DaoCode.CREATION_SUCCESS, code);

    // Running PlayerDao.createPlayer on same username, password, and name.
    DaoCode code2 = PlayerDao.createPlayer(username, password, name);

    // should provide DaoCode of Username Taken because username is already taken.
    assertEquals(DaoCode.USERNAME_TAKEN, code2);

  }

  /**
   * Test checking login with valid Username and Password returns appropriate DaoCode.
   */
  @Test
  void checkLogin() {
    //New player info created in createPlayer test
    String username = "TheNewPlayer";
    String password = "Password123!";

    // Running PlayerDao.checkLogin
    DaoCode code = PlayerDao.checkLogin(username, password);

    // Should provide DaoCode of Login Success
    assertEquals(DaoCode.LOGIN_SUCCESS, code);
  }

  /**
   * Test that the proper player stats are returned in a String array.
   */
  @Test
  void getPlayerStats() {
    String username = "TheNewPlayer";

    // get stats from table for TheNewPlayer
    String[] stats = PlayerDao.getPlayerStats(username);

    // Should contain five values.
    assertEquals(5, stats.length);
    // Should be player name
    assertEquals("TESTER", stats[0]);
    // Should be player character index (default 0)
    assertEquals("0", stats[1]);
    // Should be player monstruo string (default 0)
    assertEquals("0", stats[2]);
    // should be player level (default 0)
    assertEquals("0", stats[3]);
    // should be player health (default 100.0)
    assertEquals("100.0", stats[4]);
  }

  /**
   * Test that passwords are properly updating.
   */
  @Test
  void updatePassword() {
    // Same username as before.
    String username = "TheNewPlayer";
    // New password value
    String password = "NewPassword123!";

    DaoCode code = PlayerDao.checkLogin(username, password);
    //Login should fail at first because password has not been changed.
    assertEquals(DaoCode.LOGIN_FAILURE, code);

    DaoCode code2 = PlayerDao.updatePassword(username, password);
    // Password should have changed
    assertEquals(DaoCode.PASSWORD_UPDATE_SUCCESS, code2);

    DaoCode code3 = PlayerDao.checkLogin(username, password);
    // Now login should succeed.
    assertEquals(DaoCode.LOGIN_SUCCESS, code3);
  }

  /**
   * Test that accounts are actually getting deleted and returning the appropriate DaoCode.
   */
  @Test
  void deleteAccount() {
    // Same username
    String username = "TheNewPlayer";
    // The new password we changed to.
    String password = "Password123!";
    // Delete the account
    DaoCode code = PlayerDao.deleteAccount(username);
    // Account should have deleted.
    assertEquals(DaoCode.ACCOUNT_DELETE_SUCCESS, code);

    // Running it again will show failure because account is already deleted.
    DaoCode code2 = PlayerDao.deleteAccount(username);
    assertEquals(DaoCode.ACCOUNT_DELETE_FAILURE, code2);

    // Login will show failure because username doesn't exist.
    DaoCode code3 = PlayerDao.checkLogin(username, password);
    assertEquals(DaoCode.LOGIN_FAILURE, code3);
  }
}