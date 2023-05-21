### Printing Board  :

Describe how you manage to print the board representation. How did you construct loops? What are the challenges? How did you manage to solve them?

Answer:

The main challenge was printing A B C D E G H on the upper and lower side of the board so I first created a letters array that included lowercase letters a to h(didn’t give uppercase of a to h because I will be using the letters array in the getSquareAt() and getPieceAt() methods.). I have totally constructed three for loop two of them(at the beginning and at the end ) have been used for printing  A B C D E G H.The loop in the middle has one for loop inside it because those two loops are created to look at 64 Square objects and if there is a piece returns its toString() else it will return “ “.

Defining Board and Square Classes :

A board can have 64 squares. How did you define the relation between Board and Square objects?

	Answer:

In my implementation, the ChessBoard class has a board ArrayList which stores 64 Square objects. Every square object has a piece attribute whether it contains a piece or not. I used initialize() method in the constructor of the ChessBoard class to give 64 Square objects to the board ArrayList and also initialize() method gives appropriate pieces with the correct initial order(as it is in the chess) to the squares.

Implementing methods of Board and Square Classes :

Describe each method in these classes except mutator and accessor methods.
In each method description, you should provide.

What does the method do?
One sentence description of each parameter
What does it return?
How did you implement the functionality?

Answer :

For ChessBoard Class:

public void initialize();
The method sets up the starting configuration of a board by creating 64 Square objects and placing white and black pieces in their correct initial order on the board.
The method does not have any parameter.
The method does not return anything.
Firstly the method creates 64 Square objects and stores them on the board. Then creates 2 List<Piece> which include all type(black and white) of pieces except pawns. Lastly, the last loop iterates the list of pieces and assigns them with the correct initial order to the Squares. While it was doing it, it also assign the Pawns with the correct initial order to the Squares.


public Piece getPieceAt(String from);
The method gets string(from) like d2,a1,etc. and find this location on the board and returns the valid Piece for given string.
String from - Consist of row and column coordinates as a string
The method returns valid Piece for given string
First, the method gets second character of the string and parse it to the Integer than subtract with one.This process gets our row coordinate . Then, the method gets first character of the string . For loop iterates on letters array when it finds first character of string in the array, it gets index of the first character. This process gets our column coordinate. Finally, the method returns the Piece on the board with founded row and column coordinates .

public Square getSquareAt(String to);
The method gets string(to) like d2,a1,etc. and find this location on the board and returns the valid Square for given string .
String to - Consist of row and column coordinates as a string
The method returns valid Square for given string
The method has same process with getPieceAt() . Only differences with getPieceAt() is it returns Square on the board with founded row and column coordinates .

public Square[ ] getSquaresBetween(Square s1 , Square s2);
The method returns the square between given squares if they are at the same row or same column or same diagonal .
Square s1 - Beginning square , Square s2 - Final square
The method returns array of squares as the same order from s1 square to s2 square not including s1 and s2, null is returned if no squares are in between.
The method firstly checks if the squares are in the same row or same column or same diagonal . Then, finds a distance of two square and assign it to the distance variable . In addition, The method calculate rowConstant and colConstant which we will find 1 or 0 or -1 because it will be used for finding our path . Finally , for loop finds all the square between square s1 and square s2 respectively and stores in the path array then returns path if loop executed else it will return null.

public void checkGame();
The method checks if the game has ended or not by checking opposite color of attacking pieces.
The method does not take any parameter.
The method does not return anything.
The method iterates over the row and columns of the board if it finds any piece that has opposite color of attacking pieces it will do nothing. If method can not find any piece that has opposite color of attacking piece , it changes the gameSituation to true.

public boolean isGameTie(); 	
The method checks if the game has not finished but there are no any pieces that have possible move . If it finds at least one piece that has possible move it returns false.In else codition it return true.
The method does not take any parameter .
The method returns false if it finds at least one piece that has possible move.In else  condition returns true.
The method iterates over the rows and columns of the board and checks all the pieces has possible move or not . If at least one piece has possible move it return false.

