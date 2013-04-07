package com.matthieu.epi;

public class TowersOfHanoiToTheRight implements Solution {
    private int nextToTheRight(int from) {
        from++;
        return (from==4)?1:from;
    }

    public void solveHanoi(int height, int from, int to) {
        // works although it's not optimum, sometimes the top ring loops around...
        if (height >1) {
            int intermediary = 6-from-to;

            if (intermediary == nextToTheRight(from)) { // from - blank - to
                solveHanoi(height, from, intermediary);
                solveHanoi(height, intermediary, to);
            }
            else { // from - to - blank
                solveHanoi(height-1, from, intermediary);
                System.out.print(from+" to "+ to+", ");
                solveHanoi(height-1, intermediary, to);
            }
        }
        else {
            do {
                System.out.print(from+" to "+nextToTheRight(from)+", ");
                from = nextToTheRight(from);
            } while (from != to);
        }
    }

    @Override
    public void solveProblem() {
        solveHanoi(3, 1, 2);
        System.out.println();
    }
}
