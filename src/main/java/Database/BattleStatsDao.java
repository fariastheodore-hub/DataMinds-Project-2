package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Performs CRUD operations for the battle_stats table.
 */
public final class BattleStatsDao {

    private static final String CREATE_SQL = """
                INSERT INTO battle_stats (user_id) 
                VALUES (?)
            """;

    private static final String READ_SQL = """
                SELECT user_id, wins, losses, flees
                FROM battle_stats
                WHERE user_id = ?
            """;

    private static final String UPDATE_SQL = """
                UPDATE battle_stats
                SET wins = ?, losses = ?, flees = ?
                WHERE user_id = ?
            """;

    private static final String DELETE_SQL = """
                DELETE FROM battle_stats
                WHERE user_id = ?
            """;

    /**
     * Creates a battle statistics row for a player.
     */

    public static boolean create(int userId) {

        DatabaseManager.getInstance();

        try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(CREATE_SQL)) {

            pstmt.setInt(1, userId);
            return pstmt.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Could not create battle stats: " + e.getMessage());
            return false;
        }
    }

/**
 * Retrieves a player's battle statistics.
 */

    public static Optional<BattleStats> read(int userId) {
        DatabaseManager.getInstance();

        try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(READ_SQL)) {

            pstmt.setInt(1, userId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                BattleStats stats = new BattleStats(
                        resultSet.getInt("user_id"),
                        resultSet.getInt("wins"),
                        resultSet.getInt("losses"),
                        resultSet.getInt("flees")
                );
                return Optional.of(stats);
            }

        } catch (SQLException e) {
            System.err.println(
                    "Could not read battle stats: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * updates players battle statistics like wins losses and flees
     */
    public static boolean update(BattleStats stats) {
        DatabaseManager.getInstance();

        try (PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(UPDATE_SQL)) {

            pstmt.setInt(1, stats.wins());
            pstmt.setInt(2, stats.losses());
            pstmt.setInt(3, stats.flees());
            pstmt.setInt(4, stats.userId());

            return pstmt.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("Could not update battle stats: " + e.getMessage());
            return false;
        }
    }

    /**
     * deletes players battle statistics
     */

    public static boolean delete(int userId) {
        DatabaseManager.getInstance();

        try (PreparedStatement pstmt =
                     DatabaseManager.connection.prepareStatement(DELETE_SQL)) {

            pstmt.setInt(1, userId);
            return pstmt.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println(
                    "Could not delete battle stats: " + e.getMessage()
            );
            return false;
        }
    }
}


