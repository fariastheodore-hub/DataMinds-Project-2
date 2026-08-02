package Controllers;

import Database.PlayerDao;
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
    stage.setScene(SceneFactory.create(SceneType.LOGIN, stage));
  }

  @FXML
  private void createAccount() {
    String username = usernameField.getText();
    String name = nameField.getText();
    String password = passwordField.getText();
    String confirmPassword = confirmPasswordField.getText();

    if (username.isEmpty() || name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
      System.out.println("Please fill all the fields!");
      return;
    }
    if (!password.equals(confirmPassword)) {
      System.out.println("Passwords do not match!");
      return;
    }
    System.out.println(PlayerDao.createPlayer(username, name, password));
  }
}
