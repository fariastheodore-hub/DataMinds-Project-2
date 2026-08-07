package Database;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;

/**
 * Hashes passwords for secure storage.
 *
 * @author Theodore Farias
 * @version 0.1.0
 * @since 8/6/2026
 */

public final class PasswordHasher {

  private static final int ITERATIONS = 2;
  private static final int MEMORY = 19 * 1024;
  private static final int PARALLELISM = 1;

  private PasswordHasher() {
    // Utility class
  }

  /**
   * Creates a hashed password to save in the database.
   * @param password entered password.
   * @return String of hashed password.
   */
  public static String hashPassword(String password) {

    Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);

    char[] passwordChars = password.toCharArray();

    String hashedPassword = argon2.hash(ITERATIONS, MEMORY, PARALLELISM, passwordChars);
    argon2.wipeArray(passwordChars);
    return hashedPassword;
  }

  /**
   * Checks whether entered password matches hashed password.
   * @param password entered password.
   * @param hashedPassword hashed password.
   * @return boolean result.
   */
  public static boolean verifyPassword(String password, String hashedPassword) {
    Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
    char[] passwordChars = password.toCharArray();
    boolean result = argon2.verify(hashedPassword, passwordChars);
    argon2.wipeArray(passwordChars);
    return result;
  }
}
