package org.example;

public class Square {
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    private int row,col;
    public Square(int row, int col){
        this.row = row;
        this.col = col;
    }
    @Override
    public boolean equals(Object object){
        return object instanceof Square && ((Square) object).getCol() == this.getCol() && ((Square) object).getRow() == this.getRow();
    }
    @Override
    public String toString(){
        return "[" + getRow() + ", " + getCol() + "]";
    }
}
