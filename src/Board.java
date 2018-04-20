
public class Board {

    private static final int MIN_SIZE = 8;
    private static final int MAX_SIZE = 26;
    private Cell[][] cells;

    public Board(int size) {
        if (size < MIN_SIZE) {
            System.out.printf("Board is too small, defaulting to size %dx%d...\n", MIN_SIZE, MIN_SIZE);
            size = MIN_SIZE;
        }
        else if (size > MAX_SIZE) {
            System.out.printf("Board is too large, defaulting to size %dx%d...\n", MAX_SIZE, MAX_SIZE);
            size = MAX_SIZE;
        }
        this.cells = new Cell[size][size];
        fillCells();
    }

    private void fillCells() {
        int numCells = (int) Math.pow(cells.length, 2);
        int numBombs = numCells / 10;
        while (numBombs > 0) {
            int row = (int) (Math.random() * cells.length);
            int col = (int) (Math.random() * cells.length);
            if (cells[row][col] == null) {
                cells[row][col] = new MineCell();
                numBombs--;
            }
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (cells[i][j] == null) {
                    int cellValue = countSurroundingBombs(i, j);
                    cells[i][j] = new NumberCell(cellValue);
                }
            }
        }
        System.out.println("Finished creating board.");

    }

    private int countSurroundingBombs(int row, int col) {
        int count = 0;
        // above
        if (row > 0 && cells[row-1][col] != null && cells[row-1][col].isMine()) {
            count++;
        }
        // below
        if (row < cells.length-1 && cells[row+1][col] != null && cells[row+1][col].isMine()) {
            count++;
        }
        // left
        if (col > 0 && cells[row][col-1] != null && cells[row][col-1].isMine()) {
            count++;
        }
        // right
        if (col < cells.length-1 && cells[row][col+1] != null && cells[row][col+1].isMine()) {
            count++;
        }
        return count;

    }

    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("   ");
        for (int i = 1; i <= cells.length; i++) {
            if (i < 10) {
                sb.append(" ");
            }
            sb.append(i);
        }
        sb.append("\n    ");
        for (int i = 0; i < cells.length; i++) {
            sb.append("--");
        }
        sb.append("\n");
        for (int i = 0; i < cells.length; i++) {
            sb.append((char)('A' + i));
            sb.append(" |");
            for (int j = 0; j < cells.length; j++) {
                sb.append(" ");
                sb.append(cells[i][j].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
