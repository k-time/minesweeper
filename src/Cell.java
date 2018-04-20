
public abstract class Cell {

    private int row;
    private int col;
    private boolean hidden;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.hidden = true;
    }

    public void onClick() {
        hidden = false;
    }

    public boolean isMine() {
        return this instanceof MineCell;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isHidden() {
        return hidden;
    }
}
