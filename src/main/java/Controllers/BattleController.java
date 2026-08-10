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
        attackOpponent();
        turnCount++;
        turnLabel.setText("Turn " + turnCount);
    }

    /**
     *  Handles the damage calculations
     */
    private void attackOpponent() {
        oppHealth = Math.max(0, oppHealth - 10);
        oppHealthLabel.setText("Opponent HP: " + oppHealth);
    }

    /**
     * Handles the Run Away button.
     */
    @FXML
    private void handleRunAway() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Run Away");
        alert.setHeaderText("Would You Like to Surrender?");
        alert.setContentText("Are you sure?");

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {


            BattleStats updatedStats = new BattleStats(
                    USER_ID,
                    currentStats.wins(),
                    currentStats.losses(),
                    currentStats.flees() + 1
            );

            if (BattleStatsDao.update(updatedStats)) {
                currentStats = updatedStats;
                updateLabels();
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
