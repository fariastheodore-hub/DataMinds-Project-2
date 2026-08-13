package Controllers;

public class BattleLogic {

    public static int applyDamage(int health, int damage) {
        return Math.max(0, health - damage);
    }
}

