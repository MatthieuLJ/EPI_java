package com.matthieu.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KIncreasingDecreasingSort implements Solution {
    public static <T extends Comparable<T>> List<T> mergeKIncreasingDecreasing(T items[]) {
        if (items.length <= 1)
            return Arrays.asList(items);

        int increasing=0;
        List<List<T>> subLists = new ArrayList<List<T>>();
        List<T> currentSubList = new ArrayList<T>();
        currentSubList.add(items[0]);
        for (int index = 1; index < items.length; index++) {
            int newDirection = (items[index].compareTo(items[index-1])<0)?-1:1;
            if (newDirection * increasing == -1) {
                subLists.add(currentSubList);
                currentSubList = new ArrayList<T>();
            }
            increasing = newDirection;
            if (increasing>=0)
                currentSubList.add(items[index]);
            else
                currentSubList.add(0, items[index]);
        }
        subLists.add(currentSubList);
        return MergingSortedFiles.mergeSortedArrays(subLists.toArray(new Iterable[subLists.size()]));
    }

    @Override
    public void solveProblem() {
        Integer h[] = new Integer[50];
        boolean  increasing = true;
        h[0] =  (int) (Math.random()*30);
        for (int i=1; i<h.length; i++) {
            int offset =  (int) (Math.random()*30);
            h[i] = h[i-1] + (increasing?1:-1) * offset;
            if (Math.random() > 0.8) increasing = !increasing;
        }
        System.out.println("Sorting array "+Arrays.toString(h));
        System.out.println("Into          "+mergeKIncreasingDecreasing(h));
    }
}
