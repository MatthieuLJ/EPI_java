package com.matthieu.epi;

import java.util.Arrays;

public class SudokuChecker implements Solution {
    public static boolean checkTable(int[][] table) {
        bitArrayChecker checker = new bitArrayChecker();

        // check all rows
        for (int r=0; r<9; r++) {
            checker.clear();
            for (int i=0; i<9; i++)
                checker.addDigit(table[r][i]);
            if (!checker.checkOK())
                return false;
        }

        // check all columns
        for (int c=0; c<9; c++) {
            checker.clear();
            for (int i=0; i<9; i++)
                checker.addDigit(table[i][c]);
            if (!checker.checkOK())
                return false;
        }

        // check all 3x3 squares
        for (int s=0; s<9; s++) {
            checker.clear();
            for (int i=0; i<9; i++) {
                int row = ((int)(s/3))*3+(i/3);
                int col = (s%3)*3+(i%3);
                checker.addDigit(table[row][col]);
            }
            if (!checker.checkOK())
                return false;
        }

        return true;
    }

    private static class bitArrayChecker {
        int bitField[] = new int[10];
        public void addDigit(int d) {
            if (d==0) return;
            bitField[d]++;
        }
        public boolean checkOK() {
            for (int i=0; i<9; i++) {
                if (bitField[i]>1)
                    return false;
            }
            return true;
        }
        public void clear() {
            Arrays.fill(bitField, 0);
        }
    }

    @Override
    public void solveProblem() {
        int [][] partial = {{5,3,0,0,7,0,0,0,0},{6,0,0,1,9,5,0,0,0},{0,9,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,3},{4,0,0,8,0,3,0,0,1},{7,0,0,0,2,0,0,0,6},
                {0,6,0,0,0,0,2,8,0},{0,0,0,4,1,9,0,0,5},{0,0,0,0,8,0,0,7,9}};
        System.out.println("Checking the partial table returns "+checkTable(partial));

        int [][] complete = {{5,3,4,6,7,8,9,1,2},{6,7,2,1,9,5,3,4,8},{1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},{4,2,6,8,5,3,7,9,1},{9,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},{2,8,7,4,1,9,6,3,5},{3,4,5,2,8,6,1,7,9}};
        System.out.println("Checking the complete table returns "+checkTable(complete));
    }
}
