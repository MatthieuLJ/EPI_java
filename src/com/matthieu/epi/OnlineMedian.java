package com.matthieu.epi;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class OnlineMedian implements Solution {
    PriorityQueue<Integer> top = new PriorityQueue<Integer>();
    PriorityQueue<Integer> bottom = new PriorityQueue<Integer>(5, Collections.reverseOrder());

    private void add(int i) {
        top.add(i);
        if (top.size() > bottom.size()) {
            bottom.add(top.poll());
        }
        if ((top.size() > 0) && (bottom.size() > 0))
            while (top.peek() < bottom.peek()) {
                bottom.add(top.poll());
                top.add(bottom.poll());
            }
    }

    private int current_median() {
        if (top.size() > 0)
            return bottom.peek();
        else
            return -1;
    }
    @Override
    public void solveProblem() {
        int length = 21;
        int save[] = new int[length];
        for (int i=0; i<length; i++) {
            int new_number = (int) (Math.random()*50);
            add(new_number);
            save[i] = new_number;
        }
        Arrays.sort(save);
        System.out.println("For array "+Arrays.toString(save));
        System.out.println("Found median "+current_median()+" =? "+save[10]);

    }
}
