import java.util.ArrayList;

public class King extends Piece {

    public King(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);

        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);

        boolean firstCondition = (Math.abs(rowDistance) == Math.abs(colDistance) && Math.abs(rowDistance) == 1);
        boolean secondCondition = (Math.abs(rowDistance) == 1 && colDistance == 0);
        boolean thirdCondition = (Math.abs(colDistance) == 1 && rowDistance == 0);

        if (firstCondition || secondCondition || thirdCondition) {
            if (!targetLocation.isEmpty() && targetLocation.getBoard().getSquareAt(to).getPiece().color == this.color) {
                return validMove;
            }
            return !validMove;
        }
        return validMove;
    }

    // Check all the direction of the king movement and returns true if king has possible move :
    public boolean hasPossibleMoves(String from) {
        // This hasPossibleMoves method is same with Queen's hasPossibleMoves because I check at least pieces have one square to go .
        Square target = location.board.getSquareAt(from);
        ArrayList<ArrayList<Square>> board = location.getBoard().getBoard();
        boolean canMove = false;
        // Directions of King :
        int[][] kingMoves = {{1,0}, {-1,0}, {0,1}, {0,-1},{1,1}, {1,-1}, {-1,1}, {-1,-1}};

        for(int[] move:kingMoves){
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
        return (this.getColor() == ChessBoard.WHITE) ? "K":"k";
    }

}
