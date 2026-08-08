package Entities;

/**
 * Enum for displaying character based on location in the Characters.png image.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/7/2026
 */
public enum Characters {

  FIRST(0.0, 0.0), SECOND(355.0, 0.0), THIRD(710.0, 0.0), FOURTH(0.0, 474.0), FIFTH(355.0,
      474.0), SIXTH(710.0, 474.0);

  private final double START_X;
  private final double START_Y;

  Characters(double startX, double startY) {
    this.START_X = startX;
    this.START_Y = startY;
  }

  public double getStartX() {
    return START_X;
  }

  public double getStartY() {
    return START_Y;
  }

  public double getSizeX() {
    return 355.0;
  }

  public double getSizeY() {
    return 474.0;
  }
}
