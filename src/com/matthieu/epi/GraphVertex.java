package com.matthieu.epi;

import java.util.ArrayList;
import java.util.List;

public class GraphVertex {
    List<GraphVertex> neighbors = new ArrayList<GraphVertex>();

    public static <T extends GraphVertex> List<T> buildConnectedGraph(int numVertices, Class<T> classT) {
        List<T> res = new ArrayList<T>();
        if (numVertices <=0 )
            return null;

        try {
            res.add(classT.newInstance());

            for(int i=1; i<numVertices; i++) {
                T newVertex = classT.newInstance();
                boolean is_connected = false;
                while (!is_connected) {
                    for (int j=0; j<i; j++) {
                        if (Math.random()<0.3) {
                            newVertex.neighbors.add(res.get(j));
                            res.get(j).neighbors.add(newVertex);
                            is_connected=true;
                        }
                    }
                }
                res.add(newVertex);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }
}
