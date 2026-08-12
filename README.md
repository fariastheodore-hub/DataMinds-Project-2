# Monstruos

Monstruos - Monster Battle CST 338 Project 2 — Team **Data Minds**.

## Team & Slice Ownership
| Slice | Owner | GitHub username | Issues | Branch(es) | PR(s) | Enhancement chosen | Status |
|-------|-------|-----------------|--------|------------|-------|--------------------|--------|
| 1 — Accounts | Theodore Farias | fariastheodore-hub | #1, #4, #5, #6, #7, #8, #15, #16, #18, #23, #34, #35, #36, #37, #40, #42, #43, #44 | theodore/JavaFX-setup, theodore/sceneType, theodore/SQLite-setup, theodore/SceneFactory, theodore/gitignore-update, theodore/ai-review-fixes, theodore/FXML-setup, theodore/DAO_CRUD, theodore/first-tests, theodore/fxml-controller-cleanup, theodore/password-security, theodore/fxml-refining, theodore/playerdao-cleanup, theodore/music, theodore/character-change-fix, theodore/tests | #2, #13, #14, #19, #20, #21, #22, #25, #27, #32, #33, #38, #39, #41, #45, #48, #51, #52 | Notifications / alerts (Custom popups based on login, create account, change password, delete account | complete |
| 2 — Creature Roster | | | | | | | |
| 3 — Battle Engine | Ameen Hamdalla | Ameen-05 | #9, #10, #11, #26, #50| ameen/battle-scene, ameen/combatLogic, ameen/fightLogic, ameen/surrenderLogic | #29, #31, #46, #47, #49 | Notifications / alerts | in-progress |
| 4 — Admin & Arenas | | | | | | | |
| 5 — Marketplace & Trading | | | | | | | |
_Status values: planned · in-progress · complete_

## WILL NOT DO (declared scope cuts)
_Slices and beyond-scope items we are consciously NOT building. Move an item to a tracked
Issue if the team later decides to attempt it for extra credit._

- Slice 2 Creature Roster: Not building (Team Size)
- Slice 4 Admin & Arenas: Not building (Team Size)
- Slice 5 Marketplace & Trading: Out of scope

## Code Review Log
| PR | Author | Human reviewer(s) | AI review (link) | Outcome |
|----|--------|-------------------|------------------|---------|
| #2 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |
| #13 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |
| #14 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |
| #19 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |
| #20 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |
| #22 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |
| #25 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |
| #27 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |
| #29 | Ameen Hamdalla  | Theodore Farias | N/A | Approved/Merged |
| #31 | Ameen Hamdalla  | Theodore Farias | N/A | Approved/Merged |
| #32 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |
| #38 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |
| #39 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |
| #41 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |
| #45 | Theodore Farias | Ameen Hamdalla | https://chatgpt.com/c/6a79305d-6a18-83e8-92b8-d6fcb0d24c9e | Approved/Merged |
| #46 | Ameen Hamdalla | Theodore Farias | N/A | Approved/Merged |
| #47 | Ameen Hamdalla | Theodore Farias | N/A | Approved/Merged |
| #49 | Ameen Hamdalla | Thedore Farias | N/A | Approved/Merged |
| #48 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |
| #51 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |
| #52 | Theodore Farias | Ameen Hamdalla | N/A | Approved/Merged |

## AI Usage Log
### **AI-drafted tests:**
- Theodore Farias  [link to TESTING.md](https://github.com/fariastheodore-hub/DataMinds-Project-2/blob/main/src/test/TESTING.md) Used ChatGPT to generate tests for ControllerOps class. Reviewed and refined tests.
### **AI code reviews:**
- Theodore Farias [PR link](https://github.com/fariastheodore-hub/DataMinds-Project-2/pull/45) Agree with ChatGPT that user should have a choice to not go to battle scene if character update fails to write to the DB. Added commit to include choice popup. Agreed that javadoc comments needed to be updated based on new DaoCode return values in PlayerDao. Added commit to fix javadoc comments.

## Extra Credit Log
| Item | Who | Evidence (Issue/PR) |
|------|-----|---------------------|
| Extra enhancement - Custom reusable FXML component | Theodore Farias | in-progress |

## Build & Run
```
./gradlew run        # launch the app
./gradlew test       # run the test suite
```
Requirements: JDK 25, JavaFX <version>. Any setup notes go here.
