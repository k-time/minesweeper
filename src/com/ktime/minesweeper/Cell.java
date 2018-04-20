package com.ktime.minesweeper;

public abstract class Cell {

    private boolean hidden;

    public Cell() {
        this.hidden = true;
    }

    public void onClick() {
        hidden = true;
    }

    public boolean isMine() {
        return this instanceof MineCell;
    }
}
