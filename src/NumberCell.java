
public class NumberCell extends Cell {

    private int bombCount;

    public NumberCell(int neighborCount) {
        this.bombCount = neighborCount;
    }

    public int getBombCount() {
        return bombCount;
    }

    @Override
    public String toString() {
        if (bombCount == 0) {
            return " ";
        }
        return String.valueOf(bombCount);
    }
}