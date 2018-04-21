import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game {

    private static final boolean DEBUG = false;
    public static final Scanner in = new Scanner(System.in);
    private Board board;

    public Game() {
        createBoard();
    }

    private void createBoard() {
        System.out.print("Please enter a board size between 8 and 26: ");
        int size = -1;
        while (size < 0) {
            try {
                size = in.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("Invalid input, try again: ");
                in.next();
            }
        }
        this.board = new Board(size);
    }

    public Board.Status move() {
        Move move = promptForMove();
        return executeMove(move);
    }

    public static void end() {
        in.close();
    }

    private Move promptForMove() {
        System.out.println();
        board.print();
        if (DEBUG) {
            board.printHidden();
        }
        System.out.print("Please enter your move: ");
        while (true) {
            try {
                return parseMoveFromInput(in.next());
            }
            catch (NoSuchElementException e) {
                System.out.print("Invalid move, try again (ex. C5): ");
                in.nextLine();
            }
        }
    }

    private Move parseMoveFromInput(String input) throws InputMismatchException {
        if (input == null) {
            throw new InputMismatchException("Invalid move");
        }
        input = input.trim();
        if ((input.length() == 2 || input.length() == 3) && Character.isLetter(input.charAt(0))) {
            int row = Character.toUpperCase(input.charAt(0)) - 'A';
            try {
                int col = Integer.parseInt(input.substring(1)) - 1;
                if (row >= 0 && row < board.size() && col >= 0 && col < board.size()) {
                    return new Move(row, col);
                }
            }
            catch (NumberFormatException e) {
                throw new InputMismatchException("Invalid move");
            }
        }
        throw new InputMismatchException("Invalid move");
    }

    private Board.Status executeMove(Move move) {
        return board.processCell(move.getRow(), move.getCol());
    }
}
