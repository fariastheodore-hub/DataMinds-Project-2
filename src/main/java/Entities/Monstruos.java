package Entities;

public enum Monstruos {
  FIRST(0.0, 0.0),
  SECOND(375.0, 0.0),
  THIRD(750.0, 0.0),
  FOURTH(0.0, 430.0),
  FIFTH(375.0, 430.0),
  SIXTH(750.0, 430.0);

  private final double START_X;
  private final double START_Y;

  Monstruos(double startX, double startY) {
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
    return 375.0;
  }

  public double getSizeY() {
    return 430.0;
  }
}
