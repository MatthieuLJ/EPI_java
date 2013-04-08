package com.matthieu.epi;

import java.util.Arrays;

public class FirstElementLargerThanK implements Solution {
    public static int firstItemLargerThanK(int []array, int k) {
        int l=0, r=array.length-1;

        if (array[r] < k)
            return -1;

        while (l < r) {
            int index = l + (r-l)/2;
            if (array[index] < k) {
                l = index+1;
            } else {
                r = index;
            }
        }
        return array[l];

    }

    @Override
    public void solveProblem() {
        int h[] = new int[50];
        for (int i=0; i<h.length; i++) {
            h[i] = (int) (Math.random()*50);
        }
        Arrays.sort(h);
        int searchFor = (int) (Math.random()*50);
        System.out.println("Array is "+Arrays.toString(h));
        System.out.println("Search for "+searchFor+" gives "+firstItemLargerThanK(h, searchFor)+" as first item larger");
        System.out.println("Search for 60 gives "+firstItemLargerThanK(h, 60)+" as first item larger");
    }
}
