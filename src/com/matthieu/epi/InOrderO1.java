package com.matthieu.epi;

public class InOrderO1 implements Solution {
    public static <T> void inOrderTransversal(BinaryTreeWithParent<T> tree) {
        BinaryTreeWithParent<T> current=tree, previous=tree;

        while(current != null) {
            if ((current.left != null) && (previous != current.left) && (previous != current.right)) {
                previous = current;
                current = current.left;
            } else if ((previous==current.left) || (current.left == null)) {
                System.out.println(current.key);
                if (current.right!=null) {
                    previous = current;
                    current = current.right;
                }
                else {
                    previous = current;
                    current = current.parent;
                }
            } else if (previous == current.right) {
                previous = current;
                current = current.parent;
            }
        }
    }
    @Override
    public void solveProblem() {
        BinaryTreeWithParent<Integer> tree = BinaryTreeWithParent.buildRandomTree(2, null);
        System.out.println("Doing in order transversal for tree "+tree);
        inOrderTransversal(tree);
    }
}
