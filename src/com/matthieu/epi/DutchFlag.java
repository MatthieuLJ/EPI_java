package com.matthieu.epi;

import java.util.Arrays;

public class DutchFlag implements Solution {

    public void dutchSort(int[] list, int index) {
        // moving everything less than list[i] at the beginning, everything more at the end and then everything equal together

        int index_equal=list.length-1, index_more=list.length-1;
        int value = list[index];

        int i=0;
        while (i<index_equal) {
            if (list[i]<value) {
                i++;
            }
            else if (list[i] == value) {
                list[i] = list[index_equal];
                list[index_equal] = value;
                index_equal--;
            }
            else if (list[i] > value) {
                int tmp = list[i];
                list[i] = list[index_equal];
                list[index_equal] = value;
                index_equal--;
                list[index_more] = tmp;
                index_more--;
            }
        }
    }

    @Override
    public void solveProblem() {
        int array[] = new int[20];
        for (int i=0; i<array.length; i++) {
            array[i] = (int) (Math.random()*10);
        }
        int index = (int) (Math.random()*20);
        System.out.println("Dutch sorting array "+ Arrays.toString(array)+", with index "+index+" (value "+array[index]+")");
        dutchSort(array, index);
        System.out.println("to "+Arrays.toString(array));

    }
}
