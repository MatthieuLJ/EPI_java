package com.matthieu.epi;

import java.util.HashSet;

public class CheckingCyclicity implements Solution {

    static public <T> LinkedListNode<T> hasCycle(LinkedListNode<T> list) {
        HashSet<LinkedListNode<T>> hash = new HashSet<LinkedListNode<T>>();
        while (list != null) {
            if (hash.contains(list))
                return list;
            hash.add(list);
            list = list.next;
        }
        return null;
    }

    static public <T> LinkedListNode<T> hasCycle2(LinkedListNode<T> list) {
        LinkedListNode<T> slow=list, fast=list;
        do {
            slow = slow.next;
            fast = fast.next;
            if (fast != null)
                fast = fast.next;
        } while ((fast != slow) && (fast != null));

        if (fast == null)
            return null;

        int cycle_length=0;
        do {
            fast = fast.next;
            cycle_length++;
        } while (slow != fast);

        fast = list;
        slow = list;
        while (cycle_length > 0) {
            fast = fast.next;
            cycle_length--;
        }

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }



    @Override
    public void solveProblem() {
        LinkedListNode<Integer> no_cycle = LinkedListNode.createLinkedList(15);
        LinkedListNode<Integer> with_cycle = LinkedListNode.addCycle(LinkedListNode.createLinkedList(20), 10);

        System.out.println("First list "+(hasCycle(no_cycle)!=null?"has":"does not have")+" a cycle");
        System.out.println("Second list "+(hasCycle(with_cycle)!=null?"has":"does not have")+" a cycle");

        System.out.println("First list "+(hasCycle2(no_cycle)!=null?"has":"does not have")+" a cycle");
        System.out.println("Second list "+(hasCycle2(with_cycle)!=null?"has":"does not have")+" a cycle");

    }
}
