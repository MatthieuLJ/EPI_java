package com.matthieu.epi;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class KthLargestInHeap implements Solution {
    private static void swap(int i, int j, int heap[]) {
        int tmp=heap[i];
        heap[i]=heap[j];
        heap[j]=tmp;
    }

    public static class Heap { // not synchronized !
        int [] heap;
        int length;
        boolean [] taken;
        int size;

        public Heap(int initialCapacity) {
            size = (int) (Math.pow(2,Math.log(initialCapacity+1))-1);
            heap = new int[size];
            taken = new boolean[size];
            Arrays.fill(taken,false);
        }

        private void increaseSize() {
            int newSize = (size+1)*2-1;
            int [] newHeap = Arrays.copyOf(heap, newSize);
            boolean [] newTaken = Arrays.copyOf(taken, newSize);

            heap=newHeap;
            taken=newTaken;
            size=newSize;
        }


        public void add(int item) {
            int i;
            for (i=0; i<taken.length; i++) {
                if (!taken[i])
                    break;
            }
            if (i==taken.length) {
                increaseSize();
            }

            heap[i] = item;
            taken[i] = true;
            while ((i>0) && (heap[(i-1)/2]<heap[i])) {
                swap(i, (i-1)/2, heap);
                i = (i-1)/2;
            }
        }

        public int peek() {
            if (taken[0])
                return heap[0];
            else
                throw new NoSuchElementException();
        }

        private int countElements(int index) {
            if ((index > taken.length) || (!taken[index]))
                return 0;

            return 1+countElements((index+1)*2) + countElements((index+2)*2);
        }

        public int poll() {
            int res;
            if (taken[0])
                res = heap[0];
            else
                throw new NoSuchElementException();

            int index=0;

            while((2*index+2<taken.length) && ((taken[2*index+1]) || (taken[2*index+2]))) {
                int rCount = countElements(2*index+1);
                int lCount = countElements(2*index+2);
                if ((rCount==0) && (lCount==0))
                    break;
                if (rCount > lCount) {
                    swap(index, 2*index+1, heap);
                    index = 2*index+1;
                } else {
                    swap(index, 2*index+2, heap);
                    index = 2*index+2;
                }
            }
            taken[index] = false;

            return res;
        }

        private class MoreEqual {
            int more;
            int equal;
        }

        private void recurseCompareKth(int x, int k, int index, MoreEqual me) {
            if (index >= size)
                return;
            if (!taken[index])
                return;
            if ((me.more > k) || (me.equal + me.more > k))
                return;
            if (heap[index] < x) {
                System.out.println("Got to "+heap[index]+", index "+index);
                return;
            }
            System.out.println("Compare to "+heap[index]+", index "+index);
            if (heap[index] == x)
                me.equal++;
            else
                me.more++;

            recurseCompareKth(x, k, 2*index+1, me);
            recurseCompareKth(x, k, 2*index+2, me);

        }

        public int compareKthWithX(int x, int k) {
            MoreEqual me = new MoreEqual();
            me.more=0;
            me.equal=0;

            recurseCompareKth(x, k, 0, me);

            if (me.more+me.equal < k)
                return 1;
            if ((me.more < k) && (me.more+me.equal>=k))
                return 0;
            return -1;
        }

        @Override
        public String toString() {
            return Arrays.toString(heap);
        }
    }

    @Override
    public void solveProblem() {
        Heap heap = new Heap(2);

        for (int i=0; i<40; i++) {
            heap.add((int)(Math.random()*100));
        }

        int x = (int) (100-Math.random() * 70);
        int k = 1+(int) (Math.random() * 20);

        System.out.println("Compare "+x+" with "+k+"th element of heap "+heap);
        System.out.println("returns "+heap.compareKthWithX(x, k));
    }
}
