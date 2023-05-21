import java.util.ArrayList;

public class Bishop extends Piece{


    public Bishop(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);
        // Bishop moves with diagonal so first we need to check this :
        if(Math.abs(rowDistance) == Math.abs(colDistance)){
            // If target location is not empty and piece of targetLocation has same color with the piece :
            if(!targetLocation.isEmpty() && targetLocation.getBoard().getSquareAt(to).getPiece().color == this.color ){
                return validMove;
            }
            Square[] between = location.getBoard().getSquaresBetween(location,
                    targetLocation);
            // If path is empty means there are no any piece on our path :
            validMove = this.isPathEmpty(between);
            return validMove;
        }
        return validMove;
    }

    // Check all the direction of the bishop movement and returns true if bishop has possible move :
    public boolean hasPossibleMoves(String from) {
        Square target = location.board.getSquareAt(from);
        ArrayList<ArrayList<Square>> board = location.getBoard().getBoard();
        boolean canMove = false;
        // Directions of Bishop:
        int[][] bishopMoves = {{1,1}, {1,-1}, {-1,1}, {-1,-1}};

        for(int[] move : bishopMoves){
            int newRow = target.row + move[0];
            int newCol = target.column + move[1];
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
        return (this.getColor() == ChessBoard.WHITE) ? "B":"b";
    }
}
