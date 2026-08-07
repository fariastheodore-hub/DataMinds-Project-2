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

  private boolean passwordVisible = false;

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

  // Visible password field
  @FXML
  private TextField visiblePasswordField;

  // Confirm password field
  @FXML
  private PasswordField confirmPasswordField;

  @FXML
  private TextField visibleConfirmPasswordField;

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
    String password;
    String confirmPassword;

    if (passwordVisible) {
      password = visiblePasswordField.getText();
      confirmPassword = visibleConfirmPasswordField.getText();
    }
    else {
      password = passwordField.getText();
      confirmPassword = confirmPasswordField.getText();
    }

    String[] fields = {username, name, password, confirmPassword};
    ControllerCode code = ControllerOps.checkPassword(password, confirmPassword, fields);

    if (code.getValue() < 0) {
      PopupMessage.errorPopup("Account Creation", code.getMessage());
    }
    else {
      PopupMessage.successPopup("Account Creation", PlayerDao.createPlayer(username, password, name));
      Stage stage = (Stage) titleLabel.getScene().getWindow();
      stage.setScene(SceneFactory.create(SceneType.LOGIN, username));
    }

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

      visibleConfirmPasswordField.setText(confirmPasswordField.getText());
      visibleConfirmPasswordField.setVisible(true);
      visibleConfirmPasswordField.setManaged(true);

      passwordField.setVisible(false);
      passwordField.setManaged(false);

      confirmPasswordField.setVisible(false);
      confirmPasswordField.setManaged(false);

      passwordVisible = true;
    }
    else {
      passwordField.setText(visiblePasswordField.getText());
      passwordField.setVisible(true);
      passwordField.setManaged(true);

      confirmPasswordField.setText(visibleConfirmPasswordField.getText());
      confirmPasswordField.setVisible(true);
      confirmPasswordField.setManaged(true);

      visiblePasswordField.setVisible(false);
      visiblePasswordField.setManaged(false);

      visibleConfirmPasswordField.setVisible(false);
      visibleConfirmPasswordField.setManaged(false);

      passwordVisible = false;
    }
  }
}
