package com.matthieu.epi;

import java.util.Arrays;
import java.util.LinkedList;

public class PrintingBSTLevelOrder implements Solution {
    public <T> void printBSTLevelOrder(BinaryTree<T> tree) {
        LinkedList<BinaryTree<T>> queue = new LinkedList<BinaryTree<T>>();
        queue.addFirst(tree);

        while (queue.size() > 0) {
            BinaryTree<T> el = queue.removeLast();
            System.out.print(el.key+", ");

            if (el.left != null) queue.addFirst(el.left);
            if (el.right != null) queue.addFirst(el.right);
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
        System.out.println("Printing tree in level order "+tree);
        printBSTLevelOrder(tree);
        System.out.println();
    }
}
