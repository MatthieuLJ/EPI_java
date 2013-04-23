package com.matthieu.epi;

import java.util.Arrays;

public class NonAlignedRectanglesIntersection implements Solution {
    public static int dotProduct(Point a, Point b, Point c) {
        return (b.x-a.x)*(b.x-c.x)+(b.y-a.y)*(b.y-c.y);
    }

    public static void orderRectanglePoints(Point r[]) {
        if (dotProduct(r[0], r[1], r[2]) == 0)
            return;
        if (dotProduct(r[0], r[1], r[3]) == 0) {
            Point tmp = r[2];
            r[2] = r[3];
            r[3] = tmp;
        }
    }

    public static boolean pointInRectangle(Point a, Point r[]) {
        if (r.length!=4)
            return false;

        int firstDot = dotProduct(a, r[0], r[1]);
        if (dotProduct(a, r[1],r[2]) * firstDot < 0)
            return false;
        if (dotProduct(a, r[2],r[3]) * firstDot < 0)
            return false;
        if (dotProduct(a, r[3],r[0]) * firstDot < 0)
            return false;

        return true;

    }

    public static boolean segmentIntersect(Point a[], Point b[]) {
        if ((a.length!=2) || (b.length!=2))
            return false;

        return (dotProduct(b[0], a[0], b[1]) * dotProduct(b[0], a[1], b[1]) < 0) &&
                (dotProduct(a[0], b[0], a[1]) * dotProduct(a[0], b[1], a[1]) < 0);
    }

    public static boolean rectanglesIntersect(Point a[], Point b[]) {
        if ((a.length!=4) || (b.length!=4))
            return false;

        orderRectanglePoints(a);
        orderRectanglePoints(b);

        for (Point aP : a) {
            if (pointInRectangle(aP, b))
                return true;
        }
        for (Point bP : b) {
            if (pointInRectangle(bP, a))
                return true;
        }

        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (segmentIntersect(new Point[] {a[i], a[(i+1)%4]}, new Point[] {b[j], b[(j+1)%4]}))
                    return true;
            }
        }

        return false;
    }
    @Override
    public void solveProblem() {
        Point a1 = new Point(1,0);
        Point a2 = new Point(0,1);
        Point a3 = new Point(3,2);
        Point a4 = new Point(2,3);
        Point a[] = new Point[] {a1,a2,a3,a4};

        Point b1 = new Point(0,2);
        Point b2 = new Point(0,4);
        Point b3 = new Point(4,2);
        Point b4 = new Point(4,4);
        Point b[] = new Point[] {b1,b2,b3,b4};

        Point c1 = new Point(1,1);
        Point c2 = new Point(1,5);
        Point c3 = new Point(3,5);
        Point c4 = new Point(3,1);
        Point c[] = new Point[] {c1,c2,c3,c4};

        Point d1 = new Point(3,3);
        Point d2 = new Point(5,5);
        Point d3 = new Point(3,5);
        Point d4 = new Point(5,3);
        Point d[] = new Point[] {d1,d2,d3,d4};

        System.out.println("Rectangles "+ Arrays.toString(a)+" and "+Arrays.toString(b)+" intersect? "+rectanglesIntersect(a,b));
        System.out.println("Rectangles "+ Arrays.toString(b)+" and "+Arrays.toString(c)+" intersect? "+rectanglesIntersect(b,c));
        System.out.println("Rectangles "+ Arrays.toString(a)+" and "+Arrays.toString(d)+" intersect? "+rectanglesIntersect(a,d));
    }
}
