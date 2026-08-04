package Controllers;

import Database.PlayerDao;
import SceneBuilding.PopupMessage;
import SceneBuilding.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
  private TextField passwordField;

  // Confirm password field
  @FXML
  private TextField confirmPasswordField;

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
    String error = "";

    if (username.isEmpty() || name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
      error = "Please fill all the fields";
      PopupMessage.errorPopup("Account Creation Error", error);
      return;
    }
    if (password.length() < 8) {
      error = "Password too short";
      PopupMessage.errorPopup("Account Creation Error", error);
      return;
    }
    if (!password.equals(confirmPassword)) {
      error = "Passwords do not match";
      PopupMessage.errorPopup("Account Creation Error", error);

      return;
    }
    if (!checkPassword(password)) {
      error = "Passwords must contain specified characters";
      PopupMessage.errorPopup("Account Creation Error", error);
      return;
    }
    PopupMessage.successPopup("Account Creation", PlayerDao.createPlayer(username, password, name));
  }

  /**
   * Checks password entry to make sure it fits the specified criteria.
   * @param password password to check
   * @return boolean result of tests.
   */
  private boolean checkPassword(String password) {
    short specialChars = 0;
    short upperCaseChars = 0;
    short lowerCaseChars = 0;
    short numberChars = 0;
    for (char ch : password.toCharArray()) {
      if (ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '%' || ch == '^' || ch == '&'
          || ch == '*') {
        specialChars++;
        continue;
      }
      if (Character.isUpperCase(ch)) {
        upperCaseChars++;
        continue;
      }
      if (Character.isLowerCase(ch)) {
        lowerCaseChars++;
        continue;
      }
      if (Character.isDigit(ch)) {
        numberChars++;
      }
    }
    return specialChars > 0 && upperCaseChars > 0 && lowerCaseChars > 0 && numberChars > 0;
  }
}
