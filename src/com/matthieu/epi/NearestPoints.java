package com.matthieu.epi;

import com.sun.servicetag.SystemEnvironment;

import java.util.*;

public class NearestPoints implements Solution {
    private static class Point implements Comparable<Point> {
        public float x;
        public float y;

        static public int numDistanceComparison = 0;

        public Point(float x, float y) {
            this.x=x;
            this.y=y;
        }

        @Override
        public int compareTo(Point point) {
            if (point.x == x)
                return Float.compare(y, point.y);
            return Float.compare(x, point.x);
        }

        public float distanceTo(Point point) {
            numDistanceComparison++;
            return (float) Math.sqrt(Math.pow(point.x-x, 2)+Math.pow(point.y-y, 2));
        }

        public String toString() {
            return "("+(int)x+","+(int)y+")";
        }
    }

    public float shortestDistance(List<Point> points) {
        float res=Float.MAX_VALUE;
        for (int i=0; i<points.size(); i++) {
            for (int j=i+1; j<points.size(); j++) {
                float dist = points.get(i).distanceTo(points.get(j));
                if (dist<res)
                    res = dist;
            }
        }
        return res;
    }

    public float shortestDistanceDivideAndConquer(List<Point> points) {
        if (points.size() <= 3) {
            return shortestDistance(points);
        }

        int mid_point = points.size()/2;
        float left = shortestDistanceDivideAndConquer(points.subList(0, mid_point));
        float right = shortestDistanceDivideAndConquer(points.subList(mid_point, points.size()));
        float min = Math.min(left, right);

        ArrayList<Point> remain = new ArrayList<Point>();
        for (Point p:points) {
            if (Math.abs(p.x-points.get(mid_point).x) <= min)
                remain.add(p);
        }
        float remain_min = shortestDistance(remain);
        return Math.min(min, remain_min);
    }

    public float shortestDistanceSweep(List<Point> points) {
        PriorityQueue<Point> inRange = new PriorityQueue<Point>();

        float res = Float.MAX_VALUE;
        for (Point point : points) {
            while ((inRange.size() > 0) && (inRange.peek().x < point.x - res))
                inRange.poll();
            for (Point p : inRange) {
                float tmp_distance = p.distanceTo(point);
                if (tmp_distance < res)
                    res = tmp_distance;
            }

            inRange.add(point);
        }
        return res;
    }

    @Override
    public void solveProblem() {
        final int numPoints=2000;
        final float planeSize=100;
        List<Point> plane = new ArrayList<Point>(numPoints);

        for (int i=0; i<numPoints; i++) {
            plane.add(new Point((float) Math.random()*planeSize, (float) (Math.random()*planeSize)));
        }

        System.out.println("Brute force: Found the shortest distance is "+shortestDistance(plane));
        System.out.println("That took "+Point.numDistanceComparison+" distance calculations");

        Point.numDistanceComparison=0;

        Collections.sort(plane);

        System.out.println("Divide and Conquer: Found the shortest distance is "+shortestDistanceDivideAndConquer(plane));
        System.out.println("That took "+Point.numDistanceComparison+" distance calculations");

        Point.numDistanceComparison=0;
        System.out.println("Divide and Conquer: Found the shortest distance is "+shortestDistanceSweep(plane));
        System.out.println("That took "+Point.numDistanceComparison+" distance calculations");
    }
}
