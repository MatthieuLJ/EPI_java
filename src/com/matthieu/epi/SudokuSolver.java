package com.matthieu.epi;

import java.util.Arrays;

public class SudokuSolver implements Solution {
    private boolean solveTable(int[][] table) {
        int r0=0,c0=0;

        // find a hole in the table
        find_hole:
        for (r0=0; r0<9; r0++) {
            for (c0=0; c0<9; c0++) {
                if (table[r0][c0] == 0)
                    break find_hole;
            }
        }

        if (r0<9) {
            int []possibleValues = new int[10];
            for (int t=0; t<9; t++) {
                possibleValues[table[t][c0]]++;
                possibleValues[table[r0][t]]++;
                int s = ((int)(r0/3))*3+(c0/3);
                possibleValues[table[((int)(s/3))*3+(t/3)][(s%3)*3+(t%3)]]++;
            }
            for (int i=1; i<10; i++) {
                if (possibleValues[i]!=0)
                    continue;
                table[r0][c0]=i;
                if (solveTable(table))
                    return true;
            }
            table[r0][c0]=0;
            return false;
        }
        else {
            return SudokuChecker.checkTable(table);
        }
    }

    @Override
    public void solveProblem() {
        int [][] partial = {{5,3,0,0,7,0,0,0,0},{6,0,0,1,9,5,0,0,0},{0,9,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,3},{4,0,0,8,0,3,0,0,1},{7,0,0,0,2,0,0,0,6},
                {0,6,0,0,0,0,2,8,0},{0,0,0,4,1,9,0,0,5},{0,0,0,0,8,0,0,7,9}};
        solveTable(partial);
        System.out.println("Checking the partial table returns "+Arrays.deepToString(partial));
    }
}
