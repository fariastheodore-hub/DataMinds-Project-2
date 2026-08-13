package Controllers;

import Database.BattleStats;
import Database.BattleStatsDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 * Controls actions on the battle scene.
 */
public class BattleController {

    // Temporary player ID for this milestone.
    private static final int USER_ID = 1;

    private BattleStats currentStats;



    private int playerHealth = 100;
    private int oppHealth = 100;
    private int turnCount = 1;

    private static final int DAMAGE = 10;
    private boolean battleOver = false;
    private boolean playerStarts = Math.random() < 0.5;

    @FXML
    private Label winsLabel;

    @FXML
    private Label lossesLabel;

    @FXML
    private Label fleesLabel;

    @FXML
    private Label turnLabel;

    @FXML
    private Label playerHealthLabel;

    @FXML
    private Label oppHealthLabel;
    /**
     * Runs automatically when the battle scene opens.
     */
    @FXML
    private void initialize() {

        // Create a stats row if this player does not have one.
        if (BattleStatsDao.read(USER_ID).isEmpty()) {
            BattleStatsDao.create(USER_ID);
        }

        // Loads the player's stats.
        currentStats = BattleStatsDao.read(USER_ID).orElse(new BattleStats(USER_ID, 0, 0, 0));
        updateLabels();
        turnLabel.setText("Turn " + turnCount);
        playerHealthLabel.setText("Player HP: " + playerHealth);
        oppHealthLabel.setText("Opponent Health: " + oppHealth);
    }

    /**
     * Handles the fight sequence.
     */
    @FXML
    private void handleFight() {
        if (battleOver){
            return;
        }

        if (playerStarts){
            attackOpponent();

            if (oppHealth > 0){
                attackPlayer();
            }
        } else {
            attackPlayer();

            if (playerHealth > 0){
                attackOpponent();
            }
        }

        playerStarts = !playerStarts;

        checkBattleOver();

        if(battleOver){
            return;
        }

        turnCount++;
        turnLabel.setText("Turn " + turnCount);
    }

    /**
     *  Handles the damage calculations for the player attacking opponent
     */
    private void attackOpponent() {
        oppHealth = Math.max(0, oppHealth - DAMAGE);
        oppHealthLabel.setText("Opponent HP: " + oppHealth);
    }

    /**
     *  Handles the damage calculations for opponent attacking the player
     */
    private void attackPlayer() {
        playerHealth = Math.max(0, playerHealth - DAMAGE);
        playerHealthLabel.setText("Player HP: " + playerHealth);
    }

    /**
     *  Checks to see if the battle is over
     */
    private void checkBattleOver() {
        if (oppHealth == 0) {
            battleOver = true;
            turnLabel.setText("You Win!!");

            BattleStats updatedStats = new BattleStats(USER_ID, currentStats.wins() + 1, currentStats.losses(), currentStats.flees());

            if (BattleStatsDao.update(updatedStats)) {
                currentStats = updatedStats;
                updateLabels();
            }
        }

        if (playerHealth == 0) {
            battleOver = true;
            turnLabel.setText("You Lose!!");

            BattleStats updatedStats = new BattleStats(USER_ID, currentStats.wins(), currentStats.losses() + 1, currentStats.flees());

            if (BattleStatsDao.update(updatedStats)) {
                currentStats = updatedStats;
                updateLabels();
            }
        }


    }

    /**
     * Handles the Run Away button.
     */
    @FXML
    private void handleRunAway() {

        if (battleOver){
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Run Away");
        alert.setHeaderText("Would You Like to Surrender?");
        alert.setContentText("Are you sure?");

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {


            BattleStats updatedStats = new BattleStats(
                    USER_ID,
                    currentStats.wins(),
                    currentStats.losses() + 1,
                    currentStats.flees() + 1
            );

            if (BattleStatsDao.update(updatedStats)) {
                currentStats = updatedStats;
                updateLabels();

                battleOver = true;
                turnLabel.setText("You Surrendered.");

            } else {
                System.err.println("Could not update flee count.");
            }
        }
    }

    /**
     * Displays the current statistics in the scene.
     */
    private void updateLabels() {
        winsLabel.setText("W: " + currentStats.wins());
        lossesLabel.setText("L: " + currentStats.losses());
        fleesLabel.setText("Fled: " + currentStats.flees());
    }
}
