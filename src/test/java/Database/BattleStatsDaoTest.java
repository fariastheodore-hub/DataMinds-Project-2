package Database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BattleStatsDaoTest {

    @Test
   public void testBattleStatsCrud() {
        int userId = 999999;

        // Removes an old test row, if one exists.
        BattleStatsDao.delete(userId);

        // Creates a row
        assertTrue(BattleStatsDao.create(userId));

        // Reads row
        BattleStats stats = BattleStatsDao.read(userId).orElseThrow();
        assertEquals(0, stats.wins());

        // Updates wins
        BattleStats updatedStats = new BattleStats(userId, 1, 0, 0);
        assertTrue(BattleStatsDao.update(updatedStats));
        assertEquals(1, BattleStatsDao.read(userId).orElseThrow().wins());

        // Deletes row
        assertTrue(BattleStatsDao.delete(userId));
        assertTrue(BattleStatsDao.read(userId).isEmpty());
    }
}
