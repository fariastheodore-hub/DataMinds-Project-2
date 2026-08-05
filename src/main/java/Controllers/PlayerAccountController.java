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

/**
 * Controller for PlayerAccount scene
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/2/2026
 */
public class PlayerAccountController {

  // Current player
  private Player player;

  // Custom player greeting
  @FXML
  private Label playerGreeting;

  //Eventually the character image
  @FXML
  private ImageView characterImage;

  //Health bar
  @FXML
  private ProgressBar healthProgressBar;

  // Password field for changing password
  @FXML
  private PasswordField passwordField;

  // Confirm password for changin password
  @FXML
  private PasswordField confirmPasswordField;

  /**
   * Logs out and goes back to login scene
   */
  @FXML
  private void logout() {
    Stage stage = (Stage) playerGreeting.getScene().getWindow();
    PopupMessage.successPopup("Logout", "Logging out, goodbye " + player.getName() + "!");
    player = null;
    stage.setScene(SceneFactory.create(SceneType.LOGIN));
  }

  /**
   * Deletes player account
   */
  @FXML
  private void deleteAccount() {
    boolean result = PopupMessage.deleteAccount("Delete Account",
        "Are you sure you want to delete your account?");
    if (result) {
      if (PlayerDao.deleteAccount(player.getUsername())) {
        PopupMessage.successPopup("Account Deletion",
            "Account has been successfully deleted, goodbye!");
        Stage stage = (Stage) playerGreeting.getScene().getWindow();
        player = null;
        stage.setScene(SceneFactory.create(SceneType.LOGIN));
      } else {
        PopupMessage.errorPopup("Account Deletion", "Account was not deleted");
      }
    }
  }

  /**
   * Updates player password
   */
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
    PopupMessage.successPopup("Password Change",
        PlayerDao.updatePassword(player.getUsername(), password));
    player.setPassword(password);
  }

  //Initialization of FXML scene
  @FXML
  private void initialize() {
    playerGreeting.setText("Loading player...");
    healthProgressBar.setProgress(0.0);
  }

  /**
   * Loads player info into Player object
   * @param username username to gather player info.
   */
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

  /**
   * Updates the display with player name and current health.
   */
  private void updateDisplay() {
    if (player == null) {
      return;
    }
    playerGreeting.setText("Hello " + player.getName() + "!");

    double progress = player.getHealth() / player.getMaxHealth();
    healthProgressBar.setProgress(progress);
  }

  /**
   * Checks password to make sure it fits criteria.
   * @param password password to be checked.
   * @return boolean result of checks.
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
