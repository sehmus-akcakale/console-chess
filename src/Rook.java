import java.util.ArrayList;

public class Rook extends Piece {
    // Directions of Rook :
    int[][] rookMoves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public Rook(int color) {
        super(color);
    }

    public Rook(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {

        boolean validMove = false;

        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);

        boolean firsCondition = (rowDistance == 0 && colDistance != 0);
        boolean secondCondition = (colDistance == 0 && rowDistance != 0);

        if (firsCondition || secondCondition) {
            if (!targetLocation.isEmpty() && targetLocation.getBoard().getSquareAt(to).getPiece().color == this.color) {
                return validMove;
            }
            Square[] between = location.getBoard().getSquaresBetween(location,
                    targetLocation);
            validMove = this.isPathEmpty(between);
            return validMove;
        }
        return validMove;
    }

    // Check all the direction of the rook movement and returns true if rook has possible move :
    @Override
    public boolean hasPossibleMoves() {
        ArrayList<ArrayList<Square>> board = location.getBoard().getBoard();
        for (int[] move : rookMoves) {
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
        return (this.getColor() == ChessBoard.WHITE) ? "R" : "r";
    }
}
