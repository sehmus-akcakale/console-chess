public class Queen extends Piece {

    public Queen(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        return false;
    }

    @Override
    public void move(String to) {
    }

    @Override
    public String toString() {
        return (this.getColor() == ChessBoard.WHITE) ? "Q":"q";
    }
}
