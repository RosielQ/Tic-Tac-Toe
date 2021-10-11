CSC 242 
Project1: Tic Tac Toe

How to run our project:
javac *.java
java main


Note:

1.	
After entering the above commands, you will be ask to choose either to play
  1)	Basic 3x3 Tic-tac-toe with an agent that uses MINIMAX (Part 1) ;
  2)	Nine-board Tic-tac-toe with an agent that uses MINIMAX with alpha-beta pruning (Part 2);
  3)	Basic 3x3 Tic-tac-toe with two human player (for debug use); and
  4)	Nine-board Tic-tac-toe with two human player (for debug use).

2.	
Our part 1 performs well. However,  part 2 has some issues that the AI player may lose sometimes (I highly suspect that it      is because the cut-off depth is too shallow, <10. This is just the trade off I have to make between increasing the likelihood to win and saving both time and memory).


How we built our project:

Part 1: 

I first build the class board{ } with class variables char[ ][ ] board and char nextPly. The method of ACTION(s) is getAvailableSpots( ) which return a HashSet<String>. The method of RESULT(s, a) is putMark(String location) which put the mark of nextPly on given position.
Then I build the method humanTohuman3x3(char ply1) to test whether the class board{ } is properly designed.
After that, I wrote the class minimax{ } following the pseudo code in AIMA 5.2.1, Figure 5.3(copied as comments). In order to return a pair of variables in a method, I built the class Pair<T1, T2> with getters.
Finally, I put things together in minimax3x3(char human) to perform part 1.

Part 2:
  
I first build the class grid{ } with class variables board[ ][ ](created in part 1) , char nextPly, and String nextBrd. The method of ACTION(s) is getAvailableBoards( ), which return a HashSet<String>, combining with getAvailableSpots( ). The method of RESULT(s, a) is putMarkGrid(String brdloc, String position) which put the mark of nextPly on given position in given board.
Then I build the method humanTohuman9x9(char ply1) to test whether the class grid{ } is well designed.
After that, I wrote the class HeuristicAlphaBeta{ }. I modified the pseudo code in AIMA 5.2.3, Figure 5.7(pseudo code copied in the comments is the original one) by adding 1) ESTIMATION(grid grd, char AI) ,which return 1 at max level of the tree or -1 at the min level of the tree, and 2) int maxDep to determine which depth to cut off.
Finally, I put things together in heuristicAlphaBeta9x9(char human) to perform part 2.

The whole game will be performed in method excuteGame( ) and it is called in main method with a while loop to play several rounds.

