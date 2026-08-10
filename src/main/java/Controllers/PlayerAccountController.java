package Controllers;

import Database.DaoCode;
import Database.PlayerDao;
import Entities.Characters;
import Entities.Monstruos;
import Entities.Player;
import SceneBuilding.PopupMessage;
import SceneBuilding.SceneFactory;
import SceneBuilding.SceneType;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Controller for PlayerAccount scene
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/2/2026
 */
public class PlayerAccountController {

  // Are we in the change password UI mode?
  private boolean changePassword = false;

  // Is show password checkbox checked?
  private boolean passwordVisible = false;

  // Character changed?
  private boolean characterChanged = false;

  // Array of available Characters enums.
  private Characters[] characters;

  // Array of available Monstruos enums.
  private Monstruos[] monstruos;

  // Current player
  private Player player;

  // Custom player greeting
  @FXML
  private Label playerGreeting;

  //The character image
  @FXML
  private ImageView characterImage;

  //Health bar
  @FXML
  private ProgressBar healthProgressBar;

  // Password field for changing password
  @FXML
  private PasswordField passwordField;

  // Visible password field
  @FXML
  private TextField visiblePasswordField;

  // Confirm password for changing password
  @FXML
  private PasswordField confirmPasswordField;

  // Visible confirm password field
  @FXML
  private TextField visibleConfirmPasswordField;

  // Show password checkbox
  @FXML
  private CheckBox showPasswordCheckBox;

  // Change password button to start change password UI mode.
  @FXML
  private Button changePasswordButton;

  // Confirm change of password button.
  @FXML
  private Button confirmChangeButton;

  // Start a battle button.
  @FXML
  private Button battleButton;

  // logout button.
  @FXML
  private Button logoutButton;

  // Delete account button.
  @FXML
  private Button deleteAccountButton;

  // Chosen Monstruo image.
  @FXML
  private ImageView monstruoImage;

  // Label showing chosen Monstruo name.
  @FXML
  private Label monstruoName;

  // Label showing chosen Monstruo type.
  @FXML
  private Label monstruoType;

  /**
   * Goes to battle scene
   */
  @FXML
  private void goToBattleScene() {
    if (characterChanged) {
      // Update chosen character in DB before leaving scene.
      DaoCode code = PlayerDao.updateCharacter(player.getUsername(), player.getCharacter());
      if (code.getValue() > 0) {
        PopupMessage.successPopup("Character Update", code.getMessage());
      } else {
        boolean choice = PopupMessage.confirmChoice(code.getMessage(), "Do you want to continue to battle?");
        if (!choice) {
          return;
        }
      }
    }
    Stage stage = (Stage) playerGreeting.getScene().getWindow();
    stage.setScene(SceneFactory.create(SceneType.BATTLE));
  }

  /**
   * Logs out and goes back to login scene
   */
  @FXML
  private void logout() {
    if (characterChanged) {
      // Update chosen character in DB before leaving scene.
      DaoCode code = PlayerDao.updateCharacter(player.getUsername(), player.getCharacter());
      if (code.getValue() > 0) {
        PopupMessage.successPopup("Character Update", code.getMessage());
      } else {
        boolean choice = PopupMessage.confirmChoice(code.getMessage(), "Do you still want to logout?");
        if (!choice) {
          return;
        }
      }
    }

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
    boolean confirmDelete = PopupMessage.confirmChoice("Delete Account",
        "Are you sure you want to delete your account?");
    if (confirmDelete) {
      DaoCode code = PlayerDao.deleteAccount(player.getUsername());
      if (code.getValue() > 0) {
        PopupMessage.successPopup("Account Deletion", code.getMessage());
        Stage stage = (Stage) playerGreeting.getScene().getWindow();
        player = null;
        stage.setScene(SceneFactory.create(SceneType.LOGIN));
      } else {
        PopupMessage.errorPopup("Account Deletion", code.getMessage());
      }
    }
  }

