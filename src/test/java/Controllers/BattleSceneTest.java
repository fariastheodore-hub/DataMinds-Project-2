package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class BattleSceneTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Battle_Scene.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    void fightButtonDamagesOpp() {
        clickOn("Fight!");
        verifyThat("#oppHealthLabel", hasText("Opponent HP: 90"));
    }


}