public void resetBoard();
The method clear all the board make it as it is in the beginning of the chess game.
The method does not take any parameter.
The method does not return anything.
The method firstly assign new ArrayList object and then call the initialize() method so the will be same as it is in the beginning.


	For Square Class :

public boolean isAtLastRow(int color);
The method checks is the pawn at last square with given color argument . If pawn is in at last square for given color argument it returns true , else returns false.
int color - The color of the pawn
If pawn is at last square for given color it returns true, else returns false.
The methods first checks color . If color is equals to 0 and if pawn in the 8. row(7. index in the ArrayList) it returns true , else returns false. If color is equals to 1 and if pawn in the 1. row (0. index in the ArrayList) it returns true, else returns false.

public boolean isEmpty();
The method checks if the location is empty or not .
The method does not take any parameter.
The method returns true if the location is empty, else it returns false.
The method gets piece attribute of the location and if piece is empty it returns true, if location is not empty it returns false.

public boolean isAtSameRow(Square s);
public boolean isAtSameColumn(Square s);
The methods check the location is at same column and is at same row with square s.
Square s - Square object which we want to compare our row and column
The methods return true if the location is at the same column and is at same row with square s , else return false;
The methods checks if the row attribute of the location and row attribute of the s is equals and if the column attribute of the location and column attribute of s is equals.
public void putNewQueen(int color);
The method put an appropriate queen in a location with given color.
int color - The color of the piece.
The method does not return anything.
The method changes piece attribute of the square to an appropriate queen.


public int getColDistance(Square s);
public int getColDistance(Square s);
The methods calculate the row and column distances between location and square s. Distances can be negative or positive or zero .
Square s - Square object which we want to get row or column distance.
The methods return row and columns distances  between location and square s.
The methods subtract location row and square s row and also subtract location column and square s row. Then, return the operations.

public boolean isNeighborColumn(Square targetLocation);
The method determines if the given Square tagetLocation is neighbor column of the square object by using row and column coordinate.
Square targetLocation - Square object which we want to determine is it a neighbor column.
The method returns true if Square targetLocation is neighbor column , else return false.
The method first checks which color is playing with the metot of isWhitePlaying(). Then if white playing, it assign two variables : rightNeighbor and leftNeighboar. rightNeighbor is equals to its row + 1 and its column + 1 . leftNeighbor is equals to its row + 1 and its column - 1 . The method evaluate neighbor is based on its column . If rightNeighbor or leftNeighbor is equals to Square targetLocation , the method returns true ,else returns false . Same idea is correct for black color but rightNeighbor is equals to its row - 1 and its column - 1 and leftNeighbor is equals to its row - 1 and its column +1

Defining Piece Hierarchy:

Explain how Main class benefits from polymorphism. Explain which methods and classes can be defined as abstract in the Piece hierarchy. Is there core reuse in your implementation?

Answer :
In the Main class we defined Piece piece but we can not create a Piece object because the Piece class is an abstract class. Rook, Queen, etc. classes are inherited from the Piece class so when we assign piece variable to these kinds of Piece we can access their overriding methods. In the Piece class canMove(String to) and hasPossibleMoves(String from) methods have been defined as abstract because every piece has its own implementation for these methods. I use the same hasPossibleMoves(String from) method for Queen and King classes.


Implementing methods in Piece Hierarchy:

Do the same as described in C for the classes in Piece hierarchy.

Answer :

For Piece Class:

public void move(String to);
*** Bishop, King, Queen, Knight, and Rook Classes use this move
The method moves the piece to the given string “to”.
String to - The place where we want to move the piece.
The method does not return anything.
The method first uses getSquareAt(to) method to get targetLocation.Then, clear the current location, and lastly set the pieces to the targetLocation.

