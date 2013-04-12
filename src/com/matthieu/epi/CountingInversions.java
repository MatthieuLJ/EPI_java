package com.matthieu.epi;

import java.util.Arrays;

public class CountingInversions implements Solution {

    private static int merge(int []array, int start, int mid, int end) {
        int res=0;
        int index_lower=start, index_higher=mid, index_sorted=0;
        int sorted_array[] = new int[end-start];

        while ((index_lower<mid) && (index_higher<end)) {
            if (array[index_lower] <= array[index_higher]) {
                sorted_array[index_sorted++] = array[index_lower++];
            } else {
                res+=(mid-index_lower);
                sorted_array[index_sorted++] = array[index_higher++];
            }
        }

        if (index_lower < mid)
            System.arraycopy(array, index_lower, sorted_array, index_sorted, mid-index_lower);
        else
            System.arraycopy(array, index_higher, sorted_array, index_sorted, end-index_higher);

        System.arraycopy(sorted_array, 0, array, start, end - start);

        return res;
    }

    public static int numInversions(int []array, int start, int end) {
        if (end - start <= 1)
            return 0;

        int mid = start + (end -start)/2;
        return numInversions(array, start, mid) + numInversions(array,mid, end) + merge(array, start, mid, end);
    }

    @Override
    public void solveProblem() {
        int length = 5;
        int h[] = new int[length];
        for (int i=0; i<h.length; i++) {
            h[i] = (int) (Math.random()*30);
        }

        System.out.println("In array "+Arrays.toString(h));
        System.out.println("There are "+numInversions(h, 0, length)+" inversions");
    }
}
