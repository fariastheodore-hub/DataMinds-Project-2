package Database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerDaoTest {

  private DatabaseManager databaseManager;

  @BeforeEach
  void setUp() {
    databaseManager = DatabaseManager.getInstance();
    PlayerDao.deleteAccount("TheNewPlayer");
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
    String result = PlayerDao.createPlayer(
        username,
        password,
        name
    );

    // Should provide success message provided from PlayerDao.createPlayer
    assertEquals(
        "Account successfully created",
        result);

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