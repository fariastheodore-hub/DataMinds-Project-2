package Controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ControllerOpsTest {

  @Test
  void checkPasswordValidPasswordReturnsSuccess() {
    String password = "Password1!";
    String confirmPassword = "Password1!";
    String[] fields = {"testUser", "Test Name", password, confirmPassword};

    ControllerCode result =
        ControllerOps.checkPassword(password, confirmPassword, fields);

    assertEquals(ControllerCode.SUCCESS, result);
  }

  @Test
  void checkPasswordEmptyFieldReturnsEmptyField() {
    String password = "Password1!";
    String confirmPassword = "Password1!";
    String[] fields = {"testUser", "", password, confirmPassword};

    ControllerCode result =
        ControllerOps.checkPassword(password, confirmPassword, fields);

    assertEquals(ControllerCode.EMPTY_FIELD, result);
  }

  @Test
  void checkPasswordUsernameWithSpaceReturnsUsernameContainsSpace() {
    String password = "Password1!";
    String confirmPassword = "Password1!";
    String[] fields = {"test User", "Test Name", password, confirmPassword};

    ControllerCode result =
        ControllerOps.checkPassword(password, confirmPassword, fields);

    assertEquals(ControllerCode.USERNAME_CONTAINS_SPACE, result);
  }

  @Test
  void checkPasswordTooShortReturnsShortPassword() {
    String password = "Pass1!";
    String confirmPassword = "Pass1!";
    String[] fields = {"testUser", "Test Name", password, confirmPassword};

    ControllerCode result =
        ControllerOps.checkPassword(password, confirmPassword, fields);

    assertEquals(ControllerCode.SHORT_PASSWORD, result);
  }

  @Test
  void checkPasswordDifferentPasswordsReturnsPasswordsDontMatch() {
    String password = "Password1!";
    String confirmPassword = "Different1!";
    String[] fields = {"testUser", "Test Name", password, confirmPassword};

    ControllerCode result =
        ControllerOps.checkPassword(password, confirmPassword, fields);

    assertEquals(ControllerCode.PASSWORDS_DONT_MATCH, result);
  }

  @Test
  void checkPasswordMissingSpecialCharacterReturnsNotRequiredChars() {
    String password = "Password1";
    String confirmPassword = "Password1";
    String[] fields = {"testUser", "Test Name", password, confirmPassword};

    ControllerCode result =
        ControllerOps.checkPassword(password, confirmPassword, fields);

    assertEquals(ControllerCode.NOT_REQUIRED_CHARS, result);
  }

  @Test
  void checkFieldsWithAllFieldsFilledReturnsTrue() {
    String[] fields = {"testUser", "Test Name", "Password1!"};

    assertTrue(ControllerOps.checkFields(fields));
  }

  @Test
  void checkFieldsWithEmptyFieldReturnsFalse() {
    String[] fields = {"testUser", "", "Password1!"};

    assertFalse(ControllerOps.checkFields(fields));
  }

  @Test
  void checkPasswordLengthExactlyEightCharactersReturnsTrue() {
    // Edge case: 8 is the minimum accepted password length.
    String password = "Abcde1!x";

    assertTrue(ControllerOps.checkPasswordLength(password));
  }

  @Test
  void checkPasswordLengthSevenCharactersReturnsFalse() {
    // Negative boundary case: one character below the minimum.
    String password = "Abcd1!x";

    assertFalse(ControllerOps.checkPasswordLength(password));
  }

  @Test
  void checkPasswordsMatchMatchingPasswordsReturnsTrue() {
    assertTrue(
        ControllerOps.checkPasswordsMatch("Password1!", "Password1!")
    );
  }

  @Test
  void checkPasswordsMatchDifferentPasswordsReturnsFalse() {
    assertFalse(
        ControllerOps.checkPasswordsMatch("Password1!", "Password2!")
    );
  }

  @Test
  void checkPasswordCharsValidPasswordReturnsTrue() {
    assertTrue(ControllerOps.checkPasswordChars("Password1!"));
  }

  @Test
  void checkPasswordCharsWithoutUppercaseReturnsFalse() {
    assertFalse(ControllerOps.checkPasswordChars("password1!"));
  }

  @Test
  void checkPasswordCharsWithoutLowercaseReturnsFalse() {
    assertFalse(ControllerOps.checkPasswordChars("PASSWORD1!"));
  }

  @Test
  void checkPasswordCharsWithoutNumberReturnsFalse() {
    assertFalse(ControllerOps.checkPasswordChars("Password!"));
  }

  @Test
  void checkPasswordCharsWithoutSpecialCharacterReturnsFalse() {
    assertFalse(ControllerOps.checkPasswordChars("Password1"));
  }

  @Test
  void checkPasswordCharsUnsupportedSpecialCharacterReturnsFalse() {
    // Edge case: '?' is punctuation, but it is not one of the accepted
    // special characters: ! @ # $ % ^ & *
    assertFalse(ControllerOps.checkPasswordChars("Password1?"));
  }

  @Test
  void controllerCodeContainsExpectedValueAndMessage() {
    assertEquals(-2, ControllerCode.SHORT_PASSWORD.getValue());
    assertEquals(
        "Password must be at least 8 characters",
        ControllerCode.SHORT_PASSWORD.getMessage()
    );
  }
}