public class King extends Piece {

    public King(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        return false;
    }

    @Override
    public void move(String to) {
    }
    public String toString() {
        return (this.getColor() == ChessBoard.WHITE) ? "K":"k";
    }

}