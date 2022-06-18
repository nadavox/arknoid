/**
 * @author nadav oxenberg
 * ID: 207952144
 */
package game;

import biuoop.DrawSurface;
import Shape.Rectangle;
import interfaces.Sprite;

import java.awt.Color;

/**
 * class Background of the screen.
 */
public class BackGround implements Sprite {
    private Rectangle rectangle;
    private Color color;
    private int level;

    /**
     * constructor for the class.
     * @param rectangle the rect
     * @param color the color
     * @param level the number opf level.
     */
    public BackGround(Rectangle rectangle, Color color, int level) {
        this.rectangle = rectangle;
        this.color = color;
        this.level = level;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        if (level == 1) {
            d.fillRectangle(40, 40, 720, 600);
            d.setColor(Color.blue);
            d.drawCircle(405, 195, 140);
            d.drawCircle(405, 195, 100);
            d.drawCircle(405, 195, 60);
            d.drawLine(405, 40, 405, 175);
            d.drawLine(405, 217, 405, 352);
            d.drawLine(240, 195, 385, 195);
            d.drawLine(425, 195, 570, 195);
        } else if (level == 2) {
            d.fillRectangle(40, 40, 720, 600);
            d.setColor(new Color(235, 252, 42));
            for (int i = 0; i <= 60; i++) {
                d.drawLine(160, 140, 40 + 14 * i, 250);
            }
            d.setColor(Color.yellow);
            d.fillCircle(160, 140, 70);
            d.setColor(Color.orange);
            d.fillCircle(160, 140, 60);
            d.setColor(new Color(255, 169, 8));
            d.fillCircle(160, 140, 50);
        } else if (level == 3) {
            d.fillRectangle(40, 40, 720, 600);
            Color color = new Color(42, 40, 40);
            d.setColor(color);
            d.fillRectangle(80, 400, 110, 200);
            for (int i = 0; i <= 4; i++) {
                for (int j = 0; j <= 4; j++) {
                    d.setColor(Color.white);
                    d.fillRectangle(90 + j * 20, 410 + i * 40, 10, 30);
                }
            }
            color = new Color(52, 50, 50);
            d.setColor(color);
            d.fillRectangle(117, 340, 35, 60);
            color = new Color(64, 61, 61);
            d.setColor(color);
            d.fillRectangle(130, 150, 10, 190);
            d.setColor(Color.orange);
            d.fillCircle(135, 140, 10);
            d.setColor(Color.red);
            d.fillCircle(135, 140, 8);
            d.setColor(Color.WHITE);
            d.fillCircle(135, 140, 4);
        } else if (level == 4) {
            d.fillRectangle(40, 40, 720, 600);
            d.setColor(Color.white);
            for (int i = 0; i <= 9; i++) {
                d.drawLine(140 + 10 * i, 460, 100 + 10 * i, 600);
                d.drawLine(600 + 10 * i, 350, 520 + 10 * i, 600);
            }
            Color color = new Color(193, 187, 187);
            d.setColor(color);
            d.fillCircle(600, 350, 30);
            d.fillCircle(620, 380, 30);
            d.fillCircle(140, 460, 30);
            d.fillCircle(160, 480, 30);
            color = new Color(186, 182, 182);
            d.setColor(color);
            d.fillCircle(640, 350, 30);
            d.fillCircle(180, 450, 30);
            color = new Color(175, 169, 169);
            d.setColor(color);
            d.fillCircle(660, 380, 30);
            d.fillCircle(680, 350, 30);
            d.fillCircle(200, 480, 35);
            d.fillCircle(220, 450, 30);
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
