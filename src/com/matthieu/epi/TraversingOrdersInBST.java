package com.matthieu.epi;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TraversingOrdersInBST implements Solution {
    public static <T extends Comparable<T>> BinaryTree<T> buildBSTFromPreorder(List<T> preOrder) {
        if (preOrder.size() == 0)
            return null;

        BinaryTree<T> res = new BinaryTree<T>();
        res.key = preOrder.get(0);
        int index = 1;

        while ((index < preOrder.size()) && (preOrder.get(index).compareTo(res.key))<0) index++;

        res.left = buildBSTFromPreorder(preOrder.subList(1, index));
        res.right = buildBSTFromPreorder(preOrder.subList(index, preOrder.size()));

        return res;
    }

    @Override
    public void solveProblem() {
        Integer h[] = new Integer[20];
        h[0] = (int) (Math.random()*30);
        for (int i=1; i<h.length; i++) {
            h[i] = h[i-1] + (int) (Math.random()*10) + 1;
        }
        Arrays.sort(h);
        BinaryTree<Integer> tree = BSTFromSortedArray.buildFromArray(h);
        LinkedList<Integer> preOrder =new LinkedList<Integer>();
        TreeReconstructionFromTransversalData.buildPreOrderTransversal(tree, preOrder);

        System.out.println("Original tree is "+tree);
        System.out.println("Reconstructed is "+buildBSTFromPreorder(preOrder));
    }
}
