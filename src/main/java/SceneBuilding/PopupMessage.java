package SceneBuilding;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

/**
 * Creates popup messages of different types that popup for 5 seconds and can be closed manually.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/2/2026
 */
public interface PopupMessage {
  final int PAUSE_DURATION = 5000;

  /**
   * Creates popup message for errors.
   * @param title Title of error.
   * @param message Message of error.
   */
  static void errorPopup(String title, String message) {
    PauseTransition pause = new PauseTransition();
    pause.setDuration(Duration.millis(PAUSE_DURATION));
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(title);
    alert.setContentText(message);
    alert.show();
    pause.playFromStart();
    pause.setOnFinished(event -> {
      alert.close();
    });
  }

  /**
   * Creates informational popups that can be used for success or other info.
   *
   * @param title Title of popup.
   * @param message Message in popup.
   */
  static void successPopup(String title, String message) {
    PauseTransition pause = new PauseTransition();
    pause.setDuration(Duration.millis(PAUSE_DURATION));
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(title);
    alert.setContentText(message);
    alert.show();
    pause.playFromStart();
    pause.setOnFinished(event -> {
      alert.close();
    });
  }

}
