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

  private boolean passwordVisible = false;

  // Monstruos label
  @FXML
  private Label titleLabel;

  // Username field
  @FXML
  private TextField usernameField;

  //Password field
  @FXML
  private PasswordField passwordField;

  @FXML
  private TextField visiblePasswordField;

  /**
   * Checks if all fields are filled out, then communicates with PlayerDao to find matching if username and password match and rows
   * in database.
   */
  @FXML
  private void checkLogin() {
    String username = usernameField.getText();
    String password;

    if (passwordVisible) {
      password = visiblePasswordField.getText();
    }
    else {
      password = passwordField.getText();
    }

    ControllerCode code = ControllerOps.checkLogin(username, password);
    if (code.getValue() < 0) {
      PopupMessage.errorPopup("Login Error", code.getMessage());
    } else {
      PopupMessage.successPopup("Login Success", "Login Successful");
      Stage stage = (Stage) titleLabel.getScene().getWindow();
      stage.setScene(SceneFactory.create(SceneType.PLAYER_ACCOUNT, username));
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

  /**
   * Adds username if coming from Create Account
   * @param username
   */
  public void addUsername(String username) {
    usernameField.setText(username);
  }

  /**
   * Toggles between concealed and visible password
   */
  @FXML
  private void togglePasswordField() {
    if (!passwordVisible) {
      visiblePasswordField.setText(passwordField.getText());
      visiblePasswordField.setVisible(true);
      visiblePasswordField.setManaged(true);

      passwordField.setVisible(false);
      passwordField.setManaged(false);
      passwordVisible = true;
    }
    else {
      passwordField.setText(visiblePasswordField.getText());
      passwordField.setVisible(true);
      passwordField.setManaged(true);

      visiblePasswordField.setVisible(false);
      visiblePasswordField.setManaged(false);
      passwordVisible = false;
    }
  }
}


