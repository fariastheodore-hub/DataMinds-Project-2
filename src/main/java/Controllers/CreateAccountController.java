package Controllers;

import Database.PlayerDao;
import SceneBuilding.PopupMessage;
import SceneBuilding.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import SceneBuilding.SceneFactory;

/**
 * Controller for Create Account FXML scene.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 7/31/2026
 */
public class CreateAccountController {

  // Monstruos label
  @FXML
  private Label titleLabel;

  // Login username field
  @FXML
  private TextField usernameField;

  // Display name field
  @FXML
  private TextField nameField;

  // Password field
  @FXML
  private PasswordField passwordField;

  // Confirm password field
  @FXML
  private PasswordField confirmPasswordField;

  /**
   * Calls SceneFactory create method to create Login scene.
   * Then sets the stage with that scene.
   */
  @FXML
  private void toLogin() {
    Stage stage = (Stage) titleLabel.getScene().getWindow();
    stage.setScene(SceneFactory.create(SceneType.LOGIN));
  }

  /**
   * Checks that all fields are filled, password fields match, password meets criteria
   * the sends the data to PlayerDao for account creation.
   */
  @FXML
  private void createAccount() {
    String username = usernameField.getText();
    String name = nameField.getText();
    String password = passwordField.getText();
    String confirmPassword = confirmPasswordField.getText();
    String[] fields = {username, name, password, confirmPassword};
    ControllerCode code = ControllerOps.checkPassword(password, confirmPassword, fields);

    if (code.getValue() < 0) {
      PopupMessage.errorPopup("Account Creation", code.getMessage());
    }
    else {
      PopupMessage.successPopup("Account Creation", PlayerDao.createPlayer(username, password, name));
    }

  }
}
