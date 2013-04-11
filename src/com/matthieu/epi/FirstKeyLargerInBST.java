package com.matthieu.epi;

import java.util.Arrays;

public class FirstKeyLargerInBST implements Solution {
    private static <T> T findNextKey(BinaryTree<T> tree) {
        if (tree.left!=null)
            return findNextKey(tree.left);
        else
            return tree.key;
    }

    public static <T extends Comparable<T>> T findKeyAfterK(BinaryTree<T> tree, T key, T next) {
        if (tree==null)
            return null;

        if (tree.key.equals(key)) {
            if (tree.right!= null) {
                return findNextKey(tree.right);
            } else if (next != null) {
                return next;
            } else {
                return null;
            }
        }
        if (tree.key.compareTo(key) > 0) {
            return findKeyAfterK(tree.left, key, tree.key);
        }
        // tree.key < key
        return findKeyAfterK(tree.right, key, next);
    }

    @Override
    public void solveProblem() {
        Integer h[] = new Integer[20];
        for (int i=0; i<h.length; i++) {
            h[i] = (int) (Math.random()*30);
        }
        Arrays.sort(h);
        BinaryTree<Integer> tree = BSTFromSortedArray.buildFromArray(h);
        System.out.println("Tree is "+tree);
        int random_index = (int)(Math.random() * 18);
        System.out.println("Next value after "+h[random_index]+" is "+findKeyAfterK(tree, h[random_index], null));
        random_index = (int)(Math.random() * 30);
        System.out.println("Next value after "+random_index+" is "+findKeyAfterK(tree, random_index, null));
    }
}
