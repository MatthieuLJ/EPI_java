package com.matthieu.epi;

public class KBalancedNodes implements Solution {
    public static class KBalanceReturn<T> {
        int num_nodes;
        BinaryTree<T> return_tree=null;
    }
    public static <T> KBalanceReturn<T> checkKBalanced(BinaryTree<T> tree, int k) {

        if (tree == null) {
            KBalanceReturn<T> ret = new KBalanceReturn<T>();
            ret.num_nodes=0;
            return ret;
        }
        KBalanceReturn<T> sub_right = checkKBalanced(tree.right, k);
        KBalanceReturn<T> sub_left = checkKBalanced(tree.left, k);
        if (sub_left.return_tree != null) {
            return sub_left;
        }
        if (sub_right.return_tree != null) {
            return sub_right;
        }
        KBalanceReturn<T> ret = new KBalanceReturn<T>();
        if (Math.abs(sub_left.num_nodes - sub_right.num_nodes) > k) {
            ret.return_tree = tree;
        }
        else {
            ret.num_nodes = sub_left.num_nodes+sub_right.num_nodes+1;
        }
        return ret;
    }

    @Override
    public void solveProblem() {
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

        System.out.println("Tree is "+tmp_10);
        System.out.println("Check if 2-balanced: "+checkKBalanced(tmp_10, 2).return_tree);
        System.out.println("Check if 1-balanced: "+checkKBalanced(tmp_10, 1).return_tree);
    }
}
