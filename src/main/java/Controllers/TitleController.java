package Controllers;

import SceneBuilding.SoundManager;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

/**
 * Controller for the Monstruos title.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/12/2026
 */
public class TitleController {

  // Is music playing?
  private boolean musicPlaying = true;

  private int titleNum;

  @FXML
  private ImageView titleImage;

  /**
   * Runs at initialization of FXML scene.
   * Initializes a random title.
   */
  @FXML
  private void initialize() {
    Random random = new Random();

    random.setSeed(System.currentTimeMillis());
    int chosen = random.nextInt(0, 14);
    titleNum = chosen;
    Titles chosenTitle = Titles.values()[chosen];
    titleImage.setViewport(
        new Rectangle2D(chosenTitle.getMinX(), chosenTitle.getMinY(), chosenTitle.getWidth(),
            chosenTitle.getHeight()));

  }

  /**
   * Changes the title image if the title is clicked on.
   */
  @FXML
  private void changeTitle() {
    titleNum++;
    if (titleNum >= Titles.values().length) {
      titleNum = 0;
    }
    Titles chosenTitle = Titles.values()[titleNum];
    titleImage.setViewport(
        new Rectangle2D(chosenTitle.getMinX(), chosenTitle.getMinY(), chosenTitle.getWidth(),
            chosenTitle.getHeight()));
  }

  /**
   * Toggles the music off and on.
   */
  @FXML
  private void toggleMusic() {
    if (musicPlaying) {
      SoundManager.getInstance().stopAccountMusic();
      musicPlaying = false;
    } else {
      SoundManager.getInstance().playAccountMusic();
      musicPlaying = true;
    }
  }

}
