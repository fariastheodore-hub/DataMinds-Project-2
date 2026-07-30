import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Factory for creating the different scenes for Monstruos.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 7/30/2026
 */
public interface SceneFactory {

  /**
   * Chooses appropriate scene builder method based on provided SceneType.
   *
   * @param sceneType enum for scene types
   * @param stage primary stage
   * @return scene build by scene builder method.
   */
  public static Scene create(SceneType sceneType, Stage stage) {
    Scene scene = null;
    switch (sceneType) {
      case LOGIN -> {
        scene = buildLogin(stage);
      }
      case CREATE_ACCOUNT -> {
        scene = buildCreateAccount(stage);
      }
      case PLAYER_ACCOUNT -> {
        scene = buildPlayerAccount(stage);
      }
    }
    return scene;
  }

  private static Scene buildLogin(Stage stage) {
    DatabaseManager.getInstance();
    System.out.println("Not yet implemented in FXML");
    return null;
  }

  private static Scene buildCreateAccount(Stage stage) {
    System.out.println("Not yet implemented in FXML");
    return null;
  }

  private static Scene buildPlayerAccount(Stage stage) {
    System.out.println("Not yet implemented in FXML");
    return null;
  }
}

