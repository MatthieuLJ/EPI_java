package com.matthieu.epi;

public class IntersectingRectangles implements Solution {
    public static class Rectangle {
        int origX;
        int origY;
        int height;
        int width;
        public Rectangle(int x, int y, int h, int w) {
            origX=x;
            origY=y;
            height=h;
            width=w;
        }
        @Override
        public String toString() {
            return "Rec: ("+origX+","+origY+") -> ("+(origX+width)+","+(origY+height)+")";
        }
    }

    public static Rectangle intersection(Rectangle a, Rectangle b) {
        if ((a.origX+a.width <= b.origX) || (b.origX+b.width <= a.origX)
                || (a.origY+a.height <= b.origY) || (b.origY+b.height <= a.origY))
            return null;

        int x, y;
        int height, width;
        x=Math.max(a.origX, b.origX);
        y=Math.max(a.origY, b.origY);
        width = Math.min(a.origX+a.width, b.origX+b.width)-x;
        height = Math.min(a.origY+a.height, b.origY+b.height)-y;

        return new Rectangle(x,y,height, width);
    }

    @Override
    public void solveProblem() {
        Rectangle a = new Rectangle(1,2,2,2);
        Rectangle b = new Rectangle(2,3,2,2);
        Rectangle c = new Rectangle(4,1,3,3);

        System.out.println("Intersection of "+a+" and "+b+" is "+intersection(a,b));
        System.out.println("Intersection of "+a+" and "+c+" is "+intersection(a,c));
    }
}
