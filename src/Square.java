public class Square {

    protected int row ;
    protected int column ;
    private static ChessBoard chessBoard = new ChessBoard();

    public Square(int row , int column) {
        this.row = row;
        this.column = column;

    }

    public boolean isAtLastRow(int color){
        Square[] lastRowForWhite = new Square[8];
        Square[] lastRowForBlack = new Square[8];
        boolean isAtLast = false;

        if(color == ChessBoard.WHITE){
            for(int i = 0 ; i < 8 ; i++ ){
                lastRowForWhite[i] = new Square(7,i);
            }
            for (Square square : lastRowForWhite) {
                if (this.row == square.row && this.column == square.column) {
                    isAtLast = true;
                    break;
                }
            }
        }else{
            for(int j = 0 ; j < 8 ; j++){
                lastRowForBlack[j] = new Square(0,j);
            }
            for (Square square : lastRowForBlack) {
                if (this.row == square.row && this.column == square.column) {
                    isAtLast = true;
                    break;
                }
            }
        }
        return false;
    }

    public boolean isEmpty(){
        return Square.chessBoard.getBoard()[row][column] == null;
    }

    public boolean isAtSameColumn(Square s){
        return this.column == s.column;
    }


    public void putNewQueen(int color) {
        Square.chessBoard.getBoard()[row][column] = new Queen(color,new Square(row,column));
    }


    public void clear(){
        Square.chessBoard.getBoard()[row][column] = null;
    }

    public ChessBoard getBoard(){
        return Square.chessBoard;
    }


    public int getRowDistance(Square location) {
        return this.row - location.row;
    }
    public int getColDistance(Square location){
        return this.column - location.column;
    }

    public Piece getPiece() {
        return Square.chessBoard.getBoard()[row][column];
    }

    public void setPiece(Pawn pawn) {
        Square.chessBoard.getBoard()[row][column] = new Pawn(Square.chessBoard.isWhitePlaying() ? 0 : 1 ,new Square(this.row,this.column));
    }

    // I defined this method only for Pawn class
    public boolean isNeighborColumn(Square targetLocation) {
        boolean isNeighbor = false;
        if(Square.chessBoard.isWhitePlaying()){
            boolean rightNeighbor = this.row + 1 == targetLocation.row && this.column + 1 == targetLocation.column;
            boolean leftNeighbor = this.row + 1 == targetLocation.row && this.column - 1 == targetLocation.column;
            if(this.column == 0){
                if(rightNeighbor) {
                    isNeighbor = true;
                }
            }else if (this.column == 7){
                if(leftNeighbor){
                    isNeighbor = true;
                }
            }else{
                if(rightNeighbor || leftNeighbor){
                    isNeighbor = true;
                }
            }
        }else{
            boolean rightNeighbor = this.row - 1 == targetLocation.row && this.column + 1 == targetLocation.column;
            boolean leftNeighbor = this.row - 1 == targetLocation.row && this.column - 1 == targetLocation.column;
            if(this.column == 0){
                if(rightNeighbor) {
                    isNeighbor = true;
                }
            }else if (this.column == 7){
                if(leftNeighbor){
                    isNeighbor = true;
                }
            }else{
                if(rightNeighbor || leftNeighbor){
                    isNeighbor = true;
                }
            }
        }
        return isNeighbor;
    }
}
