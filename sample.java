class TetrisPiece {
    private int[][] shape;
    private int x;
    private int y;
    private int rotation;

    public TetrisPiece(int[][] shape, int x, int y, int rotation) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public int[][] getShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRotation() {
        return rotation;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public void rotate() {
        rotation = (rotation + 1) % 4;
    }
}

class PieceI extends TetrisPiece {
    PieceI() {
        this.super(new int[][] { { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } }, 0, 0, 0);
    }
}

class PieceJ extends TetrisPiece {
    PieceJ() {
        this.super(new int[][] { { 0, 1, 0 }, { 0, 1, 0 }, { 1, 1, 0 }, }, 0, 0, 0);
    }
}

class PieceL extends TetrisPiece {
    PieceL() {
        this.super(new int[][] { { 1, 0, 0 }, { 1, 0, 0 }, { 1, 1, 0 }, }, 0, 0, 0);
    }
}

class TetrisPieceFactory {
    public static TetrisPiece getRandomPiece() {
        int random = (int) (Math.random() * 7);
        switch (random) {
            case 0:
                return new PieceI();
            case 1:
                return new PieceJ();
            case 2:
                return new PieceL();
            default:
                return new PieceI();
        }
    }
}

public class sample {
    public static void main(String[] args) {
        TetrisPiece piece = TetrisPieceFactory.getRandomPiece();
        System.out.println(piece.getX());
        System.out.println(piece.getY());
        System.out.println(piece.getRotation());
        piece.rotate();
        System.out.println(piece.getX());
        System.out.println(piece.getY());
        System.out.println(piece.getRotation());
    }
}