  /**
   * Passes chosen Monstruo from image that user clicks on to chosen Monstruo image.
   * @param mouseEvent the click event from mouse.
   */
  @FXML
  private void chooseMonstruo(MouseEvent mouseEvent) {
    ImageView chosen = (ImageView) mouseEvent.getSource();

    // Switch based on which image was clicked.
    switch (chosen.getId()) {
      case "monstruoImage0" -> {
        monstruoImage.setViewport(
            new Rectangle2D(monstruos[0].getStartX(), monstruos[0].getStartY(),
                monstruos[0].getSizeX(), monstruos[0].getSizeY()));
        monstruoName.setText("Name: " + monstruos[0].getName());
        monstruoType.setText("Type: " + monstruos[0].getType());
      }
      case "monstruoImage1" -> {
        monstruoImage.setViewport(
            new Rectangle2D(monstruos[1].getStartX(), monstruos[1].getStartY(),
                monstruos[1].getSizeX(), monstruos[1].getSizeY()));
        monstruoName.setText("Name: " + monstruos[1].getName());
        monstruoType.setText("Type: " + monstruos[1].getType());
      }
      case "monstruoImage2" -> {
        monstruoImage.setViewport(
            new Rectangle2D(monstruos[2].getStartX(), monstruos[2].getStartY(),
                monstruos[2].getSizeX(), monstruos[2].getSizeY()));
        monstruoName.setText("Name: " + monstruos[2].getName());
        monstruoType.setText("Type: " + monstruos[2].getType());
      }
      case "monstruoImage3" -> {
        monstruoImage.setViewport(
            new Rectangle2D(monstruos[3].getStartX(), monstruos[3].getStartY(),
                monstruos[3].getSizeX(), monstruos[3].getSizeY()));
        monstruoName.setText("Name: " + monstruos[3].getName());
        monstruoType.setText("Type: " + monstruos[3].getType());
      }
      case "monstruoImage4" -> {
        monstruoImage.setViewport(
            new Rectangle2D(monstruos[4].getStartX(), monstruos[4].getStartY(),
                monstruos[4].getSizeX(), monstruos[4].getSizeY()));
        monstruoName.setText("Name: " + monstruos[4].getName());
        monstruoType.setText("Type: " + monstruos[4].getType());
      }
      case "monstruoImage5" -> {
        monstruoImage.setViewport(
            new Rectangle2D(monstruos[5].getStartX(), monstruos[5].getStartY(),
                monstruos[5].getSizeX(), monstruos[5].getSizeY()));
        monstruoName.setText("Name: " + monstruos[5].getName());
        monstruoType.setText("Type: " + monstruos[5].getType());
      }
    }
  }

  /**
   * Cycles through the available characters. Only saves character in player field until we leave the scene
   * so we don't have many writes to database.
   */
  @FXML
  private void changeCharacter() {
    int characterNum = player.getCharacter();
    characterNum++;
    if (characterNum >= characters.length) {
      characterNum = 0;
    }
    Characters chosenCharacter = characters[characterNum];
    characterImage.setViewport(
        new Rectangle2D(chosenCharacter.getStartX(), chosenCharacter.getStartY(),
            chosenCharacter.getSizeX(), chosenCharacter.getSizeY()));
    player.setCharacter(characterNum);
    characterChanged = true;
  }

  /**
   * Opens and closes change password objects
   */
  @FXML
  private void toggleChangePassword() {
    if (!changePassword) {
      // Make all the change password UI visible and managed.
      passwordField.setVisible(true);
      passwordField.setManaged(true);
      confirmPasswordField.setVisible(true);
      confirmPasswordField.setManaged(true);
      showPasswordCheckBox.setVisible(true);
      showPasswordCheckBox.setManaged(true);
      confirmChangeButton.setVisible(true);
      confirmChangeButton.setManaged(true);

      // Make the other buttons not visible or managed.
      battleButton.setVisible(false);
      battleButton.setManaged(false);
      logoutButton.setVisible(false);
      logoutButton.setManaged(false);
      deleteAccountButton.setVisible(false);
      deleteAccountButton.setManaged(false);

      changePasswordButton.setText("Never mind");
      changePassword = true;
    } else {
      // Make all the change password UI not visible or managed.
      passwordField.setVisible(false);
      passwordField.setManaged(false);
      confirmPasswordField.setVisible(false);
      confirmPasswordField.setManaged(false);
      showPasswordCheckBox.setVisible(false);
      showPasswordCheckBox.setManaged(false);
      confirmChangeButton.setVisible(false);
      confirmChangeButton.setManaged(false);
      visiblePasswordField.setVisible(false);
      visiblePasswordField.setManaged(false);
      visibleConfirmPasswordField.setVisible(false);
      visibleConfirmPasswordField.setManaged(false);

      // Make all the regular buttons visible and managed.
      battleButton.setVisible(true);
      battleButton.setManaged(true);
      logoutButton.setVisible(true);
      logoutButton.setManaged(true);
      deleteAccountButton.setVisible(true);
      deleteAccountButton.setManaged(true);

      changePasswordButton.setText("Change Password");
      changePassword = false;
    }
  }

