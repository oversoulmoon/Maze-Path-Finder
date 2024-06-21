package org.example;

import java.util.*;

public class PSSet{
    private TreeSet<PartialSolution> partialSolutionList;

    public int size(){
        return partialSolutionList.size();
    }
    public PSSet(PartialSolution solution){
        partialSolutionList = new TreeSet<>(new ParttialSolutionComparator());
        partialSolutionList.add(solution);
    }
    public PSSet(TreeSet<PartialSolution> list){
        partialSolutionList = list;
    }
    public static PSSet union(PSSet s1, PSSet s2){
        TreeSet<PartialSolution> newData = new TreeSet<>(s1.partialSolutionList);
        s2.partialSolutionList.forEach((partialSolution)->{
            if(!newData.contains(partialSolution) && !s1.contains(partialSolution)){
                newData.add(partialSolution);
            }
        });
        return new PSSet(newData);
    }
    public boolean remove(PartialSolution solution){
//        for(PartialSolution s: partialSolutionList){
//            if (s.equals(solution)) {
//                return partialSolutionList.remove(s);
//            }
//        }
//        return false;
        return partialSolutionList.remove(solution);

    }
    @Override
    public String toString(){
        String result = "";
        for(PartialSolution s: partialSolutionList){
            result += s.toString();
        }
        return result;
    }
    public PartialSolution getBestPartialSolution(){
//        PartialSolution least = partialSolutionList.first();
//        for(PartialSolution s : partialSolutionList){
//            if(s.getRating() < least.getRating()){
//                least = s;
//            }
//        }
//        return least;
        return partialSolutionList.pollFirst();
    }
    static class ParttialSolutionComparator implements Comparator<PartialSolution>{
        @Override
        public int compare(PartialSolution p1, PartialSolution p2 ){
            return 10000*p1.getRating() - 10000*p2.getRating() + hashDiff(p1, p2);

        }

        static int hashDiff(PartialSolution ps1, PartialSolution ps2){
            return (ps1.hashCode() % 1000) -
                    (ps2.hashCode() % 1000);
        }
    }
    public Iterator<PartialSolution> iterator() {
        return partialSolutionList.iterator();
    }
    public boolean contains(PartialSolution s){
        Iterator<PartialSolution> it = partialSolutionList.iterator();
        while(it.hasNext()){
            if(it.next().equals(s)){
                return true;
            }
        }
        return false;
    }
}

