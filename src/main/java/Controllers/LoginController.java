package Controllers;

import Database.PlayerDao;
import Entities.Player;
import SceneBuilding.PopupMessage;
import SceneBuilding.SceneFactory;
import SceneBuilding.SceneType;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for Login scene
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 7/31/2026
 */
public class LoginController {

  // Monstruos label
  @FXML
  private Label titleLabel;

  // Username field
  @FXML
  private TextField usernameTextField;

  //Password field
  @FXML
  private PasswordField passwordTextField;

  /**
   * Checks if all fields are filled out, then communicates with PlayerDao to find matching if username and password match and rows
   * in database.
   */
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
      Stage stage = (Stage) titleLabel.getScene().getWindow();
      stage.setScene(SceneFactory.create(SceneType.PLAYER_ACCOUNT, username));
    } else {
      PopupMessage.errorPopup("Login Error", "Wrong username or password");
    }
  }

  /**
   * Uses SceneFactory to create the Create Account scene and sets the scene.
   */
  @FXML
  private void createAccount() {
    Stage stage = (Stage) titleLabel.getScene().getWindow();
    stage.setScene(SceneFactory.create(SceneType.CREATE_ACCOUNT));
  }
}

