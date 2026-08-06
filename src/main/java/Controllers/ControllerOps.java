package Controllers;

import Database.PlayerDao;
import SceneBuilding.PopupMessage;
import SceneBuilding.SceneFactory;
import SceneBuilding.SceneType;
import javafx.stage.Stage;

/**
 * Performs operations for scene controllers
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/6/2026
 */
public interface ControllerOps {

  /**
   * Checks login credentials
   * @param username String entered username
   * @param password String entered password
   * @return Controller code result.
   */
  static ControllerCode checkLogin(String username, String password) {
    if (username.isEmpty() || password.isEmpty()) {
      return ControllerCode.EMPTY_FIELD;
    }
    if (PlayerDao.checkLogin(username, password)) {
      return ControllerCode.SUCCESS;
    } else {
      return ControllerCode.LOGIN_FAILED;
    }
  }

  static ControllerCode checkPassword(String password, String confirmPassword, String[] fields) {
    if (!checkFields(fields)) {
      return ControllerCode.EMPTY_FIELD;
    }
    if (!checkPasswordLength(password)) {
      return ControllerCode.SHORT_PASSWORD;
    }
    if (!checkPasswordsMatch(password, confirmPassword)) {
      return ControllerCode.PASSWORDS_DONT_MATCH;
    }
    if (!checkPasswordChars(password)) {
      return ControllerCode.NOT_REQUIRED_CHARS;
    }
    return ControllerCode.SUCCESS;
  }

  /**
   * Checks to see if there are empty fields.
   * @param fields String[] of fields to check.
   * @return boolean result of test.
   */

  static boolean checkFields(String[] fields) {
    for (String field : fields) {
      if (field.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks to see if password is required length.
   * @param password String password to check.
   * @return boolean result.
   */
  static boolean checkPasswordLength(String password) {
    return password.length() >= 8;
  }

  /**
   * Checks password and confirmPassword fields to make sure they match.
   * @param password String password to check.
   * @param confirmPassword String confirm password to check.
   * @return boolean result.
   */
  static boolean checkPasswordsMatch(String password, String confirmPassword) {
    return password.equals(confirmPassword);
  }

  /**
   * Checks password entry to make sure it fits the specified criteria.
   * @param password password to check
   * @return boolean result of tests.
   */
  static boolean checkPasswordChars(String password) {
    short specialChars = 0;
    short upperCaseChars = 0;
    short lowerCaseChars = 0;
    short numberChars = 0;
    for (char ch : password.toCharArray()) {
      if (ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '%' || ch == '^' || ch == '&'
          || ch == '*') {
        specialChars++;
        continue;
      }
      if (Character.isUpperCase(ch)) {
        upperCaseChars++;
        continue;
      }
      if (Character.isLowerCase(ch)) {
        lowerCaseChars++;
        continue;
      }
      if (Character.isDigit(ch)) {
        numberChars++;
      }
    }
    return (specialChars > 0 && upperCaseChars > 0 && lowerCaseChars > 0 && numberChars > 0);
  }

}
