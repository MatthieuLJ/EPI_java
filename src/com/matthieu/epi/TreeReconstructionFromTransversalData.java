package com.matthieu.epi;

import java.util.*;

public class TreeReconstructionFromTransversalData implements Solution {
    public static <T> BinaryTree<T> buildFromInOrderPreOrder(List<T> inOrder, List<T> preOrder) {
        if (inOrder.size() == 0)
            return null;

        //First element of preOrder is root
        BinaryTree<T> res = new BinaryTree<T>();
        res.key = preOrder.get(0);

        int index;
        for (index=0; index<inOrder.size(); index++) {
            if (inOrder.get(index).equals(res.key))
                break;
        }
        res.left = buildFromInOrderPreOrder(inOrder.subList(0, index), preOrder.subList(1, index+1));
        res.right = buildFromInOrderPreOrder(inOrder.subList(index+1, inOrder.size()), preOrder.subList(index + 1, preOrder.size()));

        return res;
    }

    private <T> void buildInOrderTransversal(BinaryTree<T> tree, Deque<T> output) {
        if (tree==null)
            return;
        buildInOrderTransversal(tree.left, output);
        output.addLast(tree.key);
        buildInOrderTransversal(tree.right, output);
    }

    private <T> void buildPostOrderTransversal(BinaryTree<T> tree, Deque<T> output) {
        if (tree==null)
            return;
        buildPostOrderTransversal(tree.left, output);
        buildPostOrderTransversal(tree.right, output);
        output.addLast(tree.key);
    }

    private <T> void buildPreOrderTransversal(BinaryTree<T> tree, Deque<T> output) {
        if (tree==null)
            return;
        output.addLast(tree.key);
        buildPreOrderTransversal(tree.left, output);
        buildPreOrderTransversal(tree.right, output);
    }

    private <T> boolean checkForDups(List<T> list) {
        Set<T> all_values = new HashSet<T>();
        for (int i=0; i<list.size(); i++) {
            if (all_values.contains(list.get(i)))
                return true;
            all_values.add(list.get(i));
        }
        return false;
    }

    @Override
    public void solveProblem() {

        BinaryTree<Integer> tree;
        LinkedList<Integer> inOrder = new LinkedList<Integer>();
        LinkedList<Integer> preOrder = new LinkedList<Integer>();

        do {
            inOrder.clear();
            tree = BinaryTree.buildRandomTree(4);
            buildInOrderTransversal(tree, inOrder);
        } while (checkForDups(inOrder));

        buildPreOrderTransversal(tree, preOrder);

        System.out.println("Initial tree is "+tree);
        System.out.println("Reconstruct  to "+buildFromInOrderPreOrder(inOrder, preOrder));
        return;
    }
}
