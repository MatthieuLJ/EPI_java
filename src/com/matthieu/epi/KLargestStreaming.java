package com.matthieu.epi;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KLargestStreaming implements Solution {
    public static class KLargestReceiver<T extends Comparable<T>> {
        PriorityQueue<T> heap;
        int k;

        public KLargestReceiver(int k) {
            heap  = new PriorityQueue<T>(2*k+1);
            this.k = k;
        }

        private void skim() {
            while (heap.size() > k)
                heap.poll();
        }

        public void addItem(T item) {
            heap.add(item);
            if (heap.size() == 2*k) {
                skim();
            }
        }

        public T currentKthElement() {
            skim();
            if (heap.size() < k)
                return null;

            return heap.peek();
        }
    }
    @Override
    public void solveProblem() {
        int length = 21;
        KLargestReceiver<Integer> solver = new KLargestReceiver<Integer>(5);
        int save[] = new int[length];
        for (int i=0; i<length; i++) {
            int new_number = (int) (Math.random()*50);
            solver.addItem(new_number);
            save[i] = new_number;
        }
        System.out.println("After pushing numbers :"+ Arrays.toString(save));
        Arrays.sort(save);
        System.out.println("Got fifth number is "+solver.currentKthElement()+" =? "+save[save.length-5]);
    }
}
