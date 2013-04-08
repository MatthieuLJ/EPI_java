package com.matthieu.epi;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ApproximateSort implements Solution {
    @Override
    public void solveProblem() {
        int almost_sorted[] = new int[] { 2,1,4,5,3,7,6,10,8,9,13,11,12,14,16,15,17,18,20,19};

        System.out.println("Resorting array "+ Arrays.toString(almost_sorted));
        PriorityQueue<Integer> buffer = new PriorityQueue<Integer>();
        int index;
        for (index=0; index<3; index++) {
            buffer.add(almost_sorted[index]);
        }
        while (buffer.size() > 0) {
            if (index < almost_sorted.length) {
                buffer.add(almost_sorted[index++]);
            }
            System.out.print(" "+buffer.poll());
        }
        System.out.println();

    }
}
