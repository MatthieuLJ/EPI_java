package com.matthieu.epi;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class WiringPCB implements Solution {
    public static class ColoredGraphVertex extends GraphVertex {
        int color=-1;
    }

    public static boolean canBeWired(List<ColoredGraphVertex> graph) {
        Deque<ColoredGraphVertex> queue = new ArrayDeque<ColoredGraphVertex>();
        ColoredGraphVertex firstVertex = graph.get(0);
        firstVertex.color=0;
        queue.addFirst(firstVertex);

        while(queue.size()>0) {
            ColoredGraphVertex vertex = queue.removeLast();
            for (GraphVertex n : vertex.neighbors) {
                ColoredGraphVertex nc = (ColoredGraphVertex) n;
                if (nc.color == vertex.color)
                    return false;
                else if (nc.color == -1) {
                    nc.color = 1-vertex.color;
                    queue.addFirst(nc);
                }
            }
        }
        return true;
    }

    @Override
    public void solveProblem() {
        List<ColoredGraphVertex> graph = GraphVertex.buildConnectedGraph(5, ColoredGraphVertex.class);

        System.out.println("Graph can be printed? "+canBeWired(graph));
    }
}
