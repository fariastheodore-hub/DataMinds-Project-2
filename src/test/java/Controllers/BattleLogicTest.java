package Controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleLogicTest {

    @Test
    void damageReducesHealth() {
        assertEquals(90, BattleLogic.applyDamage(100, 10));
    }

    @Test
    void damageDoesNotGoBelowZero() {
        assertEquals(0, BattleLogic.applyDamage(5, 10));
    }
}
