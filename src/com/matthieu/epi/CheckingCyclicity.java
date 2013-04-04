package com.matthieu.epi;

import java.util.HashSet;

public class CheckingCyclicity implements Solution {

    public <T> boolean hasCycle(LinkedList<T> list) {
        HashSet<LinkedList<T>> hash = new HashSet<LinkedList<T>>();
        while (list != null) {
            if (hash.contains(list))
                return true;
            hash.add(list);
            list = list.next;
        }
        return false;
    }

    private LinkedList<Integer> createLinkedList(int size) {
        LinkedList<Integer> res = null;
        LinkedList<Integer> tmp = null;
        int tmp_data=0;

        while(size > 0) {
            tmp_data = (int) (Math.random()*10);
            if (res == null) {
                res = new LinkedList<Integer>();
                tmp = res;
            }
            else {
                tmp.next = new LinkedList<Integer>();
                tmp = tmp.next;
            }
            tmp.data = tmp_data;
            size--;
        }
        return res;
    }

    private <T> LinkedList<T> addCycle(LinkedList<T> list, int at) {
        LinkedList<T> tmp = list;
        while (at > 0) {
            tmp = tmp.next;
            at--;
        }
        LinkedList<T> dest = tmp;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = dest;

        return list;
    }

    @Override
    public void solveProblem() {
        LinkedList<Integer> no_cycle = createLinkedList(15);
        LinkedList<Integer> with_cycle = addCycle(createLinkedList(20), 10);

        System.out.println("First list "+(hasCycle(no_cycle)?"has":"does not have")+" a cycle");
        System.out.println("Second list "+(hasCycle(with_cycle)?"has":"does not have")+" a cycle");
    }
}
