
public class minimax {


    public static int UTILITY(board brd, char AI) {

        char human = AI == 'x' ? 'o' : 'x';
        if (brd.isTerminal() && brd.getWinner() == AI)
            return 1;
        else if (brd.isTerminal() && brd.getWinner() == human)
            return -1;
        else
            return 0;
    }

    /*
     function MINIMAX_SEARCH(game,state) returns an action
        player <- game.TO_MOVE(state)
        value,move <- MAX_VALUE(game,state)
        return move
     */
    public static String MINIMAX_SEARCH(char AI, board brd){

        Pair<String, Integer> pair = MAX_VALUE(AI, brd);
        return pair.getMove();
    }


    /*
    function MAX_VALUE(game,state) returns a (utility, move) pair
        if game.IS_TERMINAL(state) then return game.UTILITY(state,player),null
        v <- -INFINITY
        for each a in game.ACTIONS(state) do
            v2,a2 <- MIN_VALUE(game, game.RESULT(state,a))
            if v2 > v then
                v, move <- v2, a
        return v, move
     */
    public static Pair<String, Integer> MAX_VALUE(char AI, board brd){

        if(brd.isTerminal())
            return new Pair<String, Integer>(null, UTILITY(brd, AI));

        Pair<String, Integer> pair = new Pair<>(null, Integer.MIN_VALUE);

        for(String theMove : brd.getAvailableSpots()){

           Pair<String, Integer> pair2 = MIN_VALUE(AI, brd.putMark(theMove));

           if(pair2.getValue() > pair.getValue()){
               pair = new Pair<String, Integer>(theMove, pair2.getValue());
           }
        }
        return pair;

    }


    /*
    function MIN_VALUE(game,state) returns a (utility, move) pair
        if game.IS_TERMINAL(state) then return game.UTILITY(state,player),null
        v <- +INFINITY
        for each a in game.ACTIONS(state) do
            v2,a2 <- MAX_VALUE(game, game.RESULT(state,a))
            if v2 < v then
                v, move <- v2, a
        return v, move
     */
    public static Pair<String, Integer> MIN_VALUE(char AI, board brd){
//        System.out.println();
//        System.out.println("TEST in MIN_VALUE!");
//        brd.printBoard();
//        Scanner s = new Scanner(System.in);
//        //s.next();


        if(brd.isTerminal()) {
//            System.out.println("TEST in MIN_VALUE reach Terminal!");
            return new Pair<String, Integer>(null, UTILITY(brd, AI));
        }
        Pair<String, Integer> pair = new Pair<>(null, Integer.MAX_VALUE);

//        for(String theMove : brd.getAvailableSpots()){
//            System.out.println("TEST availableSpots: " + theMove);
//        }

        for(String theMove : brd.getAvailableSpots()){

            Pair<String, Integer> pair2 = MAX_VALUE(AI, brd.putMark(theMove));

//            System.out.println("TEST pair2.getUtility(): " + pair2.getUtility());
//            System.out.println("TEST pair.getUtility(): " + pair.getUtility());

            if(pair2.getValue() < pair.getValue()){
                pair = new Pair<String, Integer>(theMove, pair2.getValue());
            }
        }
        return pair;

    }







}
