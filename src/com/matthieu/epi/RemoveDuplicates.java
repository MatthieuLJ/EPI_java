package com.matthieu.epi;

import java.util.*;

public class RemoveDuplicates implements Solution {
    public static <T> void removeDuplicates(List<T> list) {
        Set<T> set = new HashSet<T>();

        for (Iterator<T> iter = list.iterator(); iter.hasNext();) {
            T item = iter.next();
            if (set.contains(item))
                iter.remove();
            set.add(item);
        }

    }
    @Override
    public void solveProblem() {
        List<Integer> a = new ArrayList<Integer>();
        int len=50;
        for (int i=0; i<len; i++) {
            a.add((int) (Math.random()*50));
        }
        System.out.println("Removing duplicates from "+a);
        removeDuplicates(a);
        System.out.println("Gives "+a);
    }
}
