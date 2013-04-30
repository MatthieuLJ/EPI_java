package com.matthieu.epi;

import java.util.Arrays;

public class LongestNonDecreasing implements Solution {
    public static int[] longestNonDecreasing(int [] array) {
        int [] indices = new int[array.length];
        int [] predecessors = new int[array.length];

        Arrays.fill(indices, -1);
        int max_length=0;

        for (int i=0; i<array.length; i++) {
            if ((max_length==0) || (array[indices[max_length-1]] <= array[i])) {
                indices[max_length] = i;
                predecessors[i] = (max_length==0)?-1:indices[max_length-1];
                max_length++;
                continue;
            }

            if (array[indices[0]] > array[i]) {
                indices[0]=i;
                predecessors[i] = -1;
                continue;
            }

            int lo=0;
            int hi=max_length;
            int mid;
            while (lo < hi) { // find first index k where array[i] < array[indices[k]]
                mid = lo + (hi-lo)/2;
                if (array[indices[mid]] <= array[i]) {
                    lo=mid+1;
                } else {
                    hi = mid;
                }
            }
            predecessors[i] = indices[hi-1];
            indices[hi] = i;
        }

        int[] res = new int[max_length];
        int index=indices[max_length-1];
        for (int i=max_length-1; i>=0; i--) {
            res[i] = array[index];
            index = predecessors[index];
        }

        return res;
    }
    @Override
    public void solveProblem() {
        int h[] = new int[10];
        for (int i=0; i<h.length; i++) {
            h[i] = (int) (Math.random()*30);
        }
        System.out.println("Longest non decreasing array from "+Arrays.toString(h)+"\n is "+Arrays.toString(longestNonDecreasing(h)));
    }
}
