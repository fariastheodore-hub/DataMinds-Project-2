package Controllers;

import Database.BattleStats;
import Database.BattleStatsDao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controls actions on the battle scene.
 */
public class BattleController {

    // Temporary player ID for this milestone.
    private static final int USER_ID = 1;

    private BattleStats currentStats;

    @FXML
    private Label winsLabel;

    @FXML
    private Label lossesLabel;

    @FXML
    private Label fleesLabel;

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
    }

    /**
     * Handles the Run Away button.
     */
    @FXML
    private void handleRunAway() {

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

    /**
     * Displays the current statistics in the scene.
     */
    private void updateLabels() {
        winsLabel.setText("W: " + currentStats.wins());
        lossesLabel.setText("L: " + currentStats.losses());
        fleesLabel.setText("Fled: " + currentStats.flees());
    }
}
