package com.matthieu.epi;

import java.util.Arrays;

public class KLargest implements Solution {
    private static void swap(int i, int j, int[] array) {
        int tmp = array[i];
        array[i]=array[j];
        array[j]=tmp;
    }

    public static int kLargest(int []array, int k, int index_min, int index_max) {
        int pivot_index = (int) (Math.random()*(index_max - index_min)) + index_min;
        int pivot = array[pivot_index];

        int num_equal=0, num_larger=0;
        for (int i = index_min; i<index_max; i++) {
            int item = array[i];
            if (item == pivot) num_equal++;
            else if (item > pivot) num_larger++;
        }
        boolean is_larger;
        if (num_larger >= k) is_larger=true;
        else if (num_larger + num_equal >= k) return pivot;
        else is_larger=false;

        int index_of_interest=0;
        for (int i=0; i<array.length; i++) {
            if (((is_larger) && (array[i] > pivot)) ||
                    ((!is_larger) && (array[i] < pivot))) {
                swap(i, index_of_interest++, array);
            }
        }

        if (is_larger)
            return kLargest(array, k, 0, num_larger);
        else
            return kLargest(array,k-num_larger-num_equal, 0, index_max - index_min - num_equal - num_larger);

    }

    @Override
    public void solveProblem() {
        int length = 50;
        int h[] = new int[length];
        for (int i=0; i<h.length; i++) {
            h[i] = (int) (Math.random()*30);
        }
        System.out.println("Array is "+Arrays.toString(h));
        System.out.println("10th element is "+kLargest(h, 10, 0, h.length));
        Arrays.sort(h);
        System.out.println(" =? "+h[h.length-10]);

    }
}
