/**
 * @author nadav oxenberg
 * ID: 207952144
 */

package game;

import Shape.Rectangle;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import charcters.Ball;
import charcters.Block;
import charcters.Paddle;
import counter.Counter;
import geometry.Point;
import geometry.Velocity;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import nameLevel.NameLevelString;
import remover.BallRemover;
import remover.BlockRemover;
import score.ScoreIndicator;
import score.ScoreTrackingListener;
import sprites.SpriteCollection;

import java.awt.Color;


/**
 * game calss, here we start the game.
 */
public class GameLevel implements Animation {
    private static final int RADIOS = 5;
    private static final int WIDTH = 800; // the width of the screen
    private static final int HEIGHT = 600; // the height of the screen
    private static final int WIDTHOFFRAMES = 40;
    private final GUI gui;
    private final Sleeper sleeper = new Sleeper();
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter counterScore;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private int lives;
    private Paddle paddle;
    private NameLevelString nameLevelStringAndLives;
    private CountdownAnimation countdownAnimation;
    private double flagStart;
    private KeyPressStoppableAnimation keyForPause;
    private KeyPressStoppableAnimation keyForLose;



    /**
     * Game, constuctor for the game class.
     * @param levelInformation the level we play.
     * @param keyboard the keyboar sensor.
     * @param score the score the player have.
     * @param lives the lives the player left.
     * @param gui the screen of the game.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard, int score, int lives, GUI gui) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        runner = new AnimationRunner(gui, 60);
        this.keyboard = keyboard;
        running = false;
        this.levelInformation = levelInformation;
        this.lives = lives;
        flagStart = 3;
        counterScore = new Counter(score);
        this.gui = gui;
        keyForPause = new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen(keyboard));
    }

    /**
     * addCollidable.
     * @param c add collectable object to the environment.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * addSprite.
     * @param s add the spirit to the list of spirits - to the game.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * create block score.
     */
    public void createBlockScore() {
        scoreTrackingListener = new ScoreTrackingListener(counterScore);
        Rectangle rectScoreIndicator = new Rectangle(new Point(0, 0), WIDTH, 40);
        Block blockScoreIndicator = new Block(rectScoreIndicator, Color.WHITE);
        scoreIndicator = new ScoreIndicator(counterScore, blockScoreIndicator);
        scoreIndicator.addToGame(this);
    }

    /**
     * create frames of the screen.
     */
    public void makeFramesFoirTheScreen() {
        //make the frames of the screen
        Rectangle upperFrame = new Rectangle(new Point(0, 30), WIDTH, WIDTHOFFRAMES);
        Rectangle deathRegionBlock = new Rectangle(new Point(0, HEIGHT), WIDTH, WIDTHOFFRAMES);
        Rectangle rightFrame = new Rectangle(new Point(WIDTH - WIDTHOFFRAMES, 30), WIDTHOFFRAMES, HEIGHT);
        Rectangle leftFrame = new Rectangle(new Point(0, 30), WIDTHOFFRAMES, HEIGHT);
        //make the blocks for the frames
        Block upper = new Block(upperFrame, Color.blue);
        Block deathRegion = new Block(deathRegionBlock, Color.white);
        Block right = new Block(rightFrame, Color.blue);
        Block left = new Block(leftFrame, Color.blue);
        //add the blocks frames to the game
        upper.addToGame(this);
        right.addToGame(this);
        left.addToGame(this);
        // add the bottom to the game
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
    }

    /**
     * make paddle of the level.
     * @param widthpaddle the width of the paddle.
     * @param heightpaddle the hight of the paddle.
     * @param speed the speed of paddle.
     */
    public void makePaddle(int widthpaddle, int heightpaddle, int speed) {
        //make a paddle
        paddle = new Paddle(this.gui, 70, HEIGHT - 35,
                widthpaddle, heightpaddle, speed);
        //add to him the game
        paddle.addToGame(this);
    }

    /**
     * make one ball.
     * @param angle the angle of the ball.
     * @param speed the speed of the ball
     * @param color the color of the ball
     * @param pixlefromcenterX the place of the ball in x.
     * @param pixelFromCeterY the place of the ball in x.
     */
    public void makeBall(int angle, int speed, Color color, int pixlefromcenterX, int pixelFromCeterY) {
        Ball ballOne = new Ball(new Point(WIDTH / 2 + pixlefromcenterX,
                HEIGHT - pixelFromCeterY), RADIOS, color, environment);
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        ballOne.setVelocity(v);
        ballOne.addToGame(this);
        counterBalls.increase(1);
    }

