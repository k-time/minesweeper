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
        int mineCount = (this.size() * this.size()) / 6; // ~1/6 of cells will have mines
        while (mineCount > 0) {
            int row = (int) (Math.random() * this.size());
            int col = (int) (Math.random() * this.size());
            if (this.getCell(row, col) == null) {
                this.setCell(row, col, new MineCell(row, col));
                mineCount--;
            }
        }
    }

    private void populateNumberCells() {
        for (int row = 0; row < this.size(); row++) {
            for (int col = 0; col < this.size(); col++) {
                if (this.getCell(row, col) == null) {
                    int cellValue = countSurroundingBombs(row, col);
                    this.setCell(row, col, new NumberCell(row, col, cellValue));
                }
            }
        }
    }

    private int countSurroundingBombs(int row, int col) {
        int count = 0;

        int rowTop = row == 0 ? row : row-1;
        int rowBottom = row == this.size()-1 ? row : row+1;
        int colLeft = col == 0 ? col : col-1;
        int colRight = col == this.size()-1 ? col : col+1;

        for (int i = rowTop; i <= rowBottom; i++) {
            for (int j = colLeft; j <= colRight; j++) {
                Cell cell = this.getCell(i, j);
                if (cell != null && cell.isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    public void flagCell(Move move) {
        // TODO: Implement ability to flag a cell without clicking it

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

    private void setCell(int row, int col, Cell cell) {
        cells[row][col] = cell;
    }

    public Board.Status processCell(int row, int col) {
        Cell cell = this.getCell(row, col);
        if (cell.isHidden()) {
            cell.onClick();
            if (cell.isMine()) {
                System.out.println();
                this.printHidden();
                System.out.println("You hit a mine! You lose.");
                return Board.Status.FAILURE;
            }
            processNumberCell((NumberCell) cell);
            if (this.isCompleted()) {
                System.out.println();
                this.printHidden();
                System.out.println("Congratulations, you won!");
                return Board.Status.FINISHED;
            }
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
                    if (!neighbor.isMine() && neighbor.isHidden()) {
                        NumberCell nc = (NumberCell) neighbor;
                        if (nc.getNeighborMineCount() == 0) {
                            queue.add(nc);
                        }
                        else {
                            nc.onClick();
                        }
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
        for (int i = 1; i <= this.size(); i++) {
            if (i < 10) {
                sb.append(" ");
            }
            sb.append(i);
        }
        sb.append("\n   ");
        for (int i = 0; i < this.size(); i++) {
            sb.append("--");
        }
        sb.append("\n");
        for (int i = 0; i < this.size(); i++) {
            sb.append((char)('A' + i));
            sb.append(" |");
            for (int j = 0; j < this.size(); j++) {
                sb.append(" ");
                Cell cell = this.getCell(i, j);
                sb.append(cell.isHidden() && !printHidden ? " " : cell.toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
