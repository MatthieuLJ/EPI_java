package com.matthieu.epi;

import java.util.Arrays;

public class MaxDifferenceGeneralized implements Solution {
    public int maxKPairDifferences(int list[], int k) {
        int k_sum[] = new int[k*2];
        Arrays.fill(k_sum, Integer.MIN_VALUE);
        for (int i=0; i<list.length; i++) {
            int pre_k_sum[] = Arrays.copyOf(k_sum, k_sum.length);
            for (int j=0, sign=-1; j<k_sum.length && j<=i; j++, sign *= -1) {
                int diff = sign * list[i] + (j==0?0:pre_k_sum[j-1]);
                k_sum[j] = Math.max(diff, pre_k_sum[j]);
            }
            System.out.println("i="+i);
            System.out.println("k_sum: "+Arrays.toString(k_sum));
        }

        return k_sum[k*2 - 1];
    }
    @Override
    public void solveProblem() {
        int h[] = new int[30];
        for (int i=0; i<h.length; i++) {
            h[i] = (int) (Math.random()*30);
        }
        
        System.out.println("Maximum pairs of differences "+ Arrays.toString(h)+" is "+ maxKPairDifferences(h, 3));
    }
}
