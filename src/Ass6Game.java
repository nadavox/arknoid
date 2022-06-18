/**
 * @author nadav oxenberg
 * ID: 207952144
 */

import biuoop.GUI;
import game.LevelOne;
import game.LevelTwo;
import game.LevelThree;
import game.LevelFour;
import game.GameFlow;

import interfaces.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * classs the ass5game run the game.
 */
public class Ass6Game {

    /**
     * the main pogram get fro the ags the size of the radios.
     * @param args get a point and speed.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("arkanoid", 800, 600);
        //creates the list
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        // if the length is not 0
        if (args.length != 0) {
            //goes all over the args
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("1")) {
                    levels.add(new LevelOne());
                }
                if (args[i].equals("2")) {
                    levels.add(new LevelTwo());
                }
                if (args[i].equals("3")) {
                    levels.add(new LevelThree());
                }
                if (args[i].equals("4")) {
                    levels.add(new LevelFour());
                }
            }
        }
        // if the length is 0
        if (levels.size() == 0) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
            levels.add(new LevelFour());
        }
        //creates a game flow
        GameFlow game = new GameFlow(gui.getKeyboardSensor(), gui);
        //run the game
        game.runLevels(levels);
    }
}
