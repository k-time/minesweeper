import java.util.Scanner;

public class GameRunner {

    public static void main(String[] args) {
        boolean playAgain = true;
        while (playAgain) {
            Game game = new Game();
            Board.Status status = null;
            while (status != Board.Status.FINISHED) {
                status = game.move();
                if (status == Board.Status.FAILURE) {
                    break;
                }
            }
            System.out.print("Play again? (y/n): ");
            String answer = Game.in.next().toLowerCase();
            playAgain = answer.equals("y") || answer.equals("yes");
            Game.in.nextLine();
            game.end();
        }
    }
}
