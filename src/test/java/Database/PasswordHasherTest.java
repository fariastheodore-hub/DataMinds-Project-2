package Database;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PasswordHasherTest {

  /**
   * Make sure that the password hasher is not returning the same password after hashing.
   */
  @Test
  void hashPassword() {
    String password = "password";
    String hashedPassword = PasswordHasher.hashPassword(password);
    assertNotEquals("password", hashedPassword);
  }

  /**
   * Make sure that PasswordHasher is verifying the password based on its hash.
   */
  @Test
  void verifyPassword() {
    String password = "password";
    String hashedPassword = PasswordHasher.hashPassword(password);
    assertTrue(PasswordHasher.verifyPassword(password, hashedPassword));
  }
}