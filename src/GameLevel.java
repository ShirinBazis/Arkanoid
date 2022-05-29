/**
 * @author Shirin Bazis
 * 211492970
 * ass6
 * @version 15.0.2
 * @since 15 September 2020
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * GameLevel is the class that runs a single game level.
 */
public class GameLevel implements Animation {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int SECONDS = 1000;
    private static final int POINTS_TO_ALL_BLOCKS = 100;
    private static final int PADDLE_HEIGHT = 20;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.GUI gui;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter scoreCounter;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private Paddle paddle;
    private Counter livesCounter;


    /**
     * constructor.
     * @param levelInfo is the level information
     * @param ks is the keyboard sensor
     * @param ar is the animation runner
     * @param sc is the score counter
     * @param lc is the lives counter
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar, Counter sc, Counter lc) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        ///////////////////////////////
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.scoreCounter = sc;
        this.runner = ar;
        this.keyboard = ks;
        this.levelInformation = levelInfo;
        this.livesCounter = lc;
    }

    /**
     * adds new collidable to the game environment.
     *
     * @param c is the new collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * adds new sprite to the game sprite collection.
     *
     * @param s is the new sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * returns the blocks counter value.
     *
     * @return the blocks counter value.
     */
    public int getBlocksCounter() {
        return this.blocksCounter.getValue();
    }

    /**
     * returns the balls counter value.
     *
     * @return the balls counter value.
     */
    public int getBallsCounter() {
        return this.ballsCounter.getValue();
    }

    /**
     * adds the block to game.
     *
     * @param b           is the block
     * @param isRemovable checks if the block is removable according to the game instructions
     * @param isZoneBlock checks if the block is the zone of the game
     */
    private void addBlockToGame(Block b, Boolean isRemovable, Boolean isZoneBlock) {
        b.addToGame(this);
        if (isRemovable) {
            BlockRemover blockRemover = new BlockRemover(this, blocksCounter);
            b.addHitListener(blockRemover);
            blocksCounter.increase(1);
            ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(scoreCounter);
            b.addHitListener(scoreTrackingListener);
        }
        if (isZoneBlock) {
            BallRemover ballRemover = new BallRemover(this, ballsCounter);
            b.addHitListener(ballRemover);
        }
    }

    /**
     * initialize the balls in the game.
     */
    public void initializeBalls() {
        Point paddlePoint = this.paddle.getRectangle().getUpperLeft();

        /* add the balls to the game*/
        for (Velocity v : this.levelInformation.initialBallVelocities()) {
            double ballX = paddlePoint.getX() + (double) (this.levelInformation.paddleWidth() / 2);
            double ballY = paddlePoint.getY() - 40;
            Point center = new Point(ballX, ballY);
            Ball ball = new Ball(center, 5, Color.WHITE);
            ball.setVelocity(v);
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            ballsCounter.increase(1);
        }
    }

    /**
     * initialize the paddle in the game.
     */
    private void initializePaddle() {
        Point paddleP = new Point(GUI_WIDTH / 2 - this.levelInformation.paddleWidth() / 2, GUI_HEIGHT - 30);
        Rectangle paddleR = new Rectangle(paddleP, this.levelInformation.paddleWidth(), PADDLE_HEIGHT);
        paddleR.setColor(Color.ORANGE);
        this.paddle = new Paddle(paddleR, keyboard, this.levelInformation.paddleSpeed());
        this.paddle.addToGame(this);
    }

    /**
     * initialize the frame in the game.
     */
    private void initializeFrame() {
        // add the upper frame block to the game
        Point pu = new Point(0, 25);
        Rectangle ru = new Rectangle(pu, 800, 25);
        ru.setColor(Color.GRAY);
        Block bu = new Block(ru);
        bu.addToGame(this);
        // add the right frame block to the game
        Point pr = new Point(775, 25);
        Rectangle rr = new Rectangle(pr, 25, 575);
        rr.setColor(Color.GRAY);
        Block br = new Block(rr);
        br.addToGame(this);
        // add the left frame block to the game
        Point pl = new Point(0, 25);
        Rectangle rl = new Rectangle(pl, 25, 575);
        rl.setColor(Color.GRAY);
        Block bl = new Block(rl);
        bl.addToGame(this);
    }

    /**
     * initialize the indicators in the game.
     */
    private void initializeIndicators() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(350, 15, this.scoreCounter);
        scoreIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(150, 15, this.livesCounter);
        livesIndicator.addToGame(this);
        LevelNameIndicator levelNameIndicator = new LevelNameIndicator(550, 15, this.levelInformation.levelName());
        levelNameIndicator.addToGame(this);
    }

    /**
     * Initialize a new game: create all the objects of the game and add them to the game.
     */
    public void initialize() {
        // add the background block to the game
        Block background = (Block) this.levelInformation.getBackground();
        addCollidable(background);
        BallRemover ballRemover = new BallRemover(this, ballsCounter);
        background.addHitListener(ballRemover);

        /* add the removable blocks to the game*/
        for (Block b : this.levelInformation.blocks()) {
            addBlockToGame(b, true, false);
        }
        // add the paddle to the game
        initializePaddle();
        // add the frame to the game
        initializeFrame();
        // add the balls to the game
        initializeBalls();
        // add the indicators to the game
        initializeIndicators();
    }

    /**
     * returns if the frame should stop.
     *
     * @return if the frame should stop
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * does one frame in the animation.
     *
     * @param d is the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        // add the background image to the game
        this.levelInformation.drawBackgroundAdditions(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // if all the balls fall out of the screen or that all the blocks removed- end the game
        if (blocksCounter.getValue() == 0 || ballsCounter.getValue() == 0) {
            if (blocksCounter.getValue() == 0) {
                // add 100 points when all the blocks are removed
                scoreCounter.increase(POINTS_TO_ALL_BLOCKS);
            }
            this.running = false;
        }
    }

    /**
     * Runs the game -- start the animation loop.
     */
    public void run() {
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(2, 3, sprites, levelInformation));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    /**
     * removes a collidable from the game environment.
     *
     * @param c is a collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * removes a sprite from the game environment.
     *
     * @param s is a sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}