public boolean isEmpty(Square[ ] path);
The method iterates over the path and checks if every square is empty.
Square[ ] path - Square array represent our path.
The method returns true if every square is empty on the path, else returns false.
The method has loop that iterates on the path and uses isEmpty() method . if at least one square is not empty it returns false.

	For Pawn Class:

public boolean canMove(String to);
public void move(String to);
This method is implemented in the appendix so I think I should not describe them.

public boolean hasPossibleMoves(String from);



The method determines the pawn at the String “from” has any possible moves.
String from - Place of the pawn.
The method returns true if the pawn has any possible moves, else returns false.
The method gets the target square with the given String from parameter. It initializes canMove and assigns it to false to keep track of any valid move is possible.It defines possible moves array for white and black pawn. It iterates each move pattern for the pawn. It calculates newRow and newColumn values that fall within the bounds of the chessboard(between 0 and 7). It retrieves the square at the new coordinates. Then,If the new column is the same as the target column ,checks if the destination square is empty with using destination.isEmpty().Else, checks if the destination square contains a piece and if that piece has a different color than the target piece. If any of these conditions are met, set canMove to true and exit the loop. Finally, returns the value of canMove.


For Bishop Class :

public boolean canMove(String to);

The method checks if the piece can move the location (String to ).
String to - The place where we want to move the piece.
The method returns true if the piece can move the location, else returns false.
The method first calculates the rowDistance and colDistance between the piece and location(String to - our parameter). Then, the method defines some conditions valid for Bishop pieces. If any condition of these conditions is true then the method checks if targetLocation is not empty and targetLocation piece object has the same color with our piece the methods return false. In the else condition, the method uses getSquareBetween(location,targetLocation) to get all the squares between. Then we use isPathEmpty(Square[ ] s) method if the method returns true, it means piece can move and return true, else return false.


public boolean hasPossibleMoves(String from);



The method determines the bishop at the String “from” has any possible moves.
String from - Place of the bishop.
The method returns true if the bishop has any possible moves, else returns false.
The method gets the target square with the given String from parameter. It initializes canMove and assigns it to false to keep track of any valid move is possible.It defines possible moves array bishopMoves. It iterates each move pattern for the pawn. It calculates newRow and newColumn values that fall within the bounds of the chessboard(between 0 and 7). It retrieves the square at the new coordinates.Then,If destination square is empty or if the destination square is not empty and contains a piece of a different color then the target piece. If any of these conditions are met, set canMove to true and exit the loop. Finally, returns the value of canMove.





For King Class:


public boolean canMove(String to);

The method checks if the piece can move the location (String to ).
String to - The place where we want to move the piece.
The method returns true if the piece can move the location, else returns false.
The method first calculates the rowDistance and colDistance between the piece and location(String to - our parameter). Then, the method defines some conditions valid for King pieces. If any condition of these conditions is true then the method checks if targetLocation is not empty and targetLocation piece object has the same color with our piece the methods return false. In the else condition, the method uses getSquareBetween(location,targetLocation) to get all the squares between. Then we use isPathEmpty(Square[ ] s) method if the method returns true, it means piece can move and return true, else return false.

public boolean hasPossibleMoves(String from);



The method determines the king at the String “from” has any possible moves.
String from - Place of the king.
The method returns true if the king has any possible moves, else returns false.
The method gets the target square with the given String from parameter. It initializes canMove and assigns it to false to keep track of any valid move is possible.It defines possible moves array kingMoves. It iterates each move pattern for the pawn. It calculates newRow and newColumn values that fall within the bounds of the chessboard(between 0 and 7). It retrieves the square at the new coordinates.Then,If destination square is empty or if the destination square is not empty and contains a piece of a different color then the target piece. If any of these conditions are met, set canMove to true and exit the loop. Finally, returns the value of canMove.



For Queen Class:

