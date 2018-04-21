
public class NumberCell extends Cell {

    private int neighborMineCount;

    public NumberCell(int row, int col, int neighborMineCount) {
        super(row, col);
        this.neighborMineCount = neighborMineCount;
    }

    public int getNeighborMineCount() {
        return neighborMineCount;
    }

    @Override
    public String toString() {
        if (neighborMineCount == 0) {
            return "-";
        }
        return String.valueOf(neighborMineCount);
    }
}