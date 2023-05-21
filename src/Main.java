import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        ChessBoard board = new ChessBoard();

        System.out.println(board);

        while (!board.isGameEnded()) {
            String from;
            System.out.println("It is " + (board.isWhitePlaying() ? "White" : "Black") + "'s turn");
            Piece piece = null;
            do {
                System.out.print("Enter the location of the piece:");
                from = reader.next();
                piece = board.getPieceAt(from);
                // If player don't choose correct piece or empty square or if piece has anywhere to go;
                if(piece == null
                        || piece.getColor() != (board.isWhitePlaying() ? ChessBoard.WHITE : ChessBoard.BLACK)
                        || !piece.hasPossibleMoves(from)){
                    System.out.println("Invalid choose!");
                }
                // If piece has not possible move the code will be stick around so I check whether the piece has possible moves :
            } while (piece == null
                    || piece.getColor() != (board.isWhitePlaying() ? ChessBoard.WHITE : ChessBoard.BLACK)
                    || !piece.hasPossibleMoves(from)
            );
            String to = null;
            do {
                System.out.print("Enter the new location of the piece:");
                to = reader.next();
            } while (!piece.canMove(to));
            piece.move(to);

            /*
            I get different error that I should not get, so I decided to get this piece of code as a comment .
            Check at least one Piece has possible move. If all pieces have not any possible move so game is tie :
            if(board.isGameTie()){
                System.out.println("The game ended tie!");
               break;
            }*/
            // Check the game whether the game finished or not:
            board.checkGame();
            board.nextPlayer();
            System.out.println(board);
        }
        reader.close();
    }
}