    /**
     * make ball with velocity ready.
     * @param v the velocity of the ball
     * @param color the color.
     * @param middleX the place of the ball in x.
     * @param leftTopY the place of the ball in x.
     */
    public void makeBallByVelocity(Velocity v, Color color, int middleX, int leftTopY) {
        Ball ballOne = new Ball(new Point(middleX, leftTopY - 40),
                RADIOS, color, environment);
        ballOne.setVelocity(v);
        ballOne.addToGame(this);
    }

    /**
     * make name level.
     */
    public void makeNameLevel() {
        nameLevelStringAndLives = new NameLevelString(levelInformation.levelName(), lives);
        nameLevelStringAndLives.addToGame(this);
    }

    /**
     * make numbers pf balls.
     */
    public void makeBalls() {
        for (Velocity velocity : levelInformation.initialBallVelocities()) {
            int middleX = (paddle.getLeftPaddleX() + paddle.getRightPaddleX()) / 2;
            makeBallByVelocity(velocity, Color.white, middleX, paddle.getLeftPaddleY());
            counterBalls.increase(1);
        }
    }

    /**
     * Initialize a new game: create the Blocks and charcters.Ball (and charcters.Paddle) and add them to the game.
     */
    public void initialize() {
        //the number of blocks in the level.
        counterBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        //make a listenr for the removal blocks.
        blockRemover = new BlockRemover(this, counterBlocks);
        //make a hitlistenr that will be in charge of removing balls, and updating an availabe-balls counter
        counterBalls = new Counter(0);
        ballRemover = new BallRemover(this, counterBalls);
        //make the background.
        levelInformation.getBackground().addToGame(this);
        //make frames for the the level.
        makeFramesFoirTheScreen();
        //create the block of the score, this block display the score.
        createBlockScore();
        //make the level name top left.
        makeNameLevel();
        //make the blocks of the level
        for (Block block : levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
        //if it is level one:
        if (levelInformation.levelName().equals("Direct Hit")) {
            //make a paddle
             this.paddle = new Paddle(this.gui, 370, HEIGHT - 35,
                    levelInformation.paddleWidth(), 15, levelInformation.paddleSpeed());
            //add to him the game
            this.paddle.addToGame(this);
            makeBall(0,  6, Color.white, 0, 200);
        } else {
            //make the paddle
            makePaddle(levelInformation.paddleWidth(), 15, levelInformation.paddleSpeed());
            //make ball
            makeBalls();
        }
        //make the count down animation
        countdownAnimation = new CountdownAnimation(sprites);

    }

    /**
     * run, Run the game -- start the animation loop.
     */
    public void run() {
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * remove interfaces.Collidable from list.
     * @param c the interfaces.Collidable we remove.
     */
    public void removeCollidable(Collidable c) {
        environment.getCollideWith().remove(c);
    }

    /**
     * remove sprite from list.
     * @param s the sprite we remove.
     */
    public void removeSprite(Sprite s) {
        sprites.getSprites().remove(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (counterBalls.getValue() == 0 && nameLevelStringAndLives.getLives() != 0) {
            makeBalls();
            flagStart = 3;
            countdownAnimation.setSeconds(3);
        }
        if (0 <= flagStart && flagStart <= 3) {
            countdownAnimation.doOneFrame(d);
            flagStart = flagStart - 0.02;
        } else {
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();
            if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P") || this.keyboard.isPressed("פ")) {
                this.runner.run(keyForPause);
                flagStart = 2;
                countdownAnimation.setSeconds(2);
                this.keyForPause.setStop();
            }
        }
    }

    @Override
    public boolean shouldStop() {
        if (counterBalls.getValue() == 0 || counterBlocks.getValue() == 0) {
            if (counterBlocks.getValue() == 0) {
                counterScore.increase(100);
                running = true;
            }
            if (counterBalls.getValue() == 0) {
                if (nameLevelStringAndLives.getLives() != 0) {
                    nameLevelStringAndLives.updateLives();
                } else {
                    keyForLose = new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                            new LoseScreen(keyboard, counterScore.getValue()));
                    this.runner.run(keyForLose);
                    //this.runner.run(new LoseScreen(this.keyboard, getScore()));
                    running = true;
                }
            }
            return running;
        }
        return false;
    }

    /**
     * number of balls remain.
     * @return the number of balls remain.
     */
    public int getNumberBalls() {
        return counterBalls.getValue();
    }

    /**
     * get the number of blocks.
     * @return the number of blocks remain.
     */
    public int getNumberOfBlocksReamin() {
        return counterBlocks.getValue();
    }

    /**
     * get the lives.
     * @return the lives the player left.
     */
    public int getLives() {
        return nameLevelStringAndLives.getLives();
    }

    /**
     * get the score.
     * @return the current score.
     */
    public int getScore() {
        return counterScore.getValue();
    }
}