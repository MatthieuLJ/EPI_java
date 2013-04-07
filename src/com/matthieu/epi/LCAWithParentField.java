package com.matthieu.epi;

public class LCAWithParentField implements Solution {
    public static <T> BinaryTreeWithParent<T> findCommonAncestor(BinaryTreeWithParent<T> node1, BinaryTreeWithParent<T> node2) {
        int depth1=0, depth2=0;
        BinaryTreeWithParent<T> tmp;

        tmp = node1;
        while(tmp.parent != null) {
            depth1++;
            tmp = tmp.parent;
        }

        tmp = node2;
        while(tmp.parent != null) {
            depth2++;
            tmp = tmp.parent;
        }

        while (depth1 > depth2) {
            node1 = node1.parent;
            depth1--;
        }
        while (depth2 > depth1) {
            node2 = node2.parent;
            depth2--;
        }
        while (!node1.equals(node2)) {
            node1 = node1.parent;
            node2 = node2.parent;
        }
        return node1;
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
