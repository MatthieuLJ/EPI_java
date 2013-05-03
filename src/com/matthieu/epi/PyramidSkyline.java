package com.matthieu.epi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PyramidSkyline implements Solution {
    public static class Building {
        int left;
        int right;
        float height;
        public Building(int l, int r) {
            left=l;
            right=r;
            height=((float)right-left)/2;
        }
        @Override
        public String toString() {
            return "Building from "+left+" to "+right+" at height "+height;
        }
    }

    public static class BuildingAlignment implements Comparator<Building> {
        @Override
        public int compare(Building b1, Building b2) {
            return Integer.compare(b1.left, b2.left);
        }
    }

    public static class Point {
        float x;
        float y;
        public Point(float x, float y) {
            this.x=x;
            this.y=y;
        }
        @Override
        public String toString() {
            return "("+x+","+y+")";
        }
    }

    public List<Point> skyline(List<Building> buildings) {
        PriorityQueue<Building> queue = new PriorityQueue<Building>(buildings.size(), new BuildingAlignment());
        queue.addAll(buildings);
        Building lastBuilding=null;
        List<Point> res = new ArrayList<Point>();

        while (queue.size() > 0) {
            Building currentBuilding = queue.poll();
            while((queue.peek()!=null) && (queue.peek().left==currentBuilding.left)){
                if (queue.peek().right > currentBuilding.right) {
                    currentBuilding=queue.poll();
                } else {
                    queue.poll();
                }
            }

            if (lastBuilding==null) {
                // we were "on the floor"
                res.add(new Point(currentBuilding.left, 0));
            } else {
                // check where we are going to intersect
                float xIntersect = ((float)lastBuilding.right + currentBuilding.left)/2;
                float yIntersect = ((float)lastBuilding.right - currentBuilding.left)/2;
                res.add(new Point(xIntersect, yIntersect));

            }
            res.add(new Point((currentBuilding.right+currentBuilding.left)/2, currentBuilding.height));

            while ((queue.peek()!=null) && (queue.peek().right < currentBuilding.right))
                queue.poll();
            if ((queue.size() == 0) || (queue.peek().left > currentBuilding.right)) {
                res.add(new Point(currentBuilding.right, 0));
                lastBuilding=null;
            } else {
                lastBuilding = currentBuilding;
            }
        }
        return res;
    }

    @Override
    public void solveProblem() {
        List<Building> list = new ArrayList<Building>();
        int num=10;
        for (int i=0; i<num; i++) {
            int left = (int) (Math.random() * 20);
            int right = left + (int)(Math.random()*10);
            Building b = new Building(left, right);
            list.add(b);
            System.out.println("Building "+b);
        }
        System.out.println("Skyline go through points "+skyline(list));
    }
}
