package com.matthieu.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskAssignment implements Solution {
    public static class TaskPair {
        int t1;
        int t2;
        public TaskPair(int t1, int t2) {
            this.t1=t1;
            this.t2=t2;
        }
        @Override
        public String toString() {
            return "("+t1+","+t2+")";
        }
    }
    public static List<TaskPair> bestPartition(List<Integer> tasks) {
        Collections.sort(tasks);
        List<TaskPair> res = new ArrayList<TaskPair>();
        for (int i=0; i<tasks.size()/2; i++) {
            res.add(new TaskPair(tasks.get(i), tasks.get(tasks.size()-i-1)));
        }
        return res;
    }
    @Override
    public void solveProblem() {
        List<Integer> a = new ArrayList<Integer>();
        int len=20;
        for (int i=0; i<len; i++) {
            a.add((int) (Math.random()*50));
        }
        System.out.println("Best task assignment for "+a);
        System.out.println("Is "+bestPartition(a));
    }
}
