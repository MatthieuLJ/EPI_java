package com.matthieu.epi;

public class ReverseLinkedList implements Solution {
    public static <T> LinkedList<T> reverse(LinkedList<T> list) {
        if (LinkedList.getLinkListLength(list) <= 1)
            return list;

        LinkedList<T> start = list;
        LinkedList<T> pre = list;
        list = list.next;
        do {
            LinkedList<T> tmp = list.next;
            list.next = pre;
            pre = list;
            list = tmp;
        } while (list != null);
        start.next=null;

        return pre;
    }

    @Override
    public void solveProblem() {
        LinkedList<Integer> list = LinkedList.createLinkedList(20);
        System.out.println("Original list: "+list);
        System.out.println("Reversed: "+reverse(list));
    }
}
