package com.matthieu.epi;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeReconstructionFromTransversalWithMarker implements Solution {
    private static class ReconstructReturn<T> {
        BinaryTree<T> tree;
        int offset;
    }

    public static <T> ReconstructReturn<T> reconstruct(List<T> preOrder, int offset) {
        T element = preOrder.get(offset);
        if (element == null) {
            ReconstructReturn<T> res = new ReconstructReturn<T>();
            res.tree = null;
            res.offset = offset+1;
            return res;
        } else {
            BinaryTree<T> new_tree = new BinaryTree<T>();
            new_tree.key = element;
            ReconstructReturn<T> tmp_left = reconstruct(preOrder, offset+1);
            ReconstructReturn<T> tmp_right = reconstruct(preOrder, tmp_left.offset);
            new_tree.left = tmp_left.tree;
            new_tree.right = tmp_right.tree;
            ReconstructReturn<T> res = new ReconstructReturn<T>();
            res.tree = new_tree;
            res.offset = tmp_right.offset;
            return res;
        }
    }

    private <T> void buildPreOrderTransversalWithMarkers(BinaryTree<T> tree, Deque<T> output) {
        if (tree==null) {
            output.addLast(null);
            return;
        }
        output.addLast(tree.key);
        buildPreOrderTransversalWithMarkers(tree.left, output);
        buildPreOrderTransversalWithMarkers(tree.right, output);
    }

    @Override
    public void solveProblem() {
        BinaryTree<Integer> tree = BinaryTree.buildRandomTree(4);
        LinkedList<Integer> preOrder = new LinkedList<Integer>();
        buildPreOrderTransversalWithMarkers(tree, preOrder);
        System.out.println("Reconstructing tree "+tree);
        System.out.println("From markers        "+reconstruct(preOrder, 0).tree);

        BinaryTree<Integer> tmp_3 = new BinaryTree<Integer>(3);
        BinaryTree<Integer> tmp_6 = new BinaryTree<Integer>(6, tmp_3, null);
        BinaryTree<Integer> tmp_7 = new BinaryTree<Integer>(7);
        BinaryTree<Integer> tmp_5 = new BinaryTree<Integer>(5, tmp_7, tmp_6);
        BinaryTree<Integer> tmp_13 = new BinaryTree<Integer>(13);
        BinaryTree<Integer> tmp_15 = new BinaryTree<Integer>(15);
        BinaryTree<Integer> tmp_12 = new BinaryTree<Integer>(12);
        BinaryTree<Integer> tmp_8 = new BinaryTree<Integer>(8, tmp_13, tmp_15);
        BinaryTree<Integer> tmp_4 = new BinaryTree<Integer>(4, tmp_12, tmp_8);
        BinaryTree<Integer> tmp_10 = new BinaryTree<Integer>(10, tmp_5, tmp_4);
        preOrder.clear();
        buildPreOrderTransversalWithMarkers(tmp_10, preOrder);
        System.out.println("Reconstructing tree "+tmp_10);
        System.out.println("From markers        "+reconstruct(preOrder, 0).tree);
    }
}
