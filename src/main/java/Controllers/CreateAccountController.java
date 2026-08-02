package Controllers;
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
  private TextField nameField;
@FXML
  private TextField lastNameField;

@FXML
  private void toLogin() {
  Stage stage = (Stage) titleLabel.getScene().getWindow();
  stage.setScene(SceneFactory.create(SceneType.LOGIN, stage));
}
}
