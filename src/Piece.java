
public abstract class Piece {

    protected Square location;
    protected int color;

    public Piece(int color) {
        this.color = color;
    }

    public Piece(int color, Square location) {
        this.location = location;
        this.color = color;
    }

    public Square getLocation() {
        return location;
    }

    public void setLocation(Square location) {
        this.location = location;
    }

    public int getColor() {
        return color;
    }

    // All pieces have different implementation for these methods so I defined them abstract :
    public abstract boolean canMove(String to);

    // The idea of the hasPossibleMoves if the piece has at least one Square to go :
    public abstract boolean hasPossibleMoves();

    // First clear the location then move the piece to the given location :
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        location.clear();
        targetLocation.setPiece(this);
    }

    // It is useful with getSquareBetween because it is looking if Squares are empty or not :
    protected boolean isPathEmpty(Square[] path) {
        for (Square i : path) {
            if (!i.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
