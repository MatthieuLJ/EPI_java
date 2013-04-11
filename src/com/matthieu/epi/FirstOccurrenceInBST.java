package com.matthieu.epi;

import java.util.Arrays;

public class FirstOccurrenceInBST implements Solution {
    public static <T extends Comparable<T>> BinaryTree<T> FirstOccurrenceK(BinaryTree<T> tree, T k) {
        if (tree == null)
            return null;

        if (tree.key.equals(k)) {
            if (tree.left!=null) {
                BinaryTree<T> res =FirstOccurrenceK(tree.left, k);
                if (res == null)
                    return tree;
                else
                    return res;
            } else
                return tree;

        } else if (tree.key.compareTo(k)<0) {
            return FirstOccurrenceK(tree.right, k);
        } else {
            return FirstOccurrenceK(tree.left, k);
        }
    }

    public static <T extends Comparable<T>> BinaryTree<T> FirstOccurrenceKIterative(BinaryTree<T> tree, T k) {
        BinaryTree<T> res = null;

        while(tree != null) {
            if (tree.key.equals(k)) {
                res = tree;
                tree = tree.left;
            } else if (tree.key.compareTo(k) < 0) {
                tree = tree.right;
            } else {
                tree = tree.left;
            }
        }

        return res;
    }

    @Override
    public void solveProblem() {
        Integer h[] = new Integer[50];
        for (int i=0; i<h.length; i++) {
            h[i] = (int) (Math.random()*30);
        }
        Arrays.sort(h);
        BinaryTree<Integer> tree = BSTFromSortedArray.buildFromArray(h);
        int index = (int) (Math.random()*50);
        System.out.println("First occurence of "+h[index]+" in "+tree);
        System.out.println("is "+FirstOccurrenceK(tree, h[index]));
        System.out.println("is (iterative) "+FirstOccurrenceKIterative(tree, h[index]));

    }
}
