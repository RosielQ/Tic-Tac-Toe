import java.util.Scanner;

public class game {

    public void executeGame() {
        System.out.println("Tic-Tac-Toe by Qianqian Wei");
        System.out.println("Choose your game:");
        System.out.println("1. Basic 3x3 Tic-tac-toe with an agent that uses MINIMAX");
        System.out.println("2. Nine-board Tic-tac-toe with an agent that uses MINIMAX with alpha-beta pruning");
        System.out.println("3. Basic 3x3 Tic-tac-toe with two human player (for debug use)");
        System.out.println("4. Nine-board Tic-tac-toe with two human player (for debug use)");
        System.out.println("Your choice?");
        Scanner scn = new Scanner(System.in);
        int whichGame = scn.nextInt();
        while (whichGame != 1 && whichGame != 2) {
            System.out.println("Please enter either 1 or 2");
            whichGame = scn.nextInt();
        }

        System.out.println("Do you want to play X or O?");
        char humanMark = scn.next().charAt(0);
        while (humanMark != 'x' && humanMark != 'o') {
            System.out.println("Please enter either x or o");
            humanMark = scn.next().charAt(0);
        }


        if (whichGame == 1) {
            minimax3x3(humanMark);
        }else if (whichGame == 2){
            heuristicAlphaBeta9x9(humanMark);
        }else if(whichGame == 3){
            humanTohuman3x3('x');
        }else{
            humanTohuman9x9('x');
        }
    }

    /*
    @param user     the mark that the human choose to play
     */
    public void humanTohuman3x3(char ply1) {
        Scanner scn = new Scanner(System.in);
        board brd = new board(ply1);

        while (!brd.isTerminal()) {
            brd.printBoard();
            System.out.println("Your move [col row]?");
            String pos = scn.nextLine();
            if (!brd.getAvailableSpots().contains(pos)) {
                System.out.println("Please enter a blank location");
                pos = scn.nextLine();
            }
            brd = brd.putMark(pos);

        }
        brd.printBoard();
        System.out.println("Winner is: " + brd.getWinner());
        System.out.println("Game end");
    }

    public void humanTohuman9x9(char ply1) {
        Scanner scn = new Scanner(System.in);
        grid grd = new grid(ply1);

        while (!grd.gridIsTerminal()) {
            grd.printGrid();
            if(grd.nextBrd == null){
                System.out.println("Your board [col row]?");
                grd.nextBrd = scn.nextLine();
                while (!grd.getAvailableBoards().contains(grd.nextBrd)) {
                    System.out.println("Please enter a board that is available");
                    grd.nextBrd = scn.nextLine();
                }
            }

            System.out.println("Your board to put mark is " + grd.nextBrd + ".");
            System.out.println("Next player is " + grd.nextPly);
            char boardFirst = grd.nextBrd.charAt(0);
            char boardSecond = grd.nextBrd.charAt(1);
            int a = (boardSecond=='1') ? 0 : (boardSecond == '2' ? 1 : 2);
            int b = (boardFirst=='A') ? 0 : (boardFirst == 'B' ? 1 : 2);
            System.out.println("Your move [col row]?");
            String pos = scn.nextLine();
            while (!grd.grid[a][b].getAvailableSpots().contains(pos)) {
                System.out.println("Please enter a position that is available at board " + grd.nextBrd);
                pos = scn.nextLine();
            }
            grd = grd.putMarkGrid(grd.nextBrd, pos);

        }
        grd.printGrid();
        System.out.println("Winner is: " + grd.gridGetWinner());
        System.out.println("Game end");
    }

    public void minimax3x3(char human) {
        Scanner scn = new Scanner(System.in);
        char AI = (human=='x') ? 'o' : 'x';
        board brd = new board('x');

        while (!brd.isTerminal()) {
            brd.printBoard();

            if(human == 'x'){
                if(brd.nextPly == 'x'){
                    System.out.println("Your move [col row]?");
                    String pos = scn.nextLine();
                    while (!brd.getAvailableSpots().contains(pos)) {
                        System.out.println("Please enter a blank location");
                        pos = scn.nextLine();
                    }
                    brd = brd.putMark(pos);
                }else{//next player is AI 'o'
                    board copy = brd.copyState();
                    String pos = minimax.MINIMAX_SEARCH(AI,copy);
                    brd = brd.putMark(pos);
                }
            }

            if(human == 'o'){
                if(brd.nextPly == 'o'){
                    System.out.println("Your move [col row]?");
                    String pos = scn.nextLine();
                    while (!brd.getAvailableSpots().contains(pos)) {
                        System.out.println("Please enter a blank location");
                        pos = scn.nextLine();
                    }
                    brd = brd.putMark(pos);
                }else{//next player is AI 'x'
                    board copy = brd.copyState();
                    String pos = minimax.MINIMAX_SEARCH(AI,copy);
                    brd = brd.putMark(pos);
                }
            }
        }
        brd.printBoard();
        System.out.println("Winner is: " + brd.getWinner());
        System.out.println("Game end");

    }

