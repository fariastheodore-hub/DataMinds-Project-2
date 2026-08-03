package Controllers;
import Database.PlayerDao;
import SceneBuilding.PopupMessage;
import SceneBuilding.SceneFactory;
import SceneBuilding.SceneType;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController {

  @FXML
  private Label titleLabel;

  @FXML
  TextField usernameTextField;

  @FXML
  TextField passwordTextField;

  @FXML
  private void checkLogin() {
    String username = usernameTextField.getText();
    String password = passwordTextField.getText();

    if (username.isEmpty() || password.isEmpty()) {
      PopupMessage.errorPopup("Login Error", "Please fill out all the fields");
      return;
    }
    if (PlayerDao.checkLogin(username, password)) {
     PopupMessage.successPopup("Login Success", "Login Successful");
      PauseTransition pause = new PauseTransition();
      pause.setDuration(Duration.millis(3000));
      pause.setOnFinished(event -> {
        Stage stage = (Stage) titleLabel.getScene().getWindow();
        stage.setScene(SceneFactory.create(SceneType.PLAYER_ACCOUNT));
      });
    }
    else {
      PopupMessage.errorPopup("Login Error", "Wrong username or password");
    }
  }

  @FXML
  private void createAccount() {
    Stage stage = (Stage) titleLabel.getScene().getWindow();
    stage.setScene(SceneFactory.create(SceneType.CREATE_ACCOUNT));
  }
}

