package com.matthieu.epi;

import java.util.*;

public class Skyline implements Solution {
    public static class Building {
        int left;
        int right;
        int height;
        public Building(int l, int r, int h) {
            left=l;
            right=r;
            height=h;
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

    public static class BuildingHighest implements Comparator<Building> {
        @Override
        public int compare(Building b1, Building b2) {
            return Integer.compare(b1.height, b2.height);
        }
    }

    public static int[] skylineHeights(List<Building> buildings, int left, int right) {
        int []res = new int[right-left+1];

        PriorityQueue<Building> sweep = new PriorityQueue<Building>(buildings.size(), new BuildingAlignment());
        PriorityQueue<Building> highest = new PriorityQueue<Building>(buildings.size(), Collections.reverseOrder(new BuildingHighest()));

        for (Building b : buildings)
            sweep.add(b);

        for (int i=left; i<=right; i++) {
            while ((sweep.peek() != null) && (sweep.peek().left <= i)) {
                highest.add(sweep.poll());
            }
            while((highest.peek() != null) && (highest.peek().right<i)) {
                highest.poll();
            }
            res[i-left] = (highest.peek() == null)? 0:highest.peek().height;
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
            int height = 1+(int) (Math.random() * 30);
            Building b = new Building(left, right, height);
            list.add(b);
            System.out.println("Building "+b);
        }
        System.out.println("Skyline is "+ Arrays.toString(skylineHeights(list, 0, 30)));

    }
}
