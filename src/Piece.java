public abstract class Piece {

    protected Square location;
    protected int color;

    public Piece(int color, Square location) {
        this.location = location;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public abstract boolean canMove(String to);

    public abstract void move(String to);

}
