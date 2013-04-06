package com.matthieu.epi;

public class TowersOfHanoiAlwaysUsingP3 implements Solution {
    public void solveHanoi(int height, int from, int to) {
        if (height >1) {
            solveHanoi(height-1, from, to);
            System.out.print(from+" to 3, ");
            solveHanoi(height-1, to, from);
            System.out.print("3 to "+to+", ");
            solveHanoi(height-1, from, to);
        }
        else
            System.out.print(from+" to 3, 3 to "+to+", ");
    }

    @Override
    public void solveProblem() {
        solveHanoi(3, 1, 2);
        System.out.println();
    }
}
