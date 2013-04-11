package com.matthieu.epi;

import java.util.Arrays;

public class CheckBSTProperty implements Solution {
    private static class MinMax<T> {
        T min;
        T max;
    }

    public static <T extends Comparable<T>> boolean checkBST(BinaryTree<T> tree, MinMax<T> minMax) {
        if (tree == null) {
            minMax.min = null;
            minMax.max = null;
            return true;
        }

        MinMax<T> leftMinMax = new MinMax<T>();
        boolean left_check = checkBST(tree.left, leftMinMax);
        if (!left_check) return false;
        if ((leftMinMax.max != null) && (leftMinMax.max.compareTo(tree.key) > 0)) return false;

        MinMax<T> rightMinMax = new MinMax<T>();
        boolean right_check = checkBST(tree.right, rightMinMax);
        if (!right_check) return false;
        if ((rightMinMax.min != null) && (rightMinMax.min.compareTo(tree.key) < 0)) return false;

        minMax.min = leftMinMax.min!=null ? leftMinMax.min : tree.key;
        minMax.max = rightMinMax.max != null ? rightMinMax.max : tree.key;

        return true;
    }

    @Override
    public void solveProblem() {
        Integer h[] = new Integer[20];
        for (int i=0; i<h.length; i++) {
            h[i] = (int) (Math.random()*30);
        }
        BinaryTree<Integer> tree = BSTFromSortedArray.buildFromArray(h);
        System.out.println("checking tree "+tree+" return "+checkBST(tree, new MinMax<Integer>()));
        Arrays.sort(h);
        tree = BSTFromSortedArray.buildFromArray(h);
        System.out.println("checking tree "+tree+" return "+checkBST(tree, new MinMax<Integer>()));
    }
}
