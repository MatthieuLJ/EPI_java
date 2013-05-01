package com.matthieu.epi;

import java.util.Arrays;
import java.util.LinkedList;

public class LongestAlternating implements Solution {
    public static int[] longestAlternating(int [] array) {
        LinkedList<Integer> indices = new LinkedList<Integer>();
        indices.add(0);
        int currentIndex=1;
        while (currentIndex<array.length) {
            while ((currentIndex<array.length) &&
                    (((indices.size() % 2 == 0) && (array[currentIndex] >= array[indices.getLast()]))
                            || ((indices.size() % 2 == 1) && (array[currentIndex] <= array[indices.getLast()])))) {
                indices.pollLast();
                indices.offerLast(currentIndex);
                currentIndex++;
            }
            if (currentIndex<array.length) {
                indices.offerLast(currentIndex);
                currentIndex++;
            }
        }
        int []res = new int[indices.size()];
        for (int i=0; i<indices.size(); i++) {
            res[i] = array[indices.get(i)];
        }
        return res;
    }
    @Override
    public void solveProblem() {
        int h[] = new int[20];
        for (int i=0; i<h.length; i++) {
            h[i] = (int) (Math.random()*30);
        }
        System.out.println("The longest alternating sequence in "+ Arrays.toString(h));
        System.out.println("is "+Arrays.toString(longestAlternating(h)));
    }
}
