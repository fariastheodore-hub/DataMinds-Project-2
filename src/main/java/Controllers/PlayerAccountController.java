package Controllers;

import Database.PlayerDao;
import Entities.Player;
import SceneBuilding.PopupMessage;
import SceneBuilding.SceneFactory;
import SceneBuilding.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PlayerAccountController {


  private Player player;

  @FXML
  private Label playerGreeting;

  @FXML
  private ImageView characterImage;

  @FXML
  private ProgressBar healthProgressBar;

  @FXML
  private PasswordField passwordField;

  @FXML
  private PasswordField confirmPasswordField;

  @FXML
  private void logout() {
    Stage stage = (Stage) playerGreeting.getScene().getWindow();
    PopupMessage.successPopup("Logout", "Logging out, goodbye " + player.getName() + "!");
    player = null;
    stage.setScene(SceneFactory.create(SceneType.LOGIN));
  }

  @FXML
  private void updatePassword() {
    String password = passwordField.getText();
    String confirmPassword = confirmPasswordField.getText();
    String error = "";

    if (password.isEmpty() || confirmPassword.isEmpty()) {
      error = "Please fill all the fields";
      PopupMessage.errorPopup("Password Change Error", error);
      return;
    }
    if (password.length() < 8) {
      error = "Password too short";
      PopupMessage.errorPopup("Password Change Error", error);
      return;
    }
    if (!password.equals(confirmPassword)) {
      error = "Passwords do not match";
      PopupMessage.errorPopup("Password Change Error", error);

      return;
    }
    if (!checkPassword(password)) {
      error = "Passwords must contain specified characters";
      PopupMessage.errorPopup("Account Creation Error", error);
      return;
    }
    PopupMessage.successPopup("Password Changed", PlayerDao.createPlayer(username, password, name));
  }

  @FXML
  private void initialize() {
    playerGreeting.setText("Loading player...");
    healthProgressBar.setProgress(0.0);
  }

  public void loadPlayer(String username) {
    String[] playerStats = PlayerDao.getPlayerStats(username);

    System.out.println(username);
    if (playerStats == null || playerStats.length < 6) {
      PopupMessage.errorPopup("Invalid Player", "No player found");
      playerGreeting.setText("Invalid Player");
      healthProgressBar.setProgress(0.0);
      return;
    }
    String password = playerStats[0];
    String name = playerStats[1];
    int character = Integer.parseInt(playerStats[2]);
    String monstruos = playerStats[3];
    int level = Integer.parseInt(playerStats[4]);
    float health = Float.parseFloat(playerStats[5]);
    player = new Player(username, password, name, character, monstruos, level, health);
    updateDisplay();
  }

  private void updateDisplay() {
    if (player == null) {
      return;
    }
    playerGreeting.setText("Hello " + player.getName() + "!");

    double progress = player.getHealth() / player.getMaxHealth();
    healthProgressBar.setProgress(progress);
  }

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
