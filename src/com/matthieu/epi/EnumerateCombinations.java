package com.matthieu.epi;

import java.util.Arrays;
import java.util.List;

public class EnumerateCombinations implements Solution {
    public static <T> void enumerate(int k, List<T> list) {
        if (list.size() < k)
            return;

        int items[] = new int[k];
        for (int i=0; i<items.length; i++) {
            items[i] = i;
        }

        while (items[k-1] < list.size()) {
            System.out.print("<");
            for (int i : items) {
                System.out.print(list.get(i));
            }
            System.out.print(">");

            // find the smallest index i in items such as items[i+1] > items[i]+1
            // reset everything below to the starting point
            int i=0;
            while ((i<items.length-1) && (items[i]==items[i+1]-1)) {
                items[i] = i;
                i++;
            }
            items[i]++;
        }
        System.out.println();

    }

    @Override
    public void solveProblem() {
        enumerate(3, Arrays.asList(0,1,2,3,4,5));
    }
}
