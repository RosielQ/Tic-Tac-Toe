import java.util.HashSet;

class board {
    char[][] board;      //'x' OR 'o' OR ' '
    char nextPly;       //'x' OR 'o'
    //constructor
    public board(char next){
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
       nextPly = next;
    }


    //print the current board
    public void printBoard() {
        System.out.println("The current board is ");
        System.out.println("   a     b     c");
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.print(i+1);
            for (int j = 0; j < 3; j++) {
                System.out.printf("%3s", board[i][j]);
                if(j != 2)
                    System.out.print("  |");
            }
            System.out.println();
            if(i != 2)
                System.out.println("  ----+-----+----");
        }
        System.out.println("nextPly: " + nextPly);

    }

    public board copyState(){
        board copy = new board(nextPly);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++)
                copy.board[i][j] = this.board[i][j];
        }
        return copy;
    }

    //check if the given mark form a row
    public boolean formRow(){
        for(int i=0;i<3;i++){
            if(board[i][0]=='x' && board[i][1]=='x' && board[i][2]=='x')
                return true;
        }

        for(int i=0;i<3;i++){
            if(board[i][0]=='o' && board[i][1]=='o' && board[i][2]=='o')
                return true;
        }

        return false;
    }

    //check if the given mark form a column
    public boolean formColumn(){

        for(int j=0;j<3;j++){
            if(board[0][j]=='x' && board[1][j]=='x' && board[2][j]=='x')
            return true;
        }

        for(int j=0;j<3;j++){
            if(board[0][j]=='o' && board[1][j]=='o' && board[2][j]=='o')
                return true;
        }

        return false;
    }

    //check if the same marks form a diagonal
    public boolean formDiagonal(){

        if(board[0][0]=='x' && board[1][1]=='x' && board[2][2] == 'x')
            return true;

        if(board[0][0]=='o' && board[1][1]=='o' && board[2][2] == 'o')
            return true;

        if(board[0][2]=='x' && board[1][1]=='x' && board[2][0]=='x')
            return true;

        if(board[0][2]=='o' && board[1][1]=='o' && board[2][0]=='o')
            return true;

        return false;
    }

    //check whether the board is full(tie)
    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;

    }

    public boolean isTerminal(){
        if(formRow() || formColumn() || formDiagonal() || isFull())
            return true;

        return false;
    }

    public char getWinner(){//use after isTerminal

        if(formRow()) {
            for (int i = 0; i < 3; i++) {
                if (board[i][0] == 'x' && board[i][1] == 'x' && board[i][2] == 'x')
                    return 'x';
            }
            for (int i = 0; i < 3; i++) {
                if (board[i][0] == 'o' && board[i][1] == 'o' && board[i][2] == 'o')
                    return 'o';
            }
        }

            if (formColumn()) {
                for (int j = 0; j < 3; j++) {
                    if (board[0][j] == 'x' && board[1][j] == 'x' && board[2][j] == 'x')
                        return 'x';
                }
                for (int j = 0; j < 3; j++) {
                    if (board[0][j] == 'o' && board[1][j] == 'o' && board[2][j] == 'o')
                        return 'o';
                }
            }

            if (board[0][0] == 'x' && board[1][1] == 'x' && board[2][2] == 'x')
                return 'x';
            if (board[0][0] == 'o' && board[1][1] == 'o' && board[2][2] == 'o')
                return 'o';
            if (board[0][2] == 'x' && board[1][1] == 'x' && board[2][0] == 'x')
                return 'x';
            if (board[0][2] == 'o' && board[1][1] == 'o' && board[2][0] == 'o')
                return 'o';

            return 't';
        }


    //ACTION(s): get all the available positions of putting a mark
    public HashSet<String> getAvailableSpots(){
        HashSet<String> spots = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] == ' '){
                    spots.add("abc".charAt(j) + "123".substring(i, i+1));
                }
            }
        }
        return spots;
    }

    //RESULT(s,a): put (mark) on (location) of the board and return the new board
    public board putMark(String location){
        char first = location.charAt(0);
        char second = location.charAt(1);
        int row = (second=='1') ? 0 : (second == '2' ? 1 : 2);
        int col = (first=='a') ? 0 : (first == 'b' ? 1 : 2);
        board copy = copyState();
        copy.board[row][col] = nextPly;
        copy.nextPly = nextPly == 'x' ? 'o' : 'x';

        return copy;
    }


    public static void main(String[] args){
        board brd = new board('x');
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                brd.board[i][j] = 'x';
            }
        }
        brd.printBoard();
    }


}
