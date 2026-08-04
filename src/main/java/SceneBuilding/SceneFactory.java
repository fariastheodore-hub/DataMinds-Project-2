package SceneBuilding;

import Database.DatabaseManager;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

  private static final String CSS_STYLE_SHEET = "/css/styles.css";

  private SceneFactory() {
    //Utility class
  }

  /**
   * Chooses appropriate scene builder method based on provided SceneBuilding.SceneType.
   *
   * @param sceneType enum for scene types
   * @return scene build by scene builder method.
   */
  public static Scene create(SceneType sceneType) {
    return switch (sceneType) {
      case LOGIN -> buildLogin();
      case CREATE_ACCOUNT -> buildCreateAccount();
      case PLAYER_ACCOUNT -> buildPlayerAccount();
    };
  }

  /**
   * Builds Login scene
   *
   * @return Login scene
   */
  private static Scene buildLogin() {
    try {
      DatabaseManager.getInstance();
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
   * @return Create Account scene
   */
  private static Scene buildCreateAccount() {
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
   * Builds Entities.Player Account scene
   *
   * @return Entities.Player Account scene
   */
  private static Scene buildPlayerAccount() {
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

