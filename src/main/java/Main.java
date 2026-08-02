import Database.DatabaseManager;
import SceneBuilding.SceneFactory;
import SceneBuilding.SceneType;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the entry point into the JavaFX program, Monstruos.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 07/26/2026
 */

public class Main extends Application {
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Monstruos");
    primaryStage.setScene(SceneFactory.create(SceneType.LOGIN, primaryStage));
    primaryStage.show();
  }

  @Override
  public void stop() {
    DatabaseManager.getInstance().close();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
