import java.util.Scanner;

public class HeuristicAlphaBeta {


    public static int UTILITY(grid grd, char AI) {
        char human = AI == 'x' ? 'o' : 'x';
        if (grd.gridIsTerminal() && grd.gridGetWinner() == AI)
            return 1;
        else if (grd.gridIsTerminal() && grd.gridGetWinner() == human)
            return -1;
        else
            return 0;
    }

    //if maximizer‘s turn => 1
    //if minimizer‘s turn => -1
    public static int ESTIMATION(grid grd, char AI){
        if(grd.nextPly == AI){
            return 1;
        }else{
            return -1;
        }
    }



    /*
   function ALPHA-BETA-SEARCH(game,state)returns an action
       play <- game.TO-MOVE(state)
       value,move <- MAX-VALUE(game,state,-infinity,+infinity)
       return move
    */
    public static String ALPHA_BETA_SEARCH(grid grd, char AI, int maxDep){
        if(AI == 'x'){
            Pair<String, Integer> pair = ALPHA_BETA_MAX_VALUE(grd, Integer.MIN_VALUE, Integer.MAX_VALUE, AI, maxDep,0);
            return pair.getMove();
        }
        else{
            Pair<String, Integer> pair = ALPHA_BETA_MAX_VALUE(grd, Integer.MIN_VALUE, Integer.MAX_VALUE, AI, maxDep,0);
            return pair.getMove();
        }
    }




    /*
    function MAX-VALUE(game,state,alpha,beta) returns a (utility,move) pair
        if game.IS-TERMINAL(state) then return game.UTILITY(state,player),null
        v <- -infinity
        for each a in game.ACTIONS(state) do
            v2,a2 <- MIN-VALUE(game,game.RESULT(state,a),alpha,beta)
            if v2 > v then
                v,move <- v2,a
                alpha <- MAX(alpha,v)
            if v>= beta then return v,move
        return v,move
     */
    public static Pair<String, Integer> ALPHA_BETA_MAX_VALUE(grid grd, int alpha, int beta, char AI, int maxDep, int currDep) {

        if (grd.gridIsTerminal()) {
            return new Pair<String, Integer>(null, UTILITY(grd, AI));
        }

        if(currDep == maxDep){
            return new Pair<String, Integer>(null, ESTIMATION(grd, AI));
        }

        Pair<String, Integer> pair = new Pair<>(null, Integer.MIN_VALUE);

        char boardFirst = grd.nextBrd.charAt(0);
        char boardSecond = grd.nextBrd.charAt(1);
        int a = (boardSecond=='1') ? 0 : (boardSecond == '2' ? 1 : 2);
        int b = (boardFirst=='A') ? 0 : (boardFirst == 'B' ? 1 : 2);

//        for (int a=0;a<3;a++){
//            for(int b=0;b<3;b++){
                for(String theMove : grd.grid[a][b].getAvailableSpots()){

                    Pair<String, Integer> pair2 = ALPHA_BETA_MIN_VALUE(grd.putMarkGrid(grd.nextBrd, theMove), alpha, beta, AI, maxDep,currDep+1);

                    if (pair2.getValue() > pair.getValue()) {
                        pair = new Pair<String, Integer>(theMove, pair2.getValue());
                        alpha = Math.max(alpha, pair.getValue());
                    }

                    if (pair.getValue() >= beta)
                        return pair;
                }
//            }
//        }
        return pair;
    }




    /*
    function MIN-VALUE(game,state,alpha,beta) returns a (utility,move) pair
        if game.IS-TERMINAL(state) then return game.UTILITY(state,player),null
        v <- +infinity
        for each a in game.ACTIONS(state) do
            v2,a2 <- MAX-VALUE(game,game.RESULT(state,a),alpha,beta)
            if v2 < v then
                v,move <- v2,a
                beta <- MIN(beta,v)
            if v <= alpha then return v,move
        return v,move
     */

    public static Pair<String, Integer> ALPHA_BETA_MIN_VALUE(grid grd, int alpha, int beta, char AI, int maxDep, int currDep){
        if (grd.gridIsTerminal()) {
            return new Pair<String, Integer>(null, UTILITY(grd, AI));
        }

        if(currDep == maxDep){
            return new Pair<String, Integer>(null, ESTIMATION(grd, AI));
        }

        Pair<String, Integer> pair = new Pair<>(null, Integer.MAX_VALUE);

        char boardFirst = grd.nextBrd.charAt(0);
        char boardSecond = grd.nextBrd.charAt(1);
        int a = (boardSecond=='1') ? 0 : (boardSecond == '2' ? 1 : 2);
        int b = (boardFirst=='A') ? 0 : (boardFirst == 'B' ? 1 : 2);

//        for (int a=0;a<3;a++){
//            for(int b=0;b<3;b++){
                for(String theMove : grd.grid[a][b].getAvailableSpots()){
                    Pair<String, Integer> pair2 = ALPHA_BETA_MAX_VALUE(grd.putMarkGrid(grd.nextBrd, theMove), alpha, beta, AI, maxDep,currDep+1);

                    if (pair2.getValue() < pair.getValue()) {
                        pair = new Pair<String, Integer>(theMove, pair2.getValue());
                        beta = Math.min(beta, pair.getValue());
                    }

                    if (pair.getValue() <= alpha)
                        return pair;
                }
//            }
//        }
        return pair;
    }

}


