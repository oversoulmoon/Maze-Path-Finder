package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Path {
    private List<Square> path;
    public Path(Maze m){
        this.path = new ArrayList<>();
        this.path.add(m.getStart());
    }
    public Path(Path path){
        List<Square> cpy = new ArrayList<>();
        cpy.addAll(path.path);
        this.path = cpy;
    }
    public int evalPath(Maze maze){
        return (maze.evalSquare(path.get(path.size()-1)) ) + path.size() ;
    }
    public Path move(Maze maze, Direction direction) throws UnableToMoveException{
        try{
            this.push(maze.squareAdjacent(path.get(path.size()-1), direction));
        }catch (NoSuchElementException e){
            throw new UnableToMoveException();
        }
        if(isNotRetracing())
            return this;
        else
            throw new UnableToMoveException();
    }
    public boolean isNotRetracing(){
        Square lastMove = path.get(path.size()-1);
        for(int i = 0 ; i < path.size()-1;i++){
            if(lastMove.equals(path.get(i))){
                return false;
            }
        }
        return true;
    }
    public int size(){
        return path.size();
    }
    public boolean isSolutionPath(Maze m){
        return (m.isEndPoint(path.get(path.size()-1)));
    }
    public void push(Square s){
        path.add(s);
    }
    public void pop(){
        path.remove(path.size()-1);
    }
    public String getPathString(){
        String result = "";
        for(Square s : path){
            result += s.toString() + " | ";
        }
        return result;
    }
    @Override
    public String toString(){
        String result = "";
        for(Square s : path){
            result += s.toString() + " => ";
        }
        result += "end";
        return result;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Path && this.path.size() == ((Path) obj).path.size()){
            for(int i = 0 ; i < path.size();i++){
                if(!path.get(i).equals(((Path) obj).path.get(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
class UnableToMoveException extends RuntimeException{
    public UnableToMoveException(){
        super("Attemping to retrace or moving into a blocked square is illegal");
    }
}
