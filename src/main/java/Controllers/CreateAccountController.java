package Controllers;

import Database.PlayerDao;
import SceneBuilding.PopupMessage;
import SceneBuilding.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import SceneBuilding.SceneFactory;

public class CreateAccountController {

  @FXML
  private Label titleLabel;

  @FXML
  private TextField usernameField;

  @FXML
  private TextField nameField;

  @FXML
  private TextField passwordField;

  @FXML
  private TextField confirmPasswordField;

  @FXML
  private void toLogin() {
    Stage stage = (Stage) titleLabel.getScene().getWindow();
    stage.setScene(SceneFactory.create(SceneType.LOGIN));
  }

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

  private boolean checkPassword(String password) {
    short specialChars = 0;
    short upperCaseChars = 0;
    short lowerCaseChars = 0;
    short numberChars = 0;
    for (char ch : password.toCharArray()) {
      if (ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '%' || ch == '^' || ch == '&' || ch == '*') {
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
