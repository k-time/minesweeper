import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Board {

    private static final int MIN_SIZE = 8;
    private static final int MAX_SIZE = 26;
    private Cell[][] cells;

    public enum Status {
        FINISHED,
        SUCCESS,
        FAILURE
    }

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
        populateCells();
    }

    private void populateCells() {
        populateMineCells();
        populateNumberCells();
    }

    private void populateMineCells() {
        int mineCount = (this.size() * this.size()) / 6; // ~20% of cells will have mines
        while (mineCount > 0) {
            int row = (int) (Math.random() * cells.length);
            int col = (int) (Math.random() * cells.length);
            if (cells[row][col] == null) {
                cells[row][col] = new MineCell(row, col);
                mineCount--;
            }
        }
    }

    private void populateNumberCells() {
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells.length; col++) {
                if (cells[row][col] == null) {
                    int cellValue = countSurroundingBombs(row, col);
                    cells[row][col] = new NumberCell(row, col, cellValue);
                }
            }
        }
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

    public boolean isCompleted() {
        for (int row = 0; row < this.size(); row++) {
            for (int col = 0; col < this.size(); col++) {
                Cell cell = this.getCell(row, col);
                if (!cell.isMine() && cell.isHidden()) {
                    return false;
                }
            }
        }
        return true;
    }

    public int size() {
        return cells.length;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public Board.Status processCell(int row, int col) {
        Cell cell = this.getCell(row, col);
        cell.onClick();
        if (cell.isMine()) {
            System.out.println("You hit a mine! You lose.");
            return Board.Status.FAILURE;
        }
        processNumberCell((NumberCell) cell);
        if (this.isCompleted()) {
            return Board.Status.FINISHED;
        }
        return Board.Status.SUCCESS;
    }

    private void processNumberCell(NumberCell cell) {
        Queue<NumberCell> queue = new LinkedList<>();
        queue.add(cell);
        while (!queue.isEmpty()) {
            NumberCell cur = queue.remove();
            cur.onClick();
            if (cur.getNeighborMineCount() == 0) {
                for (Cell neighbor : this.getAdjacentCells(cur)) {
                    if (!neighbor.isMine() && neighbor.isHidden()
                            && ((NumberCell) neighbor).getNeighborMineCount() == 0) {
                        queue.add((NumberCell) neighbor);
                    }
                }
            }
        }
    }

    private List<Cell> getAdjacentCells(Cell cell) {
        List<Cell> list = new ArrayList<>();
        int row = cell.getRow();
        int col = cell.getCol();
        if (row > 0) {
            list.add(this.getCell(row-1, col));
        }
        if (row < this.size()-1) {
            list.add(this.getCell(row+1, col));
        }
        if (col > 0) {
            list.add(this.getCell(row, col-1));
        }
        if (col < this.size()-1) {
            list.add(this.getCell(row, col+1));
        }
        return list;
    }

    public void print() {
        System.out.println(this.toString(false));
    }

    public void printHidden() {
        System.out.println(this.toString(true));
    }

    public String toString(boolean printHidden) {
        StringBuilder sb = new StringBuilder("   ");
        for (int i = 1; i <= cells.length; i++) {
            if (i < 10) {
                sb.append(" ");
            }
            sb.append(i);
        }
        sb.append("\n   ");
        for (int i = 0; i < cells.length; i++) {
            sb.append("--");
        }
        sb.append("\n");
        for (int i = 0; i < cells.length; i++) {
            sb.append((char)('A' + i));
            sb.append(" |");
            for (int j = 0; j < cells.length; j++) {
                sb.append(" ");
                Cell cell = this.getCell(i, j);
                sb.append(cell.isHidden() && !printHidden ? " " : cell.toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
