package com.matthieu.epi;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding implements Solution {
    private static void assignSolution(BinaryTree<String> tree, Map<String,String> res, String prefix) {
        if (tree==null)
            return;
        if (tree.key.length()==1) {
            res.put(tree.key, prefix);
            return;
        }
        assignSolution(tree.left , res, prefix+"0");
        assignSolution(tree.right, res, prefix+"1");
    }

    public static Map<String,String> huffmanCoding(final Map<String, Float> frequencies) {
        PriorityQueue<BinaryTree<String>> nodeQ = new PriorityQueue<BinaryTree<String>>(frequencies.size(),
                new Comparator<BinaryTree<String>>() {
                    @Override
                    public int compare(BinaryTree<String> o1, BinaryTree<String> o2) {
                        float total1=0;
                        for (int i=0;i<o1.key.length(); i++) {
                            total1 += frequencies.get(o1.key.substring(i, i + 1));
                        }
                        float total2=0;
                        for (int i=0;i<o2.key.length(); i++) {
                            total2 += frequencies.get(o2.key.substring(i,i+1));
                        }
                        return Float.compare(total1, total2);
                    }
                });
        for (String character : frequencies.keySet()) {
            nodeQ.add(new BinaryTree<String>(character));
        }

        while (nodeQ.size()>1) {
            BinaryTree<String> lowest_node1 = nodeQ.poll();
            BinaryTree<String> lowest_node2 = nodeQ.poll();
            BinaryTree<String> new_node = new BinaryTree<String>(lowest_node1.key+lowest_node2.key, lowest_node1, lowest_node2);
            nodeQ.add(new_node);
        }

        //System.out.println("Got tree "+nodeQ.poll());

        Map<String,String> res = new HashMap<String, String>();
        assignSolution(nodeQ.poll(), res,"");
        return res;
    }

    @Override
    public void solveProblem() {
        Map<String, Float> frequencies = new HashMap<String, Float>();
        frequencies.put("a", 8.17f);
        frequencies.put("b", 1.49f);
        frequencies.put("c", 2.78f);
        frequencies.put("d", 4.25f);
        frequencies.put("e", 12.70f);
        frequencies.put("f", 2.23f);
        frequencies.put("g", 2.02f);
        frequencies.put("h", 6.09f);
        frequencies.put("i", 6.97f);
        frequencies.put("j", 0.15f);
        frequencies.put("k", 0.77f);
        frequencies.put("l", 4.03f);
        frequencies.put("m", 2.41f);
        frequencies.put("n", 6.75f);
        frequencies.put("o", 7.51f);
        frequencies.put("p", 1.93f);
        frequencies.put("q", 0.10f);
        frequencies.put("r", 5.99f);
        frequencies.put("s", 6.33f);
        frequencies.put("t", 9.06f);
        frequencies.put("u", 2.76f);
        frequencies.put("v", 0.98f);
        frequencies.put("w", 2.36f);
        frequencies.put("x", 0.15f);
        frequencies.put("y", 1.97f);
        frequencies.put("z", 0.07f);

        System.out.println("Got the encoding: "+huffmanCoding(frequencies));
    }
}