  /**
   * Updates player password
   */
  @FXML
  private void updatePassword() {
    String password;
    String confirmPassword;

    if (passwordVisible) {
      password = visiblePasswordField.getText();
      confirmPassword = visibleConfirmPasswordField.getText();
    } else {
      password = passwordField.getText();
      confirmPassword = confirmPasswordField.getText();
    }

    String[] fields = {password, confirmPassword};
    ControllerCode code = ControllerOps.checkPassword(password, confirmPassword, fields);

    if (code.getValue() < 0) {
      PopupMessage.errorPopup("Password Change", code.getMessage());
    } else if (PlayerDao.checkLogin(player.getUsername(), password).getValue() > 0) {
      PopupMessage.errorPopup("Password Change", "Entered password matches current password");
    } else {
      DaoCode daoCode = PlayerDao.updatePassword(player.getUsername(), password);
      if (daoCode.getValue() > 0) {
        PopupMessage.successPopup("Password Change", daoCode.getMessage());
      } else {
        PopupMessage.errorPopup("Password Change", daoCode.getMessage());
      }
      toggleChangePassword();
    }
  }

  /**
   * Runs and initialization of FXML scene.
   */
  @FXML
  private void initialize() {
    playerGreeting.setText("Loading player...");
    healthProgressBar.setProgress(0.0);
    characters = Characters.values();
    monstruos = Monstruos.values();
  }

  /**
   * Loads player info into Player object
   * @param username username to gather player info.
   */
  public void loadPlayer(String username) {
    String[] playerStats = PlayerDao.getPlayerStats(username);

    System.out.println(username);
    if (playerStats == null || playerStats.length < 5) {
      PopupMessage.errorPopup("Invalid Player", "No player found");
      playerGreeting.setText("Invalid Player");
      healthProgressBar.setProgress(0.0);
      return;
    }
    String name = playerStats[0];
    int character = Integer.parseInt(playerStats[1]);
    String monstruos = playerStats[2];
    int level = Integer.parseInt(playerStats[3]);
    float health = Float.parseFloat(playerStats[4]);
    player = new Player(username, name, character, monstruos, level, health);
    updateDisplay();
  }

  /**
   * Updates the display with player name and current health and current character.
   */
  private void updateDisplay() {
    if (player == null) {
      return;
    }
    playerGreeting.setText("Hello " + player.getName() + "!");

    double progress = player.getHealth() / player.getMaxHealth();
    healthProgressBar.setProgress(progress);
    Characters chosenCharacter = characters[player.getCharacter()];
    characterImage.setViewport(
        new Rectangle2D(chosenCharacter.getStartX(), chosenCharacter.getStartY(),
            chosenCharacter.getSizeX(), chosenCharacter.getSizeY()));
  }

  /**
   * Toggles between concealed and visible password
   */
  @FXML
  private void togglePasswordField() {
    if (!passwordVisible) {
      // Make the visible password TextFields visible and managed.
      visiblePasswordField.setText(passwordField.getText());
      visiblePasswordField.setVisible(true);
      visiblePasswordField.setManaged(true);

      visibleConfirmPasswordField.setText(confirmPasswordField.getText());
      visibleConfirmPasswordField.setVisible(true);
      visibleConfirmPasswordField.setManaged(true);

      // Make the hidden character PasswordFields not managed nor visible.
      passwordField.setVisible(false);
      passwordField.setManaged(false);

      confirmPasswordField.setVisible(false);
      confirmPasswordField.setManaged(false);

      passwordVisible = true;
    } else {
      // Make the hidden character PasswordFields managed and visible.
      passwordField.setText(visiblePasswordField.getText());
      passwordField.setVisible(true);
      passwordField.setManaged(true);

      confirmPasswordField.setText(visibleConfirmPasswordField.getText());
      confirmPasswordField.setVisible(true);
      confirmPasswordField.setManaged(true);

      // Make the visible character TextFields not visible nor managed.
      visiblePasswordField.setVisible(false);
      visiblePasswordField.setManaged(false);

      visibleConfirmPasswordField.setVisible(false);
      visibleConfirmPasswordField.setManaged(false);

      passwordVisible = false;
    }
  }
}
