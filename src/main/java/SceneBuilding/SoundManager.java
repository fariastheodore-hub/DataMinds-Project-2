package SceneBuilding;

import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Singleton SoundManager class to manage all sounds in application.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/10/2026
 */
public class SoundManager {

  private static SoundManager instance;
  private static MediaPlayer accountMusicPlayer;
  private final String ACCOUNT_MUSIC_PATH = "/sounds/MonstruosAccountsSong.mp3";


  /**
   * Private constructor for singleton SoundManager
   */
  private SoundManager() {
    try {
      URL accountMusicURL = SoundManager.class.getResource(ACCOUNT_MUSIC_PATH);
      Media accountMusic = new Media(accountMusicURL.toExternalForm());
      accountMusicPlayer = new MediaPlayer(accountMusic);
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  /**
   * Singleton SoundManager instance.
   * @return instance of SoundManager.
   */
  public static SoundManager getInstance() {
    if (instance == null) {
      instance = new SoundManager();
    }
    return instance;
  }


  /**
   * Plays the Accounts slice background music.
   */
  public void playAccountMusic() {
    accountMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    accountMusicPlayer.setVolume(0.10);
    accountMusicPlayer.play();
  }

  /**
   * Stops the Accounts slice background music.
   */
  public void stopAccountMusic() {
    accountMusicPlayer.stop();
  }

}
