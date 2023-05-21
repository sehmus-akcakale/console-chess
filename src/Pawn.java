import java.util.ArrayList;

public class Pawn extends Piece {

    boolean initialLocation = true;

    public Pawn(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);  
        if (this.location.isAtSameColumn(targetLocation)) {
            if (color == ChessBoard.WHITE && rowDistance > 0 && rowDistance <= 2) {
                if (rowDistance == 2) {
                    if (initialLocation) {
                        //pawn is moving twice, check two squares in front are empty
                        Square[] between = location.getBoard().getSquaresBetween(location,
                                targetLocation);
                        validMove = targetLocation.isEmpty() && between[0].isEmpty();
                    }
                } else {
                    validMove = targetLocation.isEmpty();
                }
                return validMove;
            } else if (color == ChessBoard.BLACK && rowDistance < 0 && rowDistance >= -2) {
                if (rowDistance == -2) {
                    if (initialLocation) {
                        //pawn is moving twice, check two squares in front are empty
                        Square[] between = location.getBoard().getSquaresBetween(location,targetLocation);
                        validMove = targetLocation.isEmpty() && between[0].isEmpty();
                    }
                } else {
                    validMove = targetLocation.isEmpty();
                }
            }
            // attacking diagonals
        } else if (this.location.isNeighborColumn(targetLocation)) {
            if (color == ChessBoard.WHITE && rowDistance == 1) {
                validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() ==
                        ChessBoard.BLACK;
            } else if (color == ChessBoard.BLACK && rowDistance == -1) {
                validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() ==
                        ChessBoard.WHITE;
            }
        }
        return validMove;
    }

    @Override
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        //clear previous location
        location.clear();
        //promoteToQueen
        if (targetLocation.isAtLastRow(color)) {
            targetLocation.putNewQueen(color);
        } else {
            targetLocation.setPiece(this);
        }
        initialLocation = false;
    }

    // Checks if the pawn has any possible move . It is different from canMove because it looks all the moves of the pawn :
    public boolean hasPossibleMoves(String from) {
        Square target = location.board.getSquareAt(from);
        ArrayList<ArrayList<Square>> board = location.getBoard().getBoard();
        boolean canMove = false;
        int[][] whitePawnMoves = {{1,0},{1,1},{1,-1}};
        int[][] blackPawnMoves = {{-1,0},{-1,1},{-1,-1}};
        // We want to get the valid array so this line of code will help us;
       int[][] neededPawnMoves = (target.piece.color == ChessBoard.WHITE) ? whitePawnMoves : blackPawnMoves;

       for(int[] move : neededPawnMoves){
           int newRow = target.row + move[0];
           int newCol = target.column+ move[1];
           if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
               Square destination = board.get(newRow).get(newCol);
               boolean condition = false ;
               // This if evaluate to the forward move .
               if(newCol == target.column){
                   condition = destination.isEmpty();

               }else{
                   condition = destination.piece != null &&  destination.piece.color != target.piece.color;
               }
               if(condition){
                   canMove = true;
                   break;
               }

           }
       }
        return canMove;
    }


    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "P" : "p";
    }
}