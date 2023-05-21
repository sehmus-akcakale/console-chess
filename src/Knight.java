import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);

        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);

        boolean firstCondition = (Math.abs(rowDistance) == 2) && (Math.abs(colDistance) == 1);
        boolean secondCondition = (Math.abs(colDistance) == 2) && (Math.abs(rowDistance) == 1);

        if(firstCondition || secondCondition){
            if(!targetLocation.isEmpty() && targetLocation.getBoard().getSquareAt(to).getPiece().color == this.color ){
                return validMove;
            }
            return true;
        }
        return validMove;
    }
    // Check all the direction of the knight movement and returns true if knight has possible move :
    @Override
    public boolean hasPossibleMoves(String from) {
        Square target = location.board.getSquareAt(from);
        ArrayList<ArrayList<Square>> board = location.getBoard().getBoard();
        boolean canMove = false;
        // Directions of Knight :
        int[][] knightMoves = {{-2, -1}, {-1, -2}, {-2, 1}, {-1, 2}, {2, -1}, {1, -2}, {2, 1}, {1, 2}};

        for (int[] move : knightMoves) {
            int newRow = target.row + move[0];
            int newCol = target.column+ move[1];
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square destination = board.get(newRow).get(newCol);
                boolean condition = destination.isEmpty() || (!destination.isEmpty() && destination.piece.color != target.piece.color);
                if (condition) {
                    canMove = true;
                    break;
                }
            }
        }

        return canMove;
    }



    public String toString() {
        return (this.getColor() == ChessBoard.WHITE) ? "N":"n";
    }
}
