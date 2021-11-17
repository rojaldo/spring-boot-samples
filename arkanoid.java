import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;
import java.util.ArrayList;

public class arkanoid {
    abstract class BallShape {
        int x;
        int y;
        int xSpeed;
        int ySpeed;
        int xDirection;
        int yDirection;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    class SimpleBall extends BallShape {
        int radius;
        final int DAMAGE = 1;

        SimpleBall(int x, int y, int radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        public void move(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    class LaserBall extends BallShape {
        int radius;
        final int DAMAGE = 10;

        LaserBall(int x, int y, int radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        public void move(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

    }

    class BlockShape {
        int x;
        int y;
        int width;
        int height;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    class Block extends BlockShape {
        int DEFAULT_WIDTH = 20;
        int DEFAULT_HEIGHT = 10;

        Block(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void move(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    class CanvasComposite {
        SimpleBall ballProtorype = new SimpleBall(0, 0, 0);
        LaserBall laserBallPrototype = new LaserBall(0, 0, 0);
        ArrayList<BallShape> balls = new ArrayList<BallShape>();
        ArrayList<BlockShape> blocks = new ArrayList<BlockShape>();

        CanvasComposite() {
        }

        public void startGame() {
        }
    }

    class CanvasBuilder {
        CanvasComposite canvasComposite = new CanvasComposite();

        CanvasBuilder() {
        }

        public void buildBall(int x, int y, int radius) {
            SimpleBall ball = (SimpleBall) canvasComposite.ballProtorype.clone();
            ball.x = x;
            ball.y = y;
            ball.radius = radius;
            canvasComposite.balls.add(ball);
        }

        public void buildLaserBall(int x, int y, int radius) {
            LaserBall ball = (LaserBall) canvasComposite.laserBallPrototype.clone();
            ball.x = x;
            ball.y = y;
            ball.radius = radius;
            canvasComposite.balls.add(ball);
        }

        public void buildBlock(int x, int y, int width, int height) {
            Block block = new Block(x, y, width, height);
            canvasComposite.blocks.add(block);
        }

        // a method that reads a matrix of int and creates blocks
        public void buildBlocks(int[][] matrix) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 1) {
                        buildBlock(j * 20, i * 10, 20, 10);
                    }
                }
            }
        }

        public CanvasComposite getCanvasComposite() {
            return canvasComposite;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        CanvasBuilder canvasBuilder = new CanvasBuilder();
        int[][] matrix = {{1,1,1,0,0},
                          {1,1,1,0,0},
                          {0,0,0,0,0},
                          {0,0,0,0,0},
                          {0,0,0,0,0}};
        canvasBuilder.buildBlocks(matrix);
    }
}
