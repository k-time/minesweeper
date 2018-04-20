import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game {

    private static final Scanner in = new Scanner(System.in);
    private Board board;

    public enum Status {
        FINISHED,
        SUCCESS,
        FAILURE
    }

    public Game() {
        System.out.println("Welcome to Minesweeper!");
        System.out.println("Enter your moves as the letter and number corresponding to each cell (ex. C5).\n");
        createBoard();
    }

    private void createBoard() {
        System.out.print("Please enter a board size between 8 and 26: ");
        Scanner in = new Scanner(System.in);
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

    public Status move() {
        Move move = promptForMove();
        return executeMove(move);
    }

    public void end() {
        System.out.println("Thanks for playing!");
        in.close();
    }

    private Move promptForMove() {
        System.out.println();
        board.print();
        System.out.println();
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

    private Status executeMove(Move move) {
        Cell cell = board.getCell(move.getRow(), move.getCol());
        if (cell.isMine()) {
            System.out.println("You hit a mine!");
            return Status.FAILURE;
        }
        processNumberCell(cell);
        return board.isCompleted() ? Status.FINISHED : Status.SUCCESS;
    }

    private void processNumberCell(Cell cell) {

    }
}
