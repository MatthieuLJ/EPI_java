package com.matthieu.epi;

import java.util.Arrays;

public class BSTFromSortedArray implements Solution {
    public static <T extends Comparable<T>> BinaryTree<T> buildFromArray(T t[]) {
        if (t.length==0)
            return null;

        BinaryTree<T> res = new BinaryTree<T>();
        if (t.length==1) {
            res.key = t[0];
            return res;
        }
        int mid_point = t.length/2;
        res.key=t[mid_point];
        res.left = buildFromArray(Arrays.copyOfRange(t, 0, mid_point));
        res.right = buildFromArray(Arrays.copyOfRange(t, mid_point+1, t.length));

        return res;
    }

    @Override
    public void solveProblem() {
        Integer h[] = new Integer[10];
        for (int i=0; i<h.length; i++) {
            h[i] = (int) (Math.random()*30);
        }
        Arrays.sort(h);

        System.out.println("BST generated from array: "+Arrays.toString(h));
        System.out.println("is : "+buildFromArray(h));
    }
}
