import java.util.Scanner;

public class GameRunner {

    public static void main(String[] args) {
        System.out.println(
                "************************** Welcome to Minesweeper! **************************\n" +
                "Enter your moves as the letter and number corresponding to each cell (ex. C5).\n");
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
            playAgain = answer.startsWith("y");
        }
        System.out.println("Thanks for playing!");
        Game.end();
    }
}
