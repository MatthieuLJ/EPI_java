package com.matthieu.epi;

import java.util.Arrays;

public class EightQueens implements Solution {
    public static void placeQueen(int [][]board, int column) {
        if (column>=8) {
            System.out.println("One solution:");
            for (int i=0; i<8; i++) {
                for (int j=0; j<8; j++) {
                    if (board[j][i] == -1)
                        System.out.println("One queen at "+i+","+j);
                }
            }
            return;
        }

        for (int row=0; row<8; row++) {
            if (board[column][row] == 0) {
                moveQueen(board, row, column, true);
                placeQueen(board, column+1);
                moveQueen(board,row, column, false);
            }
        }
    }

    private static void moveQueen(int[][] board, int row, int column, boolean add) {
        int change = add?1:-1;

        for (int i=0; i<8; i++) {
            board[i][row]+=change;
            board[column][i]+=change;
            if ((row-i>=0) && (column-i>=0))
                board[column-i][row-i]+=change;
            if ((row-i>=0) && (column+i<8))
                board[column+i][row-i]+=change;
            if ((row+i<8) && (column-i>=0))
                board[column-i][row+i]+=change;
            if ((row+i<8) && (column+i<8))
                board[column+i][row+i]+=change;
        }
        if (add)
            board[column][row] = -1;
        else
            board[column][row] = 0;
    }

    public static void eightQueens() {
        int[][] board = new int[8][8];
        for (int i=0; i<board.length; i++)
            Arrays.fill(board[i], 0);

        placeQueen(board, 0);
    }

    @Override
    public void solveProblem() {
        eightQueens();
    }
}
