package com.matthieu.epi;

public class BinaryTreeWithParent<T> {
    public BinaryTreeWithParent<T> parent;
    public BinaryTreeWithParent<T> left=null;
    public BinaryTreeWithParent<T> right=null;
    public T key;

    public static BinaryTreeWithParent<Integer> buildRandomTree(int h, BinaryTreeWithParent<Integer> parent) {
        BinaryTreeWithParent<Integer> node = new BinaryTreeWithParent<Integer>();
        node.key = (int) (Math.random() * 50);
        node.parent = parent;
        if (h > 0) {
            node.left = buildRandomTree(h-1, node);
            node.right = buildRandomTree(h-1, node);
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
