import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoard {
    // To print a board and to get a column address of pieces I defined letters :
    private final String[] letters = { "a", "b", "c", "d", "e", "f", "g", "h" };

    public static final int WHITE = 0;
    public static final int BLACK = 1;

    // gameSituation = false (Game is not finished yet ) gameSituation = true (Game is finished):
    private boolean gameSituaton = false;
    // To check which color is playing :
    private boolean isWhiteAttacking = true;

    public ArrayList<ArrayList<Square>> board = new ArrayList<ArrayList<Square>>();

    // When we create ChessBoard object it call initialize() method :
    public ChessBoard() {
        initialize();
    }


    public boolean isGameEnded() {
        return gameSituaton;
    }

    public boolean isWhitePlaying() {
        return isWhiteAttacking;
    }

    // initialize() add 64 Square object to the board with the initial pieces inside the Square objects :
    public void initialize() {
        // Creating 64 Square objects :
        for (int row = 0; row < 8; row++) {
            ArrayList<Square> rowList = new ArrayList<Square>();
            for (int column = 0; column < 8; column++) {
                rowList.add(new Square(row, column, this));
            }
            board.add(rowList);
        }
        // White and Black pieces :
        List<Piece> whitePieces = Arrays.asList(
                new Rook(WHITE, null),
                new Knight(WHITE, null),
                new Bishop(WHITE, null),
                new Queen(WHITE, null),
                new King(WHITE, null),
                new Bishop(WHITE, null),
                new Knight(WHITE, null),
                new Rook(WHITE, null)

        );
        List<Piece> blackPieces = Arrays.asList(
                new Rook(BLACK, null),
                new Knight(BLACK, null),
                new Bishop(BLACK, null),
                new Queen(BLACK, null),
                new King(BLACK, null),
                new Bishop(BLACK, null),
                new Knight(BLACK, null),
                new Rook(BLACK, null)

        );
        // Initial position of pieces in Chess :
        for (int i = 0; i < whitePieces.size(); i++) {
            // Add whites
            board.get(0).get(i).setPiece(whitePieces.get(i));
            board.get(1).get(i).setPiece(new Pawn(WHITE, null));
            // Add blacks
            board.get(6).get(i).setPiece(new Pawn(BLACK, null));
            board.get(7).get(i).setPiece(blackPieces.get(i));
        }

    }

    public ArrayList<ArrayList<Square>> getBoard() {
        return this.board;
    }

    // Change the attacking side
    public void nextPlayer() {
        isWhiteAttacking = !isWhiteAttacking;
    }

    // Returns correct Pieces at the given string(from) :
    public Piece getPieceAt(String from) {
        int rowCoordinate = Integer.parseInt(from.strip().split("")[1]) - 1;
        int colCoordinate = -1;
        for (int i = 0; i < letters.length; i++) {
            if (from.split("")[0].equals(letters[i])) {
                colCoordinate = i;
                break;
            }
        }
        // If given value is invalid throws IllegalArgumentException :
        if ((rowCoordinate > 7) || (rowCoordinate < 0) || (colCoordinate == -1)) {
            throw new IllegalArgumentException("You should enter the location correctly");
        }
        return this.board.get(rowCoordinate).get(colCoordinate).getPiece();

    }
    //Returns correct Square object at given string(to) :
    public Square getSquareAt(String to) {
        int rowCoordinate = Integer.parseInt(to.strip().split("")[1]) - 1;
        int colCoordinate = -1;
        for (int i = 0; i < letters.length; i++) {
            if (to.split("")[0].equals(letters[i])) {
                colCoordinate = i;
                break;
            }
        }
        // Same idea with getPieceAt
        if ((rowCoordinate > 7) || (rowCoordinate < 0) || (colCoordinate == -1)) {
            throw new IllegalArgumentException("You should enter the location correctly");
        }
        return this.getBoard().get(rowCoordinate).get(colCoordinate);
    }

    // Returns Square array so that we can check our path to move another Square :
    public Square[] getSquaresBetween(Square s1, Square s2) {

        boolean isAtSameRow = s1.row == s2.row;
        boolean isAtSameColumn = s1.column == s2.column;
        boolean isDiagonal = Math.abs(s1.row - s2.row) == Math.abs(s1.column - s2.column);

        if (isAtSameRow || isAtSameColumn || isDiagonal) {

            int distance = Math.max(Math.abs(s1.row - s2.row), Math.abs(s1.column - s2.column));
            int rowConstant = (s2.row - s1.row) / distance;
            int colConstant = (s2.column - s1.column) / distance;

            Square[] path = new Square[distance - 1];
            for (int i = 1; i < distance; i++) {
                int row = s1.row + i * rowConstant;
                int column = s1.column + i * colConstant;
                path[i - 1] = this.board.get(row).get(column);
            }
            return path;
        } else {
            return null;
        }
    }
    // We check opposite color of isWhiteAttacking because if there is no another different color pieces means IsWhiteAttacking won the game :
    public void checkGame(){
        boolean isEnded = true;
        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j < 8 ; j++){
                if(board.get(i).get(j).getPiece()!= null && board.get(i).get(j).getPiece().color == (isWhiteAttacking ? 1 : 0)){
                    isEnded = false;
                    break;
                }
            }
        }
        if(isEnded){
            gameSituaton = true;
            System.out.println("The " + (isWhiteAttacking ? "White":"Black") + " pieces won.");
        }
    }

    //Check at least one piece (no matter the color ) has possible move . If there are no possible moves so game is tie :
    public boolean isGameTie(){
        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j<8;j++){
                int row = (j+1);
                String from = letters[i] + row;
                if(board.get(i).get(j).piece != null ){
                    if(board.get(i).get(j).piece.hasPossibleMoves(from)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Prints board as well as the pieces on it :
    @Override
    public String toString() {
        String string = "";
        for (int m = 0; m < letters.length; m++) {
            if (m == 0) {
                string = "   " + letters[m].toUpperCase();
            } else {
                string = string + " " + letters[m].toUpperCase();
            }
        }
        string = string + "\n" + "  -----------------\n";
        for (int i = 7; i >= 0; i--) {
            string = string + (i + 1) + " ";
            for (int j = 0; j < 8; j++) {
                Square s = board.get(i).get(j);
                if (s == null) {
                    string = string + "|" + " ";
                } else {
                    string = string + "|" + s;
                }
                if (j == 7) {
                    string = string + "| ";
                }
            }
            string = string + (i + 1) + "\n";
            if (i == 0) {
                continue;
            } else {
                string = string + "  -----------------\n";
            }
        }
        string = string + "  -----------------\n";
        for (int k = 0; k < letters.length; k++) {
            if (k == 0) {
                string = string + "   " + letters[k].toUpperCase();
            } else {
                string = string + " " + letters[k].toUpperCase();
            }
        }
        return string;
    }

    // If we want to start the game again this code is ready for us :
    public void resetTheBoard() {
        board = new ArrayList<ArrayList<Square>>();
        initialize();
    }
}
