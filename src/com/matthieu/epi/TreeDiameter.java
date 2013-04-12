package com.matthieu.epi;

public class TreeDiameter implements Solution {
    private static class HeightDiameter {
        int height;
        int diameter;
        public HeightDiameter(int h, int d) {
            height=h;
            diameter=d;
        }
    }

    public static <T> HeightDiameter getTreeDiameter(BinaryTree<T> tree) {
        if (tree==null) {
            return new HeightDiameter(0,0);
        }
        HeightDiameter left = getTreeDiameter(tree.left);
        HeightDiameter right = getTreeDiameter(tree.right);
        int best_diameter = Math.max(left.diameter, right.diameter);

        return new HeightDiameter(Math.max(left.height, right.height)+1,
                Math.max(best_diameter, left.height+right.height+1));
    }

    @Override
    public void solveProblem() {
        BinaryTree<Integer> tree = BinaryTree.buildRandomTree(10);
        System.out.println("Diameter of tree "+tree+"\n is "+getTreeDiameter(tree).diameter);
    }
}
