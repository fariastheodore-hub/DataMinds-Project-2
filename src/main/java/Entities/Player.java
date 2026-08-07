package Entities;

/**
 * Entities.Player that holds stats during gameplay.
 */
public class Player {
  private String username;
  private String name;
  private int character;
  private String monstruos;
  private int level;
  private float health;
  private float MaxHealth;


  /**
   * Creates player object.
   * @param username
   * @param name
   * @param character
   * @param monstruos
   * @param level
   * @param health
   */
  public Player(String username, String name, int character, String monstruos, int level, float health) {
    this.username = username;
    this.name = name;
    this.character = character;
    this.monstruos = monstruos;
    this.level = level;
    MaxHealth = health + (level * 5);
    this.health = MaxHealth;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCharacter() {
    return character;
  }

  public void setCharacter(int character) {
    this.character = character;
  }

  public String getMonstruos() {
    return monstruos;
  }

  public void setMonstruos(String monstruos) {
    this.monstruos = monstruos;
  }

  public float getHealth() {
    return health;
  }

  public void setHealth(float health) {
    this.health = health;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public float getMaxHealth() {
    return MaxHealth;
  }
}
