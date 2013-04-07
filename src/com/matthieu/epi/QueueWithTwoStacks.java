package com.matthieu.epi;

import java.util.LinkedList;

public class QueueWithTwoStacks implements Solution {
    public static class DoubleStackQueue<T> {
        LinkedList<T> input = new LinkedList<T>();
        LinkedList<T> output = new LinkedList<T>();

        public synchronized int size() {
            return input.size() + output.size();
        }

        public synchronized void push(T obj) {
            input.addFirst(obj);
        }

        public synchronized T poll() {
            if (size() == 0)
                return null;

            if (output.size() == 0) {
                // transfer from input to output
                while (input.size() > 0) {
                    T element = input.removeFirst();
                    output.addFirst(element);
                }
            }
            return output.removeFirst();
        }
    }
    @Override
    public void solveProblem() {
        DoubleStackQueue<Integer> queue = new DoubleStackQueue<Integer>();

        for (int i=0; i<3; i++) {
            int new_number = (int) (Math.random()*10);
            queue.push(new_number);
            System.out.println("pushed "+new_number);
        }
        for (int i=0; i<10; i++) {
            if (Math.random() < 0.5) {
                System.out.println("Polling "+queue.poll());
            } else {
                int new_number = (int) (Math.random()*10);
                queue.push(new_number);
                System.out.println("pushed "+new_number);
            }
        }
    }
}
