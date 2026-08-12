package Controllers;

import SceneBuilding.PopupMessage;
import SceneBuilding.SceneFactory;
import SceneBuilding.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for Login scene
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 7/31/2026
 */
public class LoginController {

  // Is show password checkbox checked?
  private boolean passwordVisible = false;

  // Username field
  @FXML
  private TextField usernameField;

  //Password field
  @FXML
  private PasswordField passwordField;

  // Visible password field
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

    // Choose password field based on whether we have visible TextField active or not.
    if (passwordVisible) {
      password = visiblePasswordField.getText();
    } else {
      password = passwordField.getText();
    }

    ControllerCode code = ControllerOps.checkLogin(username, password);
    if (code.getValue() < 0) {
      PopupMessage.errorPopup("Login Error", code.getMessage());
    } else {
      PopupMessage.successPopup("Login Success", "Login Successful");
      Stage stage = (Stage) usernameField.getScene().getWindow();
      stage.setScene(SceneFactory.create(SceneType.PLAYER_ACCOUNT, username));
    }
  }

  /**
   * Uses SceneFactory to create the Create Account scene and sets the scene.
   */
  @FXML
  private void createAccount() {
    Stage stage = (Stage) usernameField.getScene().getWindow();
    stage.setScene(SceneFactory.create(SceneType.CREATE_ACCOUNT));
  }

  /**
   * Adds username if coming from Create Account
   * @param username from Creat Account scene.
   */
  public void addUsername(String username) {
    usernameField.setText(username);
  }

  /**
   * Toggles between concealed and visible password. Passes entry between them.
   */
  @FXML
  private void togglePasswordField() {
    if (!passwordVisible) {
      // Show the visible password TextField and manage it.
      visiblePasswordField.setText(passwordField.getText());
      visiblePasswordField.setVisible(true);
      visiblePasswordField.setManaged(true);

      // Hide and don't manage the hidden character PasswordField.
      passwordField.setVisible(false);
      passwordField.setManaged(false);
      passwordVisible = true;
    } else {
      // Show and manage the hidden character PasswordField.
      passwordField.setText(visiblePasswordField.getText());
      passwordField.setVisible(true);
      passwordField.setManaged(true);

      // Hide and don't manage the visible password TextField.
      visiblePasswordField.setVisible(false);
      visiblePasswordField.setManaged(false);
      passwordVisible = false;
    }
  }
}


