/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import biuoop.GUI;

import java.util.LinkedList;
import java.util.List;

/**
 * this main runs the Arkanoid game.
 */
public class Ass6Game {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", GUI_WIDTH, GUI_HEIGHT);
        AnimationRunner runner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(runner, gui.getKeyboardSensor(), 800, 600, 7);
        List<LevelInformation> levels = new LinkedList();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new Green3());
        levels.add(new FinalFour());
        gameFlow.runLevels(levels);
        System.exit(0);
    }
}
