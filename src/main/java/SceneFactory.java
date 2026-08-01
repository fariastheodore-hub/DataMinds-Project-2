import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Factory for creating the different scenes for Monstruos application.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 7/30/2026
 */
public final class SceneFactory {

  private static final String CSS_STYLE_SHEET = "/css/styles.css";

  private SceneFactory() {
    //Utility class
  }

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
    try {
      URL fxmlLocation = Main.class.getResource(SceneType.LOGIN.getFxml_url());
      FXMLLoader loader = new FXMLLoader(fxmlLocation);
      Parent root = loader.load();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(CSS_STYLE_SHEET);
      return scene;
    } catch (IOException e) {
      System.out.println("Could not load FXML Scene" + e.getMessage());
      return null;
    }

  }

  /**
   * Builds Create Account scene
   *
   * @param stage primaryStage
   * @return Create Account scene
   */
  private static Scene buildCreateAccount(Stage stage) {
    try {
      URL fxmlLocation = Main.class.getResource(SceneType.CREATE_ACCOUNT.getFxml_url());
      FXMLLoader loader = new FXMLLoader(fxmlLocation);
      Parent root = loader.load();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(CSS_STYLE_SHEET);
      return scene;
    } catch (IOException e) {
      System.out.println("Could not load FXML Scene" + e.getMessage());
      return null;
    }
  }

  /**
   * Builds Player Account scene
   *
   * @param stage primaryStage
   * @return Player Account scene
   */
  private static Scene buildPlayerAccount(Stage stage) {
    try {
      URL fxmlLocation = Main.class.getResource(SceneType.PLAYER_ACCOUNT.getFxml_url());
      FXMLLoader loader = new FXMLLoader(fxmlLocation);
      Parent root = loader.load();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(CSS_STYLE_SHEET);
      return scene;
    } catch (IOException e) {
      System.out.println("Could not load FXML Scene" + e.getMessage());
      return null;
    }
  }
}

