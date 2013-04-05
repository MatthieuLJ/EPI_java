package com.matthieu.epi;

import java.util.*;
import java.util.LinkedList;

public class StackMax<T extends Comparable<T>> implements Solution {
    private class embed {
        T object;
        T max;
    }
    java.util.LinkedList<embed> innerStack = new LinkedList<embed>();

    public void addFirst(T t) {
        embed new_obj = new embed();
        new_obj.object = t;
        T current_max = currentMax();
        if ((current_max==null) || (current_max.compareTo(t)<0))
            new_obj.max = t;
        else
            new_obj.max = current_max;

        innerStack.addFirst(new_obj);
    }

    public T peekFirst() {
        if (innerStack.size()==0)
            return null;

        embed top = innerStack.peekFirst();
        return top.object;
    }

    public T removeFirst() throws NoSuchElementException {
        if (innerStack.size()==0)
            throw new NoSuchElementException();

        embed top = innerStack.removeFirst();

        return top.object;
    }

    public int size() {
        return innerStack.size();
    }

    public T currentMax()  {
        if (innerStack.size()==0)
            return null;

        embed top = innerStack.peekFirst();
        return top.max;
    }

    @Override
    public void solveProblem() {
        int test[] = new int[10];
        for (int i=0; i<test.length; i++) {
            test[i] = (int) (Math.random()*30);
        }
        StackMax<Integer> test_stack = new StackMax<Integer>();

        for (int t : test) {
            test_stack.addFirst(t);
            System.out.println("Pushing "+t+", max is "+test_stack.currentMax());
        }
        while (test_stack.size()>0) {
            System.out.println("Pop "+test_stack.removeFirst()+", currentMax is now "+test_stack.currentMax());
        }
    }
}
