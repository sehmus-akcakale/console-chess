import java.util.ArrayList;

public class Queen extends Piece {
    // Directions of Queen :
    int[][] queenMoves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public Queen(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {

        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);

        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);

        boolean firstCondition = (rowDistance == 0) && (colDistance != 0);
        boolean secondCondition = (colDistance == 0) && (rowDistance != 0);
        boolean thirdCondition = Math.abs(rowDistance) == Math.abs(colDistance);

        if (firstCondition || secondCondition || thirdCondition) {
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

    // Check all the direction of the queen movement and returns true if queen has possible move :
    @Override
    public boolean hasPossibleMoves() {
        // I defined the board here because it will be easy to write code below
        ArrayList<ArrayList<Square>> board = location.getBoard().getBoard();
        boolean canMove = false;

        for (int[] move : queenMoves) {
            int newRow = location.row + move[0];
            int newCol = location.column + move[1];
            // Condition checks if the newRow or newColumn is out of the bound of the board.
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square destination = board.get(newRow).get(newCol);
                boolean condition = destination.isEmpty() || (destination.piece.color != this.color);
                if (condition) {
                    canMove = true;
                    break;
                }
            }
        }
        return canMove;

    }


    @Override
    public String toString() {
        return (this.getColor() == ChessBoard.WHITE) ? "Q" : "q";
    }
}
