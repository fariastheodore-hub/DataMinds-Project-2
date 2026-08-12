package Database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for DatabaseManager
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/11/2026
 */
class DatabaseManagerTest {

  /**
   * Set up the in-memory database for each test.
   */
  @BeforeEach
  void setUp() {
    // Set up in-memory database.
    System.setProperty("app.db.url", "jdbc:sqlite::memory:");
    DatabaseManager.resetForTesting();
  }

  /**
   * Close the connection to the database for each test and null the instance of DatabaseManager.
   */
  @AfterEach
  void tearDown() {
    DatabaseManager.resetForTesting();
  }

  /**
   * Test that the DatabaseManager is properly set up as singleton.
   */
  @Test
  void singletonIdentity() {
    DatabaseManager a = DatabaseManager.getInstance();
    DatabaseManager b = DatabaseManager.getInstance();
    assertSame(a, b);
  }

  /**
   * Test that the connection is getting closed and the instance nullified.
   * The new instance should now be the same as the old one.
   */
  @Test
  void close() {
    DatabaseManager a = DatabaseManager.getInstance();
    a.close();
    DatabaseManager b = DatabaseManager.getInstance();
    assertNotSame(a, b);
  }
}