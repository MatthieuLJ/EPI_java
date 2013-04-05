package com.matthieu.epi;

public class OverlappingLists2 implements Solution {
    static public <T> LinkedList<T> firstCommonNode(LinkedList<T> a, LinkedList<T> b) {
        LinkedList<T> cycle_start_a = CheckingCyclicity.hasCycle(a);
        LinkedList<T> cycle_start_b = CheckingCyclicity.hasCycle(b);
        if ((cycle_start_a==null) && (cycle_start_b==null))
            return OverlappingLists1.findFirstCommonNode(a, b);
        else if ((cycle_start_a!=null) && (cycle_start_b!=null)) {
            LinkedList<T> tmp = cycle_start_a;
            do {
                tmp = tmp.next;
            } while ((tmp != cycle_start_a) && (tmp!=cycle_start_b));
            return (tmp == cycle_start_a)?null:cycle_start_a;
        } else {
            return null;
        }
    }

    @Override
    public void solveProblem() {

    }
}
