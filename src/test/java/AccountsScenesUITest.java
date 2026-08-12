import Database.DatabaseManager;
import SceneBuilding.SceneFactory;
import SceneBuilding.SceneType;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing Accounts slice UI using TestFX.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/11/2026
 */
public class AccountsScenesUITest extends ApplicationTest {

  /**
   * Gets the program started and implements temporary database in memory.
   * @param stage primary stage.
   */
  @Override
  public void start(Stage stage) throws Exception {
    System.setProperty("app.db.url", "jdbc:sqlite::memory:");
    DatabaseManager.resetForTesting();
    DatabaseManager.getInstance();
    stage.setScene(SceneFactory.create(SceneType.LOGIN));
    // Moves stage to the front for robot to test.
    stage.setAlwaysOnTop(true);
    stage.show();
    stage.toFront();
    stage.requestFocus();

    // Makes sure popup messages can take the stage.
    stage.setAlwaysOnTop(false);
  }

  /**
   * Tests transitions through all three Accounts scenes.
   */
  @Test
  void loginWithUserFoundAfterCreation() {
    // To set the robotContext
    clickOn("#usernameField");

    // Save login scene for comparison later.
    Scene loginScene = robotContext().getWindowFinder().targetWindow().getScene();
    clickOn("#createAccountButton");

    // Should have transitioned to create account scene
    Scene createAccountScene = robotContext().getWindowFinder().targetWindow().getScene();

    // Make sure we did transition.
    assertNotSame(loginScene, createAccountScene);

    // Fill the fields
    clickOn("#usernameField").write("TestUser");
    clickOn("#passwordField").write("Password!123");
    clickOn("#nameField").write("Tester");
    clickOn("#confirmPasswordField").write("Password!123");
    clickOn("#createAccountButton");

    // Should be back at login scene
    clickOn("OK");
    clickOn("#passwordField").write("Password!123");

    // Record new login scene (SceneBuilder creates new scene each time)
    Scene loginScene2 = robotContext().getWindowFinder().targetWindow().getScene();

    // Test the transition back to login scene from create account scene.
    assertNotSame(loginScene2, createAccountScene);

    // Log in
    clickOn("#loginButton");

    // Login Success should be present on the popup.
    assertTrue(lookup("Login Success").tryQuery().isPresent());
    clickOn("OK");

    // Should have transitioned to player account scene from loginScene2
    Scene playerAccountScene = robotContext().getWindowFinder().targetWindow().getScene();
    assertNotSame(playerAccountScene, loginScene2);

    // Wait for values to load from database.
    WaitForAsyncUtils.waitForFxEvents();

    // The playerGreeting label should show "Hello " + name + "!", in this case name is "Tester"
    assertEquals("Hello Tester!", lookup("#playerGreeting").queryLabeled().getText());
  }
}

