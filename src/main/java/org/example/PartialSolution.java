package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

public class PartialSolution {

    private ArrayList<Direction> moves;
    private Path path;
    private int rating;
    private Maze m;
    public static int count = 0;
    public PartialSolution(Maze maze){
        moves = new ArrayList<>();
        path = new Path(maze);
        rating = path.evalPath(maze);
    }
    @Override
    public boolean equals(Object obj){
        return obj instanceof PartialSolution && ((PartialSolution) obj).getPath().equals(this.getPath()) && this.rating == ((PartialSolution) obj).getRating();
    }
    private PartialSolution(PartialSolution partialSolution, Maze m, Direction d){
        this.moves = new ArrayList<>();
        moves.addAll(partialSolution.getMoves());
        moves.add(d);
        this.path = new Path(partialSolution.getPath());
        path.move(m,d);
        this.rating = this.path.evalPath(m);
    }
    public PartialSolution(ArrayList<Direction> moves, Path path, Maze maze){
        this.moves = moves;
        this.path = path;
        this.rating = this.path.evalPath(maze);
        this.m = maze;
    }
    public PSSet expandPartialSolution(Maze maze){
        count++;
        TreeSet<PartialSolution> solutions = new TreeSet<>(new PSSet.ParttialSolutionComparator());
        for(Direction d : Direction.values()){
            try {
                PartialSolution s = new PartialSolution(this, maze,d);
                solutions.add(s);
            }catch(UnableToMoveException ignored){
            }
        }
        return new PSSet(solutions);
    }
    public ArrayList<Direction> getMoves(){
        return moves;
    }
    public Path getPath(){
        return path;
    }
    public boolean isSolution(Maze maze){
        return path.isSolutionPath(maze);
    }
    public int getRating() {
        return rating;
    }
    @Override
    public String toString(){
        return path.toString() + " | rating: " + rating + "\n";
    }

}
