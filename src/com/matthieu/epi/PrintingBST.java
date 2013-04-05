package com.matthieu.epi;

import java.util.Arrays;
import java.util.LinkedList;

public class PrintingBST implements Solution {
    public <T> void printBST(BinaryTree<T> tree) {
        LinkedList stack = new LinkedList();
        stack.push(tree);

        while(stack.size() > 0) {
            Object element = stack.removeFirst();
            if (element instanceof BinaryTree) {
                BinaryTree t = (BinaryTree) element;
                if (t.right != null) stack.push(t.right);
                stack.push(t.key);
                if (t.left != null) stack.push(t.left);
            }
            else
                System.out.println(element);
        }

    }
    @Override
    public void solveProblem() {
        Integer h[] = new Integer[20];
        for (int i=0; i<h.length; i++) {
            h[i] = (int) (Math.random()*30);
        }
        Arrays.sort(h);
        BinaryTree<Integer> tree = BSTFromSortedArray.buildFromArray(h);
        System.out.println("Printing tree "+tree);
        printBST(tree);

    }
}
