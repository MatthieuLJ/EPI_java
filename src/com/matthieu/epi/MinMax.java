package com.matthieu.epi;

import java.util.Arrays;

public class MinMax implements Solution {
    public static class MinMaxResult {
        int min;
        int max;
    }
    public static MinMaxResult minMax(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int index;

        if (array.length %2 == 1) {
            min = array[0];
            max = array[0];
            index = 1;
        }
        else {
            index = 0;
        }
        for ( ; index<array.length; index += 2) {
            if (array[index] < array[index+1]) {
                if (array[index] < min) min = array[index];
                if (array[index+1] > max) max = array[index+1];
            } else {
                if (array[index+1] < min) min = array[index+1];
                if (array[index] > max) max = array[index];
            }
        }
        MinMaxResult res = new MinMaxResult();
        res.min = min;
        res.max = max;
        return res;
    }

    @Override
    public void solveProblem() {
        int h[] = new int[25];
        h[0] =  (int) (Math.random()*300);
        for (int i=1; i<h.length; i++) {
            h[i] = (int) (Math.random()*30);
        }
        MinMaxResult res = minMax(h);
        System.out.println("Min is "+res.min+", max is "+res.max+" from array "+ Arrays.toString(h));
    }
}
