
public class NumberCell extends Cell {

    private int neighborMineCount;

    public NumberCell(int neighborCount) {
        this.neighborMineCount = neighborCount;
    }

    public int getNeighborMineCount() {
        return neighborMineCount;
    }

    @Override
    public String toString() {
        if (neighborMineCount == 0) {
            return " ";
        }
        return String.valueOf(neighborMineCount);
    }
}