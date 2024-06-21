package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Maze {
    public char data[][];
    public Maze(int size, String blocked, String start, String end){
        data = new char[size][size];
        for(int x = 0 ; x < size;x++){
            for(int y = 0 ; y < size; y++){
                data[x][y] = 'C';
            }
        }
        Square starts = parseSquare(start);
        data[starts.getCol()][starts.getRow()] = 'S';
        Square ends =parseSquare(end);
        data[ends.getCol()][ends.getRow()] = 'E';
        for(Square s: parseSquareList(blocked)){
            data[s.getCol()][s.getRow()] = 'B';
        }
    }
    public static ArrayList<Square> parseSquareList(String data){
        if(!data.equals("[]")) {
            data = data.substring(data.indexOf("[") + 1, data.lastIndexOf("]")).replace(" ", "");
//        data = data.replace(" ","");
            String[] newData = data.split("],");
            ArrayList<Square> result = new ArrayList<>();
            for (String s : newData) {
                if(s.contains(","))
                    result.add(parseSquare(s));
            }
            return result;
        }
        return new ArrayList<Square>();
    }
    public static Square parseSquare(String data){
        data = data.replace(" ", "").replace("[","").replace("]","");
        String[] nData = data.split(",");
        return new Square( Integer.parseInt(nData[0]),Integer.parseInt(nData[1]));
    }
    public int getSize(){
        return data.length;
    }
    public Square getStart(){
        Square result = null;
        for(int x = 0 ; x < data.length;x++){
            for(int y = 0 ; y < data.length; y++){
                if(data[x][y] == 'S'){
                    result = new Square(y,x);
                }
            }
        }
        return result;

    }
    public Square getEnd(){
        Square result = null;
        for(int x = 0 ; x < data.length;x++){
            for(int y = 0 ; y < data.length; y++){
                if(data[x][y] == 'E'){
                    result = new Square(y,x);
                }
            }
        }
        return result;

    }
    public List<Square> getBlocked(){
        List<Square> blocked = new ArrayList<>();
        for(int x = 0 ; x < data.length;x++){
            for(int y = 0 ; y < data.length; y++){
                if(data[x][y] == 'B'){
                    blocked.add(new Square(y,x));
                }
            }
        }
        return blocked;
    }
    public Square squareAdjacent(Square s, Direction d){
        Square sr = null;
        switch(d){
            case UP:
                sr= new Square(s.getRow()-1,s.getCol());
                break;
            case DOWN:
                sr= new Square( s.getRow()+1, s.getCol());
                break;
            case LEFT:
                sr= new Square(s.getRow(), s.getCol()-1);
                break;
            case RIGHT:
                sr= new Square( s.getRow(), s.getCol()+1);
                break;
        }
        if (sr.getCol() >= data.length || sr.getRow() >= data.length || sr.getRow() < 0|| sr.getCol() < 0 || isBlockedSquare(sr)){
            throw new NoSuchElementException();
        }
        return sr;
    }
    public boolean isBlockedSquare(Square square){
        return this.getBlocked().contains(square);
    }
    public boolean isEndPoint(Square square){
        return this.getEnd().equals(square);
    }
    public int evalSquare(Square square){
        int distance = distance(square, getEnd());
        int blocked = countBlockedSquares(square, getEnd());
        if (2 * blocked > distance) {
            return distance + (int) Math.pow((2 * blocked) - distance, 3);
        }
        return distance;
    }
    public int countBlockedSquares(Square square1, Square square2) {
        int rStart = Math.min(square1.getRow(), square2.getRow());
        int cStart = Math.min(square1.getCol(), square2.getCol());

        int cEnd = Math.max(square1.getCol(), square2.getCol());
        int rEnd = Math.max(square1.getRow(), square2.getRow());

        int i = 0;
        for (Square square : getBlocked()) {
            int row = square.getRow();
            int col = square.getCol();
            if (row >= rStart && row <= rEnd && col >= cStart && col <= cEnd) {
                i++;
            }
        }
        return i;
    }
    public int distance(Square s1, Square s2){
        return Math.abs(s1.getCol()-s2.getCol()) + Math.abs(s1.getRow() - s2.getRow());
    }
    @Override
    public String toString(){
        String result = "";
        for(int y = 0; y < getSize();y++){
            for(int x = 0 ; x < getSize();x++){
                String status = "_";
                Square s = new Square(y,x);
                if (isBlockedSquare(s)) {
                    status = "B";
                }else if (isEndPoint(s)){
                    status = "E";
                }else if(getStart().equals(s)){
                    status = "S";
                }
                result += status + " ";
            }
            result += "\n";
        }
        return result;
    }
}
