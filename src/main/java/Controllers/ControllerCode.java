package Controllers;

/**
 * Success or error codes for scene controllers
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/6/2026
 */
public enum ControllerCode {
  SUCCESS(1, "Success"),
  EMPTY_FIELD(-1, "Please fill all the fields"),
  SHORT_PASSWORD(-2, "Password must be at least 8 characters"),
  PASSWORDS_DONT_MATCH(-3, "Passwords don't match"),
  NOT_REQUIRED_CHARS(-4, "Password must contain specified characters"),
  LOGIN_FAILED(-5, "Username or password is incorrect"),
  USERNAME_CONTAINS_SPACE(-6, "Username cannot contain spaces");

  private final int VALUE;
  private final String MESSAGE;

  ControllerCode(int value, String message) {
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
