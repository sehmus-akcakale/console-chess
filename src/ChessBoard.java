
public class ChessBoard {

    private final String[] letters = {"a","b","c","d","e","f","g","h"};

    public static final int WHITE = 0;
    public static final int BLACK = 1;
    // false - Still not end / true - Game is ended
    private static boolean  gameSituaton= false;
    private static boolean isWhiteAttacking= true;

    public static Piece[][] board ;

    public ChessBoard(){
        ChessBoard.board = new Piece[8][8];
        initialize();

    }
    public boolean isGameEnded() {
        return ChessBoard.gameSituaton;
    }
    public boolean isWhitePlaying(){
        return  ChessBoard.isWhiteAttacking;
    }

    public void initialize(){
        // Defining the white pieces
        board[0][0] = new Rook(0,new Square(0,0));
        board[0][1] = new Knight(0,new Square(0,1));
        board[0][2] = new Bishop(0,new Square(0,2));
        board[0][4] = new King(0,new Square(0,4));
        board[0][3] = new Queen(0,new Square(0,3));
        board[0][5] = new Bishop(0,new Square(0,5));
        board[0][6] = new Knight(0,new Square(0,6));
        board[0][7] = new Rook(0,new Square(0,7));
        for(int i = 0 ; i < 8 ; i++ ){
            board[1][i] = new Pawn(0,new Square(1,i));
        }
        // Defining the black pieces
        board[7][0] = new Rook(1,new Square(0,0));
        board[7][1] = new Knight(1,new Square(0,1));
        board[7][2] = new Bishop(1,new Square(0,2));
        board[7][4] = new King(1,new Square(0,4));
        board[7][3] = new Queen(1,new Square(0,3));
        board[7][5] = new Bishop(1,new Square(0,5));
        board[7][6] = new Knight(1,new Square(0,6));
        board[7][7] = new Rook(1,new Square(0,7));
        for(int j = 0 ; j < 8 ; j++){
            board[6][j] = new Pawn(1,new Square(6,j));
        }
    }

    public Piece[][] getBoard(){
        return ChessBoard.board;
    }

    public void nextPlayer() {
        boolean change = !ChessBoard.isWhiteAttacking;
        ChessBoard.isWhiteAttacking = change;
    }

    public Piece getPieceAt(String from) {
        int rowCoordinate = Integer.parseInt(from.strip().split("")[1]) - 1;
        int colCoordinate = -1;
        for(int i = 0 ; i < letters.length ; i++){
            if(from.split("")[0].equals(letters[i])){
                colCoordinate = i;
                break;
            }
        }
        if((rowCoordinate > 7) || (rowCoordinate < 0)|| (colCoordinate == -1)){
            throw new IllegalArgumentException("You should enter the location correctly");
        }
      return ChessBoard.board[rowCoordinate][colCoordinate] ;

    }
    public Square getSquareAt(String to) {
        int rowCoordinate = Integer.parseInt(to.strip().split("")[1]) - 1;
        int colCoordinate = -1;
        for(int i = 0 ; i < letters.length ; i++){
            if(to.split("")[0].equals(letters[i])){
                colCoordinate = i;
                break;
            }
        }
        if((rowCoordinate > 7) || (rowCoordinate < 0)|| (colCoordinate == -1)){
            throw new IllegalArgumentException("You should enter the location correctly");
        }
        return new Square(rowCoordinate,colCoordinate);
    }


    public Square[] getSquaresBetween(Square s1, Square s2) {
        boolean isAtSameRow = s1.row == s2.row;
        boolean isAtSameColumn = s1.column == s2.column;
        boolean isDiagonal = Math.abs(s1.row - s2.row) == Math.abs(s1.column - s2.column);
        if (isAtSameRow|| isAtSameColumn|| isDiagonal) {
            int distance = Math.max(Math.abs(s1.row - s2.row), Math.abs(s1.column - s2.column));
            Square[] squares = new Square[distance - 1];
            for (int i = 1; i < distance; i++) {
                int row = s1.row + i * (s2.row - s1.row) / distance;
                int column = s1.column + i * (s2.column - s1.column) / distance;
                squares[i - 1] = new Square(row, column);
            }
            return squares;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        String string = "";
        for(int m = 0 ; m < letters.length ; m++){
            if(m == 0){
                string = "   " + letters[m].toUpperCase();
            }else{
                string = string + " " + letters[m].toUpperCase();
            }
        }
        string = string + "\n" + "  -----------------\n" ;
        for(int i = 7 ; i >=0 ; i--){
            string = string + (i+1) + " ";
            for(int j = 0 ; j < 8 ; j++){
                if(board[i][j] == null){
                    string = string + "|" + " ";
                }else{
                    string = string + "|" + board[i][j] ;
                }
                if(j==7){
                    string = string + "| ";
                }
            }
            string = string + (i+1) + "\n";
            if(i==0){
                continue;
            }else{
                string = string + "  -----------------\n";
            }
        }
        string = string + "  -----------------\n";
        for(int k = 0 ; k < letters.length ; k++){
            if(k == 0){
                string = string + "   " + letters[k].toUpperCase();
            }else{
                string = string + " " + letters[k].toUpperCase();
            }
        }
        return string;
    }
    public void resetTheBoard(){
        board = new Piece[8][8];
        initialize();
    }

}
