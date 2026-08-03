package SceneBuilding;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

public interface PopupMessage {
  final int PAUSE_DURATION = 5000;

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
