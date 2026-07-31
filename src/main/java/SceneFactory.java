import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Factory for creating the different scenes for Monstruos application.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 7/30/2026
 */
public final class SceneFactory {

  /**
   * Chooses appropriate scene builder method based on provided SceneType.
   *
   * @param sceneType enum for scene types
   * @param stage     primary stage
   * @return scene build by scene builder method.
   */
  public static Scene create(SceneType sceneType, Stage stage) {
    return switch (sceneType) {
      case LOGIN -> buildLogin(stage);
      case CREATE_ACCOUNT -> buildCreateAccount(stage);
      case PLAYER_ACCOUNT -> buildPlayerAccount(stage);
    };
  }

  /**
   * Builds Login scene
   *
   * @param stage primaryStage
   * @return Login scene
   */
  private static Scene buildLogin(Stage stage) {
    DatabaseManager.getInstance();
    System.out.println("Not yet implemented in FXML");
    return null;
  }

  /**
   * Builds Create Account scene
   *
   * @param stage primaryStage
   * @return Create Account scene
   */
  private static Scene buildCreateAccount(Stage stage) {
    System.out.println("Not yet implemented in FXML");
    return null;
  }

  /**
   * Builds Player Account scene
   *
   * @param stage primaryStage
   * @return Player Account scene
   */
  private static Scene buildPlayerAccount(Stage stage) {
    System.out.println("Not yet implemented in FXML");
    return null;
  }
}