public boolean canMove(String to);
The method checks if the piece can move the location (String to ).
String to - The place where we want to move the piece.
The method returns true if the piece can move the location, else returns false.
The method first calculates the rowDistance and colDistance between the piece and location(String to - our parameter). Then, the method defines some conditions valid for Queen pieces. If any condition of these conditions is true then the method checks if targetLocation is not empty and targetLocation piece object has the same color with our piece the methods return false. In the else condition, the method uses getSquareBetween(location,targetLocation) to get all the squares between. Then we use isPathEmpty(Square[ ] s) method if the method returns true, it means piece can move and return true, else return false.

public boolean hasPossibleMoves(String from);





The method determines the queen at the String “from” has any possible moves.
String from - Place of the queen.
The method returns true if the queen has any possible moves, else returns false.
The method gets the target square with the given String from parameter. It initializes canMove and assigns it to false to keep track of any valid move is possible.It defines possible moves array queenMoves. It iterates each move pattern for the pawn. It calculates newRow and newColumn values that fall within the bounds of the chessboard(between 0 and 7). It retrieves the square at the new coordinates.Then,If destination square is empty or if the destination square is not empty and contains a piece of a different color then the target piece. If any of these conditions are met, set canMove to true and exit the loop. Finally, returns the value of canMove.





	For Knight Class:

public boolean canMove(String to);
The method checks if the piece can move the location (String to ).
String to - The place where we want to move the piece.
The method returns true if the piece can move the location, else returns false.
The method first calculates the rowDistance and colDistance between the piece and location(String to - our parameter). Then, the method defines some conditions valid for Knight pieces. If any condition of these conditions is true then the method checks if targetLocation is not empty and targetLocation piece object has the same color with our piece the methods return false. In the else condition, the method uses getSquareBetween(location,targetLocation) to get all the squares between. Then we use isPathEmpty(Square[ ] s) method if the method returns true, it means piece can move and return true, else return false.

public boolean hasPossibleMoves(String from);



The method determines the knight at the String “from” has any possible moves.
String from - Place of the knight.
The method returns true if the knight has any possible moves, else returns false.
The method gets the target square with the given String from parameter. It initializes canMove and assigns it to false to keep track of any valid move is possible.It defines possible moves array knightMoves. It iterates each move pattern for the pawn. It calculates newRow and newColumn values that fall within the bounds of the chessboard(between 0 and 7). It retrieves the square at the new coordinates.Then,If destination square is empty or if the destination square is not empty and contains a piece of a different color then the target piece. If any of these conditions are met, set canMove to true and exit the loop. Finally, returns the value of canMove.


	For Rook Class:

public boolean canMove(String to);
The method checks if the piece can move the location (String to ).
String to - The place where we want to move the piece.
The method returns true if the piece can move the location, else returns false.
The method first calculates the rowDistance and colDistance between the piece and location(String to - our parameter). Then, the method defines some conditions valid for Rook pieces. If any condition of these conditions is true then the method checks if targetLocation is not empty and targetLocation piece object has the same color with our piece the methods return false. In the else condition, the method uses getSquareBetween(location,targetLocation) to get all the squares between. Then we use isPathEmpty(Square[ ] s) method if the method returns true, it means piece can move and return true, else return false.

public boolean hasPossibleMoves(String from);



The method determines the rook at the String “from” has any possible moves.
String from - Place of the rook.
The method returns true if the rook has any possible moves, else returns false.
The method gets the target square with the given String from parameter. It initializes canMove and assigns it to false to keep track of any valid move is possible.It defines possible moves array rookMoves. It iterates each move pattern for the pawn. It calculates newRow and newColumn values that fall within the bounds of the chessboard(between 0 and 7). It retrieves the square at the new coordinates.Then,If destination square is empty or if the destination square is not empty and contains a piece of a different color then the target piece. If any of these conditions are met, set canMove to true and exit the loop. Finally, returns the value of canMove.


