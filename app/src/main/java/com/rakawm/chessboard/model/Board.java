package com.rakawm.chessboard.model;

/**
 * Created by raka on 06/03/2015.
 */
public class Board {

    private String color;
    private int row;
    private String column;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
