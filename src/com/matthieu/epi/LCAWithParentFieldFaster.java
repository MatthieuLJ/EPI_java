package com.matthieu.epi;

import java.util.HashSet;
import java.util.Set;

public class LCAWithParentFieldFaster implements Solution {
    public static <T> BinaryTreeWithParent<T> findCommonAncestor(BinaryTreeWithParent<T> node1, BinaryTreeWithParent<T> node2) {
        Set<BinaryTreeWithParent<T>> explored = new HashSet<BinaryTreeWithParent<T>>();

        while (true) {
            if (node1 != null) {
                if (explored.contains(node1))
                    return node1;
                explored.add(node1);
                node1 = node1.parent;
            }
            if (node2 != null) {
                if (explored.contains(node2))
                    return node2;
                explored.add(node2);
                node2 = node2.parent;
            }
        }
    }

    private static <T> BinaryTreeWithParent<T> pickRandomNode(BinaryTreeWithParent<T> tree, int height) {
        if (height == 0)
            return tree;
        if ((tree.left == null) && (tree.right==null))
            return tree;
        if (tree.left == null)
            return pickRandomNode(tree.right, height-1);
        if (tree.right == null)
            return pickRandomNode(tree.left, height-1);

        if (Math.random()<0.5)
            return pickRandomNode(tree.left, height-1);
        else
            return pickRandomNode(tree.right, height-1);

    }
    @Override
    public void solveProblem() {
        int height = 5;
        BinaryTreeWithParent<Integer> tree = BinaryTreeWithParent.buildRandomTree(height, null);
        BinaryTreeWithParent<Integer> node1 = pickRandomNode(tree, (int) (height*Math.random()));
        BinaryTreeWithParent<Integer> node2 = pickRandomNode(tree, (int) (height*Math.random()));

        System.out.println("In tree "+tree+", the common node for "+node1.key+" and "+node2.key+" is "+findCommonAncestor(node1, node2).key);
    }
}
