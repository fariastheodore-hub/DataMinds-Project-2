package Database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerDaoTest {

  private static final String DB_URL = "jdbc:sqlite:test.db";
  private static Connection connection;

  @BeforeAll
  static void setUpDatabase() {
      DatabaseManager.getInstance(DB_URL);
      PlayerDao.deleteAccount("TheNewPlayer");
  }

  @BeforeEach
  void setUp() {
    // Make sure "TheNewPLayer" is deleted prior to testing.
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void createPlayer() {
    // New Player info
    String username = "TheNewPlayer";
    String password = "Password123!";
    String name = "TESTER";

    // Running PlayerDao.createPlayer
    DaoCode code = PlayerDao.createPlayer(username, password, name);

    // Should provide success message provided from PlayerDao.createPlayer
    assertEquals(DaoCode.CREATION_SUCCESS, code);

  }

  @Test
  void checkLogin() {
  }

  @Test
  void getPlayerStats() {
  }

  @Test
  void updatePassword() {
  }

  @Test
  void deleteAccount() {
  }
}