package com.matthieu.epi;

public class MergeSortedLinkedList implements Solution {
    public LinkedList<Integer> merge(LinkedList<Integer> a, LinkedList<Integer> b) {
        LinkedList<Integer> res = null;
        LinkedList<Integer> tmp = null;

        while ((a!=null) && (b!=null)) {
            boolean next_is_a = a.data < b.data;
            LinkedList<Integer> next = next_is_a?a:b;

            if(next_is_a)
                a=a.next;
            else
                b=b.next;

            if (res == null) {
                res=next;
            }
            else {
                tmp.next = next;
            }
            tmp = next;
        }
        if (a==null) tmp.next=b; else tmp.next=a;

        return res;
    }

    private LinkedList<Integer> create_sorted_list(int size) {
        LinkedList<Integer> res = null;
        LinkedList<Integer> tmp = null;
        int tmp_data=0;

        while(size > 0) {
            tmp_data += (int) (Math.random()*10);
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

    @Override
    public void solveProblem() {
        LinkedList<Integer> a = create_sorted_list(10);
        LinkedList<Integer> b = create_sorted_list(10);

        System.out.println("Merging list "+a.toString()+" and "+b.toString());
        System.out.println("Give "+merge(a, b).toString());
    }
}
