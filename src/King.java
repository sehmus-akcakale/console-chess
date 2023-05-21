import java.util.ArrayList;

public class King extends Piece {
    // Directions of King :
    int[][] kingMoves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public King(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);

        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);

        boolean isOneDiagonalMove = (Math.abs(rowDistance) == Math.abs(colDistance) && Math.abs(rowDistance) == 1);
        boolean isOneRowMove = (Math.abs(rowDistance) == 1 && colDistance == 0);
        boolean isOneColumnMove = (Math.abs(colDistance) == 1 && rowDistance == 0);

        if (isOneDiagonalMove || isOneRowMove || isOneColumnMove) {
            if (!targetLocation.isEmpty() && targetLocation.getPiece().color == this.color) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Check all the direction of the king movement and returns true if king has possible move :
     *
     * @return true/false
     */
    @Override
    public boolean hasPossibleMoves() {
        // This hasPossibleMoves method is same with Queen's hasPossibleMoves because I check at least pieces have one square to go .
        ArrayList<ArrayList<Square>> board = location.getBoard().getBoard();
        for (int[] move : kingMoves) {
            int newRow = location.row + move[0];
            int newCol = location.column + move[1];
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square destination = board.get(newRow).get(newCol);
                boolean condition = destination.isEmpty() || (destination.piece.color != this.color);
                if (condition) {
                    return true;
                }
            }
        }
        return false;
    }


    public String toString() {
        return (this.getColor() == ChessBoard.WHITE) ? "K" : "k";
    }

}
