package com.matthieu.epi;

public class OverlappingLists1 implements Solution {

    static public <T> LinkedListNode<T> findFirstCommonNode(LinkedListNode<T> a, LinkedListNode<T> b) {
        int length_a = LinkedListNode.getLinkListLength(a);
        int length_b = LinkedListNode.getLinkListLength(b);

        LinkedListNode<T> tmp_a = a;
        LinkedListNode<T> tmp_b = b;

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
        LinkedListNode<Integer> a = LinkedListNode.createLinkedList(10);
        LinkedListNode<Integer> b = LinkedListNode.createLinkedList(15);
        System.out.println("Lists a and b are "+(findFirstCommonNode(a,b)==null?"not joining":"joining"));
        LinkedListNode<Integer> c = LinkedListNode.createLinkedList(5);
        a = LinkedListNode.concatenate(a, c);
        b = LinkedListNode.concatenate(b, c);
        System.out.println("After adding common tail, lists a and b are "+(findFirstCommonNode(a,b)==null?"not joining":"joining"));
    }
}
