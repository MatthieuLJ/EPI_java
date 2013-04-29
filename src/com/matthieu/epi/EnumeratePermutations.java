package com.matthieu.epi;

import java.util.Arrays;
import java.util.List;

public class EnumeratePermutations implements Solution {
    private static void swap(int []array, int i, int j) {
        if (array[i] != array[j]) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static <T> void enumerate(List<T> list) {
        if (list.size() == 0)
            return;
        if (list.size() == 1) {
            System.out.println(list.get(0));
            return;
        }
        int [] indices = new int[list.size()];
        for (int i=0; i<indices.length; i++)
            indices[i]=i;

        while (true) {
            System.out.print("<");
            for (int i : indices) {
                System.out.print(list.get(i));
            }
            System.out.print(">");

            // find the first index from the end of the table such as indices[i]>indices[i+1]
            int i = list.size()-2;
            while ((i>=0) && (indices[i] > indices[i+1]))
                i--;
            if (i<0) {
                System.out.println();
                break;
            }

            // find the first index from the end of the table such a indices[j]>indices[i]
            int j=list.size()-1;
            while (indices[j]<indices[i])
                j--;

            swap(indices, i, j);

            // reverse everything at (i+1) and after
            int t = i+1;
            int s = list.size()-1;
            while (t < s) {
                swap(indices, t, s);
                t++;
                s--;
            }
        }
    }
    @Override
    public void solveProblem() {
        enumerate(Arrays.asList(1,2,3));
    }
}
