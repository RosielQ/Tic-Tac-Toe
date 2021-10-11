import java.util.HashSet;

public class grid {
    board[][] grid;
    char nextPly;//the next player
    String nextBrd;//the next board to put mark

    //constructor
    public grid(char next) {
        grid = new board[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = new board(next);
            }
        }
        nextPly = next;
        nextBrd = null;
        System.out.println();
    }


    //print the current grid
    public void printGrid() {
        //(a,b) means the positions in grid
        //(i,j) means the positions in board
        System.out.println("          A                B               C");
        for (int a = 0; a < 3; a++) {
            System.out.println("     a    b    c      a    b    c      a    b    c ");
            for (int i = 0; i < 3; i++) {
                if (i == 1) {
                    System.out.print((a + 1) + " ");
                } else {
                    System.out.print("  ");
                }
                System.out.print((i + 1) + " ");
                for (int b = 0; b < 3; b++) {
                    for (int j = 0; j < 3; j++) {
                        //System.out.print(this.nineboard[a][b].currentBoard3by3[i][j] +" " + "| ");
                        if (j != 2){
                            System.out.print(" " + grid[a][b].board[i][j] );
                            System.out.print(" | ");
                        }else{
                            System.out.print(" " + grid[a][b].board[i][j] + "     ");

                        }

//                        if (j != 2) System.out.print("| ");
//                        else System.out.print("|| ");
                    }
                }
                if(i != 2) {
                    System.out.println();
                    System.out.println("   ----+----+-----  ----+----+-----  ----+----+-----");
                }else{
                    System.out.println();
                }
            }
            System.out.println();
        }
    }

    //copy the grid
    public grid copyGrid(){
        grid copy = new grid(nextPly);//copy grid.nextPly
        for(int a=0;a<3;a++){
            for(int i=0;i<3;i++){
                for(int b=0;b<3;b++){
                    for(int j=0;j<3;j++){
                       copy.grid[a][b].board[i][j] = this.grid[a][b].board[i][j];//copy grid.board.position
                    }
                }
            }
        }
        for(int a=0;a<3;a++){
            for(int b=0;b<3;b++){
                copy.grid[a][b].nextPly = this.grid[a][b].nextPly;//copy grid.board.nextPly
            }

        }

        copy.nextBrd = this.nextBrd;//copy grid.nextBrd

        return copy;
    }

    //check if any board in the grid has met the condition: formRow()
    public boolean anyBoardFormRow(){
        for(int a=0;a<3;a++){
            for(int b=0;b<3;b++){
                if(grid[a][b].formRow())
                    return true;
            }
        }
        return false;
    }

    //check which board in the grid has met the condition: formRow()
    public String whichBoardFormRow(){
        for(int a=0;a<3;a++){
            for(int b=0;b<3;b++){
                if(grid[a][b].formRow())
                    return "ABC".charAt(b) + "123".substring(a,a+1);
            }
        }
        return null;
    }

    //check if any board in the grid has met the condition: formColumn()
    public boolean anyBoardFormColumn(){
        for(int a=0;a<3;a++){
            for(int b=0;b<3;b++){
                if(grid[a][b].formColumn())
                    return true;
            }
        }
        return false;
    }

    //check which board in the grid has met the condition: formColumn()
    public String whichBoardFormColumn(){
        for(int a=0;a<3;a++){
            for(int b=0;b<3;b++){
                if(grid[a][b].formColumn())
                    return "ABC".charAt(b) + "123".substring(a,a+1);
            }
        }
        return null;
    }

    //check if any board in the grid has met the condition: formDiagonal()
    public boolean anyBoardFormDiagonal(){
        for(int a=0;a<3;a++){
            for(int b=0;b<3;b++){
                if(grid[a][b].formDiagonal())
                    return true;
            }
        }
        return false;
    }

    //check which board in the grid has met the condition: formDiagonal()
    public String whichBoardFormDiagonal(){
        for(int a=0;a<3;a++){
            for(int b=0;b<3;b++){
                if(grid[a][b].formDiagonal())
                    return "ABC".charAt(b) + "123".substring(a,a+1);
            }
        }
        return null;
    }


    //check if any board in the grid has met the condition: isFull
    public boolean anyBoardisFull(){
        for(int a=0;a<3;a++){
            for(int b=0;b<3;b++){
                if(grid[a][b].isFull())
                    return true;
            }
        }
        return false;
    }

    //check which board in the grid is full and return the board location
    public String whichBoardisFull(){//use after anyBoardisFull
        for(int a=0;a<3;a++){
            for(int b=0;b<3;b++){
                if(grid[a][b].isFull())
                    return "ABC".charAt(b) + "123".substring(a,a+1);
            }
        }
        return null;
    }

    //
    public boolean gridIsTerminal(){
        if(anyBoardFormRow() || anyBoardFormColumn() || anyBoardFormDiagonal() || anyBoardisFull())
            return true;

        return false;
    }




    //
    public char gridGetWinner(){//use after gridIsTerminal

        if(anyBoardFormRow()) {
            String brdloc = whichBoardFormRow();
            char boardFirst = brdloc.charAt(0);
            char boardSecond = brdloc.charAt(1);
            int a = (boardSecond=='1') ? 0 : (boardSecond == '2' ? 1 : 2);
            int b = (boardFirst=='A') ? 0 : (boardFirst == 'B' ? 1 : 2);
            for (int i = 0; i < 3; i++) {
                if (grid[a][b].board[i][0] == 'x' && grid[a][b].board[i][1] == 'x' && grid[a][b].board[i][2] == 'x')
                    return 'x';
            }
            for (int i = 0; i < 3; i++) {
                if (grid[a][b].board[i][0] == 'o' && grid[a][b].board[i][1] == 'o' && grid[a][b].board[i][2] == 'o')
                    return 'o';
            }
        }

        if (anyBoardFormColumn()) {
            String brdloc = whichBoardFormColumn();
            char boardFirst = brdloc.charAt(0);
            char boardSecond = brdloc.charAt(1);
            int a = (boardSecond=='1') ? 0 : (boardSecond == '2' ? 1 : 2);
            int b = (boardFirst=='A') ? 0 : (boardFirst == 'B' ? 1 : 2);
            for (int j = 0; j < 3; j++) {
                if (grid[a][b].board[0][j] == 'x' && grid[a][b].board[1][j] == 'x' && grid[a][b].board[2][j] == 'x')
                    return 'x';
            }
            for (int j = 0; j < 3; j++) {
                if (grid[a][b].board[0][j] == 'o' && grid[a][b].board[1][j] == 'o' && grid[a][b].board[2][j] == 'o')
                    return 'o';
            }
        }

        String brdloc = whichBoardFormDiagonal();
        char boardFirst = brdloc.charAt(0);
        char boardSecond = brdloc.charAt(1);
        int a = (boardSecond=='1') ? 0 : (boardSecond == '2' ? 1 : 2);
        int b = (boardFirst=='A') ? 0 : (boardFirst == 'B' ? 1 : 2);
        if (grid[a][b].board[0][0] == 'x' && grid[a][b].board[1][1] == 'x' && grid[a][b].board[2][2] == 'x')
            return 'x';
        if (grid[a][b].board[0][0] == 'o' && grid[a][b].board[1][1] == 'o' && grid[a][b].board[2][2] == 'o')
            return 'o';
        if (grid[a][b].board[0][2] == 'x' && grid[a][b].board[1][1] == 'x' && grid[a][b].board[2][0] == 'x')
            return 'x';
        if (grid[a][b].board[0][2] == 'o' && grid[a][b].board[1][1] == 'o' && grid[a][b].board[2][0] == 'o')
            return 'o';

        return 't';

    }

    public HashSet<String> getAvailableBoards(){
        HashSet<String> boards = new HashSet<>();
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                if(!grid[a][b].isFull()){
                    boards.add("ABC".charAt(b) + "123".substring(a, a+1));
                }
            }
        }
        return boards;
    }

    //!!! before use this method, check whether this board is full
    public grid putMarkGrid(String brdloc, String position){
        char boardFirst = brdloc.charAt(0);
        char boardSecond = brdloc.charAt(1);
        char positionFirst = position.charAt(0);
        char positionSecond = position.charAt(1);

        int a = (boardSecond=='1') ? 0 : (boardSecond == '2' ? 1 : 2);
        int b = (boardFirst=='A') ? 0 : (boardFirst == 'B' ? 1 : 2);
        int i = (positionSecond=='1') ? 0 : (positionSecond == '2' ? 1 : 2);
        int j = (positionFirst=='a') ? 0 : (positionFirst == 'b' ? 1 : 2);

        grid copy = copyGrid();
        copy.grid[a][b].board[i][j] = nextPly;//put the mark
        copy.nextPly = (nextPly=='x') ? 'o' : 'x';//update grid.nextPly没问题
        for(int k=0;k<3;k++){
            for(int l=0;l<3;l++){
                copy.grid[k][l].nextPly = copy.nextPly;//update board.nextPly in each board没问题
            }
        }
        String posToBrd;
        if(positionFirst == 'a')
            posToBrd = "A" + Character.toString(positionSecond);
        else if(positionFirst == 'b')
            posToBrd = "B" + Character.toString(positionSecond);
        else
            posToBrd = "C" + Character.toString(positionSecond);

        if (getAvailableBoards().contains(posToBrd)) {
            copy.nextBrd = posToBrd;//update grid.nextBrd
        }else{
            System.out.println("Next board to put mark: Board[" + j + "][" + i + "] is full.");
            System.out.println("You can choose any available board to put the mark.");
            copy.nextBrd = null;
        }

        return copy;
    }

}
