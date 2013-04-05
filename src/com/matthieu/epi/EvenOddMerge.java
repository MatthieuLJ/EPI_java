package com.matthieu.epi;

public class EvenOddMerge implements Solution {
    public <T> LinkedList<T> evenOddMerge(LinkedList<T> list) {
        if (LinkedList.getLinkListLength(list)<=2)
            return list;
        LinkedList<T> evenStart = list;
        LinkedList<T> oddStart = list.next;

        LinkedList<T> tmp_even = evenStart;
        LinkedList<T> tmp_odd = oddStart;

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

        return LinkedList.concatenate(evenStart,oddStart);
    }

    @Override
    public void solveProblem() {
        LinkedList<Integer> list = LinkedList.createLinkedList(20);

        System.out.println("Original list is "+list);
        System.out.println("After even-odd merge: "+evenOddMerge(list));
    }
}
