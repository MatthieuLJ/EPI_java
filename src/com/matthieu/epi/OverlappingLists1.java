package com.matthieu.epi;

public class OverlappingLists1 implements Solution {

    static public <T> LinkedList<T> findFirstCommonNode(LinkedList<T> a, LinkedList<T> b) {
        int length_a = LinkedList.getLinkListLength(a);
        int length_b = LinkedList.getLinkListLength(b);

        LinkedList<T> tmp_a = a;
        LinkedList<T> tmp_b = b;

        if (length_a < length_b) {
            int diff = length_b - length_a;
            while (diff > 0) {
                tmp_b = tmp_b.next;
                diff--;
            }
        } else {
            int diff = length_a - length_b;
            while (diff > 0) {
                tmp_a = tmp_a.next;
                diff--;
            }
        }
        while (tmp_a != tmp_b) {
            tmp_a = tmp_a.next;
            tmp_b = tmp_b.next;
            if (tmp_a == null)
                return null;
        }
        return tmp_a;
    }

    @Override
    public void solveProblem() {
        LinkedList<Integer> a = LinkedList.createLinkedList(10);
        LinkedList<Integer> b = LinkedList.createLinkedList(15);
        System.out.println("Lists a and b are "+(findFirstCommonNode(a,b)==null?"not joining":"joining"));
        LinkedList<Integer> c = LinkedList.createLinkedList(5);
        a = LinkedList.concatenate(a,c);
        b = LinkedList.concatenate(b,c);
        System.out.println("After adding common tail, lists a and b are "+(findFirstCommonNode(a,b)==null?"not joining":"joining"));
    }
}
