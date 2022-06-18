/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * key press clas for knowing what has pressed.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private Boolean stop;
    private Boolean isPressed;

    /**
     * This is the constructor method which creates a KeyPressStoppableAnimation object.
     *
     * @param sensor    , A type of KeyboardSensor that symbolizes the keyboard.
     * @param key       , A type of String variable that symbolizes the button we are clicking.
     * @param animation , A type of Animation that symbolizes the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isPressed = true;
    }

    /**
     * This is the doOneFrame drawing on the surface one frame.
     *
     * @param d , A type of DrawSurface which is the surface that we are drawing on.
     */
    public void doOneFrame(DrawSurface d) {
        // calling that animation doOneFrame method
        this.animation.doOneFrame(d);
        // check if key button was pressed
        if (this.sensor.isPressed(this.key)) {
            // check if he already been pressed
            if (isAlreadyPressed()) {
                //returns
                return;
            }
            // change stop to be true
            this.stop = true;
        } else {
            // else change stop to be false
            this.stop = false;
            isPressed = false;
        }
    }

    /**
     * This is the shouldStop method which checks if the animation need to stop.
     *
     * @return the is we need to stop the animation.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * set the stop to false.
     */
    public void setStop() {
        this.stop = false;
    }

    /**
     * This is the isAlreadyPressed method which returns the pressed field.
     *
     * @return is the key button already been pressed
     */
    public boolean isAlreadyPressed() {
        return this.isPressed;
    }
}
