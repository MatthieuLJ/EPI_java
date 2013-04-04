package com.matthieu.epi;

import java.util.Arrays;

public class MaxDifference implements Solution {
    public int maxDifference(int[] heights) {
        int min = heights[0];
        int max_difference = 0;

        for (int height : heights) {
            if (height < min) {
                min = height;
                continue;
            }
            if (height - min > max_difference) {
                max_difference = height - min;
            }
        }
        return max_difference;
    }

    @Override
    public void solveProblem() {
        int h[] = new int[30];
        for (int i=0; i<h.length; i++) {
            h[i] = (int) (Math.random()*30);
        }
        System.out.println("Path with heights "+ Arrays.toString(h)+" has a max difference of "+ maxDifference(h));
    }
}
