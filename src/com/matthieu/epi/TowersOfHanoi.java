package com.matthieu.epi;

public class TowersOfHanoi implements Solution{
    public void solveHanoi(int height, int from, int to) {
        if (height == 1) {
            System.out.print(from+" to "+to+", ");
            return;
        }

        int intermediary = 6-from-to;

        solveHanoi(height-1, from, intermediary);
        System.out.print(from+" to "+ to+", ");
        solveHanoi(height-1, intermediary, to);
    }
    @Override
    public void solveProblem() {
        solveHanoi(4, 1, 2);
        System.out.println();
    }
}
