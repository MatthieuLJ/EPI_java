package com.matthieu.epi;

public class ReverseLinkedList implements Solution {
    public static <T> LinkedListNode<T> reverse(LinkedListNode<T> list) {
        if (LinkedListNode.getLinkListLength(list) <= 1)
            return list;

        LinkedListNode<T> start = list;
        LinkedListNode<T> pre = list;
        list = list.next;
        do {
            LinkedListNode<T> tmp = list.next;
            list.next = pre;
            pre = list;
            list = tmp;
        } while (list != null);
        start.next=null;

        return pre;
    }

    @Override
    public void solveProblem() {
        LinkedListNode<Integer> list = LinkedListNode.createLinkedList(20);
        System.out.println("Original list: "+list);
        System.out.println("Reversed: "+reverse(list));
    }
}
