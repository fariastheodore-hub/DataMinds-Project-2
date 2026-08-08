package Entities;

public enum Monstruos {
  FIRST(0.0, 0.0, "Yellow Fellow", "Static"),
  SECOND(375.0, 0.0, "Purple Pal", "Slimy"),
  THIRD(750.0, 0.0, "Green Guy", "Sticky"),
  FOURTH(0.0, 430.0, "Boo Berry", "Scary"),
  FIFTH(375.0, 430.0, "Diablito", "Spicy"),
  SIXTH(750.0, 430.0, "Java Lava", "Smoldering");

  private final double START_X;
  private final double START_Y;
  private final String NAME;
  private final String TYPE;

  Monstruos(double startX, double startY, String name, String type) {
    this.START_X = startX;
    this.START_Y = startY;
    this.NAME = name;
    this.TYPE = type;
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
  public String getName() {
    return NAME;
  }
  public String getType() {
    return TYPE;
  }
}
