package com.matthieu.epi;

public class BinaryTree<T> {
    public BinaryTree<T> left=null;
    public BinaryTree<T> right=null;
    public T key;

    public boolean isLeaf() {
        return (left==null) && (right==null);
    }

    public static BinaryTree<Integer> buildRandomTree(int h) {
        BinaryTree<Integer> node = new BinaryTree<Integer>();
        node.key = (int) (Math.random() * 50);
        if (h > 0) {
            node.left = buildRandomTree(h-1);
            node.right = buildRandomTree(h-1);
        }
        return node;
    }

    public int getHeight() {
        if ((left==null) && (right==null))
            return 0;
        if (left == null)
            return 1+right.getHeight();
        if (right == null)
            return 1+left.getHeight();

        return 1+Math.max(left.getHeight(), right.getHeight());
    }

    public String toString() {
        return key.toString() + (left!=null || right!=null ? " [":"") + (left!=null?left.toString():"") +
                (left!=null || right!=null ? " : ":"") + (right!=null?right.toString():"") + (left!=null || right!=null ? " ]":"");
    }
}
