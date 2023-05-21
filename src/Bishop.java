public class Bishop extends Piece {
    // Directions of Bishop:
    final int[][] bishopMoves = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public Bishop(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);
        // Bishop moves with diagonal so first we need to check this :
        if (Math.abs(rowDistance) == Math.abs(colDistance)) {
            // If target location is not empty and piece of targetLocation has same color with the piece :
            if (!targetLocation.isEmpty() && targetLocation.getPiece().color == this.color) {
                return false;
            }
            Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);
            // If path is empty means there are no any piece on our path :
            return this.isPathEmpty(between);
        }
        return false;
    }

    // Check all the direction of the bishop movement and returns true if bishop has possible move :
    @Override
    public boolean hasPossibleMoves() {
        var board = location.getBoard().getBoard();
        for (int[] move : bishopMoves) {
            int newRow = location.row + move[0];
            int newCol = location.column + move[1];
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square destination = board.get(newRow).get(newCol);
                boolean isValidMove = destination.isEmpty() || (destination.piece.color != this.color);
                if (isValidMove) {
                    return true;
                }
            }
        }
        return false;
    }


    public String toString() {
        return (this.getColor() == ChessBoard.WHITE) ? "B" : "b";
    }
}