    public void heuristicAlphaBeta9x9(char human){
        Scanner scn = new Scanner(System.in);
        char AI = (human=='x') ? 'o' : 'x';
        grid grd = new grid('x');

        int round = 0;//

        while (!grd.gridIsTerminal()) {
            grd.printGrid();

//            if(grd.nextBrd == null){
//                System.out.println("Your board [col row]?");
//                grd.nextBrd = scn.nextLine();
//                while (!grd.getAvailableBoards().contains(grd.nextBrd)) {
//                    System.out.println("Please enter a board that is available");
//                    grd.nextBrd = scn.nextLine();
//                }
//            }

            if(human == 'x'){
                if(grd.nextPly == 'x'){

                    if(grd.nextBrd == null){
                        System.out.println("Your board [col row]?");
                        grd.nextBrd = scn.nextLine();
                        while (!grd.getAvailableBoards().contains(grd.nextBrd)) {
                            System.out.println("Please enter a board that is available");
                            grd.nextBrd = scn.nextLine();
                        }
                    }

                    System.out.println("Your board to put mark is " + grd.nextBrd);
                    System.out.println("Next player is x");

                    char boardFirst = grd.nextBrd.charAt(0);
                    char boardSecond = grd.nextBrd.charAt(1);
                    int a = (boardSecond=='1') ? 0 : (boardSecond == '2' ? 1 : 2);
                    int b = (boardFirst=='A') ? 0 : (boardFirst == 'B' ? 1 : 2);

                    System.out.println("Your move [col row]?");
                    String pos = scn.nextLine();
                    while (!grd.grid[a][b].getAvailableSpots().contains(pos)) {
                        System.out.println("Please enter a position that is available at board " + grd.nextBrd);
                        pos = scn.nextLine();
                    }
                    grd = grd.putMarkGrid(grd.nextBrd, pos);

                }else{//next player is AI 'o'
                    grid copy = grd.copyGrid();
                    String pos = HeuristicAlphaBeta.ALPHA_BETA_SEARCH(grd, AI, 9);
                    grd = grd.putMarkGrid(grd.nextBrd, pos);
                }
            }

            if(human == 'o'){
                if(grd.nextPly == 'o'){

                    if(grd.nextBrd == null){
                        System.out.println("Your board [col row]?");
                        grd.nextBrd = scn.nextLine();
                        while (!grd.getAvailableBoards().contains(grd.nextBrd)) {
                            System.out.println("Please enter a board that is available");
                            grd.nextBrd = scn.nextLine();
                        }
                    }

                    System.out.println("Your board to put mark is " + grd.nextBrd);
                    System.out.println("Next player is o");

                    char boardFirst = grd.nextBrd.charAt(0);
                    char boardSecond = grd.nextBrd.charAt(1);
                    int a = (boardSecond=='1') ? 0 : (boardSecond == '2' ? 1 : 2);
                    int b = (boardFirst=='A') ? 0 : (boardFirst == 'B' ? 1 : 2);

                    System.out.println("Your move [col row]?");
                    String pos = scn.nextLine();
                    while (!grd.grid[a][b].getAvailableSpots().contains(pos)) {
                        System.out.println("Please enter a position that is available at board " + grd.nextBrd);
                        pos = scn.nextLine();
                    }
                    grd = grd.putMarkGrid(grd.nextBrd, pos);

                }else{//next player is AI 'x'
                    if(round == 0){
                        grd.nextBrd = "B2";
                        round++;
                    }

                    grid copy = grd.copyGrid();
                    String pos = HeuristicAlphaBeta.ALPHA_BETA_SEARCH(grd, AI, 10);
                    grd = grd.putMarkGrid(grd.nextBrd, pos);
                }
            }
        }
        grd.printGrid();
        System.out.println("Winner is: " + grd.gridGetWinner());
        System.out.println("Game end");

    }


}
