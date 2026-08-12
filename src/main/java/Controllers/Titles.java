package Controllers;

/**
 * Enum for switching between all the different titles.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/12/2026
 */
public enum Titles {
  TITLE0( 0, 13, 170, 630),
  TITLE1(0, 183, 170, 630),
  TITLE2( 0, 348, 170, 630),
  TITLE3( 0, 519, 170, 630),
  TITLE4( 0, 705, 170, 630),
  TITLE5( 0, 862, 170, 630),
  TITLE6( 0, 1033, 189, 630),
  TITLE7( 635, 7, 170, 630),
  TITLE8( 635, 177, 170, 630),
  TITLE9( 635, 348, 170, 630),
  TITLE10(635, 528, 170, 630),
  TITLE11( 635, 708, 170, 630),
  TITLE12( 635, 881, 170, 630),
  TITLE13( 635, 1051, 170, 630);

  private final double MIN_X;
  private final double MIN_Y;
  private final double HEIGHT;
  private final double WIDTH;

  Titles(double MIN_X, double MIN_Y, double HEIGHT, double WIDTH) {
    this.MIN_X = MIN_X;
    this.MIN_Y = MIN_Y;
    this.HEIGHT = HEIGHT;
    this.WIDTH = WIDTH;
  }

  public double getMinX() {
    return MIN_X;
  }

  public double getMinY() {
    return MIN_Y;
  }

  public double getHeight() {
    return HEIGHT;
  }

  public double getWidth() {
    return WIDTH;
  }
}
