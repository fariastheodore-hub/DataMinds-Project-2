import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the entry point into the JavaFX program.
 *
 * @author Theodore Farias
 * @since 07/26/2026
 * @version 0.1.0
 */

public class Main extends Application {

  @Override
  public void start(Stage primaryStage)  {
    primaryStage.setTitle("Monstruos");
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
