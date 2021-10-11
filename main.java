import java.util.Scanner;

public class main {
    public static void main (String[] args){

        game g = new game();
        Scanner s = new Scanner(System.in);
        String quit = "a";
        while(!quit.equals("quit")) {
            g.executeGame();
            System.out.println("Enter quit to leave the game.");
            quit = s.nextLine();
        }

    }
}
