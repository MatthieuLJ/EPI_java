package com.matthieu.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestConvex implements Solution {
    // a[i] < (a[i-1] + a[i+1])/2 <=> a[i]-a[i-1] < a[i+1]-a[i]
    public static int[] longestConvex(int [] array) {
        // Build array B where B[x][y] = a[x]-a[y] if x>y, 0 otherwise
        int[][] B = new int[array.length][array.length];
        for (int i=0; i<array.length; i++) {
            for (int j=i+1; j<array.length; j++) {
                B[j][i] = array[j] - array[i];
            }
        }

        // find the longest increasing streak in B
        // streak is picking an index and then continue on the row with that index
        // goal is to make the longest increasing sequence
        List<Integer> bestIndices = new ArrayList<Integer>();
        for (int i=0; i<B.length; i++) {
            List<Integer> tmp = longestStreakIndices(B, Integer.MIN_VALUE, i);
            if (tmp.size() +1> bestIndices.size()) {
                tmp.add(0, i);
                bestIndices = tmp;
            }
        }

        int []res = new int[bestIndices.size()];
        for (int i=0; i<res.length; i++) {
            res[i] = array[bestIndices.get(i)];
        }
        return res;
    }

    private static List<Integer> longestStreakIndices(int [][] B, int minimum, int lastIndex) {
        List<Integer> res = new ArrayList<Integer>();

        for (int i=lastIndex+1; i<B.length; i++) {
            if (B[i][lastIndex] <= minimum)
                continue;
            List<Integer> tmp = longestStreakIndices(B, B[i][lastIndex], i);
            if (tmp.size() +1 > res.size()) {
                tmp.add(0, i);
                res = tmp;
            }
        }
        return res;

    }
    @Override
    public void solveProblem() {
        int h[] = new int[20];
        for (int i=0; i<h.length; i++) {
            h[i] = (int) (Math.random()*30);
        }
        System.out.println("Longest convex sequence for "+Arrays.toString(h)+"\n is "+Arrays.toString(longestConvex(h)));
    }
}
