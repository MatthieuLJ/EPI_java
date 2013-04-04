package com.matthieu.epi;

public class LinkedList<T> {
    public LinkedList<T> next;
    public T data;

    public String toString() {
        return data.toString()+(next!=null?" --> "+next.toString():"");
    }
}
