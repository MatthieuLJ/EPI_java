package com.matthieu.epi;

public class EvenOddMerge implements Solution {
    public <T> LinkedListNode<T> evenOddMerge(LinkedListNode<T> list) {
        if (LinkedListNode.getLinkListLength(list)<=2)
            return list;
        LinkedListNode<T> evenStart = list;
        LinkedListNode<T> oddStart = list.next;

        LinkedListNode<T> tmp_even = evenStart;
        LinkedListNode<T> tmp_odd = oddStart;

        while (true) {
            if (tmp_odd.next != null) {
                tmp_even.next = tmp_odd.next;
                tmp_even = tmp_even.next;
            }
            else {
                tmp_even.next=null;
                break;
            }
            if (tmp_even.next != null) {
                tmp_odd.next = tmp_even.next;
                tmp_odd = tmp_odd.next;
            }
            else {
                tmp_odd.next = null;
                break;
            }
        }

        return LinkedListNode.concatenate(evenStart, oddStart);
    }

    @Override
    public void solveProblem() {
        LinkedListNode<Integer> list = LinkedListNode.createLinkedList(20);

        System.out.println("Original list is "+list);
        System.out.println("After even-odd merge: "+evenOddMerge(list));
    }
}
