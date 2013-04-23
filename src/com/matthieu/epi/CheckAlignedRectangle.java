package com.matthieu.epi;

import java.util.Arrays;

public class CheckAlignedRectangle implements Solution {
    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x=x;
            this.y=y;
        }
        @Override
        public String toString() {
            return "("+x+","+y+")";
        }
    }

    public static boolean isAlignedRectangle(Point[] p) {
        int minX=Integer.MAX_VALUE;
        int maxX=Integer.MIN_VALUE;
        int minY=Integer.MAX_VALUE;
        int maxY=Integer.MIN_VALUE;

        if (p.length!=4)
            return false;
        for (Point aP : p) {
            minX = Math.min(minX, aP.x);
            maxX = Math.max(maxX, aP.x);
            minY = Math.min(minY, aP.y);
            maxY = Math.max(maxY, aP.y);
        }
        int count[] = new int[4];
        Arrays.fill(count, 0);
        for (Point aP : p) {
            if (aP.x == minX)
                count[0]++;
            if (aP.x == maxX)
                count[1]++;
            if (aP.y == minY)
                count[2]++;
            if (aP.y == maxY)
                count[3]++;
        }
        for (int i=0; i<4; i++) {
            if (count[i] != 2)
                return false;
        }
        return true;

    }

    @Override
    public void solveProblem() {
        Point a = new Point(0,0);
        Point b = new Point(1,0);
        Point c = new Point(1,1);
        Point d = new Point(0,1);
        Point e = new Point(2,0);
        Point f = new Point(2,1);

        System.out.println("Shape "+a+" "+b+" "+c+" "+d+" is rectangle ?"+isAlignedRectangle(new Point[] {a,b,c,d}));
        System.out.println("Shape "+a+" "+b+" "+c+" "+e+" is rectangle ?"+isAlignedRectangle(new Point[] {a,b,c,e}));
        System.out.println("Shape "+a+" "+d+" "+f+" "+e+" is rectangle ?"+isAlignedRectangle(new Point[] {a,d,f,e}));
    }
}
