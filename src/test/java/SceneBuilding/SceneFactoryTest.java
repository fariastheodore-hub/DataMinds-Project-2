package SceneBuilding;

import Database.DatabaseManager;
import Database.PlayerDao;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for creating scenes (Only Account scenes at this time).
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/11/2026
 */

class SceneFactoryTest {

  /**
   * Get JavaFX started.
   * @throws Exception may throw exception.
   */
  @BeforeAll
  static void bootToolkit() throws Exception {
    FxToolkit.registerPrimaryStage();
    // starts JavaFX once for the class.
  }

  /**
   * Makes a new database and stage for each test.
   * @throws Exception may throw exception.
   */
  @BeforeEach
  void freshDb() throws Exception {
    System.setProperty("app.db.url", "jdbc:sqlite::memory:");
    DatabaseManager.resetForTesting();
    DatabaseManager.getInstance();
    FxToolkit.registerPrimaryStage();
  }

  /**
   * Verify that the login scene has a root.
   * @throws Exception may throw exception.
   */
  @Test
  void loginSceneHasARoot() throws Exception {
    Scene scene = FxToolkit.setupScene(() -> SceneFactory.create(SceneType.LOGIN));
    assertNotNull(scene);
    assertNotNull(scene.getRoot());
  }

  /**
   * Verify that the create account scene has a root.
   * @throws Exception may throw exception.
   */
  @Test
  void createAccountSceneHasARoot() throws Exception {
    Scene scene  = FxToolkit.setupScene(() -> SceneFactory.create(SceneType.CREATE_ACCOUNT));
    assertNotNull(scene);
    assertNotNull(scene.getRoot());
  }

  /**
   * Verify that the player account scene has a root.
   * @throws Exception may throw exception.
   */
  @Test
  void playerAccountSceneHasARoot() throws Exception {
    Scene scene  = FxToolkit.setupScene(() -> SceneFactory.create(SceneType.PLAYER_ACCOUNT));
    assertNotNull(scene);
    assertNotNull(scene.getRoot());
  }

  /**
   * Verify that the player account scene is pulling data from the database.
   * @throws Exception may throw exception.
   */
  @Test
  void playerAccountReadsFromDatabase() throws Exception {
    PlayerDao.createPlayer("TestUser", "Password123!", "Tester");
    Scene scene = FxToolkit.setupScene(() -> SceneFactory.create(SceneType.PLAYER_ACCOUNT, "TestUser"));
    Label label = (Label) scene.lookup("#playerGreeting");
    assertNotNull(label);
    assertEquals("Hello Tester!", label.getText());
  }
}