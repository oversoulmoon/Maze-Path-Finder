package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Maze m = new Maze(4, "[[1,1],[1,2],[2,3],[3,3],[3,1]]","[0,3]","[2,1]");
        ArrayList<Maze> mazes = new ArrayList<>();
        mazes.add( new Maze(2,"[[0,0]]", "[1,0]","[0,1]"));
        mazes.add(new Maze(4, "[[1,1],[2,3],[0,3],[2,1],[0,2]]", "[0,0]", "[1,3]"));
        mazes.add(new Maze(10, //Maze written by Alex Herman
                "[[0,1], [0,3], [1,1], [1,6], [1,7], [2,1], [2,3], [2,4], [2,5], [2,9], [3,3], [3,6], [3,7], [3,8], [4,1], [5,1], [5,2], [5,3], [5,4], [5,6], [5,7], [5,8], [6,4], [6,9], [7,0], [7,1], [7,2], [7,4], [7,6], [7,7], [8,4], [8,8], [9,4], [9,6], [9,7]]"
                ,"[0,0]","[2,6]"));
        mazes.add(new Maze(4, "[[0, 1], [1, 1], [1, 2]", "[0, 0]", "[0, 2]"));
        mazes.add(new Maze(6, "[[1,0], [1,1], [1,2], [1,3], [1,4], [1,4], [2,4], [3,4], [4,4]]", "[2,0]", "[0,0]"));

        String s1 = "[[8,8],[8,7],[8,6],[8,5],[8,4],[8,3],[8,2],[8,1],[7,1],[6,1],[5,1],[4,1],[3,1],[2,1],[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7],[2,7],[3,7],[4,7],[5,7],[6,7],[6,6],[6,5],[6,4],[6,3],[5,3],[4,3],[3,3],[3,4],[3,5],[4,5]]";
        String s2 = "[[9,0],[9,1],[9,2],[9,3],[9,4],[9,5],[0,1],[0,2],[0,3],[2,1],[2,2],[2,3],[2,4],[2,5],[2,6],[1,5],[1,6],[3,5],[4,5],[4,1],[5,1],[6,1],[7,1],[5,2],[7,2],[5,3],[6,3],[7,3],[5,4],[5,5],[5,6],[5,7],[5,8],[4,8],[3,8],[2,8],[1,8],[8,9],[7,4],[7,5],[7,6],[7,7],[8,7]]";
        mazes.add( new Maze(9, s1,"[8,0]","[4,4]"));
        mazes.add( new Maze(10, s2,"[0,0]","[9,9]"));
        mazes.add(m);
        mazes.forEach(maze -> {
            System.out.println(solveMaze(maze,100));
        });
    }
    public static List<Direction> solveMaze(Maze m, int maxRuntime) {
        long start = System.currentTimeMillis();
        PSSet set = new PSSet(new PartialSolution(m));
        PartialSolution solution = set.getBestPartialSolution();

        int time =(int)((start-System.currentTimeMillis())/1000);
        while(!solution.isSolution(m) && time < maxRuntime){
            set.remove(solution);
            set = PSSet.union(set,solution.expandPartialSolution(m));
            solution = set.getBestPartialSolution();
            time =(int)((System.currentTimeMillis()-start)/1000);
        }
        if(time >= maxRuntime){
            throw new RuntimeException("Ran out of time...");
        }
        return solution.getMoves();

//        long begin = System.currentTimeMillis(); // This will start the time
//        boolean status = true; //Using this condition to make the while occur until
//
//        PSSet currentPSSet = new PSSet(new PartialSolution(maze));
//        PartialSolution bestPath = null;
//
//        while(status){
//            bestPath =currentPSSet.getBestPartialSolution();
//            if (bestPath.isSolution(maze)) { //If we have found the best path
//                break;
//            } else if(System.currentTimeMillis()-begin>maxRuntime){
//                break; //if we run out of time just break and return what ever we have right now
//            }else{
//                PSSet newPSSet = bestPath.expandPartialSolution(maze); // Creating more partial solution
//                currentPSSet = PSSet.union(currentPSSet,newPSSet); //Joining two sets we have
//                currentPSSet.remove(bestPath);
//            }
//
//        }
//
//        return bestPath.getMoves();
    }
}