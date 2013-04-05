package com.matthieu.epi;

public class LinkedListNode<T> {
    public LinkedListNode<T> next;
    public T data;

    public String toString() {
        return data.toString()+(next!=null?" --> "+next.toString():"");
    }

    static public <T> int getLinkListLength(LinkedListNode<T> list) {
        int res=0;
        while (list != null) {
            list = list.next;
            res++;
        }
        return res;
    }


    static public LinkedListNode<Integer> createLinkedList(int size) {
        LinkedListNode<Integer> res = null;
        LinkedListNode<Integer> tmp = null;
        int tmp_data=0;

        while(size > 0) {
            tmp_data = (int) (Math.random()*10);
            if (res == null) {
                res = new LinkedListNode<Integer>();
                tmp = res;
            }
            else {
                tmp.next = new LinkedListNode<Integer>();
                tmp = tmp.next;
            }
            tmp.data = tmp_data;
            size--;
        }
        return res;
    }

    static public <T> LinkedListNode<T> addCycle(LinkedListNode<T> list, int at) {
        LinkedListNode<T> tmp = list;
        while (at > 0) {
            tmp = tmp.next;
            at--;
        }
        LinkedListNode<T> dest = tmp;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = dest;

        return list;
    }

    static public <T> LinkedListNode<T> concatenate(LinkedListNode<T> start, LinkedListNode<T> end) {
        LinkedListNode<T> tmp = start;
        if (tmp == null)
            return end;
        while(tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = end;
        return start;
    }
}
