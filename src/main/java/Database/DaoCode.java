package Database;

/**
 * Enum for codes based on Dao CRUD operations.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/9/2026
 */
public enum DaoCode {
  CREATION_SUCCESS(1, "Account creation successful"), LOGIN_SUCCESS(2,
      "Login successful"), PASSWORD_UPDATE_SUCCESS(3,
      "Password successfully updated"), CHARACTER_UPDATE_SUCCESS(4,
      "Character successfully updated"), ACCOUNT_DELETE_SUCCESS(5,
      "Account successfully deleted, goodbye!"), CREATION_FAILURE(-1,
      "Account creation failed"), USERNAME_TAKEN(-2, "Username already taken"), LOGIN_FAILURE(-3,
      "Login failed, wrong username or password"), PASSWORD_UPDATE_FAILURE(-4,
      "Password update failed"), CHARACTER_UPDATE_FAILURE(-5,
      "Character update failed"), ACCOUNT_DELETE_FAILURE(-6, "Account deletion failed");


  private final int VALUE;
  private final String MESSAGE;

  DaoCode(int value, String message) {
    VALUE = value;
    MESSAGE = message;
  }

  public int getValue() {
    return VALUE;
  }

  public String getMessage() {
    return MESSAGE;
  }
}
