package com.matthieu.epi;

import java.util.*;

public class TheoryOfEquality implements Solution {
    public static class EqualityInequality {
        boolean equality;
        int index1;
        int index2;
        public EqualityInequality(boolean eq, int ind1, int ind2) {
            equality=eq;
            index1 = ind1;
            index2 = ind2;
        }
    }

    private static void colorFromVertex(List<ColoredGraphVertex> graph, int startIndex, int color) {
        Deque<ColoredGraphVertex> queue = new ArrayDeque<ColoredGraphVertex>();
        queue.addFirst(graph.get(startIndex));
        while(queue.size() > 0) {
            ColoredGraphVertex vertex = queue.removeLast();
            for (GraphVertex n : vertex.neighbors) {
                ColoredGraphVertex nc = (ColoredGraphVertex) n;
                if (nc.color == -1) {
                    nc.color = color;
                    queue.addFirst(nc);
                }
            }
        }
    }

    public static boolean testPossibility(List<EqualityInequality> tests, int num_items) {
        // create graph
        List<ColoredGraphVertex> graph = new ArrayList<ColoredGraphVertex>(num_items);
        for (int i=0; i<num_items; i++)
            graph.add(new ColoredGraphVertex());

        for (EqualityInequality eq : tests) {
            if (eq.equality) {
                graph.get(eq.index1).neighbors.add(graph.get(eq.index2));
                graph.get(eq.index2).neighbors.add(graph.get(eq.index1));
            }
        }

        // color graph according to equalities
        int current_color = 1;

        color_graph:
        while (true) {
            for (int i=0; i<graph.size(); i++) {
                ColoredGraphVertex v = graph.get(i);
                if (v.color == -1) {
                    colorFromVertex(graph, i, current_color);
                    current_color++;
                    continue color_graph;
                }
            }
            break;
        }

        // check the inequalities
        for (EqualityInequality t : tests) {
            if (t.equality)
                continue;
            if (graph.get(t.index1).color == graph.get(t.index2).color)
                return false;
        }
        return true;
    }

    @Override
    public void solveProblem() {
        // x1=x2=x3, x4=x5, x2!=x5
        List<EqualityInequality> set = new ArrayList<EqualityInequality>();
        set.add(new EqualityInequality(true, 0,1));
        set.add(new EqualityInequality(true, 1,2));
        set.add(new EqualityInequality(true, 3,4));
        set.add(new EqualityInequality(false, 1,4));

        System.out.println("Can make first set : "+testPossibility(set, 5));

        // x3 = x5
        set.add(new EqualityInequality(true, 2, 4));
        System.out.println("Can make second set : "+testPossibility(set, 5));

    }
}
