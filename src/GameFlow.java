/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import biuoop.KeyboardSensor;

import java.util.List;

/**
 * the class of the flowing of the levels in the game.
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private int gameWidth;
    private int gameHeight;
    private int numberOfLives;
    private Counter scoreCounter;

    /**
     * constructor.
     *
     * @param ar  the animation runner
     * @param ks  the keyboard sensor
     * @param gw  the game width
     * @param gh  the game height
     * @param nol the number of lives
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int gw, int gh, int nol) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gameWidth = gw;
        this.gameHeight = gh;
        this.numberOfLives = nol;
        this.scoreCounter = new Counter();

    }

    /**
     * runs the levels in the game.
     *
     * @param levels the levels in the game
     */
    public void runLevels(List<LevelInformation> levels) {
        // add the lives indicator to the game
        Counter livesCounter = new Counter(this.numberOfLives);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, keyboardSensor, animationRunner, this.scoreCounter,
                    livesCounter);
            level.initialize();

            /* as long as there are still blocks and balls in the level- run the level*/
            while (level.getBlocksCounter() > 0 && level.getBallsCounter() > 0 && livesCounter.getValue() > 0) {
                level.run();
                if (level.getBlocksCounter() > 0) {
                    livesCounter.decrease(1);
                }
                level.initializeBalls();
            }
            if (level.getBallsCounter() == 0) {
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                new EndScreen(scoreCounter, this.keyboardSensor, livesCounter.getValue() != 0)));
    }
}

