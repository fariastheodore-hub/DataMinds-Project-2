import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginController {

  @FXML
  private Label titleLabel;

  @FXML
  private void createAccount() {
    Stage stage = (Stage) titleLabel.getScene().getWindow();
    stage.setScene(SceneFactory.create(SceneType.CREATE_ACCOUNT, stage));
  }
}

