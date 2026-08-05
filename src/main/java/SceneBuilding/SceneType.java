package SceneBuilding;

/**
 * enums to define scene types that can be created.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 7/27/2026
 */
public enum SceneType {
  // Place commas between enums and semicolon after last.
  LOGIN("/login-screen.fxml"),
  CREATE_ACCOUNT("/create-account-screen.fxml"),
  PLAYER_ACCOUNT("/player-account-screen.fxml"),
  ;

  private final String fxml_url;

  SceneType(String fxml_url) {
    this.fxml_url = fxml_url;
  }

  public String getFxml_url() {
    return fxml_url;
  }
}
