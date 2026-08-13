package Controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleLogicTest {

    @Test
    void damageReducesHealth() {
        assertEquals(90, BattleLogic.applyDamage(100, 10));
    }

    @Test
    void damageStopsAtZero() {
        int result =  BattleLogic.applyDamage(5, 10);
        assertEquals(0, result);
    }
}
