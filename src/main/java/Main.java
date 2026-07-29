import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the entry point into the JavaFX program.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 07/26/2026
 */

public class Main extends Application {


  @Override
  public void start(Stage primaryStage) {

    DatabaseManager.getInstance();
    primaryStage.setTitle("Monstruos");
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
