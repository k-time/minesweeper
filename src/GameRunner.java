
public class GameRunner {

    public static void main(String[] args) {
        Game game = new Game();
        Game.Status status = Game.Status.SUCCESS;
        while (status != Game.Status.FINISHED) {
            status = game.move();
            if (status == Game.Status.FAILURE) {
                break;
            }
        }
        game.end();
    }
}
