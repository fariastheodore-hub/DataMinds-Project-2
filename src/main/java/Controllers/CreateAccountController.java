package Controllers;

import Database.DaoCode;
import Database.PlayerDao;
import SceneBuilding.PopupMessage;
import SceneBuilding.SceneType;
import javafx.fxml.FXML;
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

  // Is show password checkbox checked?
  private boolean passwordVisible = false;

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

  // Visible confirm password field
  @FXML
  private TextField visibleConfirmPasswordField;

  /**
   * Calls SceneFactory create method to create Login scene.
   * Then sets the stage with that scene.
   */
  @FXML
  private void toLogin() {
    Stage stage = (Stage) usernameField.getScene().getWindow();
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

    // Choose passwords based on if we are using the visible TextFields or not.
    if (passwordVisible) {
      password = visiblePasswordField.getText();
      confirmPassword = visibleConfirmPasswordField.getText();
    } else {
      password = passwordField.getText();
      confirmPassword = confirmPasswordField.getText();
    }

    String[] fields = {username, name, password, confirmPassword};
    ControllerCode code = ControllerOps.checkPassword(password, confirmPassword, fields);

    if (code.getValue() < 0) {
      PopupMessage.errorPopup("Account Creation", code.getMessage());
    } else {
      DaoCode daoCode = PlayerDao.createPlayer(username, password, name);
      if (daoCode.getValue() > 0) {
        PopupMessage.successPopup("Account Creation", daoCode.getMessage());
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(SceneFactory.create(SceneType.LOGIN, username));
      } else {
        PopupMessage.errorPopup("Account Creation", daoCode.getMessage());
      }
    }
  }

  /**
   * Toggles between concealed and visible password. Passes entry between them.
   */
  @FXML
  private void togglePasswordField() {
    if (!passwordVisible) {
      // Make the visible password TexFields visible and managed.
      visiblePasswordField.setText(passwordField.getText());
      visiblePasswordField.setVisible(true);
      visiblePasswordField.setManaged(true);

      visibleConfirmPasswordField.setText(confirmPasswordField.getText());
      visibleConfirmPasswordField.setVisible(true);
      visibleConfirmPasswordField.setManaged(true);

      // Make the hidden character PasswordFields not visible and not managed.
      passwordField.setVisible(false);
      passwordField.setManaged(false);

      confirmPasswordField.setVisible(false);
      confirmPasswordField.setManaged(false);

      passwordVisible = true;
    } else {
      // Make the hidden character PasswordFields visible and managed.
      passwordField.setText(visiblePasswordField.getText());
      passwordField.setVisible(true);
      passwordField.setManaged(true);

      confirmPasswordField.setText(visibleConfirmPasswordField.getText());
      confirmPasswordField.setVisible(true);
      confirmPasswordField.setManaged(true);

      // Make the visible password TextFields hidden and not managed.
      visiblePasswordField.setVisible(false);
      visiblePasswordField.setManaged(false);

      visibleConfirmPasswordField.setVisible(false);
      visibleConfirmPasswordField.setManaged(false);

      passwordVisible = false;
    }
  }
}
