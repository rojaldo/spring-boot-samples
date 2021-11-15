public class tetris {

    interface ITetrisPiece {
        String getShape();

        int getX();

        int getY();

        void setX(int x);

        void setY(int y);

        void moveLeft();

        void moveRight();

        void moveDown();

        void rotate();
    }

    abstract class TetrisPiece {
        private int x;
        private int y;
        private int[][] shape = new int[5][5];

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void moveLeft() {
            x--;
        }

        public void moveRight() {
            x++;
        }

        public void moveDown() {
            y++;
        }

        public void rotate() {
            int[][] temp = new int[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    temp[i][j] = shape[j][i];
                }
            }
            shape = temp;
        }

    }

    class LPiece extends TetrisPiece implements ITetrisPiece {

        public LPiece(int x, int y) {
            super.x = x;
            super.y = y;
            super.shape = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 1, 0 },
                    { 0, 0, 0, 0, 0 } };
        }

        public String getShape() {
            return "L";
        }

    }

    class JPiece extends TetrisPiece implements ITetrisPiece {

        public JPiece(int x, int y) {
            super.x = x;
            super.y = y;
            super.shape = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 1, 0, 0 },
                    { 0, 0, 0, 0, 0 } };
        }

        public String getShape() {
            return "J";
        }
    }

    class SPiece extends TetrisPiece implements ITetrisPiece {

        public SPiece(int x, int y) {
            super.x = x;
            super.y = y;
            super.shape = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 1, 0 }, { 0, 1, 1, 0, 0 },
                    { 0, 0, 0, 0, 0 } };
        }

        public String getShape() {
            return "S";
        }
    }

    class ZPiece extends TetrisPiece implements ITetrisPiece {

        public ZPiece(int x, int y) {
            super.x = x;
            super.y = y;
            super.shape = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 0 }, { 0, 0, 1, 1, 0 },
                    { 0, 0, 0, 0, 0 } };
        }

        public String getShape() {
            return "Z";
        }
    }

    class TPiece extends TetrisPiece implements ITetrisPiece {

        public TPiece(int x, int y) {
            super.x = x;
            super.y = y;
            super.shape = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 1, 1, 0 },
                    { 0, 0, 0, 0, 0 } };
        }

        public String getShape() {
            return "T";
        }
    }

    class OPiece extends TetrisPiece implements ITetrisPiece {

        public OPiece(int x, int y) {
            super.x = x;
            super.y = y;
            super.shape = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 0 }, { 0, 1, 1, 0, 0 },
                    { 0, 0, 0, 0, 0 } };
        }

        public String getShape() {
            return "O";
        }

        @Override
        public void rotate() {
            // do nothing
        }
    }

    class TetrisPieceFactory {

        TetrisPieceFactory() {
        }

        public ITetrisPiece getPiece(String shape, int x, int y) {
            switch (shape) {
            case "L":
                return new LPiece(x, y);
            case "J":
                return new JPiece(x, y);
            case "S":
                return new SPiece(x, y);
            case "Z":
                return new ZPiece(x, y);
            case "T":
                return new TPiece(x, y);
            case "O":
                return new OPiece(x, y);
            default:
                return null;
            }
        }
    }

    class TetrisCompoundBuilder {

        TetrisCompoundComposite result;
        ScoreInterpreter scoreInterpreter;

        TetrisCompoundBuilder() {
            this.result = new TetrisCompoundComposite();
            this.scoreInterpreter = new ScoreInterpreter();
        }

        TetrisCompoundBuilder(ScoreInterpreter scoreInterpreter) {
            this.result = new TetrisCompoundComposite();
            this.scoreInterpreter = scoreInterpreter;
        }

        public void removeRow(int rowNumber) {
            for (int i = 0; i < result.shape[rowNumber].length; i++) {
                result.shape[rowNumber][i] = 0;
                this.scoreInterpreter.interpret(1);
            }
        }

        void addPiece(TetrisPiece piece, int[] position) {

        }

        int[] checkRowsComplete() {
            int[] rows = new int[result.shape.length];
            for (int i = 0; i < result.shape.length; i++) {
                boolean complete = true;
                for (int j = 0; j < result.shape[i].length; j++) {
                    if (result.shape[i][j] == 0) {
                        complete = false;
                        break;
                    }
                }
                if (complete) {
                    rows[i] = 1;
                }
            }
            return rows;
        }

    }

    class TetrisCompoundComposite {

        private int[][] shape = new int[20][10];
        int height;

        TetrisCompoundComposite() {
            this.height = 0;
        }

        public void setShape(int[][] shape) {
            this.shape = shape;
        }

        public int[][] getShape() {
            return shape;
        }

        void calculateHeight() {
            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[i].length; j++) {
                    if (shape[i][j] == 1) {
                        height = i;
                        break;
                    }
                }
            }
        }

    }

    class TetrisBoard {
        private int[][] board = new int[20][10];
        TetrisPiece currentPiece;
        TetrisCompoundBuilder builder;
        TetrisCompoundComposite compound;

        public int getHeight() {
            return board.length;
        }

        public int getWidth() {
            return board[0].length;
        }

        public int getCell(int x, int y) {
            return board[y][x];
        }

        public void setCell(int x, int y, int value) {
            board[y][x] = value;
        }

        public void clear() {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = 0;
                }
            }
        }

    }

    class ScoreBoard {
        private int score;
        private int level;
        private int lines;
        ScoreInterpreter scoreInterpreter;

        ScoreBoard() {
            this.score = 0;
        }

        ScoreBoard(ScoreInterpreter scoreInterpreter) {
            this.score = 0;
            this.scoreInterpreter = scoreInterpreter;
        }

        public int getScore() {
            return score;
        }

        public int getLevel() {
            return level;
        }

        public int getLines() {
            return lines;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }

    public interface IScoreInterpreter {
        public int interpret(int lines);
    }

    public class ScoreInterpreter implements IScoreInterpreter {

        int level;
        int lines;

        ScoreInterpreter() {
        }

        public int interpret(int lines) {
            return lines * 100;
        }
    }



    public static void main(String[] args) {
        
        
    }

    
}
