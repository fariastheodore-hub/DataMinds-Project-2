package Controllers;

import Database.PlayerDao;
import Entities.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class PlayerAccountController {


  private Player player;
  private PlayerAccountController instance;

  private PlayerAccountController createPlayerAccountController() {
    if (instance == null) {
      return new PlayerAccountController();
    }
    return instance;
  }

  @FXML
  private Label playerGreeting;

  @FXML
  private ImageView characterImage;

  @FXML
  private ProgressBar healthProgressBar;

  public void initializePlayerAccountValues(String username) {
  String[] playerStats = PlayerDao.getPlayerStats(username);
  String password = playerStats[0];
  String name = playerStats[1];
  int character = Integer.parseInt(playerStats[2]);
  String monstruos = playerStats[3];
  int level = Integer.parseInt(playerStats[4]);
  float health = Float.parseFloat(playerStats[5]);
  Player player = new Player(username, password, name, character, monstruos, level, health);
  setPlayer(player);
  }

  private void setPlayer(Player player) {
    this.player = player;
  }

  @FXML
  public String setGreeting() {
    return "Hello " + player.getName() + "!";
  }

}
