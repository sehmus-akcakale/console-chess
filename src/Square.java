public class Square {
    protected int row;
    protected int column;
    protected Piece piece;
    protected ChessBoard board;

    // The idea of the board is We need board to keep Square object on it :
    public Square(int row, int column, ChessBoard board) {
        this.row = row;
        this.column = column;
        this.board = board;
    }

    public Square(int row, int column) {
        this.row = row;
        this.column = column;
    }

    // It is useful just for Pawn object and It evaluates if the piece at last row, so it can be queen :
    public boolean isAtLastRow(int color) {
        if (color == ChessBoard.WHITE) {
            return this.row == 7;
        } else {
            return this.row == 0;
        }
    }

    // Square object has piece on it or not :
    public boolean isEmpty() {
        return this.piece == null;
    }

    // We are looking if the Squares are at the same row or same column :
    public boolean isAtSameRow(Square s) {
        return this.row == s.row;
    }

    public boolean isAtSameColumn(Square s) {
        return this.column == s.column;
    }

    // If pawn piece are at the last row we are changing it to the queen :
    public void putNewQueen(int color) {
        this.piece = new Queen(color, this);
    }

    // Clears the Square object piece to the null :
    public void clear() {
        this.piece = null;
    }

    // Returns ChessBoard object of the Square object :
    public ChessBoard getBoard() {
        return this.board;
    }

    // Returns row and column distances (It can ve negative , positive or zero) :
    public int getRowDistance(Square location) {
        return this.row - location.row;
    }

    public int getColDistance(Square location) {
        return this.column - location.column;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        piece.setLocation(this);
    }

    // I defined this method only for Pawn class because I didn't understand how to use with other Pieces :
    // Checks the right Neighbor and left Neighbor for the pawn object :
    public boolean isNeighborColumn(Square targetLocation) {
        boolean isNeighbor = false;
        if (this.board.isWhitePlaying()) {
            boolean rightNeighbor = this.row + 1 == targetLocation.row && this.column + 1 == targetLocation.column;
            boolean leftNeighbor = this.row + 1 == targetLocation.row && this.column - 1 == targetLocation.column;
            if (this.column == 0) {
                if (rightNeighbor) {
                    isNeighbor = true;
                }
            } else if (this.column == 7) {
                if (leftNeighbor) {
                    isNeighbor = true;
                }
            } else {
                if (rightNeighbor || leftNeighbor) {
                    isNeighbor = true;
                }
            }
        } else {
            boolean rightNeighbor = this.row - 1 == targetLocation.row && this.column + 1 == targetLocation.column;
            boolean leftNeighbor = this.row - 1 == targetLocation.row && this.column - 1 == targetLocation.column;
            if (this.column == 0) {
                if (rightNeighbor) {
                    isNeighbor = true;
                }
            } else if (this.column == 7) {
                if (leftNeighbor) {
                    isNeighbor = true;
                }
            } else {
                if (rightNeighbor || leftNeighbor) {
                    isNeighbor = true;
                }
            }
        }
        return isNeighbor;
    }

    @Override
    // toString return the piece string because it is easy to use when we  want to print board :
    public String toString() {
        if (this.piece == null)
            return " ";
        return this.piece.toString();
    }
}
