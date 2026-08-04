package Database;

/**
 * Stores one player's battle statistics.
 *
 * @param userId player ID associated with these statistics
 * @param wins number of battles won
 * @param losses number of battles lost
 * @param flees number of battles fled
 */
public record BattleStats(
        int userId,
        int wins,
        int losses,
        int flees
) {
}
