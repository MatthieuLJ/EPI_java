package com.matthieu.epi;

public class LinkedList<T> {
    public LinkedList<T> next;
    public T data;

    public String toString() {
        return data.toString()+(next!=null?" --> "+next.toString():"");
    }

    static public LinkedList<Integer> createLinkedList(int size) {
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

    static public <T> LinkedList<T> addCycle(LinkedList<T> list, int at) {
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

    static public <T> LinkedList<T> concatenate(LinkedList<T> start, LinkedList<T> end) {
        LinkedList<T> tmp = start;
        if (tmp == null)
            return end;
        while(tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = end;
        return start;
    }
}
