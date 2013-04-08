package com.matthieu.epi;

import java.util.Arrays;

public class SearchForAIeI implements Solution {
    public static int findAiei(int []array) {
        int []copy = Arrays.copyOf(array, array.length);
        for (int i=0; i<copy.length; i++) {
            copy[i] = copy[i]-i;
        }
        return Arrays.binarySearch(copy, 0);
    }

    @Override
    public void solveProblem() {
        int h[] = new int[50];
        h[0] =  (int) (Math.random()*30)-30;
        for (int i=1; i<h.length; i++) {
            int offset =  (int) (Math.random()*2);
            h[i] = h[i-1] + offset +1;
        }

        System.out.println("In array "+Arrays.toString(h)+", find A[i]=i at "+findAiei(h));
    }
}
